import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

    Map<City,List<Theater>>cityVsTheatre;
    List<Theater>allTheatres;
    public TheatreController() {
        cityVsTheatre=new HashMap<>();
        allTheatres=new ArrayList<>();
    }    
    public void addTheatre(String id, String name, String location, List<Show> shows,City city ){
        Theater theatre=new Theater(id, name, location, shows);
        allTheatres.add(theatre);
        List<Theater> theatres=cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }
    public Map<Theater,List<Show>> getAllShow(Movie movie, City city){
        List<Theater>cityTheatre=cityVsTheatre.get(city);
        Map<Theater,List<Show>>theatreVsShow=new HashMap<>();
        for(Theater th:cityTheatre){
            List<Show>shows=new ArrayList<>();
            for(Show sh:th.shows){
                if(sh.movie.getId()==movie.getId()){
                    shows.add(sh);
                }
            }
            if(!shows.isEmpty()){
                theatreVsShow.put(th, shows);
            }
        }
        return theatreVsShow;
    }
    
}
