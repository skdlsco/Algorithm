import java.io.BufferedReader
import java.io.InputStreamReader



fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    var i = 1
    while (i * i <= N) {
        i++
    }
    println(i - 1)
}