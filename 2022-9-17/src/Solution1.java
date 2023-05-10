/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *  解决思路：
 *      方法一：暴力求解法
 *      方法二：使用一个双端队列，双端队列里的元素按照非递增的规则入队
 *             若入队的元素大于已经在队列内的元素，则将队列内的相应元
 *             素素移除。
 *             每当窗口移动时，都要判断移除窗口的元素和队列头部的元素
 *             是否相等，若相等则将队头元素出队
 */
public class Solution1 {
}