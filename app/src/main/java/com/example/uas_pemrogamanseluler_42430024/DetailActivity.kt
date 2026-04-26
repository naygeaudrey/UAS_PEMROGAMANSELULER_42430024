package com.example.uas_pemrogamanseluler_42430024

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val TAG = "42430024"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Log.i(TAG, "DetailActivity onCreate")

        try {
            val name       = intent.getStringExtra("DEST_NAME")      ?: "Tidak diketahui"
            val category   = intent.getStringExtra("DEST_CATEGORY")  ?: "-"
            val duration   = intent.getStringExtra("DEST_DURATION")  ?: "-"
            val difficulty = intent.getStringExtra("DEST_DIFFICULTY") ?: "-"
            val price      = intent.getStringExtra("DEST_PRICE")     ?: "-"
            val rating     = intent.getFloatExtra("DEST_RATING", 0f)
            val bestTime   = intent.getStringExtra("DEST_BESTTIME")  ?: "-"
            val desc       = intent.getStringExtra("DEST_DESC")      ?: "-"
            val emoji      = intent.getStringExtra("DEST_EMOJI")     ?: "🏝️"

            Log.d(TAG, "Membuka detail: $name")

            findViewById<TextView>(R.id.tvDetailEmoji).text       = emoji
            findViewById<TextView>(R.id.tvDetailName).text        = name
            findViewById<TextView>(R.id.tvDetailCategory).text    = category
            findViewById<TextView>(R.id.tvDetailDuration).text    = "⏱ Durasi: $duration"
            findViewById<TextView>(R.id.tvDetailDifficulty).text  = "🥾 Kesulitan: $difficulty"
            findViewById<TextView>(R.id.tvDetailPrice).text       = "💰 Estimasi Biaya: $price"
            findViewById<TextView>(R.id.tvDetailRating).text      = "⭐ Rating: $rating / 5.0"
            findViewById<TextView>(R.id.tvDetailBestTime).text    = "📅 Waktu Terbaik: $bestTime"
            findViewById<TextView>(R.id.tvDetailDescription).text = desc

            if (name == "Tidak diketahui") {
                Log.w(TAG, "Detail dibuka tanpa data valid!")
            } else {
                Log.i(TAG, "Detail berhasil ditampilkan: $name")
            }

            findViewById<Button>(R.id.btnBack).setOnClickListener {
                Log.d(TAG, "Kembali dari: $name")
                finish()
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error di DetailActivity: ${e.message}", e)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "DetailActivity onDestroy")
    }
}
