/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int n = mountainArr.length();

        // Step 1: Find the peak
        int peak = findPeak(mountainArr, n);

        // Step 2: Search in increasing part
        int left = binarySearchAscending(mountainArr, target, 0, peak);

        if (left != -1) {
            return left;
        }

        // Step 3: Search in decreasing part
        return binarySearchDescending(mountainArr, target, peak + 1, n - 1);
    }

    // Find the peak index
    private int findPeak(MountainArray arr, int n) {

        int low = 0;
        int high = n - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid) < arr.get(mid + 1)) {
                // We are on the increasing side
                low = mid + 1;
            } else {
                // We are on the decreasing side
                high = mid;
            }
        }

        return low;
    }

    // Binary search on increasing part
    private int binarySearchAscending(
            MountainArray arr,
            int target,
            int low,
            int high) {

        while (low <= high) {

            int mid = low + (high - low) / 2;
            int value = arr.get(mid);

            if (value == target) {
                return mid;
            }

            if (value < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Binary search on decreasing part
    private int binarySearchDescending(
            MountainArray arr,
            int target,
            int low,
            int high) {

        while (low <= high) {

            int mid = low + (high - low) / 2;
            int value = arr.get(mid);

            if (value == target) {
                return mid;
            }

            if (value > target) {
                // Because array is decreasing,
                // move right
                low = mid + 1;
            } else {
                // Move left
                high = mid - 1;
            }
        }

        return -1;
    }
}

