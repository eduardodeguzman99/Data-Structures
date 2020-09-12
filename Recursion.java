 /***************************************************************/
 /* Program Name: Lab 1                                         */
 /*                                                             */
 /* Student Name: Eduardo Guzmán                                */
 /* Semester: Fall 2019                                         */
 /* Class-Section: CoSc 20203 - 055                             */
 /* Instructor: Dr.Rinewalt                                     */
/****************************************************************/ 

import java.util.Scanner;
import java.util.Arrays;

public class Lab1 {
	public static void main(String[] args) {
		while(true) {
			Scanner scr = new Scanner(System.in);
		
			// The first value is our maximum target value
			// The second value determines the number of entries/weights we need to check
			int target = scr.nextInt();
			int nValue = scr.nextInt();
		
			//Put the rest of the input values into an array of integers
			int[] arrInt = new int[nValue];
			for(int i=0; i<nValue; i++) {
				arrInt[i] = scr.nextInt();
			}
	
			//Get result from the recursive trySearch method 
			String x = trySearch(0,arrInt,target,target,0);
			
			if(x.endsWith(" - Full!")) {
				//If we found a perfect solution, print the string as it is
				System.out.println(x + "\n");
				
			} else {
				//Else trySearch returned the closest possible value to our target
				Scanner newScan = new Scanner(x);
				int bestTar = newScan.nextInt();
				
				//Call Recursive method to return the values for the closest value possible
				String y = trySearch(0,arrInt,bestTar,bestTar,0);
				y = y.substring(0, y.length()-8);
				
				//Print out the closest Solution, and state that there is No Solution
				System.out.println(y + " -- " + bestTar + " Is The Best I Could Do");
			}
		}
	}
		
	
	public static String trySearch(int index, int[] nums, int target, int fixedTarget, int closest) {
		//Base Case: If our target value is 0, then our knapsack is full
		//Base Case: If no more elements to check and have not filled the knapsack, return value for closest solution
		if(target == 0) return " - Full!";
		if(index >= nums.length) return closest + " - No Solution";
		
		//Get the first element in This array
		//Set the newTarget value as the difference between this element from the target value
		int element = nums[index];
		int newTarget = target - element;
		
		//If the sum does not exceed the initial target, then it is the closest value so far
		int sum = closest + element;
		if(sum <= fixedTarget) closest = sum;
		
		//If there is no solution with this element included, then exclude it and move down the array
		if(trySearch(index+1, nums, newTarget, fixedTarget, closest).endsWith(" - No Solution"))
			return "" + trySearch(index+1, nums, target, fixedTarget, closest);
		
		//Else solve the problem with this element included
		return element + " " + trySearch(index+1, nums, newTarget, fixedTarget, closest);
	}
}
