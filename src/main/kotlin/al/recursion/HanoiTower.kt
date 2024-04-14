package al.recursion

/**
 * 假设有三根柱子（编号为 A、B、C），初始时在柱子 A 上有一些盘子，
 * 它们按照从小到大的顺序叠放，最大的盘子在最下面，最小的盘子在最上面。
 * 问题的目标是将所有的盘子从柱子 A 移动到柱子 C，每次只能移动一个盘子，
 * 并且在移动过程中，大盘子不能放在小盘子上面。
 *
 * 需要打印所有的操作过程
 *
 * 最少移动步骤数为 2^n - 1
 */
class HanoiTower {

    /**
     * 表示 n 个圆盘从左移动到右
     */
    fun hanoi(n: Int) {
        leftToRight(n)
    }

    private fun leftToRight(n: Int) {
        if (n == 1) {
            println("move 1 from left to right")
            return
        }
        leftToMid(n - 1)
        println("move $n from left to right")
        midToRight(n - 1)
    }

    private fun leftToMid(n: Int) {
        if (n == 1) {
            println("move 1 from left to middle")
            return
        }
        leftToRight(n - 1)
        println("move $n from left to middle")
        rightToMid(n - 1)
    }

    private fun midToRight(n: Int) {
        if (n == 1) {
            println("move 1 from middle to right")
            return
        }
        midToLeft(n - 1)
        println("move $n from middle to right")
        leftToRight(n - 1)
    }

    private fun rightToMid(n: Int) {
        if (n == 1) {
            println("move 1 from right to middle")
            return
        }
        rightToLeft(n - 1)
        println("move $n from right to middle")
        leftToMid(n - 1)
    }

    private fun midToLeft(n: Int) {
        if (n == 1) {
            println("move 1 from middle to left")
            return
        }
        midToRight(n - 1)
        println("move $n from middle to left")
        rightToLeft(n - 1)
    }

    private fun rightToLeft(n: Int) {
        if (n == 1) {
            println("move 1 from right to left")
            return
        }
        rightToMid(n - 1)
        println("move $n from right to left")
        midToLeft(n - 1)
    }

    fun hanoi2(n: Int) {
        move(n, "left", "right", "middle")
    }

    /**
     * 通过增加参数，来适用于更多情况
     */
    private fun move(n: Int, from: String, to: String, other: String) {
        if (n == 1) {
            println("move 1 from $from to $to")
            return
        }
        move(n - 1, from, other, to)
        println("move $n from $from to $other")
        move(n - 1, other, to, from)
    }
}

fun main() {
    HanoiTower().hanoi2(3)
}