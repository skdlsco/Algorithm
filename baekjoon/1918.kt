    import java.util.*

    fun main() {
        val line = readLine()!!
        var level = 0
        val operStack = Stack<Pair<Int, Char>>()
        line.forEach {
            if (it in ('A'..'Z'))
                print(it)
            else {
                if (it == '(') {
                    level += 2
                } else if (it == '+' || it == '-') {
                    while (operStack.isNotEmpty() && operStack.lastElement().first >= level)
                        print(operStack.pop().second)
                    operStack.push(Pair(level, it))
                } else if (it == ')') {
                    level -= 2
                    while (operStack.isNotEmpty() && operStack.lastElement().first < level)
                        print(operStack.pop().second)
                } else {
                    while (operStack.isNotEmpty() && operStack.lastElement().first > level)
                        print(operStack.pop().second)
                    operStack.push(Pair(level + 1, it))
                }
            }
        }
        while (operStack.isNotEmpty())
            print(operStack.pop().second)
    }