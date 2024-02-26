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
            int searchResult = searchInMap(remaining);
            if(searchResult != -1){
                result[0] = searchResult;
                result[1] = i;
                break;
            }else
                //Save a new entry in the map if validation is not accomplished.
                map.put(i,nums[i]);
        }
        return result;
    }
    /*Method that iterates over a map searching for a specific value and returns its key if the value is
    key if the value is present, in other case return -1.*/
    private int searchInMap(int remaining){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == remaining)
                return entry.getKey();
        }
        return -1;
    }
}