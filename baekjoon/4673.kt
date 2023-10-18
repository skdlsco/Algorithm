package `4673`

fun getSumDigit(num: Int): Int {
    var temp = num
    var sum = 0
    while (temp > 0) {
        sum += temp % 10
        temp /= 10
    }
    return sum
}

fun main() {
    val memo = BooleanArray(10001) { true }
    (1..10000).forEach {
        var num = it
        if (memo[it])
            println(it)
        while (num <= 10000) {
            num += getSumDigit(num)
            if (num > 10000 || !memo[num])
                break
            memo[num] = false
        }
    }
}