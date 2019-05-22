package com.r5k.contacerveja.ui.drink.presenter

import android.util.Log
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.view.DrinkMVPView
import javax.inject.Inject


class DrinkPresenter<V : DrinkMVPView, I : DrinkMVPInteractor>
@Inject constructor(interactor: I)
    :BasePresenter<V,I>(interactor = interactor),
        DrinkMVPPresenter<V,I>{

    private val TAG = DrinkPresenter::class.java.simpleName

    override fun onPlusDrinkSelected(drink: Drink) {
        Log.d(TAG,"onPlusDrinkSelected -drink=$drink")
        Log.d(TAG,"onPlusDrinkSelected -interactor=$interactor")

/*        compositeDisposable.add(
            interactor!!.plusDrink(drink)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {t ->
                        Log.d(TAG,"onPlusDrinkSelected -t =$t")
                        if (t >0){
                            getView()?.displayTotal(drink.qnt.toString())
                        } else {
                            Log.e(TAG,"onPlusDrinkSelected nao fez o update do drink")
                        }
                    },
                    {t: Throwable -> Log.d(TAG,"onPlusDrinkSelected saindoo error"+t.localizedMessage) }
                )
        )
                */
    }

}