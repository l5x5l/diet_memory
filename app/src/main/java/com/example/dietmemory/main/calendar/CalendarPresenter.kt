package com.example.dietmemory.main.calendar

class CalendarPresenter : CalendarContract.Presenter {

    private var calendarView : CalendarContract.View ?= null

    // 특정 날에 대한 음식 섭취 요약 데이터 요구 및 UI 갱신
    override fun tryGetDateData(year: Int, month: Int, day: Int) {
        // 임시로 해당 날을 그대로 toast 메세지로 출력
        // 실제로는 해당 날을 가지고 섭취한 음식 데이터를 요청해 그 응답을 받아 다른 view 에 보여줘야 한다.
        calendarView!!.showDateData(year, month, day)
    }

    override fun tryGetMonthData(year: Int, month: Int) {
        // 원래 서버로부터 통신하여 가져와야 한다.
        val tempDataList = arrayListOf(0,0,0,1,1,1,2,0,1,2,0,2,1,1)
        calendarView!!.applyMonthData(year, month, tempDataList)
    }

    override fun takeView(inputView: CalendarContract.View) {
        calendarView = inputView
    }

    override fun dropView() {
        calendarView = null
    }

    /*// 해당 달에 대한 데이터 적용
    fun getCalendar(){
        val tempDataList = arrayListOf(0,0,0,1,1,1,2,0,1,2,0,2,1,1)
        calendarView!!.applyMonthData(tempDataList)
    }*/
}