package com.sdproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cinema")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Cinema implements Serializable {

    @Id
    private Integer id;
    private String address;
    private String name;

    @ManyToMany
    private Collection<Movie> movies = new ArrayList<Movie>();

    public Cinema(Integer id, String address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public Cinema() {}

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
