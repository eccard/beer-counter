package com.r5k.contacerveja.ui.main.presenter

import android.util.Log
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.main.interactor.MainVMPInteractor
import com.r5k.contacerveja.ui.main.view.MainMVPView
import com.r5k.contacerveja.util.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainPresenter<V:MainMVPView, I : MainVMPInteractor> @Inject internal constructor(interactor: I, schedulerProvider : SchedulerProvider, disposable : CompositeDisposable) : BasePresenter<V,I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),MainMVPPresenter<V,I>{

    private val TAG = MainPresenter::class.java.simpleName

    private val drinksListId : MutableList<Long> = mutableListOf<Long>()

    override fun onAttach(view: V?) {
        super.onAttach(view)

        Log.d(TAG,"called onAttach")
        compositeDisposable.add(
            interactor!!.getOpenedBill()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
            .subscribe({t: List<Bill> ->
                if (t.isEmpty()){
                    Log.d(TAG,"before create bill")
                    createBill()
                } else {
                    Log.d(TAG,"t.size="+t.size)
                    if (t.size == 1){
                        t[0].id?.let { loadDrinksFromBillId(it) }
                    }else {
                        Log.e(TAG,"algo está muito errado era para ter somente uma conta aberta !!!")
                    }

            }

            }, {t: Throwable -> Log.d(TAG,"saindoo error"+t.localizedMessage) }))
    }

    override fun createBill() {
        Log.d(TAG,"called createBill")

        var bill = Bill(null,Calendar.getInstance().time.time,1)


        compositeDisposable.add(
            interactor!!.createBillAndDefaultDrinks(bill)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({it
                    if (it.drink1Id != -1L && it.drink2Id != -1L && it.drink3Id != -1L) {
                        Log.d(TAG,"bill criado, billId=${it.billId}")
                        Log.d(TAG,"default drink1Id=${it.drink1Id}")
                        Log.d(TAG,"default drink2Id=${it.drink2Id}")
                        Log.d(TAG,"default drink3Id=${it.drink3Id}")

                        drinksListId.add(it.drink1Id)
                        drinksListId.add(it.drink2Id)
                        drinksListId.add(it.drink3Id)

                    } else {
                        Log.e(TAG,"bill não criado")
                    }

                }, { err -> Log.getStackTraceString(err)}))

    }

    override fun loadDrinksFromBillId(billId: Long) {
        Log.d(TAG,"called loadDrinksFromBillId")

        compositeDisposable.add(
            interactor!!.loadDrinksFromBillId(billId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({t: List<Drink> ->
                    Log.d(TAG,"loadDrinksFromBillId drinks size="+t.size)
                    getView()?.loadDrinks(t)
                }, { err -> Log.getStackTraceString(err)}))


    }

    override fun onDrawserOptionAboutClick(): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}