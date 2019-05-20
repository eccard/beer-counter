package com.r5k.contacerveja.ui.drink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.drink.view.DrinkFragment
import com.r5k.contacerveja.ui.PlusFragment

class DrinkPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var drinksList : List<Drink>? = null

    private val MOCK_PLUS_SLOT = 1

    fun setDrinkList(drinksList : List<Drink>){
        this.drinksList = drinksList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {

        val frg : Fragment


        if (drinksList!!.isNotEmpty() && position < drinksList?.size!!){
            frg = drinksList?.get(position)?.let { DrinkFragment.newInstance(it) } as Fragment
        } else {
            frg = PlusFragment()
        }

        return frg
    }

    override fun getCount(): Int = if (drinksList !=null) drinksList!!.size + MOCK_PLUS_SLOT else 0

    override fun getPageTitle(position: Int): CharSequence {
        var title = "+"

        if (count > 0 && position < count-MOCK_PLUS_SLOT)
            title = drinksList?.get(position)?.name!!

        return title
    }
}