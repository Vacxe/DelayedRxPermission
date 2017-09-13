package com.github.vacxe.rxpermissionexample.ui.mainactivity

import com.github.vacxe.rxpermissionexample.ui.base.contracts.BaseViewContract

interface Contract {
    interface View : BaseViewContract {

    }

    interface Presenter {
        fun doPresentationAction()
    }
}