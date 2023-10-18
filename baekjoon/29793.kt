package `29793`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, H) = reader.readLine().split(" ").map { it.toInt() }
    if (N >= 4 && H >= 4) {
        println(-1)
        return
    }
    val arr = reader.readLine()
    if (H >= 4) {
        val s = arr.count { it == 'S' }
        val r = arr.count { it == 'R' }
        val w = arr.count { it == 'W' }
        if (s == 3 || r == 3 || w == 3)
            println(2)
        else if (s == 2 || r == 2 || w == 2)
            println(1)
        else
            println(0)
        return
    }
    if (H == 1) {
        println(0)
        return
    }
    if (H == 2) {
        var prev = ' '
        var cnt = 0
        for (i in arr.indices) {
            val c = arr[i]
            if (c == prev) {
                val set = HashSet<Char>()
                set.add('S')
                set.add('R')
                set.add('W')
                if (i < arr.length - 1)
                    set.remove(arr[i + 1])
                set.remove(prev)
                prev = set.first()
                cnt++
            } else
                prev = c
        }
        println(cnt)
        return
    }
    val case = arrayOf("SRW", "SWR", "WRS", "WSR", "RWS", "RSW")
    var ans = Int.MAX_VALUE
    for (s in case) {
        var sum = 0
        for (i in arr.indices) {
            if (arr[i] != s[i % 3])
                sum++
        }
        ans = min(ans, sum)
    }
    println(ans)
}
    