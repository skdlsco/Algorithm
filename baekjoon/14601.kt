import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

var tileNum = 1

fun getArea(K: Int, sx: Int, sy: Int, hx: Int, hy: Int): Int {
    val length = 1 shl (K - 1)
    val cx = sx + length
    val cy = sy + length
    return if (hy in sy until cy && hx in cx until cx + length)
        1
    else if (hy in sy until cy && hx in sx until cx)
        2
    else if (hy in cy until cy + length && hx in sx until cx)
        3
    else
        4
}

fun recursive(K: Int, map: Array<Array<Int>>, sx: Int, sy: Int, hx: Int, hy: Int) {
    val area = getArea(K, sx, sy, hx, hy)
    if (K == 0)
        return
    val center = 1 shl (K - 1)
    val nx = sx + center
    val ny = sy + center
    if (area != 1)
        map[ny - 1][nx] = tileNum
    if (area != 2)
        map[ny - 1][nx - 1] = tileNum
    if (area != 3)
        map[ny][nx - 1] = tileNum
    if (area != 4)
        map[ny][nx] = tileNum
    tileNum++
    if (area == 1)
        recursive(K - 1, map, nx, sy, hx, hy)
    else
        recursive(K - 1, map, nx, sy, nx, ny - 1)
    if (area == 2)
        recursive(K - 1, map, sx, sy, hx, hy)
    else
        recursive(K - 1, map, sx, sy, nx - 1, ny - 1)
    if (area == 3)
        recursive(K - 1, map, sx, ny, hx, hy)
    else
        recursive(K - 1, map, sx, ny, nx - 1, ny)
    if (area == 4)
        recursive(K - 1, map, nx, ny, hx, hy)
    else
        recursive(K - 1, map, nx, ny, nx, ny)
}


fun main() {
    val K = reader.readLine().toInt()
    val (x, y) = reader.readLine().split(" ").map { it.toInt() - 1 }
    val map = Array<Array<Int>>(1 shl K) { Array<Int>(1 shl K) { 0 } }
    map[(1 shl K) - y - 1][x] = -1
    recursive(K, map, 0, 0, x, (1 shl K) - y - 1)
    writer.write(map.joinToString("\n") { it.joinToString(" ") })
    writer.flush()
}
