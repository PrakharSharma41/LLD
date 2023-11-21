import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {

    Map<City,List<Theatre>>cityVsTheatre;
    List<Theatre>allTheatres;
    public TheatreController() {
        cityVsTheatre=new HashMap<>();
        allTheatres=new ArrayList<>();
    }    
    public void addTheatre(Theatre theatre,City city ){
        allTheatres.add(theatre);
        List<Theatre> theatres=cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }
    public Map<Theatre,List<Show>> getAllShow(Movie movie, City city){
        List<Theatre>cityTheatre=cityVsTheatre.get(city);
        Map<Theatre,List<Show>>theatreVsShow=new HashMap<>();
        for(Theatre th:cityTheatre){
            List<Show>shows=new ArrayList<>();
            for(Show sh:th.show){
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
