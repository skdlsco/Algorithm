package clear

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class Student(val name: String = "", val korean: Int = 0, val english: Int = 0, val math: Int = 0) {
    companion object {
        fun studentFromData(str: String) : Student{
            val data = str.split(" ")
            return Student(data[0], data[1].toInt(), data[2].toInt(), data[3].toInt())
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Student>(N) { Student() }
    repeat(N) {
        arr[it] = Student.studentFromData(reader.readLine())
    }
    reader.close()
    arr.sortWith(Comparator { o1, o2 ->
        if (o1.korean == o2.korean)
        {
            if (o1.english == o2.english)
            {
                if (o1.math == o2.math)
                    compareValues(o1.name, o2.name)
                else
                    o2.math - o1.math
            }
            else
                o1.english - o2.english
        }
        else
            o2.korean - o1.korean
    })
    arr.forEach {
        writer.write(it.name + "\n")
    }
    writer.flush()
    writer.close()
}