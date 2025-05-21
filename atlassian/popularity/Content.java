import java.util.Objects;

public class Content{
    int contentId;
    int popularity=0;
    Content(int contentId){
        this.contentId=contentId;
    }
    public int getPopularity() {
        return popularity;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public int hashCode(){
        return Objects.hash(contentId);
    }

    @Override
    public boolean equals(Object o){
        if(o==null)return false;
        if(o==this)return true;
        Content content=(Content)o;
        if(content.contentId==this.contentId)return true;
        return false;
    }

}