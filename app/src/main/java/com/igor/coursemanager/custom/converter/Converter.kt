package com.igor.coursemanager.custom.converter

interface Converter<FROM, TO> {

    fun convert(from: FROM): TO
}