package com.parserdev.store.data.mapper

interface Mapper<T,R>{
    fun map(dto: T): R
}