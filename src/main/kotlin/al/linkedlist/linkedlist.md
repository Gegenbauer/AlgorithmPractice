## 链表问题
>对于笔试，不用太在乎空间复杂度，一切为了时间复杂度
> 
>对于面试，先保证时间复杂度，然后找到空间最省的方法

### 构建链表

#### 技巧

> 1. 哨兵节点/哑节点/假节点，dummy，建立一个假节点，指向头节点，这样就不需要在添加节点时判空

#### 头插法

#### 尾插法

### 快慢指针

#### 寻找中间节点

##### 结论

> 节点数为奇数时，快指针遍历到最后一个节点时，慢指针刚好指向中点
>
> 节点数为偶数数时，快指针遍历到倒数第二个节点时，慢指针刚好指向上中点

> 如果为奇数，则链表只有一个中间节点，如果为偶数，则有两个中间节点
>
> >输入链表头节点，奇数长度返回中点，偶数长度返回上中点
> >
> >输入链表头节点，奇数长度返回中点，偶数长度返回下中点
> >
> >输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个（可以通过增加一个慢指针的前节点指针来实现，也可以让慢指针晚走一步）
> >
> >输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个（可以通过增加一个慢指针的前节点指针来实现，也可以让慢指针晚走一步）

##### 代码

```kotlin
class FindMiddleNode {

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * a -> b -> c -> null
     * p1   p2   p3
     * q1        q2
     *
     * a -> b -> c -> d -> null
     * p1   p2   p3
     * q1        q2
     */
    fun findUpperMiddleNode(head: ListNode?): ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * a -> b -> c -> null
     * p1   p2   p3
     * q1        q2
     *
     * a -> b -> c -> d
     * p1   p2   p3
     * q1        q2   q3
     */
    fun findLowerMiddleNode(head: ListNode?): ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }
        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 链表长度至少为 3
     */
    fun findLastUpperMiddleNode(head: ListNode?): ListNode? {
        val dummy = ListNode(0, head)
        var slow: ListNode? = dummy
        var fast: ListNode? = head

        while (fast?.next?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        return slow
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 链表长度至少为 3
     */
    fun findLastLowerMiddleNode(head: ListNode?): ListNode? {
        val dummy = ListNode(0, head)
        var slow: ListNode? = dummy
        var fast: ListNode? = head

        while (fast?.next != null) {
            fast = fast.next?.next
            slow = slow?.next
        }

        return slow
    }
}
```

#### 判断链表是否符合回文结构

##### 方法

> 借助栈

> 从上终点开始反转，再从两端同时遍历对比

#### 链表相交

