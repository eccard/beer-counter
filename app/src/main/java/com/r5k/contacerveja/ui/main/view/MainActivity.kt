package com.r5k.contacerveja.ui.main.view

import android.os.Bundle
import android.util.Log
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.MyPagerAdapter
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.main.interactor.MainMVPInteractor
import com.r5k.contacerveja.ui.main.presenter.MainMVPPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView,MainMVPInteractor>

//    @Inject
//    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

        presenter.onAttach(this)

    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun lockDrawer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlockDrawer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDrinks(drinks: List<Drink>) {
        Log.d(TAG,"loadDrinks size = " + drinks.size)
    }
}