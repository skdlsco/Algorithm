package clear


fun main() {
    val scanner = java.util.Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = Array<Int>(N) { 0 }
    var sum: Int = 0
    var time: Int = 0

    repeat(N) {
        arr[it] = scanner.nextInt()
    }
    arr.sort()
    arr.forEach {
        time += it
        sum += time
    }
    println(sum)
}