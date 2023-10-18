package `1456`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (A, B) = reader.readLine().split(" ").map { it.toLong() }
    val prime = Array<Boolean>(10000001) { true }
    var cnt = 0
    prime[0] = false
    prime[1] = false
    for (i in 2 until 10001) {
        if (prime[i]) {
            for (j in (i * i) until 10000001 step i) {
                prime[j] = false
            }
        }
    }
    for (i in prime.indices) {
        if (prime[i]) {
            var j: Long = i.toLong()
            // j * i <= B -> j * i에서 overflow 발생
            // j <= B / i로 overflow가 안나도록 확인한다..
            while (j <= B / i) {
                j *= i
                if (j >= A)
                    cnt++
            }
        }
    }
    println(cnt)
}