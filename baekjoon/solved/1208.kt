package `1208`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun generateSubArr(arr: List<Int>): List<Int> {
    val subArr = ArrayList<Int>()
    for (bit in 1 until (1 shl arr.size)) {
        var sum = 0
        for (i in arr.indices) {
            if (bit and (1 shl i) > 0)
                sum += arr[i]
        }
        subArr.add(sum)
    }
    return subArr.sorted()
}

fun upperBound(target: Int, list: List<Int>): Int {
    var left = 0
    var right = list.lastIndex + 1
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] <= target)
            left = mid + 1
        else
            right = mid
    }
    return right
}

fun lowerBound(target: Int, list: List<Int>): Int {
    var left = 0
    var right = list.lastIndex + 1
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] < target)
            left = mid + 1
        else
            right = mid
    }
    return right
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, S) = reader.readLine().split(" ").map { it.toInt() }
    val originArr = reader.readLine().split(" ").map { it.toInt() }
    // arr 반반 자르기
    // 각 arr 모든 부분수열 생성
    val A = generateSubArr(originArr.slice(0 until N / 2))
    val B = generateSubArr(originArr.slice(N / 2 until N))
    var cnt = A.sumOf {
        val target = S - it
        val upper = upperBound(target, B)
        val lower = lowerBound(target, B)
        (upper - lower).toLong()
    }
    cnt += A.count { it == S }
    cnt += B.count { it == S }
    writer.write("${cnt}\n")
    writer.flush()
}