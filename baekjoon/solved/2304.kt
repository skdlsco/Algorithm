package `2304`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

data class Point(val x: Int, val y: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    // arr[x] 일 때 y
    val arr = Array<Int>(1000) { 0 }
    // 현재까지 만들었을 때 높이
    val dp = Array<Int>(1000) { 0 }
    val stack = Stack<Point>()
    repeat(N) {
        val (x, y) = reader.readLine().split(" ").map { it.toInt() }
        arr[x - 1] = y
    }
    dp[0] = arr[0]
    stack.push(Point(0, arr[0]))
    for (i in 1 until 1000) {
        // stack에 자신보다 같거나 큰 값이 나올 때까지 pop
        while (stack.size > 1 && stack.peek().y < arr[i]) {
            stack.pop()
        }
        val prev = stack.peek()
        dp[i] = dp[prev.x] + arr[i]
        if (prev.y > arr[i]) {
            // 큰 경우 -> 내려간다
            dp[i] += (i - prev.x - 1) * arr[i]
        } else {
            // 작은 경우 -> 올라간다.
            dp[i] += prev.y * (i - prev.x - 1)
            stack.pop()
        }
        stack.push(Point(i, arr[i]))
    }
    println(dp.last())
}

//fun main() {
//    val reader = BufferedReader(InputStreamReader(System.`in`))
//    val N = reader.readLine().toInt()
//    val arr = Array<Point>(N) {
//        val (x, y) = reader.readLine().split(" ").map { it.toInt() }
//        Point(x, y)
//    }
//    arr.sortByDescending { it.y }
//    var s = arr[0].x
//    var e = arr[0].x
//    var sum = arr[0].y
//    for (i in 1 until N) {
//        val now = arr[i]
//        if (now.x < s) {
//            sum += (s - now.x) * now.y
//            s = now.x
//        } else if (now.x > e){
//            sum += (now.x - e) * now.y
//            e = now.x
//        }
//    }
//    println(sum)
//}