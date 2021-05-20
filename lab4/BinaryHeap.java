package com.company;

import java.util.Arrays;

public class BinaryHeap {
    int[] heapArr;              // Root node index is 1
    int heapSize;
    int capacity;

    public int[] getHeapArr(){
        return this.heapArr;
    }

    private static int parent(int i) {
        return (i / 2);
    }

    private static int left(int i) {
        return (2 * i);
    }

    private static int right(int i) {
        return (2 * i + 1);
    }

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
        heapArr = new int[capacity + 1];
        heapSize = 0;
    }

    // Complexity: O(lg n)
    public void insertItem(int item) {
        if (heapSize == capacity) return;
        heapSize++;
        heapArr[heapSize] = item;
        int currentNode = heapSize;
        int parentNode = parent(currentNode);

        while (currentNode != 1 &&
                heapArr[parentNode] > heapArr[currentNode]) {
            this.swap(this.heapArr, currentNode, parentNode);
            currentNode = parentNode;
            parentNode = parent(currentNode);
        }
    }

    public int[] getSorted(){
        int[] sorted = Arrays.copyOfRange(this.heapArr, 1, this.heapSize + 1);
        Arrays.sort(sorted);
        return sorted;
    }

    // Complexity: O(lg n)
    public int extractMin() {
        if (heapSize == 0) return Integer.MAX_VALUE;
        if (heapSize == 1) {
            heapSize--;
            return heapArr[1];
        }

        int root = heapArr[1];
        heapArr[1] = heapArr[heapSize];
        heapSize--;
        minHeapify(1);

        return root;
    }

    private void minHeapify(int parent) {
        int l = left(parent);
        int r = right(parent);
        int smallest = parent;
        if (l <= heapSize && heapArr[l] < heapArr[smallest])
            smallest = l;
        if (r <= heapSize && heapArr[r] < heapArr[smallest])
            smallest = r;
        if (smallest != parent) {
            this.swap(this.heapArr, smallest, parent);
            minHeapify(smallest);
        }
    }

    private void swap(int[] arr, int firstIndex, int secondIndex){
        int a = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = a;
    }

    // Complexity: O(lg n)
    public void decreaseItem(int index, int newVal) {
        heapArr[index] = newVal;
        int parentNode = parent(index);
        while (index != 1 && heapArr[parentNode] > heapArr[index]) {
            this.swap(this.heapArr, parentNode, index);
            index = parentNode;
            parentNode = parent(index);
        }
    }

    // Complexity: O(lg n)
    public void deleteItem(int index) {
        decreaseItem(index, Integer.MIN_VALUE);
        extractMin();
    }

    // Complexity: O(n) in worst case
    public int find(int val){
        return findItemIndexHeap(val, 1);
    }

    private int findItemIndexHeap(int val, int currentNode) {
        if (currentNode > heapSize) return -1;
        if (heapArr[currentNode] > val) return -1;
        if (heapArr[currentNode] == val) return currentNode;
        int index = findItemIndexHeap(val, left(currentNode));
        if (index == -1)
            index = findItemIndexHeap(val, right(currentNode));
        return index;
    }
}