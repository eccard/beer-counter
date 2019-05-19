package com.r5k.contacerveja.ui.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.drink.DrinkPagerAdapter
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.main.interactor.DefaultDrinksForBill
import com.r5k.contacerveja.ui.main.interactor.MainInteractor
import com.r5k.contacerveja.ui.main.presenter.MainMVPPresenter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {


    private val TAG = MainActivity::class.java.simpleName

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView,MainInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var fragmentAdapter: DrinkPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentAdapter = DrinkPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

        presenter.onAttach(this)

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun lockDrawer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlockDrawer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDefaultDrinks(drinksForBill : DefaultDrinksForBill) {
        Log.d(TAG, "loadDefaultDrinks DefaultDrinksForBill size = $drinksForBill")
        fragmentAdapter.setDrinkList(drinksForBill.drinksList)
    }

    override fun loadDrinksForOpenedBill(drinks: List<Drink>) {
        Log.d(TAG, "loadDefaultDrinks size = $drinks")
        fragmentAdapter.setDrinkList(drinks)
    }
}