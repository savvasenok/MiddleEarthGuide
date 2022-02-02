package xyz.savvamirzoyan.middleearth.core

interface Mapper<T : Model, R : Model> {
    fun map(model: T): R
    fun map(models: List<T>): List<R>
}