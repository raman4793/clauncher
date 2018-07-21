package com.example.ram.clauncher

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.ComponentName
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.appsList)

        setup()
    }

    private fun setup(){
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.adapter = AppAdapter(applicationContext)
    }



    companion object {
        fun startApp(appPackage: String, context: Context){
            val intent = context.packageManager.getLaunchIntentForPackage(appPackage)
            context.startActivity(intent)
        }

        fun getIcon(context: Context, appPackage: String, activityName : String):Drawable{
            val pm = context.packageManager
            val intent = Intent()
            intent.component = ComponentName(appPackage, activityName)
            val resolveInfo = pm.resolveActivity(intent, 0)
            return resolveInfo.loadIcon(pm)
        }
    }
}
