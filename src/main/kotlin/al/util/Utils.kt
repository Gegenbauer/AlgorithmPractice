package al.util

fun swap1(index1: Int, index2: Int, arr: IntArray) {
    val temp = arr[index1]
    arr[index1] = arr[index2]
    arr[index2] = temp
}

// i == j 时会出错
fun swap2(index1: Int, index2: Int, arr: IntArray) {
    arr[index1] = arr[index1] xor arr[index2]
    arr[index2] = arr[index1] xor arr[index2]
    arr[index1] = arr[index1] xor arr[index2]
}