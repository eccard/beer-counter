package com.r5k.contacerveja.ui.drink.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BaseFragment
import javax.inject.Inject
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.r5k.contacerveja.BR
import com.r5k.contacerveja.databinding.FragmentGenericDrinkBinding
import com.r5k.contacerveja.di.factory.ViewModelProviderFactory
import com.r5k.contacerveja.ui.main.view.MainActivity
import kotlinx.android.synthetic.main.fragment_generic_drink.*


class DrinkFragment : BaseFragment<FragmentGenericDrinkBinding,DrinkViewModel>(), View.OnClickListener {

    private lateinit var drinkViewModel: DrinkViewModel

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val TAG = DrinkFragment::class.java.simpleName
    var mContext : Context? = null

    private var mDrink: Drink? = null

    companion object {
        private const val MY_DRINK = "drink"

        fun newInstance(drink:Drink): DrinkFragment = DrinkFragment().apply {
            arguments = Bundle().apply{
                putParcelable(MY_DRINK,drink)
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDrink = arguments?.getParcelable(MY_DRINK)
        setHasOptionsMenu(true)
        mContext = this.activity!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_generic_drink,container,false)
        setupObserver()
        return view
    }

    private fun setupObserver() {
        drinkViewModel.newDrinkAmount.observe(viewLifecycleOwner, Observer {
            it?.let {
                displayTotal(it)
            }
        })

        drinkViewModel.deletedDrink.observe(viewLifecycleOwner, Observer {
            it?.let {
                onDeletedDrink(it)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun getLayoutId(): Int = R.layout.fragment_generic_drink

    override fun getViewModel(): DrinkViewModel {
        drinkViewModel = ViewModelProviders.of(this,factory).get(DrinkViewModel::class.java)
        return drinkViewModel
    }

    override fun getBindingVariable(): Int = BR.viewModel

    fun setUp() {
        mDrink!!.qnt.let { qnt_drink.text = it.toString() }

        btn_plus.setOnClickListener(this)
        btn_neg.setOnClickListener(this)
        qnt_drink.setOnClickListener(this)
    }


    fun displayTotal(total: String) {
        Log.d(TAG,"displayTotal total=$total")
        qnt_drink.text = total
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_plus -> {
                drinkViewModel.onPlusDrinkSelected(mDrink!!)
            }
            R.id.btn_neg -> {
                drinkViewModel.onNegDrinkSelected(mDrink!!)
            }
            R.id.qnt_drink -> {
                showChangeAmountDialog(mDrink!!)
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
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val newDrinkName = newDrinkEditText.text
            var isValid = true
            if (newDrinkName!!.isBlank()) {
                newDrinkEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                Log.d(TAG, "Edit to newDrinkName $newDrinkName")
                val newDrink = Drink(mDrink!!.id,newDrinkName.toString(),mDrink!!.price,mDrink!!.qnt,mDrink!!.billId)
                drinkViewModel.onUpdateDrinkName(newDrink)
                updateDrink(newDrink)
            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
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

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            drinkViewModel.onDeleteDrink(mDrink!!)
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    fun onDeletedDrink(drink: Drink) {
        var mainActivity = activity as MainActivity
        mainActivity.removeDrinkFragment(drink)
    }

    private fun showChangeAmountDialog(drink : Drink){
        val builder = AlertDialog.Builder(context!!)

        val view = layoutInflater.inflate(R.layout.dialog_change_amount, null)

        val amountEditText = view.findViewById(R.id.edt_change_amount) as TextInputEditText

        builder.setView(view)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val newAmount = amountEditText.text
            var isValid = true
            if (newAmount!!.isBlank()) {
                amountEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                Log.d(TAG, "Changing drink amount to $newAmount")
                drinkViewModel.changeDrinkAmount(drink, newAmount.toString().toInt())
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

}