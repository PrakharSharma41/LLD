import java.util.List;

public class Theatre {
    int theatreId;
    List<Show>show;
    List<Screen>screen;
    String address;
    public int getTheatreId() {
        return theatreId;
    }
    public Theatre(int theatreId, List<Show> show, List<Screen> screen, String address) {
        this.theatreId = theatreId;
        this.show = show;
        this.screen = screen;
        this.address = address;
    }
    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }
    public List<Show> getShow() {
        return show;
    }
    public void setShow(List<Show> show) {
        this.show = show;
    }
    public List<Screen> getScreen() {
        return screen;
    }
    public void setScreen(List<Screen> screen) {
        this.screen = screen;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
