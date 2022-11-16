package com.faircorp.model

data class RoomDto(val roomId: Long,
                   val roomName: String,
                   val floor: Int,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?,
                   val buildingId: Long)