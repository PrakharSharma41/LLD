
public class Creator {
    public Node create(){
		Node root=new Node("company");

        Node hr=new Node("hr");
		Node mona=new Node("mona");
		Node springs=new Node("springs");
        hr.setChildren(new Node[]{mona,springs});

        Node engg=new Node("engg");

        
		Node be=new Node("be");
		Node fe=new Node("fe");
        engg.setChildren(new Node[]{be,fe});

		Node alice=new Node("alice");
		Node bob=new Node("bob");
        be.setChildren(new Node[]{alice,bob});

		Node lisa=new Node("lisa");
		Node marley=new Node("marley");
        fe.setChildren(new Node[]{lisa,marley});


		root.setChildren(new Node[]{hr,engg});
        return root;
    }
}
