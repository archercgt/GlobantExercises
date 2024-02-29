import java.util.HashMap;
import java.util.Map;

public class MainClass {
    public static void main(String[] args) {
        //Define the array for the test
        int[] array = new int[]{3,3};
        //Define the target for the test
        int target = 6;
        Solution solution = new Solution();
        //Print the elements of the array that the twoSum method returns
        for(int element: solution.twoSum(array, target)){
            System.out.println(element);
        }
    }
}

//This class must be copied and pasted in LeetCode
class Solution {
    private final static Map<Integer, Integer> map = new HashMap<>();
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        // Check if the map already have a value that summed with the current value equals the target.
        for(int i = 0; i < nums.length; i++){
            //Define the value of the number that should be found in the map.
            int remaining = target - nums[i];
            if (map.containsKey(remaining)){
                result[0] = map.get(remaining);
                result[1] = i;
                break;
            }else
                map.put(nums[i], i);
        }
        return result;
    }
}