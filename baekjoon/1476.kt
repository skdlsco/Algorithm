package `1476`

fun main() {
    val E_MAX = 15
    val S_MAX = 28
    val M_MAX = 19
    val (E, S, M) = readLine()!!.split(" ").map { it.toInt() - 1 }
    var year = 1
    while (true) {
        if (year % E_MAX == E && year % S_MAX == S && year % M_MAX == M) {
            if (year == 7980)
                println(1)
            else
                println(year + 1)
            break
        }
        year++
    }
}