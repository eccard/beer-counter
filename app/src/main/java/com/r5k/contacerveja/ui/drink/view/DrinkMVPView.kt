package com.r5k.contacerveja.ui.drink.view

import com.r5k.contacerveja.ui.base.MVPView

interface DrinkMVPView : MVPView{
    fun displayTotal(total : String)
}