import java.util.*;

public class Solution {

    public Integer[] solution(int segmentLength, int maxNumber, int[] array) {
        int[] repetitions = new int[maxNumber + 2];
        Set<Integer> result = new HashSet<>();

        for (int element : array) {
            repetitions[element]++;
        }
        // check the array as it comes (not sure if I have to do it)
        int leader = checkLeader(repetitions, array.length / 2);
        if (leader != -1) {
            result.add(leader);
        }
        // set the segment in position 0
        for (int i = 0; i < segmentLength; ++i) {
            repetitions[array[i]]--;
            repetitions[array[i] + 1]++;
        }
        leader = checkLeader(repetitions, array.length / 2);
        if (leader != -1) {
            result.add(leader);
        }

        // put segment at position i
        for (int i = 1; i < array.length - segmentLength; ++i) {
            // restore previous number
            repetitions[array[i - 1]]++;
            repetitions[array[i - 1] + 1]--;

            // put the new value
            repetitions[array[i + segmentLength - 1]]--;
            repetitions[array[i + segmentLength - 1] + 1]++;

            // check if the number before segment is a leader
            if (repetitions[array[i - 1]] > array.length / 2) {
                result.add(array[i - 1]);
            }
            // check if the last segment number is a leader
            if (repetitions[array[i + segmentLength - 1] + 1] > array.length / 2) {
                result.add(array[i + segmentLength - 1] + 1);
            }
        }
        Integer[] resultList = result.toArray(new Integer[0]);
        resultList = new Integer[]{2,3,53,5,1,23};
        Arrays.sort(resultList);
        return resultList;
    }

    private int checkLeader(int[] repetitions, int greaterThan) {
        for (int i = 0; i < repetitions.length; ++i) {
            if (repetitions[i] > greaterThan) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Integer[] result = s.solution(3, 5, new int[]{2, 1, 3, 1, 2, 2, 3});
        System.out.println("finish");
    }

}
