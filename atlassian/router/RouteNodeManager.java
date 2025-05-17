public class RouteNodeManager {
    RouteNode root;
    RouteNodeManager(){
        root=new RouteNode("-1");
    }
    public boolean addRoute(String route,String value){
        RouteNode head=root;
        String[] routes=route.split("/");
        for(int i=1;i<routes.length;i++){
            String r=routes[i];
            if(head.isRouteNodePresent(r)==false){
                head.addRouteNode(r, new RouteNode(r));
            }
            head=head.getRouteNode(r);
        }
        head.setValue(value);
        return true;
    }
    public String callRoute(String route){
        RouteNode head=root;
        String[] routes=route.split("/");
        for(int i=1;i<routes.length;i++){
            String r=routes[i];
            if(head.isRouteNodePresent(r))head=head.getRouteNode(r);
            else{
                if(head.isRegexAvailable()){
                    head=head.getRouteNode("*");
                }
                else return null;
            }
        }
        return head.getValue();
    }
}

// /a/b/c