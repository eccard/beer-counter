package com.r5k.contacerveja.ui.historic.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HistoricViewModel @Inject constructor(private val interactor: HistoricInteractor): ViewModel() {

    private var _listBillResult = MutableLiveData<List<Bill>>()
    val listBillResult: LiveData<List<Bill>> = _listBillResult

    init {
        _listBillResult.value = null
//        loadBills()
    }

   fun loadBills() {

        viewModelScope.launch(context = Dispatchers.Main) {

            val billList = withContext(context = Dispatchers.IO) {
                interactor.loadBills().await()
            }
            _listBillResult.postValue(billList)

        }
    }

    override fun onCleared() {
        _listBillResult.value = null
    }
}