import java.util.Scanner
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer 

val scanner = Scanner(System.`in`)
val writer = BufferedWriter(OutputStreamWriter(System.out))
  
fun getLisIdx(lis: ArrayList<Int>, target: Int): Int {
  var left = 0
  var right = lis.size
  while (left < right) {
    val mid = (left + right) / 2
    if (lis[mid] < target) {
      left = mid  + 1
    } else {
      right = mid
    }
  }
  return right
}

fun readInput(N: Int): Array<Int> {
    var i = 0
    val arr =  Array<Int>(N) { 0 } 
    while (i < N) {
        arr[i] = scanner.nextInt()
        i++
    }
    return arr
}


fun main() {
  while (scanner.hasNext()) {
    val N = scanner.nextInt()
    val lis = ArrayList<Int>()
    var inputArr = readInput(N)
    lis.add(inputArr[0])
    for (i in 1 until N) {
      // find lis idx 
    
      val lisIdx = getLisIdx(lis, inputArr[i])
      if (lisIdx >= lis.size)
        lis.add(inputArr[i])
      else
        lis[lisIdx] = inputArr[i]
    }
    writer.write("${lis.size}\n")
  }
  writer.flush()
}