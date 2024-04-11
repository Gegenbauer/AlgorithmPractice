package al.greedy

/**
 * 给定一个字符串 str，只由 'X' 和 '.' 两种字符构成，'X' 表示墙，不能放灯，不需要点亮
 * '.'表示街道，可以放灯，也需要点亮。
 * 如果灯放在 i 位置，i - 1, i 和 i + 1 都会被照亮
 * 返回点亮 str 中所有需要点亮的位置，至少需要几盏灯
 */
class MinLightsToLightenAllStreets {

    /**
     * 如果 i 为 'X'，直接检查下一个位置
     * 如果 i 为 '.'
     *      如果 i + 1 为 'X'，i 必须要放灯，直接检查 i + 2 位置
     *      如果 i + 1 为 '.'，
     *              如果 i + 2 为 'X'，灯一定要放在 i 或者 i + 1 位置，检查 i + 3 位置
     *              如果 i + 2 为 '.'，灯要放在 i + 1 位置，检查 i + 3 位置
     */
    fun minLight(road: String): Int {
        var index = 0
        var light = 0
        val str = road.toCharArray()

        while (index < str.size) {
            if (str[index] == 'X') {
                index++
            } else {
                light++
                if (index + 1 == str.size) {
                    break
                } else {
                    if (str[index + 1] == 'X') {
                        index += 2
                    } else {
                        index += 3
                    }
                }
            }
        }
        return light
    }

    fun dp() {
        TODO()
    }
}