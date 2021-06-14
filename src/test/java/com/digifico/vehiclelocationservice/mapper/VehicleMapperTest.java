package com.digifico.vehiclelocationservice.mapper;

import com.digifico.vehiclelocationservice.dto.LocationPointDto;
import com.digifico.vehiclelocationservice.dto.VehicleDto;
import com.digifico.vehiclelocationservice.persistence.entity.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VehicleMapperTest {
    
    private static final String TEST_ID = "1";
    private static final Double TEST_LONGITUDE = 1.5d;
    private static final Double TEST_LATITUDE = -1.5d;

    private final VehicleMapper vehicleMapper = new VehicleMapperImpl();

    @Test
    @DisplayName("Should map VehicleDto to Vehicle entity")
    void shouldMapVehicleDtoToVehicleEntity() {
        LocationPointDto locationPointDto = new LocationPointDto();
        locationPointDto.setLongitude(TEST_LONGITUDE);
        locationPointDto.setLatitude(TEST_LATITUDE);
        VehicleDto dto = new VehicleDto(TEST_ID, locationPointDto);

        Vehicle entity = vehicleMapper.dtoToEntity(dto);

        assertNotNull(entity);
        assertEquals(TEST_ID, entity.getId());
        assertNotNull(entity.getLocation());
        assertEquals(TEST_LONGITUDE, entity.getLocation().getX());
        assertEquals(TEST_LATITUDE, entity.getLocation().getY());
    }

    @Test
    @DisplayName("Should map Vehicle entity to VehicleDto")
    void shouldMapVehicleEntityToVehicleDto() {
        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(TEST_LONGITUDE, TEST_LATITUDE);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(TEST_ID);
        vehicle.setLocation(geoJsonPoint);

        VehicleDto dto = vehicleMapper.entityToDto(vehicle);

        assertNotNull(dto);
        assertEquals(TEST_ID, dto.getId());
        assertNotNull(dto.getLocation());
        assertEquals(TEST_LONGITUDE, dto.getLocation().getLongitude());
        assertEquals(TEST_LATITUDE, dto.getLocation().getLatitude());
    }

    @Test
    @DisplayName("Should map null dto to null entity")
    void shouldMapNullDtoToNullEntity() {
        Vehicle entity = vehicleMapper.dtoToEntity(null);

        assertNull(entity);
    }

    @Test
    @DisplayName("Should map null entity to null dto")
    void shouldMapNullEntityToNullDto() {
        VehicleDto dto = vehicleMapper.entityToDto(null);

        assertNull(dto);
    }

    @Test
    @DisplayName("Should map dto with null fields to entity with null fields")
    void shouldMapDtoWithNullFieldsToEntityWithNullFields() {
        VehicleDto dto = new VehicleDto(null, null);

        Vehicle entity = vehicleMapper.dtoToEntity(dto);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getLocation());
    }

    @Test
    @DisplayName("Should map entity with null fields to dto with null fields")
    void shouldMapEntityWithNullFieldsToDtoWithNullFields() {
        Vehicle entity = new Vehicle();

        VehicleDto dto = vehicleMapper.entityToDto(entity);

        assertNotNull(dto);
        assertNull(dto.getId());
        assertNull(dto.getLocation());
    }
}
