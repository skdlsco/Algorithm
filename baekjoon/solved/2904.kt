package `2904`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun getPrimeList(): List<Int> {
    val primeList = ArrayList<Int>()
    val isPrime = Array<Boolean>(1001) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (i in 2 until 1001) {
        if (isPrime[i]) {
            primeList.add(i)
            for (j in i * i until 1001 step i) {
                isPrime[j] = false
            }
        }
    }
    return primeList
}

fun getLeftNumber(leftNumberList: List<Int>): Int {
    var isLeftSameNumbers = true
    val leftNumber = leftNumberList[0]
    for (i in 1 until leftNumberList.size - 1) {
        if (leftNumber != leftNumberList[i])
            isLeftSameNumbers = false
    }
    return if (isLeftSameNumbers)
        leftNumber
    else 1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val primeList = getPrimeList()
    // 종이에 적힌 소수의 개수의 합
    val primeCountSumList = Array<Int>(primeList.size) { 0 }
    // 종이별 소수의 개수(소인수 분해 결과)
    val factorizationList = Array<Array<Int>>(N) { Array(primeList.size) { 0 } }
    // 소인수분해 후 남은 숫자
    val leftNumberList = reader.readLine().split(" ").map { it.toInt() }.mapIndexed { idx, initNumber ->
        var number = initNumber
        for (i in primeList.indices) {
            if (number < primeList[i])
                break
            while (number > 1 && number % primeList[i] == 0) {
                primeCountSumList[i]++
                factorizationList[idx][i]++
                number /= primeList[i]
            }
        }
        return@mapIndexed number
    }

    var cnt = 0
    var number = 1
    for (primeIdx in primeCountSumList.indices) {
        repeat(primeCountSumList[primeIdx] / N) {
            number *= primeList[primeIdx]
        }
    }
    for (idx in 0 until N) {
        for (primeIdx in primeCountSumList.indices) {
            val target = primeCountSumList[primeIdx] / N
            if (target > factorizationList[idx][primeIdx]) {
                cnt += target - factorizationList[idx][primeIdx]
            }
        }
    }
    // 소인수 분해후 최대 공약수 곱하기(모두 1001이상의 소수가 약수였을 경우)
    number *= getLeftNumber(leftNumberList)
    println("$number $cnt")
}