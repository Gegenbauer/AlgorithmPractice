package al.stackorqueue

/**
 * 栈和队列都是逻辑上的一种数据结构
 * 可以通过数组和链表来实现队列和栈
 */

/**
 * 两个栈相互倒腾可以改变反转原有集合的顺序
 */
interface IStack<T> {

    val size: Int

    fun pop(): T?

    fun push(value: T)

    fun peak(): T
}

/**
 * 两个队列相互倒腾无法改变原有集合的顺序
 */
interface IQueue<T> {
    val size: Int

    fun pop(): T?

    fun push(value: T)

    fun peak(): T
}