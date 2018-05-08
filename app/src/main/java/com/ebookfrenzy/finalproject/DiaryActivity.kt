package com.ebookfrenzy.finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class DiaryActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diary)

        var intent: Intent = getIntent()

        val numLoops:Int = intent.getIntExtra("numLoops",6)
        var nameArray:ArrayList<String> = ArrayList()
        var setArray:ArrayList<String> = ArrayList()
        var repArray:ArrayList<String> = ArrayList()
        var i:Int=0
        while(i<numLoops)
        {
            nameArray.add(i,intent.getStringExtra("name " + i))
            setArray.add(i,intent.getStringExtra("sets " + i))
            repArray.add(i,intent.getStringExtra("reps " + i))
            i++
        }
//        val m1:String = intent.getStringExtra("name 0")
//        val m2:String = intent.getStringExtra("name 1")
//        val m3:String = intent.getStringExtra("name 2")
//        val m4:String = intent.getStringExtra("name 3")
//        val m5:String = intent.getStringExtra("name 4")
//        val m6:String = intent.getStringExtra("name 5")
//        val s1:String = intent.getStringExtra("sets 0")
//        val s2:String = intent.getStringExtra("sets 1")
//        val s3:String = intent.getStringExtra("sets 2")
//        val s4:String = intent.getStringExtra("sets 3")
//        val s5:String = intent.getStringExtra("sets 4")
//        val s6:String = intent.getStringExtra("sets 5")
//        val r1:String = intent.getStringExtra("reps 0")
//        val r2:String = intent.getStringExtra("reps 1")
//        val r3:String = intent.getStringExtra("reps 2")
//        val r4:String = intent.getStringExtra("reps 3")
//        val r5:String = intent.getStringExtra("reps 4")
//        val r6:String = intent.getStringExtra("reps 5")

        val homeButton: Button = findViewById<Button>(R.id.home_button)

        var ex1name: TextView = findViewById(R.id.ex1name)
        var ex2name: TextView = findViewById(R.id.ex2name)
        var ex3name: TextView = findViewById(R.id.ex3name)
        var ex4name: TextView = findViewById(R.id.ex4name)
        var ex5name: TextView = findViewById(R.id.ex5name)
        var ex6name: TextView = findViewById(R.id.ex6name)

        var ex1set: TextView = findViewById(R.id.ex1set)
        var ex2set: TextView = findViewById(R.id.ex2set)
        var ex3set: TextView = findViewById(R.id.ex3set)
        var ex4set: TextView = findViewById(R.id.ex4set)
        var ex5set: TextView = findViewById(R.id.ex5set)
        var ex6set: TextView = findViewById(R.id.ex6set)

        var ex1reps: TextView = findViewById(R.id.ex1reps)
        var ex2reps: TextView = findViewById(R.id.ex2reps)
        var ex3reps: TextView = findViewById(R.id.ex3reps)
        var ex4reps: TextView = findViewById(R.id.ex4reps)
        var ex5reps: TextView = findViewById(R.id.ex5reps)
        var ex6reps: TextView = findViewById(R.id.ex6reps)



        when {
            (numLoops==1) -> {
                ex1name.text=nameArray.get(0)
                ex1set.text=setArray.get(0)
                ex1reps.text=repArray.get(0)
            }
            (numLoops==2) -> {
                ex1name.text = nameArray.get(0)
                ex2name.text = nameArray.get(1)
                ex1set.text = setArray.get(0)
                ex2set.text = setArray.get(1)
                ex1reps.text = repArray.get(0)
                ex2reps.text = repArray.get(1)
            }
            (numLoops==3)->{
                ex1name.text = nameArray.get(0)
                ex2name.text = nameArray.get(1)
                ex3name.text = nameArray.get(2)
                ex1set.text = setArray.get(0)
                ex2set.text = setArray.get(1)
                ex3set.text = setArray.get(2)
                ex1reps.text = repArray.get(0)
                ex2reps.text = repArray.get(1)
                ex3reps.text = repArray.get(2)
            }
            (numLoops==4)->{
                ex1name.text = nameArray.get(0)
                ex2name.text = nameArray.get(1)
                ex3name.text = nameArray.get(2)
                ex4name.text = nameArray.get(3)
                ex1set.text = setArray.get(0)
                ex2set.text = setArray.get(1)
                ex3set.text = setArray.get(2)
                ex4set.text = setArray.get(3)
                ex1reps.text = repArray.get(0)
                ex2reps.text = repArray.get(1)
                ex3reps.text = repArray.get(2)
                ex4reps.text = repArray.get(3)
            }
            (numLoops==5)->{
                ex1name.text = nameArray.get(0)
                ex2name.text = nameArray.get(1)
                ex3name.text = nameArray.get(2)
                ex4name.text = nameArray.get(3)
                ex5name.text = nameArray.get(4)
                ex1set.text = setArray.get(0)
                ex2set.text = setArray.get(1)
                ex3set.text = setArray.get(2)
                ex4set.text = setArray.get(3)
                ex5set.text = setArray.get(4)
                ex1reps.text = repArray.get(0)
                ex2reps.text = repArray.get(1)
                ex3reps.text = repArray.get(2)
                ex4reps.text = repArray.get(3)
                ex5reps.text = repArray.get(4)

            }(numLoops==6)->{
            ex1name.text = nameArray.get(0)
            ex2name.text = nameArray.get(1)
            ex3name.text = nameArray.get(2)
            ex4name.text = nameArray.get(3)
            ex5name.text = nameArray.get(4)
            ex6name.text = nameArray.get(5)
            ex1set.text = setArray.get(0)
            ex2set.text = setArray.get(1)
            ex3set.text = setArray.get(2)
            ex4set.text = setArray.get(3)
            ex5set.text = setArray.get(4)
            ex6set.text = setArray.get(5)
            ex1reps.text = repArray.get(0)
            ex2reps.text = repArray.get(1)
            ex3reps.text = repArray.get(2)
            ex4reps.text = repArray.get(3)
            ex5reps.text = repArray.get(4)
            ex6reps.text = repArray.get(5)

            }
        }

//        ex1name.text = nameArray.get(0)
//        ex2name.text = nameArray.get(1)
//        ex3name.text = nameArray.get(2)
//        ex4name.text = nameArray.get(3)
//        ex5name.text = nameArray.get(4)
//        ex6name.text = nameArray.get(5)
//        ex1set.text = setArray.get(0)
//        ex2set.text = setArray.get(1)
//        ex3set.text = setArray.get(2)
//        ex4set.text = setArray.get(3)
//        ex5set.text = setArray.get(4)
//        ex6set.text = setArray.get(5)
//        ex1reps.text = repArray.get(0)
//        ex2reps.text = repArray.get(1)
//        ex3reps.text = repArray.get(2)
//        ex4reps.text = repArray.get(3)
//        ex5reps.text = repArray.get(4)
//        ex6reps.text = repArray.get(5)


        homeButton.setOnClickListener {
            val intent:Intent = Intent(this,UserOptions::class.java)
            startActivity(intent)
        }
    }
}