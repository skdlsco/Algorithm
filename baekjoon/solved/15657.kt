package `15657`
import java.io.BufferedReader
import java.io.InputStreamReader

fun `15657`(N: Int, M: Int, nums: List<Int>, arr: Array<Int>, n: Int, m: Int) {
    if (m == M) {
        println(arr.joinToString(" ") { "${nums[it]}" })
        return
    }
    (n until N).forEach {
        arr[m] = it
        `15657`(N, M, nums, arr, it, m + 1)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val nums = reader.readLine().split(" ").map { it.toInt() }.sorted()

    `15657`(N, M, nums, Array(M) { 0 }, 0, 0)
}