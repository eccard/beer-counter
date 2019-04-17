package com.r5k.contacerveja

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.r5k.contacerveja.ThirdFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FirstFragment()
            }
            1 -> {
                SecondFragment()
            }
            2 -> {
                ThirdFragment()
            }
            else -> {
                return PlusFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Cerveja"
            1 -> "Vinho"
            2 -> "Cachaça"
            else -> {
                return "+"
            }
        }
    }
}