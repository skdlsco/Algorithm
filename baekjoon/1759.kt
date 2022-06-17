package clear

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun result(arr: List<Char>, password: Array<Char>, max: Int, idx: Int, arrIdx: Int) {
    if (idx == max) {
        val con: Int = password.count { it !in "aeiou" }
        val vow: Int = password.count { it in "aeiou" }
        if (con >= 2 && vow >= 1) {
            println(password.joinToString(""))
        }
        return
    }
    (arrIdx until arr.size).forEach {
        password[idx] = arr[it]
        result(arr, password, max, idx + 1, it + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val L = stringTokenizer.nextToken().toInt()
    val C = stringTokenizer.nextToken().toInt()
    val arr = reader.readLine().split(" ").map { it[0] }.sorted()
    val password = Array<Char>(L) { ' ' }

    result(arr, password, L, 0, 0)
}