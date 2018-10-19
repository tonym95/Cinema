package com.sdproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Movie implements Serializable {

    @Id
    private Integer id;
    private String name;
    private Integer year;
    private String genre;
    private Double rating;
    private Integer votesNo;

    @ManyToMany
    private Collection<Cinema> cinemas = new ArrayList<Cinema>();

    @OneToMany
    private Collection<Reservation> reservations = new ArrayList<Reservation>();

    public Movie(Integer id, String name, Integer year, String genre, Collection<Cinema> cinemas,
    Collection<Reservation> reservations) {

        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.cinemas = cinemas;
        this.reservations = reservations;

    }

    public void computeRating(Double newRating) {
        this.votesNo++;

        this.rating = (this.rating + newRating) / this.votesNo;
    }

    public Movie() {}

    public Collection<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(Collection<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getVotesNo() {
        return votesNo;
    }

    public void setVotesNo(Integer votesNo) {
        this.votesNo = votesNo;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                '}';
    }
}
