package com.extremeprogramming.financetracker.ui

import android.content.Context
import com.extremeprogramming.financetracker.R

class Colors {

   companion object {
       @Volatile
       private var COLORS : ArrayList<Int> = ArrayList()

       fun getColors(): ArrayList<Int> {
           return COLORS
       }

       fun init(context: Context) {
           synchronized(this) {
               val colors : ArrayList<Int> = ArrayList()
               val scheme = context.resources.getIntArray(R.array.color_scheme)

               for (col in scheme) {
                   colors.add(col)
               }

               COLORS = colors
           }
       }


   }
}