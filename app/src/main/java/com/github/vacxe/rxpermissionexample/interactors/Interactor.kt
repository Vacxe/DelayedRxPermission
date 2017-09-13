package com.github.vacxe.rxpermissionexample.interactors


import rx.Subscriber

interface Interactor<P, R> {
    fun setParameter(parameter: P)
    fun execute(subscriber: Subscriber<R>)
    fun unSubscribe()

}