package com.example.uas_pemrogamanseluler_42430024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_pemrogamanseluler_42430024.adapter.DestinationAdapter
import com.example.uas_pemrogamanseluler_42430024.model.Destination

class MainActivity : AppCompatActivity() {

    private val TAG = "42430024"

    private lateinit var rvDestinations: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var btnSortAZ: Button
    private lateinit var btnSortZA: Button
    private lateinit var tvStatus: TextView
    private lateinit var adapter: DestinationAdapter

    private val allDestinations = arrayOf(
        Destination(1, "Pulau Komodo", "Cagar Alam", "6-8 jam", "Sedang",
            "Rp 150.000-300.000", 4.9f, "Apr-Agustus",
            "Rumah asli komodo, hewan purba yang hanya ada di sini. Trekking menyusuri pulau dengan pemandu ranger bersenjata untuk menyaksikan kadal terbesar di dunia di habitat aslinya.", "🦎"),
        Destination(2, "Pink Beach", "Pantai Eksotis", "3-4 jam", "Mudah",
            "Rp 50.000-100.000", 4.8f, "Mei-September",
            "Salah satu dari sedikit pantai berpasir merah muda di dunia. Warna unik berasal dari campuran koral merah Foraminifera. Snorkeling di sini sangat luar biasa.", "🌸"),
        Destination(3, "Pulau Padar", "Panorama", "4-5 jam", "Berat",
            "Rp 150.000-200.000", 4.9f, "Jul-September",
            "Spot foto ikonik Labuan Bajo. Pendakian 30-45 menit menuju puncak bukit dengan pemandangan tiga teluk berwarna berbeda. Wajib datang saat matahari terbit.", "🏔️"),
        Destination(4, "Pulau Kanawa", "Menyelam & Snorkeling", "Seharian", "Mudah",
            "Rp 100.000-250.000", 4.7f, "April-Oktober",
            "Surga bawah laut dengan terumbu karang yang masih sangat terjaga. Ikan Napoleon, penyu, dan pari manta sering terlihat di sini.", "🐠"),
        Destination(5, "Manta Point", "Menyelam", "5-6 jam", "Menengah",
            "Rp 200.000-400.000", 4.8f, "Oktober-April",
            "Titik terbaik untuk berenang bersama Pari Manta Oseanik berukuran raksasa. Pengalaman yang tidak akan terlupakan seumur hidup.", "🐟"),
        Destination(6, "Pulau Rinca", "Cagar Alam", "4-5 jam", "Sedang",
            "Rp 150.000-250.000", 4.7f, "Apr-Agustus",
            "Alternatif Komodo yang lebih sepi pengunjung. Populasi komodo lebih padat di sekitar Camp Loh Buaya. Trekking melewati sabana keemasan.", "🦎"),
        Destination(7, "Batu Bolong", "Menyelam", "4-5 jam", "Menengah",
            "Rp 150.000-300.000", 4.8f, "April-November",
            "Salah satu spot diving terbaik di dunia. Tebing bawah laut penuh kehidupan: hiu karang, barrakuda, dan ribuan ikan berwarna-warni.", "🪸"),
        Destination(8, "Pulau Bidadari", "Snorkeling & Relaksasi", "2-3 jam", "Mudah",
            "Rp 50.000-150.000", 4.5f, "Sepanjang tahun",
            "Pulau terdekat dari Labuan Bajo, cocok untuk day trip santai. Air biru tosca jernih dan pasir putih halus. Populer untuk snorkeling pemula.", "🏝️"),
        Destination(9, "Pulau Kelor", "Snorkeling & Fotografi", "2-3 jam", "Mudah",
            "Rp 50.000-100.000", 4.6f, "Sepanjang tahun",
            "Pulau kecil berbentuk segitiga dengan bukit hijau yang bisa didaki 10 menit. Pemandangan sangat fotogenik. Perairan sekitarnya rumah bagi penyu.", "🌿"),
        Destination(10, "Taman Nasional Komodo", "Ekosistem Lengkap", "Seharian", "Sedang",
            "Rp 150.000-400.000", 4.9f, "Apr-September",
            "Situs Warisan Dunia UNESCO mencakup seluruh gugusan kepulauan. Biodiversitas luar biasa dengan 1000+ spesies ikan dan 260 spesies koral.", "🌏")
    )

    private var displayList = allDestinations.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "MainActivity onCreate - Aplikasi dimulai")
        initViews()
        setupRecyclerView()
        setupListeners()
    }

    private fun initViews() {
        rvDestinations = findViewById(R.id.rvDestinations)
        etSearch       = findViewById(R.id.etSearch)
        btnSearch      = findViewById(R.id.btnSearch)
        btnSortAZ      = findViewById(R.id.btnSortAZ)
        btnSortZA      = findViewById(R.id.btnSortZA)
        tvStatus       = findViewById(R.id.tvStatus)
    }

    private fun setupRecyclerView() {
        adapter = DestinationAdapter(displayList) { destination ->
            Log.d(TAG, "Item diklik: ${destination.name}")
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("DEST_NAME",       destination.name)
                putExtra("DEST_CATEGORY",   destination.category)
                putExtra("DEST_DURATION",   destination.duration)
                putExtra("DEST_DIFFICULTY", destination.difficulty)
                putExtra("DEST_PRICE",      destination.priceRange)
                putExtra("DEST_RATING",     destination.rating)
                putExtra("DEST_BESTTIME",   destination.bestTime)
                putExtra("DEST_DESC",       destination.description)
                putExtra("DEST_EMOJI",      destination.emoji)
            }
            startActivity(intent)
        }
        rvDestinations.layoutManager = LinearLayoutManager(this)
        rvDestinations.adapter = adapter
        updateStatus("Menampilkan semua ${displayList.size} destinasi")
    }

    private fun setupListeners() {
        btnSearch.setOnClickListener {
            try {
                val keyword = etSearch.text.toString().trim()
                if (keyword.isEmpty()) {
                    Toast.makeText(this, "Masukkan kata kunci pencarian!", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Pencarian gagal: keyword kosong")
                    return@setOnClickListener
                }
                if (keyword.length < 2) {
                    Toast.makeText(this, "Minimal 2 karakter!", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Pencarian gagal: keyword terlalu pendek")
                    return@setOnClickListener
                }
                Log.i(TAG, "Memulai pencarian: $keyword")
                val result = linearSearch(keyword)
                if (result.isEmpty()) {
                    Toast.makeText(this, "'$keyword' tidak ditemukan", Toast.LENGTH_SHORT).show()
                    updateStatus("Tidak ada hasil untuk '$keyword'")
                    Log.w(TAG, "Hasil pencarian kosong: $keyword")
                } else {
                    displayList = result.toMutableList()
                    adapter.updateData(displayList)
                    updateStatus("Ditemukan ${result.size} hasil untuk '$keyword'")
                    Log.i(TAG, "Pencarian selesai: ${result.size} hasil")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error saat pencarian: ${e.message}", e)
                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }

        btnSortAZ.setOnClickListener {
            try {
                if (displayList.isEmpty()) {
                    Toast.makeText(this, "Tidak ada data untuk diurutkan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Log.i(TAG, "Memulai Bubble Sort A-Z")
                bubbleSort(displayList, ascending = true)
                adapter.updateData(displayList)
                updateStatus("Diurutkan A-Z (${displayList.size} destinasi)")
                Log.i(TAG, "Bubble Sort A-Z selesai")
            } catch (e: Exception) {
                Log.e(TAG, "Error saat sorting A-Z: ${e.message}", e)
                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }

        btnSortZA.setOnClickListener {
            try {
                if (displayList.isEmpty()) {
                    Toast.makeText(this, "Tidak ada data untuk diurutkan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Log.i(TAG, "Memulai Bubble Sort Z-A")
                bubbleSort(displayList, ascending = false)
                adapter.updateData(displayList)
                updateStatus("Diurutkan Z-A (${displayList.size} destinasi)")
                Log.i(TAG, "Bubble Sort Z-A selesai")
            } catch (e: Exception) {
                Log.e(TAG, "Error saat sorting Z-A: ${e.message}", e)
                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun linearSearch(keyword: String): List<Destination> {
        val result = mutableListOf<Destination>()
        val key = keyword.lowercase()
        for (i in allDestinations.indices) {
            val d = allDestinations[i]
            if (d.name.lowercase().contains(key) ||
                d.category.lowercase().contains(key) ||
                d.difficulty.lowercase().contains(key)) {
                result.add(d)
                Log.d(TAG, "LinearSearch cocok index $i: ${d.name}")
            }
        }
        return result
    }

    private fun bubbleSort(list: MutableList<Destination>, ascending: Boolean) {
        val n = list.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                val swap = if (ascending) list[j].name > list[j+1].name
                else list[j].name < list[j+1].name
                if (swap) {
                    val tmp = list[j]; list[j] = list[j+1]; list[j+1] = tmp
                }
            }
        }
    }

    private fun updateStatus(msg: String) { tvStatus.text = msg }

    override fun onResume() {
        super.onResume()
        displayList = allDestinations.toMutableList()
        adapter.updateData(displayList)
        etSearch.setText("")
        updateStatus("Menampilkan semua ${displayList.size} destinasi")
        Log.i(TAG, "MainActivity onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "MainActivity onDestroy")
    }

    // Menyelesaikan fitur array, search, dan sort untuk Minggu 3 //
}
