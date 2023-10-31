import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun dfs(N: Int, prefix: String) {
    writer.write(prefix)
    writer.write("\"재귀함수가 뭔가요?\"\n")
    if (N == 0) {
        writer.write(prefix)
        writer.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n")
    } else {
        writer.write(prefix)
        writer.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
        writer.write(prefix)
        writer.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
        writer.write(prefix)
        writer.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n")
        dfs(N - 1, prefix + "____")
    }
    writer.write(prefix)
    writer.write("라고 답변하였지.\n")
}

fun main() {
    val (N) = reader.readLine().split(" ").map { it.toInt() }
    writer.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n")
    dfs(N, "")
    writer.flush()
}
