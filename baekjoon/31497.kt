val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

fun query(name: String) {
    writer.write("? $name\n")
    writer.flush()
}

fun main() {
    val N = reader.readLine().toInt()
    val nameList = (0 until N).map { reader.readLine() }
    for (name in nameList) {
        query(name)
        var a = reader.readLine().toInt()
        query(name)
        var b = reader.readLine().toInt()
        if (a != b) {
            writer.write("! $name\n")
            break
        }
    }
    writer.flush()
}