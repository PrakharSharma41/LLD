package RestaurantManagement.model;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    List<String>comments;
    List<Integer>allRatings;
    public int totalRatingSum=0;
    public Rating() {
        comments=new ArrayList<>();
        allRatings=new ArrayList<>();
    }
    public double getAverageRating() {
        return totalRatingSum / (allRatings.size() * 1.0);
    }
    public void addRatingAndComment(String comment, int ratingScore) {
        comments.add(comment);
        allRatings.add(ratingScore);
        totalRatingSum += ratingScore;
    }

}
