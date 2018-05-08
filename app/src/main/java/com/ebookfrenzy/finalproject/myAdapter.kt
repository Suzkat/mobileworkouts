package com.ebookfrenzy.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class myAdapter(val mCtx: Context, val layoutResId:Int, val workoutList:List<Workout>) : ArrayAdapter<Workout>(mCtx,layoutResId,workoutList){
    override fun getView(position:Int,convertView: View?,parent:ViewGroup?): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val workout = workoutList[position]
        textViewName.text = workout.name;

        return view;
    }
}