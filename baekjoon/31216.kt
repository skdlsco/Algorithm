val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val sieve = Array<Boolean>(318138) { true }
    val primes = ArrayList<Int>()
    sieve[1] = false
    for (i in 2..318137) {
        if (sieve[i]) {
            primes.add(i)
            for (j in i + i..318137 step i) {
                sieve[j] = false
            }
        }
    }
    val superPrimes = ArrayList<Int>()
    primes.forEachIndexed { idx, p ->
        if (sieve[idx + 1])
            superPrimes.add(p)
    }
    repeat(reader.readLine().toInt()) {
        val n = reader.readLine().toInt()
        writer.write("${superPrimes[n - 1]}\n")
    }
    writer.flush()
}
    