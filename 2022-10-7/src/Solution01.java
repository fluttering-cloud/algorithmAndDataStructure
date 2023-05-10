import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Solution01 {


    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        CompletableFuture future = new CompletableFuture();
    }

//    // ConcurrentHashMap 的 put 方法解读
//    final V putVal(K key, V value, boolean onlyIfAbsent) {
//        // 判断一下插入的键或者值是否为空，在ConcurrentHashHap 内不允许插入空值
//        if (key == null || value == null) throw new NullPointerException();
//        // 将key的hashCode进行扩散，进一步减小hash冲突的可能
//        int hash = spread(key.hashCode());
//        int binCount = 0;
//
//        /** 所有的插入操作都在自旋内完成，这是因为在多线程环境下插入操作可能会失败，
//         * 使用自旋的话，即使失败了也可以出现操作
//         */
//        for (ConcurrentHashMap.Node<K,V>[] tab = table;;) {
//
//            ConcurrentHashMap.Node<K,V> f; int n, i, fh;
//            // 检查 table 是否为null
//            if (tab == null || (n = tab.length) == 0)
//                //ConcurrentHashMap 默认是使用懒加载的，只有在真正插入元素时才进行空间分配
//                tab = initTable();
//
//            // 使用 table 的长度于hash进行于操作，判断该位置是否已经有元素
//            // 待插入的位置用 i 记录
//            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
//                // 如果没有元素，则使用 cas 插入新元素
//                if (casTabAt(tab, i, null,
//                        new ConcurrentHashMap.Node<K,V>(hash, key, value, null)))
//                    break;                   // no lock when adding to empty bin
//            }
//
//            // 若 hash 找到的 table 相应位置的元素不为空，则用 f记录该位置的头元素
//            // 若头元素的 hash == MOVED，则表示该处的元素是树而不是链表，MOVED的值为 -1
//            else if ((fh = f.hash) == MOVED)
//                // ？？？
//                tab = helpTransfer(tab, f);
//
//            else {
//                V oldVal = null;
//                // f是待插入元素的头节点
//
//                synchronized (f) {
//                    /** 先判断一下 i 位置的头节点是否为f,因为在多线程环境下可能会有其他线程操作该map，造成map扩容
//                     *  从而导致元素的插入位置改变
//                     */
//                    if (tabAt(tab, i) == f) {
//                        // fh 是 findNodeHash, 判断一下fh是否大于等于0，因为fh小于0时表示该节点为树的头节点
//                        if (fh >= 0) {
//                            // binCount用于记录该位置共有多少个节点
//                            binCount = 1;
//                            for (ConcurrentHashMap.Node<K,V> e = f;; ++binCount) {
//                                K ek;
//                                // key是待插入元素的key
//                                /** 先判断待插入元素的key与节点内的元素的key是否相同
//                                 * 若相同的话则进行value的更新，而不是将该元素插入
//                                 */
//                                if (e.hash == hash &&
//                                        ((ek = e.key) == key ||
//                                                (ek != null && key.equals(ek)))) {
//                                    oldVal = e.val;
//                                    if (!onlyIfAbsent)
//                                        e.val = value;
//                                    break;
//                                }
//                                ConcurrentHashMap.Node<K,V> pred = e;
//                                if ((e = e.next) == null) {
//                                    pred.next = new ConcurrentHashMap.Node<K,V>(hash, key,
//                                            value, null);
//                                    break;
//                                }
//                            }
//                        }
//                        else if (f instanceof ConcurrentHashMap.TreeBin) {
//                            ConcurrentHashMap.Node<K,V> p;
//                            binCount = 2;
//                            if ((p = ((ConcurrentHashMap.TreeBin<K,V>)f).putTreeVal(hash, key,
//                                    value)) != null) {
//                                oldVal = p.val;
//                                /** onlyIfAbsent为true时表示只有 key 不在map内才能插入,即只插入不更新，put方法的 onlyIfAbsent
//                                 * 默认为false
//                                 */
//                                if (!onlyIfAbsent)
//                                    p.val = value;
//                            }
//                        }
//                    }
//                }
//
//                // 插入完成
//                if (binCount != 0) {
//                    // 判断一下是否需要树化
//                    if (binCount >= TREEIFY_THRESHOLD)
//                        treeifyBin(tab, i);
//                    // 判断一下oldVal是否为 null,不为null表示此次插入为修改，返回旧值
//                    if (oldVal != null)
//                        return oldVal;
//                    break;
//                }
//            }
//        }
//        // 此处只有待插入的元素在table内的位置为null时才执行
//        addCount(1L, binCount);
//        return null;
//    }
//
//
//    // ConcurrentHashMap初始化方法解读
//    private final ConcurrentHashMap.Node<K,V>[] initTable() {
//        ConcurrentHashMap.Node<K,V>[] tab; int sc;
//
//        // 采用cas + 自旋保证线程的安全性
//        // 当 table 不为 null 或者 table.length > 0 时结束自旋
//        while ((tab = table) == null || tab.length == 0) {
//
//            /** sizeCtl用于控制线程初始化ConcurrentHashMap
//             *  在多线程环境下，每个线程在初始化ConcurrentHashMap之前都会判断sizeCtl是否小于0
//             *  若sizeCtl不小于0，则使用cas将其设置为-1，然后再进行ConcurrentHashMap的初始化
//             *  若sizeCtl小于0，则线程使用 yield()让出该次cpu的使用权，因为已经有线程在为ConcurrentHashMap进行初始化了
//             *  因为 ConcurrentHashMap 的初始化是在自旋锁里进行的，所以失败的线程会再次循环，当再次循环是发现ConcurrentHashHap
//             *  已经初始化好了，所以跳出循环
//             */
//            if ((sc = sizeCtl) < 0)
//                Thread.yield(); // lost initialization race; just spin
//            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
//                try {
//                    if ((tab = table) == null || tab.length == 0) {
//                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
//                        @SuppressWarnings("unchecked")
//                        ConcurrentHashMap.Node<K,V>[] nt = (ConcurrentHashMap.Node<K,V>[])new ConcurrentHashMap.Node<?,?>[n];
//                        table = tab = nt;
//                        sc = n - (n >>> 2);
//                    }
//                } finally {
//                    sizeCtl = sc;
//                }
//                break;
//            }
//        }
//        return tab;
//    }
//
//    // ConcurrentHashMap的get方法解读
//
//    /**
//     *
//     * @param key
//     * @return
//     *
//     * 注意：ConcurrentMap的get方法是没有加锁的，且没有cas，相当于没有任何线程安全性
//     *      因此get是弱一致性的，当有线程在get方法的return前将该数据该了，那么get的线
//     *      程是毫无察觉的
//     */
//    public V get(Object key) {
//        ConcurrentHashMap.Node<K,V>[] tab; ConcurrentHashMap.Node<K,V> e, p; int n, eh; K ek;
//        // 将key的hashCode进行扩散，然后用扩散后的hash定位要查找的元素的位置
//        int h = spread(key.hashCode());
//
//        /**
//         * 判断一下table是否为null、hash对应的table的位置是否为null,若都为null则返回null
//         */
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (e = tabAt(tab, (n - 1) & h)) != null) {
//
//            if ((eh = e.hash) == h) {
//                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
//                    return e.val;
//            }
//            else if (eh < 0)
//                return (p = e.find(h, key)) != null ? p.val : null;
//            while ((e = e.next) != null) {
//                if (
//                        e.hash == h && ((ek = e.key) == key
//
//
//                                || (ek != null && key.equals(ek))))
//                    return e.val;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * HashMap是如何判断一个key已经在map内存在的
//     *    1.先通过key的hash值定位到table内的位置
//     *    2.判断该位置是否为null，若为null，则key不存在
//     *    3.遍历该位置的链表，若满足
//     *      i Node的key的hash值与待比较的key的hash值相等且 node.key==key
//     *      ii 或者 node.key != null 且 node.key.equals(key) 为true
//     *    则节点的key 和待比较的key相同
//     */
//
//
//    // readLock源码解读
//    // readLock.lock()
//
//    /**
//     *
//     *  lock =-> sync.acquireShared(1);
//     *      * readLock会尝试获取共享锁
//     *  sync.acquireShared(1) -> tryAcquireShared(arg)
//     *      * acquireShared(1)实际上是调用的tryAcquireShared(arg)，
//     *      * tryAcquireShared(arg)才是实际尝试获取锁的执行者
//     *      * ReentrantReadWriteLock重写了 AQS的 tryAcquireShared(int unused) 方法，
//     *      * 使其具有以下逻辑：
//     *          ** -1：表示获取锁失败，此时有线程获取了写锁，且获取写锁的线程并不是本线程
//     *          ** 1：表示获取锁成功
//     *  当 tryAcquireShared(arg) 为-1时，执行 doAcquireShared(int arg)
//     *
//     */
//    private void doAcquireShared(int arg) {
//
//        /**
//         * 首先往 AQS 队列内添加一个节点，因为是读锁，所以该节点的类型为 SHARED，表示共享锁
//         * addWaiter方法会新建一个Node并将当前线程放入该Node内，并且使用CAS将该Node加入
//         * 到队列的尾部，然后将该Node返回
//         */
//        final AbstractQueuedSynchronizer.Node node = addWaiter(AbstractQueuedSynchronizer.Node.SHARED);
//        // 设置获取锁失败的标记为true
//        boolean failed = true;
//        try {
//            boolean interrupted = false;
//
//            for (;;) {
//                // 获取当前节点的前一个节点
//                final AbstractQueuedSynchronizer.Node p = node.predecessor();
//                /**
//                 * 判断当前节点的前一个节点是否为头节点，若是头节点则尝试获取锁
//                 */
//                if (p == head) {
//                    int r = tryAcquireShared(arg);
//                    // 获取锁成功则跟新头节点的next,并且将当前节点移除队列
//                    if (r >= 0) {
//                        setHeadAndPropagate(node, r);
//                        p.next = null; // help GC
//                        if (interrupted)
//                            selfInterrupt();
//                        failed = false;
//                        return;
//                    }
//                }
//                // 如果当前节点的上一个节点不是头节点，则阻塞当前线程
//                if (shouldParkAfterFailedAcquire(p, node) &&
//                        parkAndCheckInterrupt())
//                    interrupted = true;
//            }
//        } finally {
//            if (failed)
//                cancelAcquire(node);
//        }
//    }
//
//    // tryAcquireShared 源码解读
//    /**
//     * 该方法是 AQS 获取共享锁的具体执行方法，现在解读的是readWriterLock内重写的方法
//     * 返回值为 -1 时表示获取锁失败
//     * 返回值为 1 时表示获取锁成功
//     */
//    int tryAcquireShared(int unused) {
//        /*
//         * Walkthrough:
//         * 1. If write lock held by another thread, fail.
//         * 2. Otherwise, this thread is eligible for
//         *    lock wrt state, so ask if it should block
//         *    because of queue policy. If not, try
//         *    to grant by CASing state and updating count.
//         *    Note that step does not check for reentrant
//         *    acquires, which is postponed to full version
//         *    to avoid having to check hold count in
//         *    the more typical non-reentrant case.
//         * 3. If step 2 fails either because thread
//         *    apparently not eligible or CAS fails or count
//         *    saturated, chain to version with full retry loop.
//         */
//        Thread current = Thread.currentThread();
//        /**
//         *   获取当前锁状态，在ReadWriterLock内，读锁、写锁
//         *   的状态都保存在State内，其中读锁占高16位，写锁占低16位
//         */
//        int c = getState();
//
//        /**
//         * 通过状态判断当前是写锁还是读锁，并判断一下持有锁的线程是否为当前线程，
//         * 若当前为写锁且持有锁的线程不是当前线程，则返回 -1，因为读写互斥
//         */
//        if (exclusiveCount(c) != 0 &&
//                getExclusiveOwnerThread() != current)
//            return -1;
//        // 若当前的锁状态是读锁，则通过锁状态得到一共有多少线程占有读锁
//        int r = sharedCount(c);
//
//        /**
//         * 如果满足读锁的条件，则线程不阻塞
//         */
//        if (!readerShouldBlock() &&
//                r < MAX_COUNT &&
//                compareAndSetState(c, c + SHARED_UNIT)) {
//            // 要是当前没有读线程占有锁，则设置第一个读线程为当前线程
//            if (r == 0) {
//                firstReader = current;
//                firstReaderHoldCount = 1;
//            // 如果当前线程就是读线程，则表示重入，当前线程的重入计数 +1
//            } else if (firstReader == current) {
//                firstReaderHoldCount++;
//            // 如果在当前线程之前已经有线程获得读锁
//            } else {
//                ReentrantReadWriteLock.Sync.HoldCounter rh = cachedHoldCounter;
//                if (rh == null || rh.tid != getThreadId(current))
//                    cachedHoldCounter = rh = readHolds.get();
//                else if (rh.count == 0)
//                    readHolds.set(rh);
//                rh.count++;
//            }
//            return 1;
//        }
//        return fullTryAcquireShared(current);
//    }
}
