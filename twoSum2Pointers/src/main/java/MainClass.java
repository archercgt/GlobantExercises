public class MainClass {
    public static void main(String[] args) {
        //Define the array for the test
        int[] array = new int[]{2,7,11,15};
        //Define the target for the test
        int target = 9;
        Solution solution = new Solution();
        //Print the elements of the array that the twoSum method returns
        for(int element: solution.twoSum(array, target)){
            System.out.println(element);
        }
    }
}

//This class must be copied and pasted in LeetCode
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++; // Move the left pointer to increase the sum
            } else {
                right--; // Move the right pointer to decrease the sum
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
