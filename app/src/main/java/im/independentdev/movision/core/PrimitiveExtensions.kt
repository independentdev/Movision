package im.independentdev.movision.core

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Int?.orZero(): Int = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Float?.orZero(): Float = this ?: 0.0F
fun String?.orEmpty(): String = this ?: ""
fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true


fun String?.simpleDateConvert(newFormat: String): String? {
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