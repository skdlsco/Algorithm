package clear

import java.util.*
import kotlin.math.*

class Turret(val x: Double, val y: Double, val r: Double) {}

fun main() {
    val scanner = Scanner(System.`in`)
    val T = scanner.nextInt()

    repeat(T) {
        val t1 = Turret(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble())
        val t2 = Turret(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble())
        val distance = sqrt((t1.x - t2.x).pow(2) + (t1.y - t2.y).pow(2))
        if (distance == 0.0 && t1.r == t2.r)
            println(-1)
        else if ((t1.r - t2.r).absoluteValue == distance || (t1.r + t2.r) == distance)
            println(1)
        else if (max(t1.r, t2.r) - min(t1.r, t2.r) < distance && t1.r + t2.r > distance)
            println(2)
        else
            println(0)
    }
}