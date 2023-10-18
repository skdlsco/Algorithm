import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val tokenizer = StringTokenizer(reader.readLine())
    val N = BigInteger(tokenizer.nextToken())
    val M = BigInteger(tokenizer.nextToken())

    println(N / M)
    println(N % M)
}