package seperateChaining;

import java.util.Scanner;

public class HashSeperateChaining {
	private int sizeOfTable = 0, sizeOfList = 0;
	private Scanner input = new Scanner(System.in);
	
	public int getTableSize() {
		return sizeOfTable;
	}
	
	public void setListSize() {
		do {
			System.out.println("How mant numbers would you like in your table? ");
			sizeOfList = input.nextInt();
			if(sizeOfList > 100 || sizeOfList <=0) {
				System.out.println("Sorry enter better numbers for the table please. ");
			} 
		} while (sizeOfList > 100 || sizeOfList < 0);
		
	}
}
