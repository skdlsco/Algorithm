import java.lang.Integer.min
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

fun getClose(btns: Array<Int>, targetLen: Int, target: Int, now: Int, nowLen: Int): Int {
    val cnt = abs(now - target) + nowLen
    if (nowLen > targetLen)
        return cnt
    return min(cnt, btns.minOf {
        if (now == 0 && it == 0)
            return@minOf Int.MAX_VALUE
        getClose(btns, targetLen, target, now * 10 + it, nowLen + 1)
    })
}

fun main() {
    val scanner = Scanner(System.`in`)
    val target = scanner.nextInt()
    val brokenCtn = scanner.nextInt()
    val buttons = ArrayList<Int>(List<Int>(10) { it })
    repeat(brokenCtn) {
        buttons.remove(scanner.nextInt())
    }

    var cnt = abs(100 - target)
    if (buttons.isNotEmpty())
        cnt = min(cnt, buttons.minOf { getClose(buttons.toTypedArray(), target.toString().length, target, it, 1) })
    println(cnt)
}