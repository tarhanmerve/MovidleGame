package com.example.movidlegame;

public class Film {
    private String Title;
    private int Year;
    private String Genre;
    private String Origin;
    private String Director;
    private String Star;

    public Film(String Title, int Year, String Genre, String Origin, String Director, String Star){
        this.Title = Title;
        this.Year = Year;
        this.Genre = Genre;
        this.Director = Director;
        this.Origin = Origin;
        this.Star = Star;
    }
    public String getTitle(){return Title;}
    public String getGenre() {return Genre;}
    public String getOrigin(){return Origin;}
    public String getDirector(){return Director;}
    public String getStar(){return Star;}
    public int getYear(){return Year;}

    @Override
    public String toString() {
        return Title + " " + Year + " " + Genre + " " + Director + " " + Origin + " " + Star;
    }

}