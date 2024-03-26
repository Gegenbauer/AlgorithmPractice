package al.stackorqueue

import java.util.Stack
import kotlin.math.min


/**
 * leetcode-155 最小栈
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * pop push getMin 操作的时间复杂度都是 O(1)
 * 设计的栈类型可以使用现成的栈结构
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 */
class StackWithMinValue {
    private val dataStack = Stack<Int>()
    private val minStack = Stack<Int>()

    private val currentMinValue: Int
        get() = if (minStack.isEmpty()) {
            Int.MAX_VALUE
        } else {
            minStack.peek()
        }

    fun push(`val`: Int) {
        dataStack.push(`val`)
        minStack.push(min(`val`, currentMinValue))
    }

    fun pop() {
        dataStack.pop()
        minStack.pop()
    }

    fun top(): Int {
        return dataStack.peek()
    }

    fun getMin(): Int {
        return currentMinValue
    }
}