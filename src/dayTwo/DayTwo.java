package dayTwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {

	private static int validPasswordsPartOne(ArrayList<String[]> pw) {
		int validPasswords = 0;
		
		for (String[] entry: pw) {
			int min = Integer.parseInt(entry[0]);
			int max = Integer.parseInt(entry[1]);
			char c = entry[2].charAt(0);
			String pass= entry[3];
			
			//count how many times the special character occurs in the password using magic bullshit AKA lambda functions
			int occurences = (int) pass.chars().filter(ch -> ch == c).count();
			
			if (occurences >= min && occurences <= max) {
				validPasswords++;
			}
		}
		
		return validPasswords;
	}

	
	private static int validPasswordsPartTwo(ArrayList<String[]> pw) {
		//Seems easier than part one. Check the string at both locations, increment if only one contains the letter.
		int validPasswords = 0;
		
		for (String[] entry: pw) {
			int val1 = Integer.parseInt(entry[0]) -1; //Not using 0 based indexing is just awful.
			int val2 = Integer.parseInt(entry[1]) -1;
			char c = entry[2].charAt(0);
			String pass= entry[3];

			//XOR is nice and elegant here.
			if (pass.charAt(val1) == c ^ pass.charAt(val2) == c) {
				validPasswords++;
			}
			
		}
		return validPasswords;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = null;
		File input = new File(".\\resources\\DayTwo_Input.txt");
		ArrayList<String[]> pw = new ArrayList<String[]>();
		
		sc = new Scanner(input);
		
		while(sc.hasNextLine()) {
			String[] entry = new String[4]; //first: Minimum allowed; second: Maximum allowed; third: Qualifying letter; fourth: Password
			String[] minMax = sc.next().split("-");
			
			entry[0] = minMax[0];
			entry[1] = minMax[1];
			entry[2] = sc.next().replace(":", ""); //remove colon
			entry[3] = sc.next();
			
			pw.add(entry);
		}

		int partOne = validPasswordsPartOne(pw);
		System.out.println("Part one answer is " + partOne + "/" + pw.size());
		
		int partTwo = validPasswordsPartTwo(pw);
		System.out.println("Part two answer is " + partTwo + "/" + pw.size());
		
		sc.close();
	}
}
