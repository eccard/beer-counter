package com.r5k.contacerveja.ui.drink.presenter

import android.util.Log
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.view.DrinkMVPView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DrinkPresenter<V : DrinkMVPView, I : DrinkMVPInteractor>
@Inject constructor(interactor: I)
    :BasePresenter<V,I>(interactor = interactor),
        DrinkMVPPresenter<V,I>{

    private val TAG = DrinkPresenter::class.java.simpleName

    override fun onPlusDrinkSelected(drink: Drink) {
        updateDrinkInfoInDb(interactor!!.plusQntForDrink(drink))
    }


    override fun onNegDrinkSelected(drink: Drink) {
        interactor!!.negQntForDrink(drink)?.let { updateDrinkInfoInDb(it) }
    }

    override fun onUpdateDrinkName(drink: Drink) {
        updateDrinkInfoInDb(drink)
    }

    private fun updateDrinkInfoInDb(drink: Drink) {
        Log.d(TAG,"updateDrinkInfoInDb -drink=$drink")
        Log.d(TAG,"updateDrinkInfoInDb -interactor=$interactor")

        GlobalScope.launch(context = Dispatchers.Main) {

            val afectedRows = withContext(context = Dispatchers.IO) {
                interactor!!.updateDrinkInDb(drink).await()
            }

            Log.d(TAG,"updateDrinkInfoInDb -afectedRows =$afectedRows")
            if (afectedRows >0){
                getView()?.displayTotal(drink.qnt.toString())
            } else {
                Log.e(TAG,"updateDrinkInfoInDb nao fez o update do drink")
            }
        }
    }

    override fun onDeleteDrink(drink: Drink) {
        GlobalScope.launch(context = Dispatchers.Main) {

            val affectedRows = withContext(context = Dispatchers.IO) {
                interactor!!.deleteDrink(drink).await()
            }

            Log.d(TAG,"onDeleteDrink -afectedRows =$affectedRows")
            if (affectedRows >0){
                getView()?.onDeletedDrink(drink)
            } else {
                Log.e(TAG,"onDeleteDrink nao fez delete do drink")
            }
        }
    }

    override fun changeDrinkAmount(drink: Drink, amount: Int) {
        GlobalScope.launch(context = Dispatchers.Main) {
            val affectedRows = withContext(context = Dispatchers.IO) {
                interactor!!.changeDrinkAmount(drink, amount).await()
            }
            if (affectedRows > 0){
                getView()!!.displayTotal(amount.toString())
            } else {
                Log.e(TAG,"changeDrinkAmount from=${drink.qnt} to=${amount}")
            }
        }
    }
}