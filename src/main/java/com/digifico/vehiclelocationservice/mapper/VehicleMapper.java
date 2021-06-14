package com.digifico.vehiclelocationservice.mapper;

import com.digifico.vehiclelocationservice.dto.LocationPointDto;
import com.digifico.vehiclelocationservice.dto.VehicleDto;
import com.digifico.vehiclelocationservice.persistence.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    /*
    Using of mapstruct to map such simple entities might look as some kind of over-engineered solution, but
    mapstruct is very useful in terms of scalability (changing the number of attributes to map mostly require
    far less effort to map them as it automatically maps simple attributes such as id, name etc).
    */

    @Mappings(
            @Mapping(target = "location", source = "location", qualifiedByName = "locationDtoToGeoJson")
    )
    Vehicle dtoToEntity(VehicleDto dto);

    @Mappings(
            @Mapping(target = "location", source = "location", qualifiedByName = "geoJsonToLocationDto")
    )
    VehicleDto entityToDto(Vehicle entity);

    @Named("locationDtoToGeoJson")
    default GeoJsonPoint LocationPointDtoToGeoJsonPoint(LocationPointDto locationPointDto) {
        GeoJsonPoint geoJsonPoint = null;
        if (Objects.nonNull(locationPointDto)) {
            geoJsonPoint = new GeoJsonPoint(locationPointDto.getLongitude(), locationPointDto.getLatitude());
        }
        return geoJsonPoint;
    }

    @Named("geoJsonToLocationDto")
    default LocationPointDto geoJsonPointToLocationPointDto(GeoJsonPoint geoJsonPoint) {
        LocationPointDto locationPointDto = null;
        if (Objects.nonNull(geoJsonPoint)) {
            locationPointDto = new LocationPointDto();
            locationPointDto.setLongitude(geoJsonPoint.getX());
            locationPointDto.setLatitude(geoJsonPoint.getY());
        }
        return locationPointDto;
    }

}
