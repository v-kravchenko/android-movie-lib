package com.redcatgames.movies.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

val String.Companion.empty: String
    get() = ""


fun <T, K, R> LiveData<T>.combineWith(
    liveData: LiveData<K>,
    block: (T?, K?) -> R
): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData.value)
    }
    result.addSource(liveData) {
        result.value = block(this.value, liveData.value)
    }
    return result
}

class PairLiveData<A, B>(first: LiveData<A>, second: LiveData<B>) : MediatorLiveData<Pair<A?, B?>>() {
    init {
        addSource(first) { value = it to second.value }
        addSource(second) { value = first.value to it }
    }
}


fun <A, B> LiveData<A>.combine(other: LiveData<B>): PairLiveData<A, B> {
    return PairLiveData(this, other)
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)