package com.faircorp.model

data class HeaterDto(val id: Long,
                     val name: String,
                     val power: Long,
                     val heaterStatus: HeaterStatus,
                     val roomId: Long)