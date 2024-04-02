package al.table

/**
 * 无序表：HashMap 增删改查时间复杂度都是 O(1)
 * 有序表：TreeMap (红黑树实现) 增删改查时间复杂度都是 O(logN)
 * 需要注意的是，TreeMap 因为实现的是 MAP 接口，也不能持有相等 key 的元素
 * 优先级队列 PriorityQueue 会保留所有样本
 */