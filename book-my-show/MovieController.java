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
    public void addMovie(Movie movie,City city){
        List<Movie>mv=cityVsMovies.getOrDefault(city, new ArrayList<>());
        mv.add(movie);
        allMovies.add(movie);
        cityVsMovies.put(city, mv);
    }
    public Movie getMovieByName(String name){
        return null;
    }
}
