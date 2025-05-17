public class Test {
    public static void main(String[] args) {
        RouteNodeManager manager=new RouteNodeManager();
        manager.addRoute("/bar", "result");
        manager.addRoute("/bar/bar1", "result1");
        manager.addRoute("/foo/bar", "result2");
        manager.addRoute("/foo/*/bar", "result3");
        // manager.addRoute("/foo/a/bar", "result4");
        manager.addRoute("/foo/*/*", "result5");


        System.out.println(manager.callRoute("/bar"));
        System.out.println(manager.callRoute("/bar/bar1"));
        System.out.println(manager.callRoute("/foo/bar"));
        System.out.println(manager.callRoute("/foo/a/bar"));
        System.out.println(manager.callRoute("/foo/ab/bar1"));
        System.out.println(manager.callRoute("/foo/aasda/bar1"));



    }
}