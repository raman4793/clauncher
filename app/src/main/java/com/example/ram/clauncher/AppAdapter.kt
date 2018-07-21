package com.example.ram.clauncher

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AppAdapter(context: Context) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    private lateinit var apps: MutableList<App>

    init {
        val packageManager = context.packageManager
        apps = arrayListOf()

        val i = Intent(Intent.ACTION_MAIN, null)

        i.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps = packageManager.queryIntentActivities(i,0)
        allApps.forEach {
            val label = it.loadLabel(packageManager).toString()
            val packageName = it.activityInfo.packageName
            val drawable = it.activityInfo.loadIcon(packageManager)

            val app = App(label, packageName, drawable)
            apps.add(app)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_view, parent, false)
        return AppViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.app = app
        holder.textView.text = app.label
        holder.imageView.setImageDrawable(app.icon)
    }

    inner class AppViewHolder(view: View):RecyclerView.ViewHolder(view){
        var imageView = view.findViewById(R.id.imageView) as ImageView
        var textView  = view.findViewById(R.id.textView) as TextView
        var app: App? = null

        init {
            imageView.setOnClickListener {
                if(app!=null){
                    MainActivity.startApp(app!!.packageName, view.context)
                }
            }
        }
    }

}