package uy.edu.ude.myfirstkotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View) {
        val myToast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe(view: View) {
        //val showCountTextView = findViewById<TextView>(R.id.count_tv)
        val countString = tvCount.text.toString()
        val count = Integer.parseInt(countString) + 1
        tvCount.text = count.toString();
    }

    fun randomMe(view: View) {
        val randomIntent = Intent(this, RandomActivity::class.java)
        val count = Integer.valueOf(tvCount.text.toString())
        randomIntent.putExtra(RandomActivity.TOTAL_COUNT, count)
        startActivity(randomIntent)
    }
}
