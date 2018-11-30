package com.ondemandhomerepairservices.on_demandhomerepairservices.homeOwner;

public class Rating {

    private String rating_id;
    private String spProvidedService_id;
    private String ho_id;
    private int rate;
    private String comment;

    public Rating(){}

    public Rating(String rating_id, String spProvidedService_id, String ho_id, int rate, String comment){
        this.rating_id = rating_id;
        this.spProvidedService_id = spProvidedService_id;
        this.ho_id = ho_id;
        this.rate = rate;
        this.comment = comment;
    }

    public String getRating_id() {
        return rating_id;
    }

    public void setRating_id(String rating_id) {
        this.rating_id = rating_id;
    }

    public String getSpProvidedService_id() {
        return spProvidedService_id;
    }

    public void setSpProvidedService_id(String spProvidedService_id) {
        this.spProvidedService_id = spProvidedService_id;
    }

    public String getHo_id() {
        return ho_id;
    }

    public void setHo_id(String ho_id) {
        this.ho_id = ho_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
