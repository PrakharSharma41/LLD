public class Main {
    public static void main(String[] args) {
        Popularity popularity=new Popularity();
        System.out.println(popularity.getMaxPopular());
        popularity.increasePopularity(3);
        System.out.println(popularity.getMaxPopular());
        popularity.increasePopularity(4);
        popularity.increasePopularity(4);
        System.out.println(popularity.getMaxPopular());
        popularity.increasePopularity(3);
        popularity.increasePopularity(4);
        System.out.println(popularity.getMaxPopular());


        popularity.decreasePopularity(4);
        popularity.decreasePopularity(4);
        popularity.decreasePopularity(4);
        popularity.decreasePopularity(4);
        popularity.decreasePopularity(3);

        popularity.decreasePopularity(3);
        popularity.decreasePopularity(3);

        System.out.println(popularity.getMaxPopular());
    }
}
