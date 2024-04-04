package al.sort.simple

fun swap(arr: IntArray, index1: Int, index2: Int) {
    val temp = arr[index2]
    arr[index2] = arr[index1]
    arr[index1] = temp
}

fun swap(arr: MutableList<Int>, index1: Int, index2: Int) {
    val temp = arr[index2]
    arr[index2] = arr[index1]
    arr[index1] = temp
}

fun swap(arr: Array<Any?>, index1: Int, index2: Int) {
    val temp = arr[index2]
    arr[index2] = arr[index1]
    arr[index1] = temp
}

/**
 * 最好时 O(n2)
 * 最坏时 O(n2)
 * 综合 O(n2)
 */
fun selectionSort(arr: IntArray) {
    if (arr.size < 2) return

    for (index in arr.indices) { // 找出处于 index 位置该放置的值，即第 index 小的值
        var minValueIndexInSubArray = index

        for (indexInner in index + 1 until arr.size) { // 找出最小值
            minValueIndexInSubArray = if (arr[indexInner] < arr[minValueIndexInSubArray]) {
                indexInner
            } else {
                minValueIndexInSubArray
            }
        }
        swap(arr, minValueIndexInSubArray, index)
    }
}

/**
 * 最好时 O(n)
 * 最坏时 O(n2)
 * 综合 O(n2)
 */
fun bubbleSort(arr: IntArray) {
    if (arr.size < 2) return

    for (index in arr.lastIndex downTo 0) { //  通过冒泡找到放置在 index 位置的数。
        var isArrayFullySorted = true
        for (innerIndex in 1..index) { // 进行冒泡

            if (arr[innerIndex - 1] > arr[innerIndex]) {
                swap(arr, innerIndex - 1, innerIndex)
                isArrayFullySorted = false
            }
        }

        if (isArrayFullySorted) break
    }
}

/**
 * 最好时 O(n)
 * 最坏时 O(n2)
 * 综合 O(n2)
 */
fun insertSort(arr: IntArray) {
    if (arr.size < 2) return

    for (index in arr.indices) { // 让 0-index 有序

        if (index - 1 < 0) continue

        for (innerIndex in index - 1 downTo 0) {
            if (arr[innerIndex + 1] < arr[innerIndex]) {
                swap(arr, innerIndex + 1, innerIndex)
            } else {
                break
            }
        }
    }
}

