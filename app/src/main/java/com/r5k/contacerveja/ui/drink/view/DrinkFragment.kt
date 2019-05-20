package com.r5k.contacerveja.ui.drink.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BaseFragment
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.presenter.DrinkMVPPresenter
import com.r5k.contacerveja.ui.drink.presenter.DrinkPresenter
import kotlinx.android.synthetic.main.fragment_generic_drink.*
import javax.inject.Inject


class DrinkFragment : BaseFragment(),DrinkMVPView, View.OnClickListener {

    private val TAG = DrinkFragment::class.java.simpleName


    companion object {
        private const val MY_DRINK = "drink"

        fun newInstance(drink:Drink): DrinkFragment = DrinkFragment().apply {
            arguments = Bundle().apply{
                putParcelable(MY_DRINK,drink)
            }

        }
    }

    @Inject
    internal lateinit var presenter : DrinkMVPPresenter<DrinkMVPView,DrinkMVPInteractor>

    private var mDrink: Drink? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDrink = arguments?.getParcelable(MY_DRINK)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generic_drink,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        mDrink!!.qnt.let { qnt_drink.text = it.toString() }

        btn_plus.setOnClickListener(this)
    }


    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun displayTotal(total: String) {
        Log.d(TAG,"displayTotal total=$total")
        qnt_drink.text = total
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_plus -> {
                mDrink!!.qnt = mDrink!!.qnt + 1
                presenter.onPlusDrinkSelected(mDrink!!)
            }
        }
    }
}