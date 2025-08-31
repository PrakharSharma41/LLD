package pullBased;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Topic {
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Topic other = (Topic) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    String name;
    List<Message>messages;
    Set<Consumer>consumers; // for topic we dont need to maintain this set because in consumer
    // we are already having map for topic to offset map
    Topic(String name){
        this.name=name;
        messages=new CopyOnWriteArrayList<>();
        consumers=new CopyOnWriteArraySet<>();
    }
    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }
    public void removeConsumer(Consumer consumer){
        consumers.remove(consumer);
    }
    public Message readFrom(int offset){
        if(offset==messages.size())return null;
        return messages.get(offset);
    }
    public void addMessage(Message message){
        messages.add(message);
    }
}
