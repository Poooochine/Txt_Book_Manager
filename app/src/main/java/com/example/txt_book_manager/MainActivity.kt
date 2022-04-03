package com.example.txt_book_manager
//mainactivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
//import android.view.Menu
//import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    internal var helper = DatabaseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            var intent = Intent(this, Add::class.java)
            intent.putExtra("ADD", true)
            startActivity(intent)
        }

        var btnView: Button = findViewById(R.id.btnView)
        btnView.setOnClickListener {
            var intent = Intent(this, book_listing::class.java)
            startActivity(intent)
        }

    }
    }






