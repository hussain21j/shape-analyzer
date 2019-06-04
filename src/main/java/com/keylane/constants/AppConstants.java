package com.keylane.constants;

import com.keylane.dto.TriangleBySide;

/**
 * Constants for the application
 */
public class AppConstants {
    /**
     * Triangle type
     *
     * Todo: rename
     */
    public enum Geometry {
        EQUILATERAL("Equilateral Triangle"),
        ISOSCELES("Isosceles Triangle"),
        SCALENE("Scalene triangle"),
        RIGHT_ANGLE("Right Angle Triangle");

        private String type;

        Geometry(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum Geometries {
        TRIANGLE_BY_SIDES(TriangleBySide.class);

        private Class type;

        Geometries(Class type) {
            this.type = type;
        }

        public Class getType() {
            return type;
        }
    }
}
