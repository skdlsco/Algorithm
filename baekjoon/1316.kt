package clear

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val scanner = Scanner(System.`in`)
    val case = scanner.nextInt()
    var cnt = 0
    repeat(case) {
        val str = scanner.next()
        val map = HashMap<Char, Int>()
        var isGroup = true
        str.forEachIndexed { index, c ->
            if (map.containsKey(c) && map[c]!! + 1 != index)
                isGroup = false
            map[c] = index
        }
        if (isGroup)
            cnt++
    }
    println(cnt)
}