package uy.edu.ude.myfirstkotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_random.tvLabel
import kotlinx.android.synthetic.main.activity_random.tvRandom
import java.util.Random

class RandomActivity : AppCompatActivity() {
    // En Kotlin se requiere usar los companion object para declarar objetos estaticos
    // En Java seria algo como
    // static final String TOTAL_COUNT = "total_count";
    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        showRandomNumber()
    }

    fun showRandomNumber() {
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val random = Random()
        var randomInt = 0
        if (count > 0) {
            randomInt = random.nextInt(count + 1)
        }
        tvRandom.text = Integer.toString(randomInt)
        tvLabel.text = getString(R.string.random_heading, count)
    }
}
