package com.r5k.contacerveja.ui.main.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.drink.DrinkPagerAdapter
import com.r5k.contacerveja.ui.main.interactor.DefaultDrinksForBill
import com.r5k.contacerveja.ui.main.interactor.MainInteractor
import com.r5k.contacerveja.ui.main.presenter.MainMVPPresenter
import com.r5k.contacerveja.util.AppConstants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


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
        bottom_nav.setOnNavigationItemSelectedListener(this)
        fab.setOnClickListener(this)

        presenter.onAttach(this)

        if (intent?.action.equals(AppConstants.ACTION_NEW_BILL)){
            presenter.createBill()
        } else if (intent?.action.equals(AppConstants.ACTION_LOAD_BILL)){
            if (intent.hasExtra(AppConstants.KEY_BILL_ID)){
                val billId = intent.getLongExtra(AppConstants.KEY_BILL_ID, -1)
                presenter.loadDrinksFromBillId(billId)
            }
        }
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
        fragmentAdapter.setDrinkList(drinks.toMutableList())
    }

    override fun addNewDrink(drink: Drink) {
        fragmentAdapter.addDrink(drink)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_add_drink -> {
                showAddDrinkDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAddDrinkDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)

        // https://stackoverflow.com/questions/10695103/creating-custom-alertdialog-what-is-the-root-view
        // Seems ok to inflate view with null rootView
        val view = layoutInflater.inflate(R.layout.dialog_new_drink, null)

        val newDrinkEditText = view.findViewById(R.id.edt_new_drink_name) as TextInputEditText

        builder.setView(view)

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val newDrinkName = newDrinkEditText.text
            var isValid = true
            if (newDrinkName!!.isBlank()) {
                newDrinkEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                Log.d(TAG, "Adding newDrinkName $newDrinkName")
                presenter.addNewDrink(newDrinkName.toString())
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }


    fun updateTitle(drink : Drink){
        fragmentAdapter.updateDrinkName(drink)
    }

    fun removeDrinkFragment(drink : Drink) {
        fragmentAdapter.removeDrinkFragment(drink)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId){
            R.id.navigation_history -> 1
            else -> 2
        }

        return true
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.fab){
                presenter.loadTotalOfBill()
            }
        }
    }

    override fun showTotal(drinks: List<Drink>, total: Double) {
        val context = this
        val builder = AlertDialog.Builder(context)

        val view = layoutInflater.inflate(R.layout.dialog_show_total, null)

        var totalDrinks = 0
        drinks.forEach {

            totalDrinks += it.qnt

            val listItem = layoutInflater.inflate(android.R.layout.simple_list_item_1, null)

            var priceAndTotal = ""
            if ( it.price != null) {
                priceAndTotal = String.format("%f  Total=%f",it.price,(it.qnt * it.price.toDouble()))

            }
            val details = String.format("%s \t%d \t%s", it.name, it.qnt, priceAndTotal)
            listItem.findViewById<TextView>(android.R.id.text1).text = details
            listItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            view.findViewById<LinearLayout>(R.id.ll_container).addView(listItem)
        }

        val textViewTotal = TextView(this)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            textViewTotal.setTextAppearance(this,android.R.style.TextAppearance_Large)
        } else {
            textViewTotal.setTextAppearance(android.R.style.TextAppearance_Large)
        }
        textViewTotal.text = String.format("Drinks =%d \nPrice  =%.2f",totalDrinks,total)
        textViewTotal.layoutParams  = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        view.findViewById<LinearLayout>(R.id.ll_container).addView(textViewTotal)

        builder.setView(view)

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            presenter.closeBill()
        }

        builder.show()
    }

    override fun onClosedBill() {
        finish()
    }

}