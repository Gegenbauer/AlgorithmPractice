## 暴力递归

暴力递归就是尝试
1，肥问题转化为规模缩小了的同类问题的子问题
2，有明确的不需要继续进行递归的条件（basecase）
3，有当得到了子问题的结果之后的决策过程
4，不记录每一个子问题的解

### 汉诺塔问题

汉诺塔问题是一个经典的数学谜题，它涉及到一个传说中的印度神庙——汉诺塔。问题的描述如下：

假设有三根柱子（编号为 A、B、C），初始时在柱子 A 上有一些盘子，它们按照从小到大的顺序叠放，最大的盘子在最下面，最小的盘子在最上面。问题的目标是将所有的盘子从柱子 A 移动到柱子 C，每次只能移动一个盘子，并且在移动过程中，大盘子不能放在小盘子上面。

需要打印所有的操作过程

### 递归转化为非递归

#### 直接转化法

直接用循环结构的算法替代递归算法

没有通用的转化方法

直接转化法特别适用于**尾递归**和**单向递归**



递归是将问题规模逐渐减小，然后求解最小规模问题，再往上汇总到大问题的求解

转化为循环相当于是直接从小规模问题开始求解，逐步增加问题规模，最终求解目标问题

#### 尾递归

尾递归即只有一个递归调用语句，且处于算法的最后。

尾递归的特点是，递归调用的结果会直接被返回，不需要再进行其他计算或处理。这种情况下，编译器或解释器可以对递归调用进行优化，将其转换为循环，从而节省内存空间并提高性能。这种优化称为尾递归优化。

尾递归优化的原理是将递归调用转换为迭代调用，这样就可以在每次迭代中重复使用相同的栈帧（stack frame），而不会产生额外的栈空间消耗。因此，尾递归优化可以避免递归深度过大导致的栈溢出问题，并提高程序的执行效率。

尾递归在**函数式编程**中很常见，因为函数式编程通常使用递归来处理列表、树等数据结构。通过使用尾递归并进行优化，可以编写出高效且清晰的函数式代码。

改成尾递归之后实际上就和循环结构下的算法思想一致了

```kotlin
fun getMaxValue(arr: IntArray): Int {
    return getMaxValue(arr, arr.size - 1)
}

private fun getMaxValue(arr: IntArray, endIndex: Int): Int {
    if (endIndex == 0) {
        return arr[0]
    }
    return max(getMaxValue(arr, endIndex - 1), arr[endIndex])
}

fun getMaxValue2(arr: IntArray): Int {
    return getMaxValue(arr, arr[0], arr.lastIndex)
}

// 尾递归
fun getMaxValue(arr: IntArray, curMax: Int, endIndex: Int): Int {
    if (endIndex == 0) {
        return curMax
    }
    return getMaxValue(arr, max(curMax, arr[endIndex]), endIndex - 1)
}

// 非递归实现
fun getMaxValue3(arr: IntArray): Int {
    var max = arr[0]
    var cur = 1
    while (cur < arr.size) {
        max = max(max, arr[cur])
    }
    return max
}

fun fibonacci(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
}

fun fibonacci2(n: Int): Int {
    return fibonacci(n, 1, 0)
}

// 尾递归
fun fibonacci(n: Int, pre: Int, pre2: Int): Int {
    if (n == 0) return pre2
    if (n == 1) return pre
    return fibonacci(n - 1, pre + pre2, pre)
}

// 非递归实现
fun fibonacci3(n: Int): Int {
    var pre2 = 0
    var pre1 = 1
    var curIndex = 2
    var curValue: Int = if (n == 0) pre2 else pre1
    while (curIndex <= n) {
        curValue = pre1 + pre2
        pre2 = pre1
        pre1 = curValue
        curIndex++
    }
    return curValue
}
```

#### 单向递归

递归函数中虽然只有一处以上的递归调用语句，但各次递归调用语句的参数之和主调用函数有关，**相互之间参数无**关，并且这些递归调用语句也和尾递归一样**处于算法的最后**。

```kotlin
fun fibonacci(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
}
```

#### 间接转化法

所有递归程序都可以转化为非递归程序

用**栈**模拟系统的运行过程，通过分析只保存必须保存的信息。

```kotlin
// 递归实现
fun preOrder(root: TreeNode?, visitor: (TreeNode) -> Boolean) {
    if (root == null || shouldStop) return

    if (!visitor(root)) {
        shouldStop = true
        return
    }
    preOrder(root.left, visitor)
    preOrder(root.right, visitor)
}
```

### 递归函数设计

递归函数设计最关键的是递归函数参数设计，由递归尝试转成动态规划，其中要保证递归尝试函数的参数设计不能太差
