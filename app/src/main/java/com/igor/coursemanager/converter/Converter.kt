package com.igor.coursemanager.converter

interface Converter<FROM, TO> {

    fun convert(from: FROM): TO
}