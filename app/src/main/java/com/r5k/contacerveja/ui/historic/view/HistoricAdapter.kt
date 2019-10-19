package com.r5k.contacerveja.ui.historic.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.data.database.repository.bill.Bill
import com.r5k.contacerveja.data.database.repository.bill.BillState
import java.text.SimpleDateFormat

class HistoricAdapter(val context: Context): RecyclerView.Adapter<HistoricAdapter.ViewHolder>() {

    interface OnBillCliked{
        fun onClickedBill(billId : Long)
    }

    private var bills : List<Bill>? = null
    var billClickListener :OnBillCliked? =  null

    fun setBills(bills : List<Bill>){
        this.bills = bills
        notifyDataSetChanged()
    }

    fun setClickListener(billClickListener :OnBillCliked){
        this.billClickListener = billClickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        var count  = 0

        bills?.let {
            count = it.size
        }


        return  count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(bills?.get(position),billClickListener!!)
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val title = itemView.findViewById<TextView>(android.R.id.text1)
        val description = itemView.findViewById<TextView>(android.R.id.text2)
        var billClickListener :OnBillCliked? =  null
        var billId : Long? = -1

        fun bindView (bill : Bill?,billClickListener :OnBillCliked){
            itemView.setOnClickListener(this)
            this.billClickListener = billClickListener
            bill?.let {
                title.text = SimpleDateFormat.getInstance().format(it.date)
                val descrition :String
                val color : Int
                if (it.status == BillState.OPEN.ordinal){
                    descrition = "Opened"
                    color = Color.MAGENTA
                } else {
                    descrition = "Closed"
                    color = Color.BLACK
                }
                description.text = descrition
                description.setTextColor(color)
                this.billId = bill.id
            }
        }

        override fun onClick(v: View?) {
            billId?.let { billClickListener?.onClickedBill(it) }
        }
    }

}