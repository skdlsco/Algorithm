package `25206`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val table = HashMap<String, Double>().apply {
    put("A+", 4.5)
    put("A0", 4.0)
    put("B+", 3.5)
    put("B0", 3.0)
    put("C+", 2.5)
    put("C0", 2.0)
    put("D+", 1.5)
    put("D0", 1.0)
    put("F", 0.0)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var totalGrade = 0.0
    var totalCredit = 0.0
    repeat(20) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val name = stringTokenizer.nextToken()
        val credit = stringTokenizer.nextToken().toDouble()
        val grade = stringTokenizer.nextToken()
        if (grade != "P") {
            totalCredit += credit
            totalGrade += table[grade]!! * credit
        }
    }
    writer.write("${totalGrade / totalCredit}\n")
    writer.flush()
}