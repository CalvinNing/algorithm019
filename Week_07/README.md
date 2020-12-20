### 双向 BFS 代码模版
```java
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginElement);
        Set<String> endSet = new HashSet<>();
        endSet.add(endElement);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = beginSet;
            }
            //从数据量较少的一端扩展到下一层
            Set<String> nextSet = new HashSet<>();
            nextSet = generateNextSet(beginSet);
            if nextSet reaches endSet
                finish and return result;
            else
                beginSet = nextSet;
        }
        never reached;
```
