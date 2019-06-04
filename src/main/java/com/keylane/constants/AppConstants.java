package com.keylane.constants;

import com.keylane.dto.TriangleBySide;

/**
 * Constants for the application
 */
public class AppConstants {
    /**
     * Enum holding Triangle type
     */
    public enum TriangleTypes {
        EQUILATERAL("Equilateral Triangle"),
        ISOSCELES("Isosceles Triangle"),
        SCALENE("Scalene triangle"),
        RIGHT_ANGLE("Right Angle Triangle");

        private String type;

        TriangleTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * Enum holding different types of the geometries
     */
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
