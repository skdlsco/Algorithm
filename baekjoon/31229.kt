package C

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()


fun main() {
    val N = reader.readLine().toInt()
    val seive = Array<Boolean>(1000001) { true }
    val primes = ArrayList<Int>()
    for (i in 2 until 1000001) {
        if (seive[i]) {
            primes.add(i)
            for (j in i + i until 1000001 step i) {
                seive[j] = false
            }
        }
    }
    repeat(N) {
        writer.write("${primes[it]} ")
    }
    writer.flush()
}
    