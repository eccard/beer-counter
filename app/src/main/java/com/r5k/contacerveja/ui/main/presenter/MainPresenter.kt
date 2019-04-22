package com.r5k.contacerveja.ui.main.presenter

import android.util.Log
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.main.interactor.MainVMPInteractor
import com.r5k.contacerveja.ui.main.view.MainMVPView
import com.r5k.contacerveja.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class MainPresenter<V:MainMVPView, I : MainVMPInteractor> @Inject internal constructor(interactor: I, schedulerProvider : SchedulerProvider, disposable : CompositeDisposable) : BasePresenter<V,I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),MainMVPPresenter<V,I>{

    private val TAG = MainPresenter::class.java.simpleName

    override fun onAttach(view: V?) {
        super.onAttach(view)

        Log.d(TAG,"called onAttach")
        compositeDisposable.add(
            interactor!!.getOpenedBill()
            .compose(schedulerProvider.ioToMainSingleScheduler())
            .subscribe({t: List<Bill> -> if (t.isEmpty()){

                Log.d(TAG,"before create bill")
                createBill()
            } else Log.d(TAG,"t.size="+t.size)

            }, {t: Throwable -> Log.d(TAG,"saindoo error"+t.localizedMessage) }))
    }

    override fun createBill() {
        Log.d(TAG,"called createBill")

        var bill = Bill(null,Calendar.getInstance().time.time,1)


//        compositeDisposable.add(
//            interactor!!.createBill(bill)
//                .compose(schedulerProvider.ioToMainSingleScheduler())
//                .subscribe({t: Boolean ->
//                    if (t) {Log.d(TAG,"bill criado")} else {Log.e(TAG,"bill não criado")}
//
//                }, { err -> Log.getStackTraceString(err)}))



    }

    override fun onDrawserOptionAboutClick(): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}