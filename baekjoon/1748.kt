package `1748`

fun main() {
    val N = readLine()!!.toInt()
    var a = 10
    var i = 1
    println((1..N).sumOf {
        if (it >= a) {
            a *= 10
            i++
        }
        i
    })
}