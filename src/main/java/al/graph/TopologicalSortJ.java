package al.graph;

import java.util.*;

public class TopologicalSortJ {

    /**
     * 邻接表法
     */
    static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    /**
     * lintcode-127 拓扑排序
     * 方法一（通过入度排序）
     *
     * 图结点的个数 <= 5000
     *
     * @param graph: A list of Directed graph node
     * @return Any topological order for the given graph.
     */
    public List<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {

        // write your code here
        Queue<DirectedGraphNode> zeroInDegreeNodes = new LinkedList<>();
        Map<DirectedGraphNode, Integer> inDegrees = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            if (!inDegrees.containsKey(node)) {
                inDegrees.put(node, 0);
            }
            for (DirectedGraphNode neighbour : node.neighbors) {
                if (!inDegrees.containsKey(neighbour)) {
                    inDegrees.put(neighbour, 0);
                }
                inDegrees.put(neighbour, inDegrees.get(neighbour) + 1);
            }
        }
        for (DirectedGraphNode node : inDegrees.keySet()) {
            if (inDegrees.get(node) == 0) {
                zeroInDegreeNodes.add(node);
            }
        }

        ArrayList<DirectedGraphNode> nodes = new ArrayList<>();
        while (!zeroInDegreeNodes.isEmpty()) {
            DirectedGraphNode cur = zeroInDegreeNodes.poll();
            nodes.add(cur);
            for (DirectedGraphNode neighbour : cur.neighbors) {
                inDegrees.put(neighbour, inDegrees.get(neighbour) - 1);
                if (inDegrees.get(neighbour) == 0) {
                    zeroInDegreeNodes.add(neighbour);
                }
            }
        }
        return nodes;
    }

    /**
     * 方法二（通过点次排序）
     * 如果从节点 A 出发能达到的节点数 大于从节点 B 出发能到达的节点数，则拓扑排序结果 A 一定在 B 前面
     * 这里需要注意，由于会遍历非常多节点，包括重复节点，所以 reachableNodeCount 可能会溢出
     */
    public List<DirectedGraphNode> topSort2(List<DirectedGraphNode> graph) {
        List<Record> records = new ArrayList<>();
        Map<DirectedGraphNode, Record> cache = new HashMap<>();
        // 所有节点不止有 graph 列表里的节点
        for (DirectedGraphNode node : graph) {
            generateReachableNodeRecord(node, cache);
        }
        for (Record record : cache.values()) {
            records.add(record);
        }

        records.sort((o1, o2) -> {
            if (o1.reachableNodeCount == o2.reachableNodeCount) {
                return 0;
            } else if (o1.reachableNodeCount > o2.reachableNodeCount){
                return -1;
            } else {
                return 1;
            }
        });
        List<DirectedGraphNode> result = new ArrayList<>();
        for (Record record : records) {
            result.add(record.node);
        }
        return result;
    }

    private Record generateReachableNodeRecord(DirectedGraphNode node, Map<DirectedGraphNode, Record> cache) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        // 能到达的节点数，包括自己
        long reachableNodeCount = 0;
        for (DirectedGraphNode neighbour : node.neighbors) {
            reachableNodeCount += generateReachableNodeRecord(neighbour, cache).reachableNodeCount;
        }
        Record record = new Record(node, reachableNodeCount + 1);
        cache.put(node, record);
        return record;
    }

    public List<DirectedGraphNode> topSort3(List<DirectedGraphNode> graph) {
        List<DepthRecord> records = new ArrayList<>();
        Map<DirectedGraphNode, DepthRecord> cache = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            generateDepthRecord(node, cache);
        }
        for (DepthRecord record : cache.values()) {
            records.add(record);
        }
        records.sort(((o1, o2) -> o2.maxDepth - o1.maxDepth));
        List<DirectedGraphNode> result = new ArrayList<>();
        for (DepthRecord record : records) {
            result.add(record.node);
        }
        return result;
    }

    private DepthRecord generateDepthRecord(DirectedGraphNode node, Map<DirectedGraphNode, DepthRecord> cache) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        int maxDepth = 0;
        for (DirectedGraphNode neighbour : node.neighbors) {
            maxDepth = Math.max(maxDepth, generateDepthRecord(neighbour, cache).maxDepth);
        }
        DepthRecord record = new DepthRecord(node, maxDepth + 1);
        cache.put(node, record);
        return record;
    }

    private static class DepthRecord {
        DirectedGraphNode node;
        int maxDepth;

        DepthRecord(DirectedGraphNode node, int maxDepth) {
            this.node = node;
            this.maxDepth = maxDepth;
        }
    }

    private static class Record {
        DirectedGraphNode node;
        long reachableNodeCount;

        public Record(DirectedGraphNode node, long reachableNodeCount) {
            this.node = node;
            this.reachableNodeCount = reachableNodeCount;
        }
    }
}
