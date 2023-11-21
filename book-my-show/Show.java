import java.util.List;

public class Show {
    int showId;
    Movie movie;
    Screen screen;
    int showStartTime;
    int duration;
    public Show(int showId, Movie movie, Screen screen, int showStartTime, int duration, List<Seat> bookedSeats) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showStartTime = showStartTime;
        this.duration = duration;
        this.bookedSeats = bookedSeats;
    }
    List<Seat> bookedSeats;
    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }
    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
    public int getShowId() {
        return showId;
    }
    public void setShowId(int showId) {
        this.showId = showId;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public Screen getScreen() {
        return screen;
    }
    public void setScreen(Screen screen) {
        this.screen = screen;
    }
    public int getShowStartTime() {
        return showStartTime;
    }
    public void setShowStartTime(int showStartTime) {
        this.showStartTime = showStartTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}
