package com.hashedin.hu22.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.List;
import java.util.Collection;

@Entity(name = "movie")
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String language;

    private String genre;

    private String description;

    private String producer;

    private String cast;

    private String duration;

    private Integer rating;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id" , referencedColumnName = "id")
    private List<Rating> ratingList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id" , referencedColumnName = "id")
    private List<Threatre> threatres = new ArrayList<>();

    private ArrayList<Date> timing;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Threatre> getThreatres() {
        return threatres;
    }

    public void setThreatres(List<Threatre> threatres) {
        this.threatres = threatres;
    }

    public ArrayList<Date> getTiming() {
        return timing;
    }

    public void setTiming(ArrayList<Date> timing) {
        this.timing = timing;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
