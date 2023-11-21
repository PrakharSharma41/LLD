import java.util.ArrayList;
import java.util.List;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;
    public void initialize(){
        createMovies();
    }
    public void createMovies(){
        Movie movie1 =new Movie("1", "movie1");
        Movie movie2 =new Movie("2", "movie2");    
        movieController.addMovie(movie2, City.Bangalore);
        movieController.addMovie(movie1, City.Delhi);
    }
    public void createTheatre(){
        Movie movie1=movieController.getMovieByName("movie1");
        Movie movie2=movieController.getMovieByName("movie2");
        Theatre theatre1=new Theatre(1,null,null,"address1");
        Theatre theatre2=new Theatre(2,null,null,"address2");
        theatre1.setScreen(createScreen());
        theatre2.setScreen(createScreen());
        List<Show>inoxshows=createShows(1,movie1,theatre1.getScreen().get(0));
        List<Show>pvrshows=createShows(2,movie2,theatre2.getScreen().get(0));
        theatre1.setShow(inoxshows);
        theatre2.setShow(pvrshows);        
    }
    public List<Show> createShows(int movieId,Movie movie, Screen screen){
        List<Show>shows=new ArrayList<>();
        shows.add(new Show(movieId, movie, screen, 12, 2, null));
        return shows;
    }
    public List<Screen> createScreen(){
        List<Screen> screens=new ArrayList<>();
        Screen screen1=new Screen();
        screen1.setScreenId(1);
        screen1.setSeats(createSeats());;
        return screens;
    }
    public List<Seat> createSeats(){
        List<Seat> seats = new ArrayList<>();

        //1 to 40 : SILVER
        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat(i,i,SeatCategory.NORMAL,SeatCategory.NORMAL.getDefaultValue());
            seats.add(seat);
        }

        //41 to 70 : SILVER
        for (int i = 40; i < 70; i++) {
            Seat seat = new Seat(i,i,SeatCategory.ROYAL,SeatCategory.ROYAL.getDefaultValue());
            seats.add(seat);
        }

        //1 to 40 : SILVER
        for (int i = 70; i < 100; i++) {
            Seat seat = new Seat(i,i,SeatCategory.RECLINER,SeatCategory.RECLINER.getDefaultValue());
            seats.add(seat);
        }

        return seats;
         
    }

}
