package com.digifico.vehiclelocationservice.utils;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;

public class GeometryUtils {

    /**
     * Creates {@link Box} spanning from the given first to the second point by provided longitudes and latitudes.
     * Order of points doesn't matter.
     *
     * @param firstPointLongitude longitude of the first point
     * @param firstPointLatitude latitude of the first point
     * @param secondPointLongitude longitude of the second point
     * @param secondPointLatitude latitude of the second point
     * @return created instance of {@link Box}
     */
    public static Box searchAreaBoxFromPointsCoordinates(Double firstPointLongitude, Double firstPointLatitude,
                                                         Double secondPointLongitude, Double secondPointLatitude) {
        Point firstPoint = new Point(firstPointLongitude, firstPointLatitude);
        Point secondPoint = new Point(secondPointLongitude, secondPointLatitude);
        return new Box(firstPoint, secondPoint);
    }

}
