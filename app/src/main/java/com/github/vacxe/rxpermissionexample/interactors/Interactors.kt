package com.github.vacxe.rxpermissionexample.interactors

import android.Manifest
import android.app.Activity
import com.github.vacxe.rxpermissionexample.ui.base.contracts.BaseViewContract
import com.tbruyelle.rxpermissions.RxPermissions
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class PresentationInteractor(val weakActivity: WeakReference<Activity>,
                             val loaderContract: BaseViewContract) : BaseInteractor<Long, String>() {
    override fun getObservable(): Observable<String> {
        return param?.let {
            Observable
                    .just(it)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        loaderContract.showLoader()
                    }
                    .delay(2, TimeUnit.SECONDS, Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { loaderContract.hideLoader() }
                    .flatMap {
                        Observable.combineLatest(Observable.just(it), RxPermissions(weakActivity.get()!!)
                                .request(Manifest.permission.CAMERA), { value, permission ->
                            if (!permission) {
                                throw DeniedCameraPermission()
                            }
                            value
                        })
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.toString()
                    }
                    .doOnTerminate { weakActivity.clear() }
        } ?: Observable.empty()

    }

    class DeniedCameraPermission : Throwable()
}