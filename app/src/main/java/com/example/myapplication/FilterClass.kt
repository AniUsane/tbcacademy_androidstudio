package com.example.myapplication

import java.util.UUID

enum class FilterStatus{
    Pending, Delivered, Cancelled
}

data class FilterClass(val id: UUID, val filterStatus:FilterStatus)
