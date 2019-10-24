package  com.r5k.contacerveja.ui.historic.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.BR
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.databinding.ActivityHistoricBinding
import com.r5k.contacerveja.di.factory.ViewModelProviderFactory
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import com.r5k.contacerveja.ui.main.view.MainActivity
import com.r5k.contacerveja.util.AppConstants
import kotlinx.android.synthetic.main.activity_historic.*
import javax.inject.Inject

class HistoricActivity : BaseActivity<ActivityHistoricBinding,HistoricViewModel>(), View.OnClickListener, HistoricAdapter.OnBillCliked {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var activityHistoricBinding: ActivityHistoricBinding

    private lateinit var historicViewModel: HistoricViewModel

    override fun getLayoutId(): Int = R.layout.activity_historic

    override fun getViewModel(): HistoricViewModel {
        historicViewModel = ViewModelProviders.of(this, factory).get(HistoricViewModel::class.java)
        return historicViewModel
    }

    override fun getBindingVariable(): Int = BR.viewModel

    private var recyclerView :RecyclerView? = null
    private var adapter :HistoricAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHistoricBinding = getViewDataBinding()

        recyclerView = historic_rv
        adapter = HistoricAdapter(this)
        adapter?.setClickListener(this)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.addItemDecoration(SimpleDividerItemDecoration(this))
        fab_hitoric.setOnClickListener(this)
        setupObserver()
    }

    private fun setupObserver() {
        historicViewModel.listBillResult.observe(this, Observer {
            it?.let {
                showBills(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        historicViewModel.loadBills()

    }

    fun showBills(bills: List<Bill>) {
        adapter?.setBills(bills)
    }

    fun openMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.fab_hitoric){

            val intent = Intent(this,MainActivity::class.java)
            intent.action = AppConstants.ACTION_NEW_BILL
            startActivity(intent)
        }
    }

    override fun onClickedBill(billId: Long) {
        val intent = Intent(this,MainActivity::class.java)
        intent.action = AppConstants.ACTION_LOAD_BILL
        intent.putExtra(AppConstants.KEY_BILL_ID,billId)
        startActivity(intent)
    }
}