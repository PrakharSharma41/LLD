package atlassian.interview.test.src.main.java.com.example.test;

import java.util.Arrays;
import java.util.HashMap;

public class TestApplication {

	public static void main(String[] args) {
		Creator creator=new Creator();
		Node root=creator.create();		
		NodeManager nodeManager=new NodeManager(root);

		System.out.println(nodeManager.findClosestParent(Arrays.asList("mona","springs","alice")));
	}

}
// class Node{
// 	String value
// 	Node[] children
// }

// mona,alice,lisa

// ans company

// i=1
// mona company,hr
// alice company,engg,be
// lisa company,,engg,fe