import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    var next = true
    var len = 0
    var cnt = 0
    repeat(M) {
        val ch = reader.read().toChar()
        if (next && ch == 'I') {
            next = false
            if (len >= N)
                cnt++
        } else if (!next && ch == 'O') {
            next = true
            len++
        } else {
            next = ch == 'O'
            len = 0
        }
        ch
    }
    println(cnt)
}