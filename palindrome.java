/*백준 10988(palindrome)*/
/*https://www.acmicpc.net/problem/10988*/
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Stack stack = new Stack(str.length());
		int secondStartIdx = 0;
		if(str.length()%2 == 1)
			secondStartIdx = str.length()/2+1;
		else
			secondStartIdx = str.length()/2;
		
		for(int i=0; i<=str.length()/2-1; i++) {
			stack.push(str.charAt(i));
		}
		for(int i=secondStartIdx; i<str.length(); i++) {
			if(stack.getTop() == str.charAt(i)) {
				stack.pop();
			}
		}
		if(stack.isEmpty() == true)
			System.out.print(1);
		else
			System.out.print(0);
	}
}

class Stack {
	private char[] arr;
	private int topIdx;
	public Stack(int max) {
		arr = new char[max];
		topIdx = -1;
	}
	public boolean isEmpty() {
		if(topIdx != -1)
			return false;
		else
			return true;
	}
	public void push(char data) {
		arr[++topIdx] = data;
	}
	public void pop() {
		if(!isEmpty())
			topIdx--;
	}
	public char getTop() {
		if(!isEmpty())
			return arr[topIdx];
		else
			return '-';
	}
}
