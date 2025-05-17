import java.util.HashMap;
import java.util.Map;

public class RouteNode{
    Map<String,RouteNode>childRoutes;
    String value;
    boolean isRegexAvailable;
    RouteNode(String value){
        this.value=value;
        isRegexAvailable=false;
        childRoutes=new HashMap<>();
    }
    public RouteNode getRouteNode(String key){
        return childRoutes.get(key);
    }
    public boolean isRouteNodePresent(String key){
        return childRoutes.containsKey(key)?true:false;
    }
    public void addRouteNode(String key,RouteNode node){
        if(key.equals("*")){
            setRegexAvailable();
        }
        childRoutes.put(key,node);
    }
    public String getValue(){
        return value;
    }
    public boolean isRegexAvailable(){
        return isRegexAvailable;
    }
    public void setRegexAvailable(){
        isRegexAvailable=true;
    }
    public void setValue(String value) {
        this.value=value;
    }
}
// /bar/*/baz