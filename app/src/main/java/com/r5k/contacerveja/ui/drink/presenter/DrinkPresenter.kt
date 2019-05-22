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
        Log.d(TAG,"onPlusDrinkSelected -drink=$drink")
        Log.d(TAG,"onPlusDrinkSelected -interactor=$interactor")

        GlobalScope.launch(context = Dispatchers.Main) {

            val afectedRows = withContext(context = Dispatchers.IO) {
                interactor!!.plusDrink(drink).await()
            }

            Log.d(TAG,"onPlusDrinkSelected -t =$afectedRows")
            if (afectedRows >0){
                getView()?.displayTotal(drink.qnt.toString())
            } else {
                Log.e(TAG,"onPlusDrinkSelected nao fez o update do drink")
            }
        }
    }

}