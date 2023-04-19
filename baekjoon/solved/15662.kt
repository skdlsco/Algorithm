package `15662`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

const val GEAR_SIZE = 8

class Machine(val T: Int, val gears: Array<Array<Int>>) {
    private val gearNPositions = Array<Int>(T) { 0 }

    private fun getGearValue(gearNumber: Int, isRight: Boolean): Int {
        return gears[gearNumber][(gearNPositions[gearNumber] + if (isRight) 6 else 2) % GEAR_SIZE]
    }

    fun countSGear(): Int {
        return (0 until T).count {
            gears[it][gearNPositions[it]] == 1
        }
    }

    private fun rotateGears(rotationValues: Array<Int>) {
        for (i in 0 until T) {
            gearNPositions[i] = (GEAR_SIZE + gearNPositions[i] + rotationValues[i]) % GEAR_SIZE
        }
    }

    fun runMachine(gearNumber: Int, direction: Int) {
        val rotationValues = Array<Int>(T) { 0 }
        rotationValues[gearNumber] = direction
        // left
        for (i in gearNumber - 1 downTo 0) {
            val rightGear = getGearValue(i + 1, true)
            val leftGear = getGearValue(i, false)
            if (rightGear == leftGear)
                break
            rotationValues[i] = rotationValues[i + 1] * -1
        }

        //right
        for (i in gearNumber + 1 until T) {
            val rightGear = getGearValue(i, true)
            val leftGear = getGearValue(i - 1, false)
            if (rightGear == leftGear)
                break
            rotationValues[i] = rotationValues[i - 1] * -1
        }
        rotateGears(rotationValues)
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val gears = Array<Array<Int>>(T) { reader.readLine().map { it.digitToInt() }.toTypedArray() }
    val machine = Machine(T, gears)

    val K = reader.readLine().toInt()
    repeat(K) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        val gearNumber = stringTokenizer.nextToken().toInt() - 1
        val direction = stringTokenizer.nextToken().toInt() * -1

        machine.runMachine(gearNumber, direction)
    }
    writer.write("${machine.countSGear()}\n")
    writer.flush()
}