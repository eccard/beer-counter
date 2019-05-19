package com.r5k.contacerveja.ui.drink.presenter

import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BasePresenter
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.view.DrinkMVPView
import com.r5k.contacerveja.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DrinkPresenter<V : DrinkMVPView, I : DrinkMVPInteractor>
@Inject constructor(interactor: I, schedulerProvider : SchedulerProvider, disposable : CompositeDisposable)
    :BasePresenter<V,I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable),
        DrinkMVPPresenter<V,I>{

    override fun onPlusDrinkSelected(drink: Drink) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}