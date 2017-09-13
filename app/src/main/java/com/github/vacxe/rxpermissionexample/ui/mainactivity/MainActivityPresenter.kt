package com.github.vacxe.rxpermissionexample.ui.mainactivity

import com.github.vacxe.rxpermissionexample.interactors.Interactor
import com.github.vacxe.rxpermissionexample.interactors.PresentationInteractor
import com.github.vacxe.rxpermissionexample.ui.base.presenter.BasePresenter
import rx.Subscriber

class MainActivityPresenter(val presentationInteractor: Interactor<Long, String>) : BasePresenter<Contract.View>(), Contract.Presenter {
    override fun doPresentationAction() {
        presentationInteractor.setParameter(System.currentTimeMillis())
        presentationInteractor.execute(object : Subscriber<String>() {
            override fun onNext(t: String?) {
                t?.let {
                    view?.showToast(it)
                }
            }

            override fun onError(e: Throwable) {
                when (e) {
                    is PresentationInteractor.DeniedCameraPermission -> {
                        view?.showToast("Permission not garanted")
                    }
                    else -> view?.showToast(e.toString())
                }
            }

            override fun onCompleted() {

            }
        })
    }
}