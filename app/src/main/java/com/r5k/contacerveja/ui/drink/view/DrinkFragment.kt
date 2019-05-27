package com.r5k.contacerveja.ui.drink.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BaseFragment
import com.r5k.contacerveja.ui.drink.interactor.DrinkMVPInteractor
import com.r5k.contacerveja.ui.drink.presenter.DrinkMVPPresenter
import kotlinx.android.synthetic.main.fragment_generic_drink.*
import javax.inject.Inject
import android.view.*
import com.r5k.contacerveja.ui.main.view.MainActivity


class DrinkFragment : BaseFragment(),DrinkMVPView, View.OnClickListener {

    private val TAG = DrinkFragment::class.java.simpleName
    var mContext : Context? = null

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
        setHasOptionsMenu(true)
        mContext = this!!.activity!!
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
        btn_neg.setOnClickListener(this)
        qnt_drink.setOnClickListener(this)
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
                presenter.onPlusDrinkSelected(mDrink!!)
            }
            R.id.btn_neg -> {
                presenter.onNegDrinkSelected(mDrink!!)
            }
            R.id.qnt_drink -> {
                (activity as MainActivity).showChangeAmountDialog(mDrink!!)
            }
        }
    }

    private fun showEditDialog(){

        val builder = mContext?.let { AlertDialog.Builder(it) }

        // https://stackoverflow.com/questions/10695103/creating-custom-alertdialog-what-is-the-root-view
        // Seems ok to inflate view with null rootView
        val view = layoutInflater.inflate(R.layout.dialog_new_drink, null)

        val newDrinkEditText = view.findViewById(R.id.edt_new_drink_name) as TextInputEditText

        newDrinkEditText.setText(mDrink!!.name)

        builder!!.setView(view)

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val newDrinkName = newDrinkEditText.text
            var isValid = true
            if (newDrinkName!!.isBlank()) {
                newDrinkEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                Log.d(TAG, "Edit to newDrinkName $newDrinkName")
                val newDrink = Drink(mDrink!!.id,newDrinkName.toString(),mDrink!!.price,mDrink!!.qnt,mDrink!!.billId)
                presenter.onUpdateDrinkName(newDrink)
                updateDrink(newDrink)
            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun updateDrink(drink: Drink){
        var mainActivity = activity as MainActivity
        mainActivity.updateTitle(drink)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_edit_drink -> {
                showEditDialog()
                true
            }
            R.id.action_delete_drink -> {
                showDeleteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteDialog() {
        val builder = mContext?.let { AlertDialog.Builder(it) }
        builder!!.setMessage(getString(R.string.delete_drink_alert_title))
        builder.setMessage("""${getString(R.string.delete_drink_alert_message)} ${mDrink!!.name}?""")

        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            presenter.onDeleteDrink(mDrink!!)
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }

    override fun onDeletedDrink(drink: Drink) {
        var mainActivity = activity as MainActivity
        mainActivity.removeDrinkFragment(drink)
    }
}