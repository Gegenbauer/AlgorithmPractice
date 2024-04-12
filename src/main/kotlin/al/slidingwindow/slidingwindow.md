## 滑动窗口

### 代码模板

>```kotlin
>var ans
>// 初始化右边界、左边界
>for (遍历右边界) {
>	while(满足缩小左边界的条件) {
>    // 更新结果
>    持续缩小左边界
>  }
>	// 更新结果
>}
>```

### 示例

```kotlin
class LongestSubStrWithNonDuplicateChar {

    fun lengthOfLongestSubstring(s: String): Int {
        var left = 0
        var ans = 0
        val set = hashSetOf<Char>()
        for (right in s.indices) {
            while (s[right] in set) {
                set.remove(s[left])
                left++
            }
            ans = max(ans, right - left + 1)
            set.add(s[right])
        }
        return ans
    }
}
```

```kotlin
class MinSubArrayWithSumLargerThanTarget {

    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var left = 0
        var ans = Int.MAX_VALUE
        var sum = 0
        // 枚举右端点
        for (right in nums.indices) {
            sum += nums[right]
            while (sum >= target) {
                ans = min(ans, right - left + 1)
                sum -= nums[left]
                left++
            }
        }
        return ans.takeIf { it <= nums.size } ?: 0
    }
}
```

```kotlin
class SubArrayCountWithProductSmallThanTarget {

    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        if (k == 0) {
            return 0
        }
        var left = 0
        var product = 1
        var ans = 0
        for (right in nums.indices) {
            product *= nums[right]
            while (product >= k && left <= right) {
                product /= nums[left]
                left++
            }
            ans += right - left + 1
        }
        return ans
    }
}
```

### 相关题目

```
209. 长度最小的子数组 https://leetcode.cn/problems/minimum-size-subarray-sum/solution/biao-ti-xia-biao-zong-suan-cuo-qing-kan-k81nh/ 
3. 无重复字符的最长子串 https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/xia-biao-zong-suan-cuo-qing-kan-zhe-by-e-iaks/ 
713. 乘积小于 K 的子数组 https://leetcode.cn/problems/subarray-product-less-than-k/solution/xia-biao-zong-suan-cuo-qing-kan-zhe-by-e-jebq/

2958. 最多 K 个重复元素的最长子数组 https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency/ 
2730. 找到最长的半重复子字符串 https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring/ 
1004. 最大连续 1 的个数 III https://leetcode.cn/problems/max-consecutive-ones-iii/ 
2962. 统计最大元素出现至少 K 次的子数组 https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/ 
2302. 统计得分小于 K 的子数组数目 https://leetcode.cn/problems/count-subarrays-with-score-less-than-k/ 
1658. 将 x 减到 0 的最小操作数 https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/ 
76. 最小覆盖子串 https://leetcode.cn/problems/minimum-window-substring/ 
```



