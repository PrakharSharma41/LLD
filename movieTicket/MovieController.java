import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City,List<Movie>>cityVsMovies;
    List<Movie>allMovies;
    public MovieController() {
        cityVsMovies=new HashMap<>();
        allMovies=new ArrayList<>();
    }
    public Movie addMovie(String movieId,String title,String description,int durationInMinutes,City city){
        Movie movie=new Movie(movieId, title, description, durationInMinutes);
        List<Movie>mv=cityVsMovies.getOrDefault(city, new ArrayList<>());
        mv.add(movie);
        allMovies.add(movie);
        cityVsMovies.put(city, mv);
        return movie;
    }
    public Movie getMovieByName(String name){
        return null;
    }
}
