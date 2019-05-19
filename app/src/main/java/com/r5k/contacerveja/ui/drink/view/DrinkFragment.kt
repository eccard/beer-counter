package com.r5k.contacerveja.ui.drink.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BaseFragment
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.presenter.DrinkMVPPresenter
import kotlinx.android.synthetic.main.fragment_generic_drink.*
import javax.inject.Inject


class DrinkFragment : BaseFragment(),DrinkMVPView {

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
        mDrink!!.qnt?.let { qnt_drink.text = it.toString() }
    }


    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun displayTotal(total: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}