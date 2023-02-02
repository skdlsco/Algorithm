package `2581`

import java.util.*
import kotlin.math.min
import kotlin.math.sqrt

fun Int.isPrime() : Boolean {
    (2..sqrt(this.toDouble()).toInt()).forEach {
      if (this % it == 0)
          return false
    }
    return this != 1
}

fun main() {
    val scanner = Scanner(System.`in`)
    val M = scanner.nextInt()
    val N = scanner.nextInt()

    var minPrime = Int.MAX_VALUE
    var sum = 0
    (M..N).forEach {
        if (it.isPrime()) {
            sum += it
            minPrime = min(minPrime, it)
        }
    }
    if (sum == 0)
        println(-1)
    else {
        println(sum)
        println(minPrime)
    }
}