package com.r5k.contacerveja.ui.main.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillState
import com.r5k.contacerveja.data.database.repository.bill.BillsRepository
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.data.database.repository.drink.DrinksRepository
import com.r5k.contacerveja.ui.main.interactor.DefaultDrinksForBill
import com.r5k.contacerveja.ui.main.interactor.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    private var _defaultDrinks = MutableLiveData<DefaultDrinksForBill>()
    private var _billClosed = MutableLiveData<Boolean>()
    private var  _drinksForOpenedBill = MutableLiveData<List<Drink>>()
    private var _newDrink = MutableLiveData<Drink>()
    private var _showTotal = MutableLiveData<Pair<List<Drink>,Double>>()

    val defaultDrinks: LiveData<DefaultDrinksForBill> = _defaultDrinks
    val drinksForOpenedBill: LiveData<List<Drink>> = _drinksForOpenedBill
    val newDrink: LiveData<Drink> = _newDrink
    val billClosed: LiveData<Boolean> = _billClosed
    val showTotal : LiveData<Pair<List<Drink>,Double>> = _showTotal

    private val TAG = MainViewModel::class.java.simpleName


    init {
        _defaultDrinks.value = null
        _drinksForOpenedBill.value = null
        _newDrink.value = null
        _showTotal.value = null
        _billClosed.value = false
        loadDrinks()
    }


    fun loadDrinks() {
        viewModelScope.launch(context = Dispatchers.Main) {

            val openedBills = withContext(context = Dispatchers.IO) {
                interactor.getOpenedBill().await()
            }

            Log.d(TAG, "opened biils = $openedBills")

            if (openedBills.isEmpty()) {
                Log.d(TAG, "before create bill")
                createBill()
            } else {
                Log.d(TAG, "openedBills.size=" + openedBills.size)
                if (openedBills.size == 1) {
                    openedBills[0].id.let { loadDrinksFromBillId(openedBills[0].id!!) }
                } else {
                    Log.e(TAG, "algo está muito errado era para ter somente uma conta aberta !!!")
                }
            }
        }
    }

    fun closeBill() {

        viewModelScope.launch(context = Dispatchers.Main) {

            val affectedCollumns = withContext(context = Dispatchers.IO) {
                interactor.closeBill().await()
            }

            if (affectedCollumns > 0){
                _billClosed.postValue(true)
//                getView().onClosedBill()
            }

        }
    }


    fun createBill() {
        Log.d(TAG,"called createBill")

        var bill = Bill(null, Calendar.getInstance().time.time,BillState.OPEN.ordinal)

        viewModelScope.launch(context = Dispatchers.Main) {

            val defaultDrinks = withContext(context = Dispatchers.IO) {
                interactor!!.createBillAndDefaultDrinks(bill).await()
            }

            if (defaultDrinks.drinksList.size > 0) {

                Log.d(TAG,"bill criado, billId=${defaultDrinks.billId}")
                defaultDrinks.drinksList.forEach {
                    Log.d(TAG,"=$it")
                }

//                getView()?.loadDefaultDrinks(defaultDrinks)
                _defaultDrinks.postValue(defaultDrinks)
            } else {
                Log.e(TAG,"bill não criado")
            }

        }
    }

    fun loadDrinksFromBillId(billId: Long) {
        Log.d(TAG,"called loadDrinksFromBillId")

        viewModelScope.launch(context = Dispatchers.Main) {

            val drinksFromBill = withContext(context = Dispatchers.IO) {
                interactor.loadDrinksFromBillId(billId).await()
            }

            Log.d(TAG,"loadDrinksFromBillId drinks size=$drinksFromBill.size")
//            getView()?.loadDrinksForOpenedBill(drinksFromBill)
            _drinksForOpenedBill.postValue(drinksFromBill)
        }
    }

    fun onDrawserOptionAboutClick(): Boolean? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addNewDrink(drinkName: String) {

        viewModelScope.launch(context = Dispatchers.Main) {
            val drinkIdIn = withContext(context = Dispatchers.IO) {
                interactor!!.addDrink(drinkName).await()
            }
            Log.d(TAG,"addNewDrink drinkIdInBd=$drinkIdIn")
            _newDrink.postValue(drinkIdIn)
//            getView()!!.addNewDrink(drinkIdIn)

        }
    }

    fun loadTotalOfBill(){

        viewModelScope.launch(context = Dispatchers.Main) {

            val drinks = withContext(context = Dispatchers.IO) {
                interactor.loadDrinksFromOpenedBill().await()
            }

            val totalOfBill = withContext(context = Dispatchers.IO){
                interactor.callTotalOfBill(drinks).await()
            }

            _showTotal.postValue(Pair(drinks,totalOfBill))
//            getView()?.showTotal(drinks,totalOfBill)
        }
    }

    override fun onCleared() {
        _newDrink.value = null
        _drinksForOpenedBill.value = null
        _defaultDrinks.value = null
        _showTotal.value = null
        _billClosed.value = false
    }

}