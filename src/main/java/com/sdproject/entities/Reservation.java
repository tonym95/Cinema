package com.sdproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reservation")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Reservation implements Serializable {

    @Id
    private Integer reservationId;
    private Integer seatNo;

    @ManyToOne
    private Client client = new Client();

    @ManyToOne
    private Movie movie = new Movie();

  /*  public Reservation(Integer reservationId, Integer seatNo, Integer clientId, Integer movieId) {
        this.reservationId = reservationId;
        this.seatNo = seatNo;
        client.setId(clientId);
        movie.setId(movieId);
    }
*/
    public Reservation() {}

    public Integer getReservationId() {
        return reservationId;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", seatNo=" + seatNo +
                ", clientId=" + client.getId() +
                ", movieId=" + movie.getId() +
                '}';
    }
}
