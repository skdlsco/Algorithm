package `C`

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

val dy = arrayOf(-1, 0, 1, 0)
val dx = arrayOf(0, 1, 0, -1)

fun main() {
    val (H, W) = reader.readLine().trim().split(" ").map { it.toInt() }
    var (r, c, d) = reader.readLine().trim().split(" ").map { it.toInt() }

    var cleanCnt = 0
    var moveCnt = 0
    var ans = 0
    val ruleA = Array<Array<Int>>(H) { Array(W) { 0 } }
    val ruleB = Array<Array<Int>>(H) { Array(W) { 0 } }
    val isClean = Array<Array<Boolean>>(H) { Array(W) { false } }
    for (y in 0 until H) {
        val row = reader.readLine().trim()
        for (x in 0 until W) {
            ruleA[y][x] = row[x].digitToInt()
        }
    }
    for (y in 0 until H) {
        val row = reader.readLine().trim()
        for (x in 0 until W) {
            ruleB[y][x] = row[x].digitToInt()
        }
    }
    while (true) {
        var didClean = false
        if (!isClean[r][c]) {
            isClean[r][c] = true
            cleanCnt++
            didClean = true
        }
        if (didClean)
            d = (d + ruleA[r][c]) % 4
        else
            d = (d + ruleB[r][c]) % 4
        r += dy[d]
        c += dx[d]
        moveCnt++
        if (didClean)
            ans = moveCnt
        if (r !in 0 until H || c !in 0 until W)
            break
        if (moveCnt > 30000000)
            break
    }
    writer.write("${ans}\n")
    writer.flush()
}
    