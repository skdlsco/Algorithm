import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.StringTokenizer

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun lowerBound(arr: ArrayList<Long>, target: Long): Int {
    var l = 0
    var r = arr.size
    while (l < r) {
        val m = (l + r) / 2
        if (arr[m] < target) {
            l = m + 1
        } else {
            r = m
        }
    }
    return l
}

fun main() {
    val (N, Q) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<ArrayList<Long>>(7) { ArrayList() }

    repeat(N) {
        val st = StringTokenizer(reader.readLine(), "- :#")
        LocalDateTime.of(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt()).let {
            arr[st.nextToken().toInt()].add(it.toEpochSecond(ZoneOffset.UTC))
        }
    }
    repeat(Q) {
        val st = StringTokenizer(reader.readLine(), "- :#")
        val start = LocalDateTime.of(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt()).toEpochSecond(ZoneOffset.UTC)
        val end = LocalDateTime.of(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt()).toEpochSecond(ZoneOffset.UTC)
        val level = st.nextToken().toInt()

        val ans = (level..6).sumOf {  lowerBound(arr[it], end + 1) - lowerBound(arr[it], start) }
        writer.write("${ans}\n")
    }
    writer.flush()
}