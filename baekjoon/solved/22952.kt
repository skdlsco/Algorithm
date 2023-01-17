package `22952`


fun main() {
    val N = readLine()!!.toInt()
    repeat((N - 1) / 2) {
        print("${it + 1} ${N - it - 1} ")
    }
    if ((N - 1) % 2 == 1) {
        print("${N / 2} ")
    }
    println("${N}")
}