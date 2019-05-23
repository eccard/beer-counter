package com.r5k.contacerveja.ui.drink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.drink.view.DrinkFragment

class DrinkPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var drinksList : List<Drink>? = null

    fun setDrinkList(drinksList : List<Drink>){
        this.drinksList = drinksList
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return drinksList?.get(position)?.let { DrinkFragment.newInstance(it) } as Fragment
    }

    override fun getCount(): Int = if (drinksList !=null) drinksList!!.size else 0

    override fun getPageTitle(position: Int): CharSequence {
            return drinksList?.get(position)?.name!!
    }
}