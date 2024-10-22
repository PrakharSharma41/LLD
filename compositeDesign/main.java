package compositeDesign;

public class main {
    public static void main(String args[]){

        Directory movieDirectory = new Directory("Movie",SystemType.Directory);
 
        FileSystem border = new File("Border",SystemType.File);
        movieDirectory.add(border);
 
        Directory comedyMovieDirectory = new Directory("ComedyMovie",SystemType.Directory);
        File hulchul = new File("Hulchul",SystemType.File);
         comedyMovieDirectory.add(hulchul);
         movieDirectory.add(comedyMovieDirectory);
 
         movieDirectory.ls();
 
     }
     
}
