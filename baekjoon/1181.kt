package clear

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val arr = Array<String>(N) { "" }

    repeat(N) {
        arr[it] = scanner.next()
    }
    arr.sortWith(Comparator { o1, o2 ->
        if (o1.length == o2.length)
            o1.compareTo(o2)
        else
            o1.length - o2.length
    })
    println(arr[0])
    (1 until N).forEach {
        if (arr[it] != arr[it - 1])
            println(arr[it])
    }
}
