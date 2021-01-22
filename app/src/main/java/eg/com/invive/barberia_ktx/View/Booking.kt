package eg.com.invive.barberia_ktx.View

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import eg.com.invive.barberia_ktx.R
import eg.com.invive.barberia_ktx.Utils.daysOfWeekFromLocale
import kotlinx.android.synthetic.main.calender_view.*
import kotlinx.android.synthetic.main.fragment_booking.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class Booking : BaseFragment(R.layout.fragment_booking) {


    private val today = LocalDate.now()
    private var selectedDate = today
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val currentMonth: YearMonth = YearMonth.now()
    private val endMonth: YearMonth = currentMonth.plusMonths(1)
    private var lastPickDay: LocalDate? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalender()
        add_service.setOnClickListener { navigate(R.id.action_booking2_to_bookingServiceDialog) }

    }


    private fun initCalender(){

        class DayViewContainer(val viewLayout: View,var calenderDay: CalendarDay?): ViewContainer(viewLayout){
            val calendarDayText = viewLayout.findViewById<TextView?>(R.id.dayText)
            init {
                viewLayout.setOnClickListener {
                    calenderDay?.let {
                        if (it.date.isAfter(today) || it.date.isEqual(today)) {
                            if (!selectedDate.isEqual(it.date)) {
                                lastPickDay = selectedDate
                                selectedDate = it.date
                                calenderView.notifyDateChanged(lastPickDay!!)
                                calenderView.notifyDateChanged(it.date)
                            }
                        }
                    }
                }
            }

        }

        val dayOfWeek= daysOfWeekFromLocale()
        calenderView.apply {
            setup(currentMonth, endMonth, dayOfWeek[dayOfWeek.size - 1])
            scrollToDate(selectedDate)

            dayBinder = object : DayBinder<DayViewContainer> {
                    override fun create(view: View): DayViewContainer = DayViewContainer(view,null)

                    override fun bind(dayViewContainer:DayViewContainer, calendarDay: CalendarDay) {
                        dayViewContainer.calenderDay=calendarDay
                        dayViewContainer.calendarDayText!!.text= calendarDay.date.dayOfMonth.toString()

                        //Log.e("selectDay", selectedDate.toString())
                        if (calendarDay.date.isAfter(today) || calendarDay.date.isEqual(today)) {
                           // Log.e("day", calendarDay.date.toString())
                            if (selectedDate.isEqual(calendarDay.date)) {
                               // Log.e("select", calendarDay.date.toString())
                                dayViewContainer.calendarDayText!!.setTextColor(Color.WHITE)
                                dayViewContainer.calendarDayText!!.background = resources.getDrawable(R.drawable.selected_bg,null)
                                dayViewContainer.calendarDayText!!.isSelected = true
                            } else {
                                dayViewContainer.calendarDayText!!.setTextColor(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  resources.getColor(R.color.dark_blue,null)  else resources.getColor(R.color.dark_blue))
                                dayViewContainer.calendarDayText!!.background = resources.getDrawable(R.drawable.not_selected_bg,null)
                                dayViewContainer.calendarDayText!!.isSelected = false
                            }
                        } else {
                            dayViewContainer.calendarDayText!!.setTextColor(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  resources.getColor(R.color.dark_grey,null)  else resources.getColor(R.color.dark_grey))
                            dayViewContainer.calendarDayText!!.background = null
                            dayViewContainer.calendarDayText!!.isSelected = false
                        }
                    }
                }


            monthScrollListener = { (_, weekDays) ->
                    val first = weekDays[0][0].date
                    val last = weekDays[weekDays.size - 1][weekDays[weekDays.size - 1].size - 1].date
                    if (first.month == last.month) {
                        cal_month.text = monthTitleFormatter.format(first) + " " + first.year.toString()
                    } else {
                        cal_month.text = monthTitleFormatter.format(first) + " " + first.year
                            .toString() + "-" + monthTitleFormatter.format(last) + " " + last.year
                            .toString()
                    }
                }


        }
    }




}