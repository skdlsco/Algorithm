val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun main() {
    val N = reader.readLine().toInt()
    val data = (0 until N).map { reader.readLine().split(" ").map { it.toInt() } }
    val A = data.sortedWith() { o1, o2 ->
        if(o1[0] == o2[0])
            o1[1] - o2[1]
        else
            o2[0] - o1[0]
    }
    val B = data.sortedWith() { o1, o2 ->
        if(o1[1] == o2[1])
            o2[0] - o1[0]
        else
            o1[1] - o2[1]
    }
    writer.write("${A[0][0]} ${A[0][1]} ${A[1][0]} ${A[1][1]}\n")
    writer.write("${B[0][0]} ${B[0][1]} ${B[1][0]} ${B[1][1]}\n")
    writer.flush()
}

