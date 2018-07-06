package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>{
	
	@Query("select distinct s.location from Hotel s")
	List<String> findAllLocations();
	
	List<Hotel> findByLocation(String locatin);
}
