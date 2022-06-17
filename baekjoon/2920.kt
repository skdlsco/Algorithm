
import java.util.*
import kotlin.math.abs

fun main() {
    val scanner = Scanner(System.`in`)

    val arr = scanner.nextLine().split(" ").map { it.toInt() }
    var isMixed = false

    (0 until arr.size - 1).forEach {
        if(abs(arr[it] - arr[it + 1]) != 1) {
            isMixed = true
            return@forEach
        }
    }
    when {
        isMixed -> println("mixed")
        arr[0] == 8 -> println("descending")
        else -> println("ascending")
    }
}