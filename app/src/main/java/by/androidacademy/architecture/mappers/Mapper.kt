package by.androidacademy.architecture.mappers

interface Mapper<From, To> {

    fun map(from: From): To
}