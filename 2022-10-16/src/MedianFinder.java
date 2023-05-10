import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {

    /**
     * 假设共有 n 个数据：
     *    则中位数为 n 给数据排好序后的 最中间的一个数或者两个数
     * 中位数有以下特性：
     *     1.中位数左边的数都小于对于中位数
     *     2.中位数右边的数都大于对于中位数
     * 而堆有以下特性
     *   1.大顶堆：堆顶元素大于非堆顶元素
     *   2.小顶堆：堆顶元素小于大于非读顶元素
     *
     *思路：
     *   现在，假设有 n 个数，我们以中位数为界，中位数左边的数
     *都小于等于中位数，那么中位数和中位数左边的元素可以看成一个
     *大顶堆。同理，中位数左边的元素可以看成小顶堆
     *   由于中位数是将一个数据分为两份，且这两份数据有如下性质：
     *     1. 0 <= 左边数据的个数 - 右边数据的个数 <= 1
     *        所以大顶堆和小顶堆的元素个数的差值也是 0 或者 1
     *     2. 当大顶堆与小顶堆大小相同时，中位数是两个队顶元素的平均值，
     *        否则中位数为小顶堆的堆顶元素
     *     使用堆的话，获取中位数的时间复杂度度为O(1)
     *
     * 那么使用堆的话，如何在插入新元素时来维护中位数呢？
     *      插入元素分两种情况：
     *         1.大顶堆的元素个数大于小顶堆的元素个数：
     *            此时先把元素插入到大顶堆，再将大顶堆的堆顶弹出，插入到小顶堆中，原因如下：
     *                 因为大顶堆的元素大于小顶堆，所以此时大顶堆与小顶堆的元素个数和为奇数，而插入一个元素后，
     *             那么他们的元素个数和就为偶数了，此时就必须要从大顶堆中弹出一个元素到小顶堆了。
     *                 那么为什么不直接将元素加入小顶堆呢？原因如下：
     *                 当新元素小于等于堆顶时，插入元素后，数据流的元素个数变成了偶数个，此时中位数会小于等于原来的中位数，
     *             而大顶堆的堆顶要存的是小于等于中位数的元素，所以此时将大顶堆的堆顶元素弹出到小顶堆。
     *                 当待
     *
     *
     *
     */

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<Integer>((x,y) -> y - x);
    }

    public void addNum(int num) {
        if (maxHeap.size() != minHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        } else {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        return maxHeap.size() == minHeap.size() ?
                ( maxHeap.peek() + minHeap.peek() ) / 2.0 : maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */