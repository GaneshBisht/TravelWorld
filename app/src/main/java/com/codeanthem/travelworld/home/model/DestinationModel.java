package com.codeanthem.travelworld.home.model;

public class DestinationModel {

    private int id;
    private int category;
    private String name;
    private String place;
    private int starRating;
    private double price;
    private double rating;
    private double ratingText;
    private String cashbackOffer;
    private String redeemOffer;

    public DestinationModel(int id, int category) {
        this.id = id;
        this.category = category;
    }

    public DestinationModel(int id, int category, String name, String place, int starRating, double price, double rating, double ratingText, String cashbackOffer, String redeemOffer) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.place = place;
        this.starRating = starRating;
        this.price = price;
        this.rating = rating;
        this.ratingText = ratingText;
        this.cashbackOffer = cashbackOffer;
        this.redeemOffer = redeemOffer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRatingText() {
        return ratingText;
    }

    public void setRatingText(double ratingText) {
        this.ratingText = ratingText;
    }

    public String getCashbackOffer() {
        return cashbackOffer;
    }

    public void setCashbackOffer(String cashbackOffer) {
        this.cashbackOffer = cashbackOffer;
    }

    public String getRedeemOffer() {
        return redeemOffer;
    }

    public void setRedeemOffer(String redeemOffer) {
        this.redeemOffer = redeemOffer;
    }
}
