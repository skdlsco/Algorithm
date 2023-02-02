package `2003`

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    val arr = Array<Int>(N) { 0 }

    repeat(N) {
        arr[it] = scanner.nextInt()
    }
    var left = 0
    var right = 0
    var sum = arr[left]
    var cnt = 0
    while (right < N) {
        if (sum == M)
            cnt++
        if (sum < M) {
            right++
            sum += if (right < N) arr[right] else 0
        } else {
            sum -= arr[left]
            left++
        }
    }
    println(cnt)
}