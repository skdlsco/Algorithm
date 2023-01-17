import java.io.BufferedReader
import java.io.InputStreamReader

fun `15663`(N: Int, M: Int, nums: List<Int>, arr: Array<Int>, m: Int) {
    if (m == M) {
        println(arr.joinToString(" ") { "${nums[it]}" })
        return
    }
    (0 until N).forEach {
        if (arr.slice(0 until m).none { prev -> prev == it } && (arr[m] == -1 || (nums[arr[m]] != nums[it]))) {
            arr[m] = it
            `15663`(N, M, nums, arr, m + 1)
        }
    }
    arr[m] = -1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val nums = reader.readLine().split(" ").map { it.toInt() }.sorted()

    `15663`(N, M, nums, Array(M) { -1 }, 0)
}