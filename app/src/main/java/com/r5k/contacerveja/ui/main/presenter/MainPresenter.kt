package com.r5k.contacerveja.ui.main.presenter

import android.util.Log
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillState
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.main.interactor.MainMVPInteractor
import com.r5k.contacerveja.ui.main.view.MainMVPView
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class MainPresenter<V:MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V,I>(interactor = interactor),MainMVPPresenter<V,I>{

    private val TAG = MainPresenter::class.java.simpleName

    override fun createBill() {
        Log.d(TAG,"called createBill")

        var bill = Bill(null,Calendar.getInstance().time.time,BillState.OPEN.ordinal)

        GlobalScope.launch(context = Dispatchers.Main) {

            val defaultDrinks = withContext(context = Dispatchers.IO) {
                interactor!!.createBillAndDefaultDrinks(bill).await()
            }

            if (defaultDrinks.drinksList.size > 0) {

                Log.d(TAG,"bill criado, billId=${defaultDrinks.billId}")
                defaultDrinks.drinksList.forEach {
                    Log.d(TAG,"=$it")
                }

                checkIfBillIsOpened()
                getView()?.loadDefaultDrinks(defaultDrinks)

            } else {
                Log.e(TAG,"bill não criado")
            }

        }
    }

    override fun loadDrinksFromBillId(billId: Long) {
        Log.d(TAG,"called loadDrinksFromBillId")

        GlobalScope.launch(context = Dispatchers.Main) {

            val drinksFromBill = withContext(context = Dispatchers.IO) {
                interactor!!.loadDrinksFromBillId(billId).await()
            }

            checkIfBillIsOpened()
            Log.d(TAG,"loadDrinksFromBillId drinks size=$drinksFromBill.size")
            getView()?.loadDrinksForOpenedBill(drinksFromBill)

        }
    }

    override fun onDrawserOptionAboutClick(): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewDrink(drinkName: String) {

        GlobalScope.launch(context = Dispatchers.Main) {
            val drinkIdIn = withContext(context = Dispatchers.IO) {
                interactor!!.addDrink(drinkName).await()
            }
            Log.d(TAG,"addNewDrink drinkIdInBd=$drinkIdIn")
            getView()!!.addNewDrink(drinkIdIn)

        }
    }

    override fun loadTotalOfBill(){

        GlobalScope.launch(context = Dispatchers.Main) {

            val drinks = withContext(context = Dispatchers.IO) {
                interactor!!.loadDrinksFromOpenedBill().await()
            }

            val totalOfBill = withContext(context = Dispatchers.IO){
                interactor!!.callTotalOfBill(drinks).await()
            }

            getView()?.showTotal(drinks,totalOfBill)
        }
    }

    override fun closeBill() {

        GlobalScope.launch(context = Dispatchers.Main) {

            val affectedCollumns = withContext(context = Dispatchers.IO) {
                interactor!!.closeBill().await()
            }

            if (affectedCollumns > 0){
                getView()?.onClosedBill()
            }

        }
    }

    override fun checkIfBillIsOpened() {

        GlobalScope.launch(context = Dispatchers.Main) {

            val isBillOpened =  withContext(context = Dispatchers.IO){
                interactor!!.checkIfBillIsOpened().await()
            }

            getView()?.onBillLoadedStatus(isBillOpened)
        }

    }
}