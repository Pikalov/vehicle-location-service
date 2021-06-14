package com.digifico.vehiclelocationservice.service;

import com.digifico.vehiclelocationservice.dto.LocationPointDto;
import com.digifico.vehiclelocationservice.dto.VehicleDto;
import com.digifico.vehiclelocationservice.mapper.VehicleMapper;
import com.digifico.vehiclelocationservice.mapper.VehicleMapperImpl;
import com.digifico.vehiclelocationservice.persistence.entity.Vehicle;
import com.digifico.vehiclelocationservice.persistence.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleLocationServiceTest {

    private static final String TEST_ID = "1";
    private static final Double TEST_LONGITUDE = 1.5d;
    private static final Double TEST_LATITUDE = -1.5d;

    private static final Double FIRST_POINT_LONGITUDE = 2.3d;
    private static final Double FIRST_POINT_LATITUDE = 4.8d;
    private static final Double SECOND_POINT_LONGITUDE = 3.5d;
    private static final Double SECOND_POINT_LATITUDE = -7.9d;

    @Spy
    VehicleMapper vehicleMapper = new VehicleMapperImpl();
    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleLocationServiceImpl vehicleLocationService;

    @Test
    @DisplayName("Should insert new vehicle location")
    void shouldInsertNewVehicleLocation() {
        Vehicle expectedVehicle = createVehicle(null);
        VehicleDto vehicleDto = createVehicleDto(null);
        when(vehicleRepository.save(any())).thenReturn(expectedVehicle);

        vehicleLocationService.upsertVehicle(vehicleDto);

        verify(vehicleMapper, times(1)).dtoToEntity(vehicleDto);
        verify(vehicleRepository, times(1)).save(eq(expectedVehicle));
    }

    @Test
    @DisplayName("Should update vehicle location")
    void shouldUpdateVehicleLocation() {
        Vehicle expectedVehicle = createVehicle(TEST_ID);
        VehicleDto vehicleDto = createVehicleDto(TEST_ID);
        when(vehicleRepository.save(any())).thenReturn(expectedVehicle);

        vehicleLocationService.upsertVehicle(vehicleDto);

        verify(vehicleMapper, times(1)).dtoToEntity(vehicleDto);
        verify(vehicleRepository, times(1)).save(eq(expectedVehicle));
    }

    @Test
    @DisplayName("Should get all vehicles inside of search area")
    void shouldGetAllVehiclesInsideOfSearchArea() {
        Vehicle vehicle = createVehicle(TEST_ID);
        VehicleDto expectedVehicleDto = createVehicleDto(TEST_ID);
        when(vehicleRepository.findAllByLocationWithin(any())).thenReturn(List.of(vehicle));

        List<VehicleDto> actual =  vehicleLocationService
                .getAllVehiclesInside(FIRST_POINT_LONGITUDE, FIRST_POINT_LATITUDE, SECOND_POINT_LONGITUDE, SECOND_POINT_LATITUDE);

        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertNotNull(actual.get(0));
        assertEquals(expectedVehicleDto, actual.get(0));
    }

    @Test
    @DisplayName("Should return empty list when no vehicles inside of search area has been found")
    void shouldReturnEmptyListWhenNoVehiclesInsideOfSearchArea() {
        when(vehicleRepository.findAllByLocationWithin(any())).thenReturn(List.of());

        List<VehicleDto> actual =  vehicleLocationService
                .getAllVehiclesInside(FIRST_POINT_LONGITUDE, FIRST_POINT_LATITUDE, SECOND_POINT_LONGITUDE, SECOND_POINT_LATITUDE);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    private Vehicle createVehicle(String id) {
        GeoJsonPoint location = new GeoJsonPoint(TEST_LONGITUDE, TEST_LATITUDE);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setLocation(location);
        return vehicle;
    }

    private VehicleDto createVehicleDto(String id) {
        LocationPointDto locationPointDto = new LocationPointDto();
        locationPointDto.setLongitude(TEST_LONGITUDE);
        locationPointDto.setLatitude(TEST_LATITUDE);
        return new VehicleDto(id, locationPointDto);
    }


}
