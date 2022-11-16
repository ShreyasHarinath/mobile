package com.faircorp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.faircorp.Service.ApiServices
import androidx.lifecycle.lifecycleScope
import com.faircorp.adapter.HeaterAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeatersActivity : BasicActivity() , OnHeaterSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heaters)
        val recyclerView = findViewById<RecyclerView>(R.id.list_heaters)
        val adapter = HeaterAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
            runCatching { ApiServices().heatersApiService.findAll().execute() } // (2)
                .onSuccess {
                    withContext(context = Dispatchers.Main) { // (3)
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on heaters loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


    }

    override fun onHeaterSelected(id: Long) {
        val intent = Intent(this, HeaterActivity::class.java).putExtra(HEATER_NAME_PARAM, id)
        startActivity(intent)
    }
}