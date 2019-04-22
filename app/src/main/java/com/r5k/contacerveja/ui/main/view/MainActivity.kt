package com.r5k.contacerveja.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.r5k.contacerveja.R
import com.r5k.contacerveja.ui.MyPagerAdapter
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.main.interactor.MainMVPInteractor
import com.r5k.contacerveja.ui.main.presenter.MainMVPPresenter
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView {

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
}