package com.example.dietmemory.main.calendar.models

data class ResponseDaySummary(val isSuccess : Boolean, val message : String, val endata : GoalData, val data : IntakeData?, val food : ArrayList<CalendarFood>)
