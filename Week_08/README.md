### 学习笔记
- 冒泡排序
```java
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = length - 1; j > i; j--) {
                //if (arr[j] < arr[j - 1]) { //升序
                if (arr[j] > arr[j - 1]) { //降序
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }
```
- 插入排序
```java
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                //if (arr[j] < arr[j - 1]) { //降序
                if (arr[j] > arr[j - 1]) { //升序
                    break;
                } else {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }
```
- 选择排序
```java
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int length = arr.length;
        int minIndex = 0;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }
```
