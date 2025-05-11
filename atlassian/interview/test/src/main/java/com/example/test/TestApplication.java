import java.util.Arrays;
import java.util.HashMap;

public class TestApplication {

	public static void main(String[] args) {
		Creator creator=new Creator();
		Node root=creator.create();		
		NodeManager nodeManager=new NodeManager(root);

		System.out.println(nodeManager.findClosestParent(Arrays.asList("mona","springs","hr")));
	}

}