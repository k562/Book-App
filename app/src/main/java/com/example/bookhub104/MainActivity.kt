package com.example.bookhub104

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousmenuitem : MenuItem ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        coordinatorLayout = findViewById(R.id.coordinatorlayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.framelayout)
        navigationView = findViewById(R.id.navigationview)

        setuptoolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity
            , drawerLayout
            , R.string.Open_drawer
            ,R.string.Close_drawer)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        opendashboard()
        navigationView.setNavigationItemSelectedListener {

            if (previousmenuitem != null) {
                previousmenuitem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousmenuitem = it

            when(it.itemId){


                R.id.dashboard -> {
                opendashboard()
                 drawerLayout.closeDrawers()

                }
                 R.id.favourite ->{
                     supportFragmentManager.beginTransaction().replace(R.id.framelayout, Favourite_Fragment())
                         .commit()

                     supportActionBar?.title ="Favourite"
                     drawerLayout.closeDrawers()

                 }
                R.id.profile ->{

                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, Profile_Fragment())
                        .commit()

                    supportActionBar?.title ="Profile"
                    drawerLayout.closeDrawers()

                }

                R.id.aboutme -> {


                    supportFragmentManager.beginTransaction().replace(R.id.framelayout, Aboutme_Fragment())

                        .commit()

                    supportActionBar?.title ="Aboutme"
                    drawerLayout.closeDrawers()

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    fun setuptoolbar(){

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }


    fun opendashboard(){

        val fragment = Dashboard_Fragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()
        supportActionBar?.title ="Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
    }


    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.framelayout)

        when(frag){

            !is Dashboard_Fragment -> opendashboard()

            else -> super.onBackPressed()

        }
    }

}