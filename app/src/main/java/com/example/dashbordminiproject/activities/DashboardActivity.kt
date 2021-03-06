package com.example.dashbordminiproject.activities

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
import com.example.dashbordminiproject.R
import com.example.dashbordminiproject.recyclerViews.MyRecyclerAdapter
import com.example.dashbordminiproject.recyclerViews.MyRecyclerViewAdapterGrid
import kotlinx.android.synthetic.main.dashboard.*

class DashboardActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
        setupNavViewDrawer()
        setupRecyclerView()
    }

    fun startRegisterActivity(view: View){
        //Being called from item_for_recyclerview_tasks.xml
            startActivity(Intent(this , PhoneVerificationActivity::class.java))
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left )
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
            adapter = MyRecyclerAdapter(this@DashboardActivity)
            layoutManager = LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        recyclerView3.apply {
            adapter = MyRecyclerViewAdapterGrid(this@DashboardActivity)
            layoutManager = GridLayoutManager(
                applicationContext,
                3
            )
        }

        recyclerView2.apply {
            adapter = MyRecyclerAdapter(this@DashboardActivity)
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