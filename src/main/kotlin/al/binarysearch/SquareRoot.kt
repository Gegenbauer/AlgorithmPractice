package al.binarysearch

class SquareRoot {
    /**
     * leetcode-69
     * 小于 n 的最大值
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     *
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     *
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     *
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     *
     *
     * 提示：
     *
     * 0 <= x <= 231 - 1
     */
    fun mySqrt(x: Int): Int {
        var left = 0
        var right = (x shr 1) + 1
        var mid: Int
        var result = -1
        while (left <= right) {
            mid = left + ((right - left) shr 1)
            val square: Long = mid.toLong() * mid
            if (square > x) {
                right = mid - 1
            } else if (square < x) {
                result = mid
                left = mid + 1
            } else {
                result = mid
                break
            }
        }

        return result
    }
}