import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun back(arr: Array<String>, selected: Array<Int>, result: HashSet<String>, depth: Int) {
    if (depth == selected.size) {
        result.add(selected.map { arr[it] }.joinToString(""))
        return
    }
    for (i in 0 until arr.size) {
        if ((0 until depth).none { selected[it] == i }) {
            selected[depth] = i
            back(arr, selected, result, depth + 1)
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val K = reader.readLine().toInt()
    val arr = Array<String>(N) { reader.readLine() }
    val selected = Array<Int>(K) { 0 }
    val result = HashSet<String>()
    back(arr, selected, result, 0)
    writer.write("${result.size}\n")
    writer.flush()
}