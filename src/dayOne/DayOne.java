package dayOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DayOne {

	private final static String separator = "#######################";

	private static int findTwentyTwentyPartOne(ArrayList<Integer> entries) {
		
		//quick and dirty, compare every entry with every next entry
		//potential to do this a lot cleaner but this is by far the simplest way. For a larger data set I would definitely try to clean this up.
		for (int i = 0; i < entries.size(); i++) {
			for (int j = i; j < entries.size(); j++) {
				
				int val1 = entries.get(i);
				int val2 = entries.get(j);
				int sum = val1 + val2;
				
				if (sum == 2020) {
					System.out.println(separator);
					System.out.println("FIRST ENTRY: " + entries.get(i));
					System.out.println("SECOND ENTRY: " + entries.get(j));
					
					return val1 * val2;
				}
			}
		}
		
		return -1;
	}

	private static int findTwentyTwentyPartTwo(ArrayList<Integer> entries) {
		//Bit trickier for part two. To be more efficient, need to be a bit more clever.
		//First sort the list.
		Collections.sort(entries);
		
		for (int i = 0 ; i < entries.size(); i++) {
			//Sum entries[i] with entries[i+1] and the last value of entries. If it's too small, check i+2. If it's too big, check the second to last variable. Keep repeating until the two values pass, then move on.
			//Compute time of O(n^2), I believe.
			int j = i+1;
			int k = entries.size()-1;
			while (j < k) {
				int val1 = entries.get(i);
				int val2 = entries.get(j);
				int val3 = entries.get(k);
				int sum = val1 + val2 + val3;
				
				if (sum == 2020) {
					System.out.println(separator);
					System.out.println("FIRST ENTRY: " + entries.get(i));
					System.out.println("SECOND ENTRY: " + entries.get(j));
					System.out.println("THIRD ENTRY: " + entries.get(k));
					
					return val1 * val2 * val3;
					
				} 
				
				else if (sum > 2020) {
					k--;
				} 
				
				else if (sum < 2020) {
					j++;
				}
			}
		}
		
		return -1;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = null;
		File input = new File(".\\resources\\DayOne_Input.txt");
		ArrayList<Integer> entries = new ArrayList<Integer>();
		
		sc = new Scanner(input);

		while (sc.hasNextInt()) {
			Integer nextInt = sc.nextInt();
			entries.add(nextInt);
		}
		
		int partOne = findTwentyTwentyPartOne(entries);
		System.out.println("\nAnswer is " + partOne);
		
		int  partTwo = findTwentyTwentyPartTwo(entries);
		System.out.println("\nAnswer is " + partTwo);
		
		System.out.println(separator);
		sc.close();
	}
}
