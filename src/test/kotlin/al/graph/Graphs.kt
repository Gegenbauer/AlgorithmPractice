package al.graph

fun generateGraph(): Graph {
    return generateGraph(arrayOf(
        intArrayOf(3, 0, 1),
        intArrayOf(2, 0, 2),
        intArrayOf(7, 0, 5),
        intArrayOf(5, 1, 3),
        intArrayOf(1, 2, 1),
        intArrayOf(4, 2, 3),
        intArrayOf(6, 2, 4),
        intArrayOf(1, 3, 0),
        intArrayOf(2, 5, 4),
    ))
}