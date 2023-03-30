package im.independentdev.movision.core

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Convert nullable int to unnullable by returning 0 instead of null
 * */
fun Int?.orZero(): Int = this ?: 0

/**
 * Convert nullable Double to unnullable by returning 0 instead of null
 * */
fun Double?.orZero(): Double = this ?: 0.0

/**
 * Convert nullable Float to unnullable by returning 0 instead of null
 * */
fun Float?.orZero(): Float = this ?: 0.0F

/**
 * Convert nullable Boolean to unnullable by returning false instead of null
 * */
fun Boolean?.orFalse(): Boolean = this ?: false


/**
 * Format Date String as given format and return formatted String or
 * empty string for unexpected inputs and exceptions
 * */
fun String?.simpleDateConvert(newFormat: String): String {
	if (this.isNullOrEmpty()) return ""
	val dateFormat = SimpleDateFormat("yyyy-dd-MM", Locale.ROOT)
	
	val myDate: Date?
	try {
		myDate = dateFormat.parse(this)
	} catch (e: ParseException) {
		return ""
	}
	
	val timeFormat = SimpleDateFormat(newFormat, Locale.ROOT)
	return timeFormat.format(myDate ?: Date())
}