package com.example.dashbordminiproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavViewDrawer()
        setupRecyclerView()
    }

    fun startRegisterActivity(view: View){
            startActivity(Intent(this , RegisterActivityNew::class.java))
    }

    private fun setupNavViewDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        this.toggle = toggle
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            Toast.makeText(
                applicationContext,
                "jjjjjjjjj",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("TAG", "setupNavViewDrawer: ")
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun setupRecyclerView() {
        recyclerView1.apply {
            adapter = MyRecyclerAdapter(this@MainActivity)
            layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        recyclerView3.apply {
            adapter = MyRecyclerViewAdapterGrid(this@MainActivity)
            layoutManager = GridLayoutManager(
                applicationContext,
                3
            )
        }

        recyclerView2.apply {
            adapter = MyRecyclerAdapter(this@MainActivity)
            layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    fun openDrawer(view: View) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.openDrawer(GravityCompat.START)
    }
}