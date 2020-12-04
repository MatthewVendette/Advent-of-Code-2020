package dayThree;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {

	private static int treesPartOne(ArrayList<char[]> landscape) {
		int trees = 0;
		int x = 0;
		final int LENGTH = landscape.get(0).length; //assuming all entries are the same (they are in this case)
		
		for (char[] row: landscape) {
			if (row[x] == '#') {
				trees++;
			}

//			System.out.print(new String(row) + "\t\t");
//			row[x] = (row[x] == '#' ? 'X' : 'O');
//			System.out.println(new String(row) + "\t\tTREES: " + trees);
			
			x = (x + 3) % LENGTH;
		}
		
		return trees;
	}

	
	private static BigInteger treesPartTwo(ArrayList<char[]> landscape, int rightValue, int downValue) {
		int trees = 0;
		int x = 0;
		final int LENGTH = landscape.get(0).length; //assuming all entries are the same (they are in this case)
		
		//Way easier to use traditional for loop in this case
		for (int i = 0 ; i < landscape.size() ; i+= downValue) {
			
			char[] row = landscape.get(i);
			
			if (row[x] == '#') {
				trees++;
			}
			
			x = (x + rightValue) % LENGTH;
			
		}
		
		return new BigInteger(Integer.toString(trees)); //why is there no BigInteger(int)? I'm sure smart people have a reason but...
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = null;
		File input = new File(".\\resources\\DayThree_Input.txt");
		ArrayList<char[]> landscape = new ArrayList<char[]>();
		
		sc = new Scanner(input);
		
		while(sc.hasNextLine()) {
			landscape.add(sc.nextLine().toCharArray());
		}
		
		int partOne = treesPartOne(landscape);
		System.out.println("Part one encounters " + partOne + " trees.");

		BigInteger answer = treesPartTwo(landscape,1,1); //need BigInteger because these numbers are chonky
		answer = answer.multiply(treesPartTwo(landscape,3,1));
		answer = answer.multiply(treesPartTwo(landscape,5,1));
		answer = answer.multiply(treesPartTwo(landscape,7,1));
		answer = answer.multiply(treesPartTwo(landscape,1,2));

		System.out.println("Part two multiplies to " + answer);
		
		sc.close();
	}
}
