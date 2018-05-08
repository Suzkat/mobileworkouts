package com.ebookfrenzy.finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.ebookfrenzy.finalproject.R.layout.diary
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
import java.lang.Integer.parseInt

class CardioActivity : AppCompatActivity() {


    lateinit var exerciseName: EditText
    lateinit var exReps: EditText
    lateinit var exSets: EditText
    lateinit var buttonSave: Button
    lateinit var ref: DatabaseReference
    lateinit var workoutList: MutableList<Workout>
    lateinit var newWorkoutList: ArrayList<Workout>
    lateinit var listView: ListView
    lateinit var generateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hiit)

        workoutList = mutableListOf()
        newWorkoutList = ArrayList()

        ref = FirebaseDatabase.getInstance().getReference("workouts")


        exerciseName = findViewById(R.id.exName)
        exReps = findViewById(R.id.exReps)
        exSets = findViewById(R.id.exSets)
        buttonSave = findViewById(R.id.addExButton)
        listView = findViewById(R.id.cardiodblist)
        buttonSave.setOnClickListener {
            saveWorkout()
        }
        generateButton = findViewById(R.id.button2)
        generateButton.setOnClickListener{
            val tList: ArrayList<Workout> = generateWorkout()
            var numLoops:Int=6
            var numSets: EditText? = findViewById(R.id.editText8)
            numLoops = parseInt(numSets!!.text.toString())


            val intent: Intent = Intent(this,DiaryActivity::class.java)
            intent.putExtra("numLoops",numLoops)
            var i:Int = 0
            while(i<numLoops)
            {
                intent.putExtra("name " + i,tList.get(i).name.toString())
                intent.putExtra("sets " + i,tList.get(i).sets.toString())
                intent.putExtra("reps " + i,tList.get(i).reps.toString())
                i++
            }

            startActivity(intent)

        }



        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0!!.exists())
                {
                    workoutList.clear()
                    for(w in p0.children)
                    {
                        val workout = w.getValue(Workout::class.java)
                        workoutList.add(workout!!)

                    }

                    val adapter = myAdapter(applicationContext,R.layout.lifts,workoutList)
                    listView.adapter = adapter
                }
            }

        })
    }

    private fun saveWorkout()
    {
        val name = exerciseName.text.toString()
        if(name.isEmpty()){
            exerciseName.error="Please enter an exercise name"
        }

        val exeId = ref.push().key
        val exe = Workout(exeId,name,exSets.text.toString(),exReps.text.toString())
        ref.child(exeId).setValue(exe).addOnCompleteListener {
            Toast.makeText(applicationContext,"Exercise saved successfully", Toast.LENGTH_LONG).show()
        }

    }

    private fun generateWorkout(): ArrayList<Workout>
    {
        val mNum = 6;
        workoutList.shuffle();
        var i = 0
        while(i<mNum)
        {

            newWorkoutList.add(i,workoutList[i])
            Log.i("name",newWorkoutList.get(i).name.toString())
            i++
        }


        return newWorkoutList

    }

    public inline fun<T> MutableList<T>.shuffle()
    {
        java.util.Collections.shuffle(this)
    }



}