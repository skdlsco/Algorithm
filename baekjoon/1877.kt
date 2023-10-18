package `1877`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val M = reader.readLine().toInt()

    if (M <= 1) {
        writer.write("1 1 1 1")
        writer.flush()
        return
    }
    // A
    // 3 우선시
    // 2 남은 경우는 2
    // 1 남은 경우 2, 4 둘다 가능
    var aCnt = M / 3
    val aRemain = M % 3
    if (aRemain > 0) {
        aCnt++
        if (aRemain == 1)
            writer.write("${aCnt} ${aCnt - 1} ")
        else
            writer.write("${aCnt} ${aCnt} ")
    } else
        writer.write("${aCnt} ${aCnt} ")
    // B
    // 소인수분해
    // 2가 2개 있을 때마다 1개씩 줄일 수 있다
    var temp = M
    var bCnt = 0
    var twoCnt = 0
    var i = 2
    while (temp >= 2) {
        if (temp % i == 0) {
            bCnt++
            if (i == 2)
                twoCnt++
            temp /= i
        } else
            i++
    }
    writer.write("${bCnt} ${bCnt - twoCnt / 2}")
    writer.flush()
}