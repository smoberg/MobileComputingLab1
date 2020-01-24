package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false

        fab.setOnClickListener{

            if (!fabOpened){

                fabOpened = true

                fab_map.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                fab_time.animate().translationY(-resources.getDimension(R.dimen.standard_116))

            } else{

                fabOpened = false

                fab_map.animate().translationY(0f)
                fab_time.animate().translationY(0f)

            }
        }

        fab_time.setOnClickListener{

            startActivity(Intent(applicationContext, TimeActivity::class.java))
        }

        fab_map.setOnClickListener{
            startActivity(Intent(applicationContext, MapActivity::class.java))
        }
    }
}
