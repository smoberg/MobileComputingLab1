package com.example.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false

        //shows and hides the floating buttons for time and map activity
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

        // starts time activity when button is pressed
        fab_time.setOnClickListener{

            startActivity(Intent(applicationContext, TimeActivity::class.java))
        }

        // starts map activity when button is pressed
        fab_map.setOnClickListener{
            startActivity(Intent(applicationContext, MapActivity::class.java))
        }





    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }
    private fun refreshList(){
        doAsync {
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "reminders").build()
            val reminders = db.reminderDao().getReminders()
            db.close()

            uiThread {

                if(reminders.isNotEmpty()){
                    val adapter = ReminderAdapter(applicationContext, reminders)
                    list.adapter = adapter
                }

                else{
                    toast("No reminders yet")
                }

            }


        }
    }
}
