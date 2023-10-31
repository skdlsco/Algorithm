import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

data class Word(val word: String, var cnt: Int = 0)

fun main() {
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = HashMap<String, Word>()
    repeat(N) {
        val word = reader.readLine()
        if (map.containsKey(word)) {
            map[word]!!.cnt++
        } else {
            map[word] = Word(word, 1)
        }
    }
    val ans = map.filter { it.key.length >= M }.map { it.value }.sortedWith { o1, o2 ->
        if (o1.cnt == o2.cnt) {
            if (o1.word.length == o2.word.length) {
                o1.word.compareTo(o2.word)
            } else {
                o2.word.length.compareTo(o1.word.length)
            }
        } else {
            o2.cnt.compareTo(o1.cnt)
        }
    }.joinToString("\n") { it.word }
    writer.write(ans)
    writer.flush()
}
