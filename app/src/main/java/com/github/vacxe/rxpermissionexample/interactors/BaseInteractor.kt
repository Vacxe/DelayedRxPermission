package com.github.vacxe.rxpermissionexample.interactors

import rx.Observable
import rx.Subscriber
import rx.Subscription


abstract class BaseInteractor<P, R> : Interactor<P, R> {
    var param: P? = null
    private val subscription: Subscription? = null

    override fun setParameter(parameter: P) {
        this.param = parameter
    }

    override fun execute(subscriber: Subscriber<R>) {
        getObservable().subscribe(subscriber)
    }

    override fun unSubscribe() {
        subscription?.let {
            if (!it.isUnsubscribed) {
                it.unsubscribe()
            }
        }
    }

    abstract fun getObservable(): Observable<R>
}