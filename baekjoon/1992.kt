import java.util.*


fun quadTree(arr: Array<Array<Int>>, len: Int, startX: Int, startY: Int): String {
    if (len == 1)
        return arr[startY][startX].toString()
    val nextLen = len / 2
    var result = quadTree(arr, nextLen, startX, startY) +
            quadTree(arr, nextLen, startX + nextLen, startY) +
            quadTree(arr, nextLen, startX, startY + nextLen) +
            quadTree(arr, nextLen, startX + nextLen, startY + nextLen)
    if (result.length == 4 && result.all { it == '0' } || result.all { it == '1' })
        return result[0].toString()
    return "($result)"
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextLine().toInt()
    val arr = Array<Array<Int>>(N) { scanner.nextLine().map { it.digitToInt() }.toTypedArray() }

    println(quadTree(arr, N, 0, 0))
}
