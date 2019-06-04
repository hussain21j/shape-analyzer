package com.keylane.service;

import com.keylane.dto.Shape;

import java.util.List;

public interface ShapeService {
    public Object save(Shape shape);
    public List<Shape> findUniqueShapes();
}
