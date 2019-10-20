package com.r5k.contacerveja.ui.drink.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.drink.interactor.DrinkInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DrinkViewModel @Inject constructor(private val interactor: DrinkInteractor) : ViewModel() {

    private val _newDrinkAmount = MutableLiveData<String>()
    private val _deletedDrink = MutableLiveData<Drink>()

    val newDrinkAmount : LiveData<String> = _newDrinkAmount
    val deletedDrink : LiveData<Drink> = _deletedDrink

    private val TAG = DrinkViewModel::class.java.simpleName

    init {
        _newDrinkAmount.value = null
        _deletedDrink.value = null
    }

    fun onPlusDrinkSelected(drink: Drink) {
        updateDrinkInfoInDb(interactor.plusQntForDrink(drink))
    }


    fun onNegDrinkSelected(drink: Drink) {
        interactor.negQntForDrink(drink)?.let { updateDrinkInfoInDb(it) }
    }

    fun onUpdateDrinkName(drink: Drink) {
        updateDrinkInfoInDb(drink)
    }

    private fun updateDrinkInfoInDb(drink: Drink) {
        Log.d(TAG,"updateDrinkInfoInDb -drink=$drink")
        Log.d(TAG,"updateDrinkInfoInDb -interactor=$interactor")

        GlobalScope.launch(context = Dispatchers.Main) {

            val afectedRows = withContext(context = Dispatchers.IO) {
                interactor.updateDrinkInDb(drink).await()
            }

            Log.d(TAG,"updateDrinkInfoInDb -afectedRows =$afectedRows")
            if (afectedRows >0){
                _newDrinkAmount.postValue(drink.qnt.toString())
            } else {
                Log.e(TAG,"updateDrinkInfoInDb nao fez o update do drink")
            }
        }
    }

    fun onDeleteDrink(drink: Drink) {
        GlobalScope.launch(context = Dispatchers.Main) {

            val affectedRows = withContext(context = Dispatchers.IO) {
                interactor.deleteDrink(drink).await()
            }

            Log.d(TAG,"onDeleteDrink -afectedRows =$affectedRows")
            if (affectedRows >0){
                _deletedDrink.postValue(drink)
//                getView()?.onDeletedDrink(drink)
            } else {
                Log.e(TAG,"onDeleteDrink nao fez delete do drink")
            }
        }
    }

    fun changeDrinkAmount(drink: Drink, amount: Int) {
        GlobalScope.launch(context = Dispatchers.Main) {
            val affectedRows = withContext(context = Dispatchers.IO) {
                interactor.changeDrinkAmount(drink, amount).await()
            }
            if (affectedRows > 0){
                amount.toString()
                _newDrinkAmount.postValue(drink.qnt.toString())
            } else {
                Log.e(TAG,"changeDrinkAmount from=${drink.qnt} to=${amount}")
            }
        }
    }

    override fun onCleared() {
        _newDrinkAmount.value = null
        _deletedDrink.value = null
    }
}