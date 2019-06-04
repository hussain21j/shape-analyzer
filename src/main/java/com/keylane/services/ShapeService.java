package com.keylane.services;

import com.keylane.dto.Shape;

import java.util.List;

/**
 * Contract for the services class
 */
public interface ShapeService {
    public Object save(Shape shape);
    public List<Shape> findUniqueShapes();
}
