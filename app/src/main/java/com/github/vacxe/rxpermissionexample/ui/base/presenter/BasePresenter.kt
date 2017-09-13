package com.github.vacxe.rxpermissionexample.ui.base.presenter

import com.github.vacxe.rxpermissionexample.ui.base.contracts.BaseViewContract

abstract class BasePresenter<V : BaseViewContract> {
    var view: V? = null
}