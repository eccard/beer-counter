package com.r5k.contacerveja.ui.historic.view

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r5k.contacerveja.R
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

        val view = LayoutInflater.from(context).inflate(R.layout.historic_item,parent,false)

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
        val title = itemView.findViewById<TextView>(R.id.tv_date)
        val description = itemView.findViewById<TextView>(R.id.tv_state)
        var billClickListener :OnBillCliked? =  null
        var billId : Long? = -1

        fun bindView (bill : Bill?,billClickListener :OnBillCliked){
            itemView.setOnClickListener(this)
            this.billClickListener = billClickListener
            bill?.let {
                this.billId = bill.id
                title.text = SimpleDateFormat.getInstance().format(it.date)
                val color : Int
                val text : String
                if (it.status == 0 ){
                    text = "Open"
                    color = android.R.color.holo_green_light
                } else {
                    color = android.R.color.holo_red_light
                    text = "Closed"
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    description.setTextColor(description.context.resources.getColor(color,description.context.theme))
                } else {
                    description.setTextColor(description.context.resources.getColor(color))
                }
                description.text = text
            }
        }

        override fun onClick(v: View?) {
            billId?.let { billClickListener?.onClickedBill(it) }
        }
    }

}