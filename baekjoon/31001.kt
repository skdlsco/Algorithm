import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.*

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

// [회사 이름] = 회사 id
val companyMapping = HashMap<String, Int>()

// 회사 주가
val company = Array<Long>(101) { 0 }

// 그룹
val group = Array<ArrayList<Int>>(101) { ArrayList() }

// 소유 주식 수
val own = Array<Long>(101) { 0 }

fun main() {
    val (N, M, Q) = reader.readLine().split(" ").map { it.toLong() }
    var cash = M
    (1..N.toInt()).forEach {
        val input = StringTokenizer(reader.readLine())
        val groupId = input.nextToken().toInt()
        val name = input.nextToken()
        val price = input.nextToken().toLong()
        companyMapping[name] = it
        company[it] = price
        group[groupId].add(it)
    }
    repeat(Q.toInt()) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken().toInt()
        when (command) {
            1 -> {
                val A = companyMapping[input.nextToken()]!!
                val B = input.nextToken().toLong()
                if (company[A] * B <= cash) {
                    cash -= company[A] * B
                    own[A] += B
                }
            }

            2 -> {
                val A = companyMapping[input.nextToken()]!!
                val B = input.nextToken().toLong()
                val sell = min(B, own[A])
                own[A] -= sell
                cash += sell * company[A]
            }

            3 -> {
                val A = companyMapping[input.nextToken()]!!
                val C = input.nextToken().toLong()
                company[A] += C
            }

            4 -> {
                val D = input.nextToken().toInt()
                val C = input.nextToken().toLong()
                group[D].forEach {
                    company[it] += C
                }
            }

            5 -> {
                val D = input.nextToken().toInt()
                val E = input.nextToken().toLong()
                group[D].forEach {
                    val price = company[it] * 100
                    var result = price + company[it] * E
                    result -= result % 1000
                    company[it] = result / 100
                }
            }

            6 -> {
                writer.write("${cash}\n")
            }

            7 -> {
                val total = cash + own.withIndex().sumOf { company[it.index] * it.value }
                writer.write("${total}\n")
            }
        }
    }
    writer.flush()
}
