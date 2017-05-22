/*백준 2667(DFS, BFS)*/
/*https://www.acmicpc.net/problem/2667*/
import java.util.Arrays;
import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		Graph graph = new Graph(N);
		
		for(int i=0; i<N; i++) {
			String num = sc.nextLine();
			for(int j=0; j<N; j++) {
				graph.setGraph(i, j, num.charAt(j)-48);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph.checkTarget(i,j) == true)
					graph.bfs(i, j);
			}
		}
		graph.getResultArr();
	}
}

class Graph {
	private int[][] matrix;
	private int vertexN;
	private boolean[][] checkList;
	int[] resultArr;
	int idx=0;
	
	Graph(int N) {
		matrix = new int[N][N];
		this.vertexN = N;
		checkList = new boolean[N][N];
		resultArr = new int[N*N];
	}
	void setGraph(int i, int j, int data) {
		matrix[i][j] = data;
	}
	void printAll() {
		for(int i=0; i<vertexN; i++) {
			for(int j=0; j<vertexN; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	void getResultArr() {
		int cnt = 0;
		Arrays.sort(resultArr);
		for(int i=0; i<resultArr.length; i++) {
			if(resultArr[i] != 0)
				cnt++;
		}
		System.out.println(cnt);
		for(int i=resultArr.length-cnt; i<resultArr.length; i++) {
			System.out.println(resultArr[i]);
		}
	}
	boolean checkTarget(int x, int y) {
		if(matrix[x][y] == 1 && checkList[x][y] == false)
			return true;
		else
			return false;
	}
	void bfs(int startX, int startY) {
		Queue queue = new Queue();
		int counter = 1;
		checkList[startX][startY] = true;
		queue.enQueue(new XY(startX, startY));
		
		XY currentXY = null;
		
		while(queue.isEmpty() == false) {
			currentXY = queue.deQueue();
			
			int x = currentXY.getX() + 1;
			int y = currentXY.getY();
			if(x>=0 && x<vertexN && y>=0 && y<vertexN) {
				if(checkList[x][y] == false && matrix[x][y] == 1) {
					queue.enQueue(new XY(x,y));
					checkList[x][y] = true;
					counter++;
				}
			}
			x = currentXY.getX();
			y = currentXY.getY() + 1;
			if(x>=0 && x<vertexN && y>=0 && y<vertexN) {
				if(checkList[x][y] == false && matrix[x][y] == 1) {
					queue.enQueue(new XY(x,y));
					checkList[x][y] = true;
					counter++;
				}
			}
			x = currentXY.getX() - 1;
			y = currentXY.getY();
			if(x>=0 && x<vertexN && y>=0 && y<vertexN) {
				if(checkList[x][y] == false && matrix[x][y] == 1) {
					queue.enQueue(new XY(x,y));
					checkList[x][y] = true;
					counter++;
				}
			}
			x = currentXY.getX();
			y = currentXY.getY() - 1;
			if(x>=0 && x<vertexN && y>=0 && y<vertexN) {
				if(checkList[x][y] == false && matrix[x][y] == 1) {
					queue.enQueue(new XY(x,y));
					checkList[x][y] = true;
					counter++;
				}
			}
		}
		//if(counter != 1)
			resultArr[idx++] = counter;
	}
}

class XY {
	int x;
	int y;
	XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
}

class Node {
	private XY data;
	private Node next;
	public Node(XY data) {
		this.data = data;
		this.next = null;
	}
	public XY getData() {
		return this.data;
	}
	public void setData(XY data) {
		this.data = data;
	}
	public Node getNext() {
		return this.next;
	}
	public void setNext(XY data) {
		this.next = new Node(data);
	}
}

class Queue {
	private Node root;
	public Queue() {
		root = null;
	}
	public void enQueue(XY data) {
		if(root == null) {
			root = new Node(data);
		} else {
			Node current = root;
			Node previous = null;
			while(current != null) {
				previous = current;
				current = current.getNext();
			}
			previous.setNext(data);
		}
	}
	public XY deQueue() {
		XY returnVal = root.getData();
		root = root.getNext();
		return returnVal;
	}
	public boolean isEmpty() {
		if(root == null)
			return true;
		else
			return false;
	}
}
