/*Hackerrank, The Love-Letter Mystery by amititkgp*/
/*https://www.hackerrank.com/challenges/the-love-letter-mystery*/
import java.util.Scanner;
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = Integer.parseInt(sc.nextLine());
		for(int testcaseIdx=0; testcaseIdx<testcase; testcaseIdx++) {
			String str = sc.nextLine();
			char[] arr = new char[str.length()];
			for(int i=0; i<str.length(); i++) {
				arr[i] = str.charAt(i);
			}

			int frontIdx = 0;
			int rearIdx = arr.length-1;
			int count = 0;
			while(frontIdx < rearIdx) {
				if(arr[frontIdx] < arr[rearIdx]) {
					while(arr[frontIdx] != arr[rearIdx]) {
						arr[rearIdx] = (char)(arr[rearIdx]-1);
						count++;
					}
				} else {
					while(arr[frontIdx] != arr[rearIdx]) {
						arr[frontIdx] = (char)(arr[frontIdx]-1);
						count++;
					}
				}
				frontIdx++;
				rearIdx--;
			}
			System.out.println(count);
		}
	}
}
