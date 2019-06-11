package  com.r5k.contacerveja.ui.historic.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.R
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.ui.base.BaseActivity
import com.r5k.contacerveja.ui.historic.interactor.HistoricInteractor
import com.r5k.contacerveja.ui.historic.presenter.HistoricMVPPresenter
import com.r5k.contacerveja.ui.main.view.MainActivity
import com.r5k.contacerveja.util.AppConstants
import kotlinx.android.synthetic.main.activity_historic.*
import javax.inject.Inject

class HistoricActivity : BaseActivity(), HistoricMVPView, View.OnClickListener, HistoricAdapter.OnBillCliked {

    @Inject lateinit var presenter : HistoricMVPPresenter<HistoricMVPView,HistoricInteractor>
    private var recyclerView :RecyclerView? = null
    private var adapter :HistoricAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        recyclerView = historic_rv
        adapter = HistoricAdapter(this)
        adapter?.setClickListener(this)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.addItemDecoration(SimpleDividerItemDecoration(this))
        fab_hitoric.setOnClickListener(this)

        presenter.onAttach(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.loadBiils()

    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showBills(bills: List<Bill>) {
        adapter?.setBills(bills)
    }

    override fun openMainActivity() {
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