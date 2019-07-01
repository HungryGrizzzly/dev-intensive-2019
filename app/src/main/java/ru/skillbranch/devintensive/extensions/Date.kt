package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECONDS): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECONDS -> value * SECOND
        TimeUnits.MINUTES -> value * MINUTE
        TimeUnits.HOURS -> value * HOUR
        TimeUnits.DAYS -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(): String{
    val date = Date()
    var timeInterval = (date.time - this.time)
    var result: String
    if(timeInterval >= 0){
        result = when (timeInterval.toInt()){
            in (0* SECOND)..(1* SECOND) -> "только что"
            in (1* SECOND)..(45* SECOND) -> "несколько секунд назад"
            in (45* SECOND)..(75* SECOND) -> "минуту назад"
            in (75* SECOND)..(45 * MINUTE) -> when(timeInterval/ MINUTE % 10L){
                1L -> "${timeInterval / MINUTE} минуту назад"
                2L, 3L, 4L -> "${timeInterval / MINUTE} минуты назад"
                else -> "${timeInterval/ MINUTE} минут назад"
            }
            in (45 * MINUTE)..(75* MINUTE) -> "час назад"
            in (75 * MINUTE)..(22* HOUR) -> when(timeInterval/ HOUR % 10L){
                1L -> "${timeInterval / HOUR} час назад"
                2L, 3L, 4L -> "${timeInterval / HOUR} часа назад"
                else -> "${timeInterval/ HOUR} часов назад"
            }
            in 22* HOUR..26* HOUR -> "день назад"
            in 26* HOUR..360* DAY -> when(timeInterval/ DAY % 10L){
                1L -> "${timeInterval / DAY} день назад"
                2L, 3L, 4L -> "${timeInterval / DAY} дня назад"
                else -> "${timeInterval/ DAY} дней назад"
            }
            else -> "более года назад"
        }
    } else {
        timeInterval = abs(timeInterval)
        result = when (timeInterval.toInt()){
            in (0* SECOND)..(1* SECOND) -> "сейчас"
            in (1* SECOND)..(45* SECOND) -> "через несколько секунд"
            in (45* SECOND)..(75* SECOND) -> "через минуту"
            in (75* SECOND)..(45 * MINUTE) -> when(timeInterval/ MINUTE % 10L){
                1L -> "через ${timeInterval / MINUTE} минуту"
                2L, 3L, 4L -> "через ${timeInterval / MINUTE} минуты"
                else -> "через ${timeInterval/ MINUTE} минут"
            }
            in (45 * MINUTE)..(75* MINUTE) -> "через час"
            in (75 * MINUTE)..(22* HOUR) -> when(timeInterval/ HOUR % 10L){
                1L -> "через ${timeInterval / HOUR} час"
                2L, 3L, 4L -> "через ${timeInterval / HOUR} часа"
                else -> "через ${timeInterval/ HOUR} часов"
            }
            in 22* HOUR..26* HOUR -> "через день"
            in 26* HOUR..360* DAY -> when(timeInterval/ DAY % 10L){
                1L -> "через ${timeInterval / DAY} день"
                2L, 3L, 4L -> "через ${timeInterval / DAY} дня"
                else -> "через ${timeInterval/ DAY} дней"
            }
            else -> "более, чем через год"
        }
    }
    return result
}

enum class TimeUnits{
    SECONDS,
    MINUTES,
    HOURS,
    DAYS
}