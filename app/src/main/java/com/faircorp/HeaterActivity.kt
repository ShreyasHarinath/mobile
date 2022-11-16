package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.Service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeaterActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getLongExtra(HEATER_NAME_PARAM, 0)


        var heaterName : String
        var heaterStatus: String;
        var currentTemperature : String?


        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().heatersApiService.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        val heater = it.body()
                        if (heater != null) {
                            heaterName = heater.name
                            //roomName = heater.roomName
                            heaterStatus = heater.heaterStatus.toString()
                            val idRoom = heater.roomId

                            lifecycleScope.launch(context = Dispatchers.IO) {
                                runCatching { ApiServices().roomsApiService.findById(idRoom).execute() }
                                    .onSuccess {
                                        withContext(context = Dispatchers.Main) {
                                            val room = it.body()
                                            if (room != null) {
                                                currentTemperature = room.currentTemperature?.toString()

                                                findViewById<TextView>(R.id.txt_heater_name).text = heaterName
                                                //findViewById<TextView>(R.id.txt_room_name_h).text = roomName
                                                findViewById<TextView>(R.id.txt_heater_status).text = heaterStatus
                                                findViewById<TextView>(R.id.txt_heater_current_temperature).text = currentTemperature

                                            }
                                        }
                                    }
                                    .onFailure {
                                        withContext(context = Dispatchers.Main) { // (3)
                                            Toast.makeText(
                                                applicationContext,
                                                "Error on rooms loading $it",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            }
                        }

                    }

                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on heater loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }
    }
}