fun main() {
    val T = readLine()!!.toInt()
    repeat(T) {
        val line = readLine()!!
        println("${line.first()}${line.last()}")
    }
}