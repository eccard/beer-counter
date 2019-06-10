package com.r5k.contacerveja.ui.historic.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.data.database.repository.bill.Bill
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
                description.text = it.status.toString()
                this.billId = bill.id
            }
        }

        override fun onClick(v: View?) {
            billId?.let { billClickListener?.onClickedBill(it) }
        }
    }

}