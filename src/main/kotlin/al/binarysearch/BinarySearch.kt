import jdk.jshell.spi.ExecutionControl.NotImplementedException

/**
 * 1）在有序数组中确定 num 存在还是不存在  ==> 直接二分搜索
 * 2）在有序数组中找>=num的最左位置
 * 3）在有序数组中找<=num的最右位置
 * 4）二分搜索不一定发生在有序数组上（比如寻找峰值问题）
 * 5）“二分答案法”
 */
fun binarySearch(arr: IntArray, target: Int): Int {
    if (arr.isEmpty()) return -1

    var left = 0
    var right = arr.size - 1
    var mid: Int

    while (left <= right) { // 边界条件：L..R 至少两个数的时候
        mid = left + ((right - left) shr 1)
        //mid = (left + right) shr 1 // 容易溢出，计算过程中会产生更大的数

        if (arr[mid] == target) {
            return mid
        } else if (arr[mid] > target) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return -1
}

fun localMinimum(arr: IntArray): Int {
    throw NotImplementedException("")
}

