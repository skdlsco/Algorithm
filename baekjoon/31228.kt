package B

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun main() {
    var (N, K) = reader.readLine().split(" ").map { it.toLong() }
    if (N - K < K)
        K = N - K
    val temp = gcd(N, K)
    N /= temp
    K /= temp
    writer.write("${(K - 1) * N}")
    writer.flush()
}
    