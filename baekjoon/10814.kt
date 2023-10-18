package `10814`

import java.util.*

data class Member(val age: Int, val name: String){
    override fun toString(): String {
        return "$age $name"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val list = Array<Member>(N) { Member(0, "") }
    repeat(N) {
        val age = scanner.nextInt()
        val name = scanner.next()
        list[it] = Member(age, name)
    }
    list.sortBy { it.age }
    list.forEach { println(it) }
}