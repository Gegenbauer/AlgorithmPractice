package al.binarysearch

class FirstBadVersion {

    fun firstBadVersion(n: Int, isBadVersion: (Int) -> Boolean) : Int {
        var left = 0
        var right = n
        var mid: Int
        var firstBadVersion: Int = -1

        while (left <= right) {
            mid = left + ((right - left) shr 1)

            if (isBadVersion(mid)) {
                firstBadVersion = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return firstBadVersion
    }
}