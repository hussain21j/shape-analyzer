package com.keylane.dto;

/**
 * Contract declaration of all Shapes
 */
public interface Shape {
    String findShapeType();
    Boolean validate(Shape shape);
}
