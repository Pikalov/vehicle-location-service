package com.digifico.vehiclelocationservice.persistence.repository;

import com.digifico.vehiclelocationservice.persistence.entity.Vehicle;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, Long> {

    List<Vehicle> findAllByLocationWithin(Box searchArea);

}
