package com.r5k.contacerveja.ui.drink

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.r5k.contacerveja.data.database.repository.drink.Drink
import com.r5k.contacerveja.ui.drink.view.DrinkFragment

class DrinkPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {


    private var drinksList : MutableList<Drink>? = null
    private var entries: HashMap<Long, Fragment> = hashMapOf()

    fun setDrinkList(drinksList : MutableList<Drink>){
        this.drinksList = drinksList
        entries.clear()
        notifyDataSetChanged()
    }

    fun addDrink(drink : Drink, position:Int = drinksList!!.size){
        this.drinksList!!.add(position,drink)
        notifyDataSetChanged()
    }

    private fun removeDrinkFrg(drinkPosition: Int) {
        drinksList!!.removeAt(drinkPosition)
    }

    override fun createFragment(position: Int): Fragment {
        val itemId = getItemId(position)

        var frg = entries[itemId]
        if (frg != null) {
            return frg
        }

        frg = drinksList?.get(position)?.let { DrinkFragment.newInstance(it) }
        frg?.let { entries.put(itemId, it) }

        return frg!!
    }

    override fun getItemCount(): Int {
        return if (drinksList != null) drinksList!!.size else 0
    }


    fun getPageTitle(position: Int): CharSequence {
        return drinksList?.get(position)?.name!!
    }

    private fun findDrinkIndexById(drinkId : Long) : Int {
        var ret : Int = -1
        for ( i in 0 until drinksList!!.size) {
            if (drinksList!![i].id == drinkId) {
                ret = i
                break
            }
        }
        return ret
    }

    fun updateDrinkName(drink: Drink){
        val drinkPosition = findDrinkIndexById(drink.id!!)
        if ( drinkPosition >= 0 ){
            removeDrinkFrg(drinkPosition)
            addDrink(drink,drinkPosition)
        }
    }

    fun removeDrinkFragment(drink: Drink) {
        val drinkPosition = findDrinkIndexById(drink.id!!)
        if ( drinkPosition >= 0 ) {
            removeDrinkFrg(drinkPosition)
            notifyDataSetChanged()
        }
    }


    override fun getItemId(position: Int): Long {
        return drinksList?.get(position)?.id!!
    }
}