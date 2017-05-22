/*BAEKJOON 1406*/
/*https://www.acmicpc.net/problem/1406*/
/*한글설명 : http://potter777777.blog.me/221005436658*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List list = new List();
		list.init(br.readLine());
		int testcase = Integer.parseInt(br.readLine());
		String command = "";
		for(int i=0; i<testcase; i++) {
			command = br.readLine();
			if(command.charAt(0) == 'P')
				list.addLeftNode(command.charAt(2));
			else if(command.charAt(0) == 'L')
				list.curserGoLeft();
			else if(command.charAt(0) == 'D')
				list.curserGoRight();
			else if(command.charAt(0) == 'B')
				list.delLeftNode();
		}
		list.print();
	}
}

class Node {
	private char data;
	private Node prev;
	private Node next;
	//노드의 생성 : null <- data -> null
	public Node(char data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
	public char getData() {
		return this.data;
	}
	public Node getNext() {
		return this.next;
	}
	public Node getPrev() {
		return this.prev;
	}
	//처음 생성할 때에는 우측으로만 생성하므로 nextNode는 생각 x
    	//this <-> newNode
	public void setNext(Node newNode) {
		this.next = newNode;
		newNode.prev = this;
	}
    	//(root), null <- curserNode -> ...
	//prev 노드를 null로 마무리 짓는 메소드
	public void setPrevToNull() {
		this.prev = null;
	}
}

class List {
	private Node root;
	private Node curser; //커서를 의미하는 노드 포인터
	public List() {
		root = null;
		curser = null;
	}
	public void init(String initialString) {
		//초기 문자열 등록
		root = new Node(initialString.charAt(0));
		Node startNode = root; //지금 노드를 가리키는 노드 포인터
		for(int i=1; i<initialString.length(); i++) {
			startNode.setNext(new Node(initialString.charAt(i)));
			startNode = startNode.getNext();
		}
		Node dummyNode = new Node('T');
		curser = dummyNode;
		startNode.setNext(dummyNode);	//curser용 dummy노드를 끝에 추가
		//현재 상황 : abcdefg...T(<-curser)
	}
	//좌측 노드 추가하는 메소드
	public void addLeftNode(char data) {
		/*넣을 수 있는 상황 : 
		 * 01. ... <-> pre <-> curser <-> ...
		 * 02. curser(root) <-> ...*/
		Node newNode = new Node(data);
		//02.  curser(root) <-> ...
		if(curser == root) {
			newNode.setNext(curser);
			root = newNode;
		} else {
			//01. ... <-> pre <-> curser <-> ...
			Node preNode = curser.getPrev();
			preNode.setNext(newNode);
			newNode.setNext(curser);
		}
	}
	public void delLeftNode() {
		/*지울 수 있는 상황 : 
		 * 01. ... <-> prepre <-> pre <-> curser <-> ...
		 * 02. pre <-> curser <-> ...*/
		if(curser != root) {
			if(curser.getPrev() == root) {
				//(curser=root) <-> ...
				curser.setPrevToNull();
				root = curser;
			} else {
				//prepre <-> curser
				Node prepre = curser.getPrev().getPrev();
				prepre.setNext(curser);
			}
		}
	}
	//커서 좌측으로 옮기기
	public void curserGoLeft() {
		if(curser != root) {
			curser = curser.getPrev();
		}
	}
	//커서 우측으로 옮기기
	public void curserGoRight() {
		if(curser.getData() != 'T') {
			curser = curser.getNext();
		}
	}
	//테스트 겸용 전체 데이터 출력 메소드
  	//이 부분도 매번 print하면 비효율적이라 하나의 String을 완성하고 print는 한번만 호출.
	public void print() {
		Node current = root;
		String result = "";
		while(current.getData() != 'T') {
			result += current.getData();
			current = current.getNext();
		}
		System.out.print(result);
	}
}
