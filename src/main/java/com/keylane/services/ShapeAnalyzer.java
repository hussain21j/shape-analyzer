package com.keylane.services;

import com.keylane.constants.AppConstants;
import com.keylane.dto.Shape;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Objects;

/**
 * The Front facing class for shape Analyzer functionality
 */
public class ShapeAnalyzer {
    ApplicationContext context;
    ShapeServiceFactory serviceFactory;
    Shape shape;
    ShapeService service;

    /**
     * constructor of the class, initializes the services and
     */
    public ShapeAnalyzer(ApplicationContext applicationContext ) {
        this.context = applicationContext;
    }

    /**
     * returns the shape type and invoke the save of a shape
     */
    public String analyze(Shape shape) {
        this.shape = shape;
        String shapeType;
        shapeType = this.shape.findShapeType(); //should throw exception
        service = getServiceFactory().getShapeService(shape.getClass());
        service.save(shape);
        return shapeType;
    }

    /**
     * calls the appropriate method to find unique shapes  and returns the list
     */
    public List<Shape> getUniqueShapes(AppConstants.Geometries geometry) {
        this.service = getServiceFactory().getShapeService(geometry.getType());
        return service.findUniqueShapes();
    }

    public ShapeServiceFactory getServiceFactory() {
        return Objects.isNull(serviceFactory) ? (new ShapeServiceFactory(context)) : serviceFactory;
    }
}
