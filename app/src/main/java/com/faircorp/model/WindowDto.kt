package com.faircorp.model

data class WindowDto(val id: Long,
                     val windowStatus: Status,
                     val name: String,
                     val roomName: String,
                     val roomId: Long)