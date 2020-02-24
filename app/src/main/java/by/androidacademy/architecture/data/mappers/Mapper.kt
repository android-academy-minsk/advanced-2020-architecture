package by.androidacademy.architecture.data.mappers

interface Mapper<From, To> {

    fun map(from: From): To
}