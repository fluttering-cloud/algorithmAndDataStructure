import java.util.LinkedHashMap;

public class Solution01 {

    public char firstUniqChar(String s) {
        LinkedHashMap<Character,Boolean> map = new LinkedHashMap();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (map.containsKey(ch)){
                map.put(ch,false);
            }else {
                map.put(ch,true);
            }
        }

        for (char ch : chars) {
            if (map.get(ch)){
                return ch;
            }
        }
        return ' ';
    }


    public int search(int[] nums, int target) {

        if (nums.length == 0 || nums == null) return 0;

        int l = 0;
        int r = nums.length - 1;
        int mid = l + (r - l) / 2;
        //while (l != r && nums[mid] != target && l < r )
        while (l < r && nums[mid] != target ){
            if (nums[mid] > target){
                r = mid - 1;
            } else if (nums[mid] < target){
                l = mid + 1;
            }
            mid = l + (r - l) / 2;
        }
        if (nums[mid] != target) return 0;


        int left = 0;
        int right = nums.length - 1;
        int lmid = mid;
        int rmid = mid;

        while ( lmid != left){
            if (nums[left + (lmid - left) / 2] == target){
                lmid = left + (lmid - left) / 2;
            }else {
                left = left + (lmid - left) / 2 + 1;
            }
        }

        while ( (right - rmid) > 1) {
            if (nums[rmid + (right - rmid) / 2] == target){
                rmid = rmid + (right - rmid) / 2;
            }else {
                right = rmid + (right - rmid) / 2;
            }
        }
        if (nums[right] == target) right++;

        return right - left;
    }


    public int missingNumber(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if (mid == nums[mid] ) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        Solution01 s = new Solution01();
        //int[] array = {5,7,7,8,8,10};
        int[] array = {2,2};
        System.out.println(s.search(array,1));
    }
}
