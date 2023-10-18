package `2941`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val str = scanner.next()
    var idx = 0
    var cnt = 0
    val croa = arrayOf("c=", "c-", "d-", "lj", "nj", "s=", "z=")

    while (idx < str.length) {
        if (idx < str.length - 1 && str.substring(idx, idx + 2) in croa) {
            idx++
        } else if (idx < str.length - 2 && str.substring(idx, idx + 3) == "dz=") {
            idx += 2
        }
        cnt++
        idx++
    }
    println(cnt)
}