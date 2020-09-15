package com.igor.coursemanager.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.igor.coursemanager.R
import com.igor.coursemanager.databinding.CurrencyListDataBinding
import com.igor.coursemanager.model.SimpleDate
import com.igor.coursemanager.model.product.Currency
import com.igor.coursemanager.presentation.CurrencyViewModel
import com.igor.coursemanager.presentation.date.observer.DateObserver
import com.igor.coursemanager.presentation.date.owner.DateOwner
import com.igor.coursemanager.presentation.listener.CurrencyListOwner
import com.igor.coursemanager.presentation.listener.SearchTextListener
import com.igor.coursemanager.presentation.toolbar.ToolbarManager
import com.igor.coursemanager.presentation.toolbar.ToolbarManagerImpl
import com.igor.coursemanager.presentation.viewModelFactory
import com.igor.coursemanager.view.adapter.CurrencyListAdapter

class CurrencyFragment : Fragment(), DateObserver, LifecycleOwner, CurrencyListOwner {

    override val currencyList: MutableList<Currency> = mutableListOf()

    private val currencyAdapter = CurrencyListAdapter(currencyList)
    private val searchTextListener = SearchTextListener(this)
    private lateinit var dateOwner: DateOwner

    private lateinit var currencyListView: RecyclerView
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var toolbarManager: ToolbarManager
    private lateinit var toolbar: Toolbar

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dateOwner = requireActivity() as DateOwner
        dateOwner.addSubscriber(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initDataBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    override fun onStart() {
        super.onStart()

        setUpView()
    }

    override fun updateObservedDate(newDate: SimpleDate) {
        viewModel.updateDate(newDate)
    }

    private fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        val binding = DataBindingUtil.inflate<CurrencyListDataBinding>(
            inflater,
            R.layout.currency_fragment,
            container,
            false
        )

        binding.setVariable(com.igor.coursemanager.BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory {
                CurrencyViewModel(
                    dateOwner.date
                )
            }
        ).get(CurrencyViewModel::class.java)
        lifecycle.addObserver(viewModel)

    }

    private fun initViews(view: View) {
        currencyListView = view.findViewById(R.id.currency_list_view)
        toolbar = requireActivity().findViewById(R.id.search_toolbar)
        toolbarManager = ToolbarManagerImpl(toolbar, searchTextListener)
    }

    private fun setUpView() {
        currencyListView.adapter = currencyAdapter
        toolbarManager.setUpViews()
        viewModel.getCurrencyEvent().observeForever {
            updateCurrencies(it)
        }
    }

    override fun updateList(filteredList: List<Currency>) {
        currencyAdapter.updateList(filteredList)
    }

    private fun updateCurrencies(newCurrencies: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newCurrencies)
        searchTextListener.updateSearchedData(toolbarManager.searchedData())
    }
}