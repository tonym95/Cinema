package com.sdproject.repositories;

import com.sdproject.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

}
