import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 数组或者链表法，时间复杂度为 O(n) 会超时
 */
class MedianFinder1 {

    public ArrayList<Integer> list;

    public MedianFinder1() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int i = 0;
        while (i < list.size() && list.get(i) < num) {
            i++;
        }

        if (i == list.size()) {
            list.add(num);
            return;
        }

        for (int j = i; j < list.size(); j++) {
            int temp = list.get(j);
            list.set(j,num);
            num = temp;
        }
        list.add(num);
    }

    public double findMedian() {
        if (list.size() == 0) return 0;

        if (list.size() % 2 == 0) {
            return ( list.get(list.size() / 2) + list.get(list.size() / 2 - 1) ) / 2;
        } else {
            return list.get(list.size() / 2);
        }
    }

}

/**
 * 使用两个堆来存储
 *    大顶堆存储比中位数小或者等于中位数的数据
 *    小顶堆存储比中位数大的数
 *
 *    因为 大顶堆存的是 小于等于中位数的 数，所以大顶堆的元素个数会大于等于小顶堆的元素个数
 *
 *    每次插入新元素时，中位数都有可能会更新，此时有以下情况：
 *      1：插入的数小于等于中位数，此时新的中位数小于等于原来的中位数
 *      2.
 */
class MedianFinder {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>((a,b)-> (a - b));
        minHeap = new PriorityQueue<Integer>((a,b) -> (b - a));
    }

    public void addNum(int num) {
        if (minHeap.size() == 0 || num <= minHeap.peek()){
            minHeap.offer(num);
            if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        } else {
            maxHeap.offer(num);
            if (maxHeap.size() > minHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }


    }

    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return ( minHeap.peek() + maxHeap.peek() ) / 2.0;
        }
    }

}

public class Solution01 {

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        System.out.println(heap.peek());
    }
}

