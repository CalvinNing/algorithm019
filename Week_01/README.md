### 学习笔记
- 用 add first 或 add last 这套新的 API 改写 Deque 的代码
```java
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
```
- 分析 Queue 的源码
    - 添加。`boolean add(E e)` 和 `boolean offer(E e)`。在实现类 `java.util.LinkedList`中，offer也是调用的add方法，而且都是允许加入null元素的。
    
        ```java
        public boolean offer(E e) {
            return add(e);
        }
        public boolean add(E e) {
            linkLast(e);
            return true;
        }
        //  可见，始终返回的true
        void linkLast(E e) {
            //  在链表的末端加入数据
            final Node<E> l = last;
            final Node<E> newNode = new Node<>(l, e, null);
            last = newNode;
            if (l == null)
                first = newNode;
            else
                l.next = newNode;
            size++;
            modCount++;
        }
        ```
    - 删除。`E remove()`和`E poll()`。在实现类 `java.util.LinkedList`中，如果队列为空，remove抛出异常，poll返回null。
    - 查看。`E element()`和`E peek()`。在实现类 `java.util.LinkedList`中，如果队列为空，element抛出异常，peek返回null。
