package source.kevtimov.landlordcommunicationapp.models;

import java.io.Serializable;

public class Rating implements Serializable {


    private int ratingID;
    private int voteFor;
    private int voteFrom;
    private double rating;


    public Rating(){
        //default
    }

    public Rating(int voteFor, int voteFrom, double rating){
        setVoteFor(voteFor);
        setVoteFrom(voteFrom);
        setRating(rating);
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public int getVoteFor() {
        return voteFor;
    }

    private void setVoteFor(int voteFor) {
        this.voteFor = voteFor;
    }

    public int getVoteFrom() {
        return voteFrom;
    }

    private void setVoteFrom(int voteFrom) {
        this.voteFrom = voteFrom;
    }

    public double getRating() {
        return rating;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }
}
