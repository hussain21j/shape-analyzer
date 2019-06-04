package com.keylane.service;

import com.keylane.dto.TriangleBySide;
import com.keylane.exception.NoServiceAvailable;
import org.springframework.context.ApplicationContext;

/**
 * Factory to get the service for a specific type of shape
 */
public class ShapeServiceFactory {
    ApplicationContext context;
    TriangleService triangleService;

    /**
     * constructor of the class, initializes the bean of all the all concrete shape services
     */
    ShapeServiceFactory( ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    /**
     * Finds and returns the service for a shape
     */
    ShapeService getShapeService(Class shape) throws NoServiceAvailable{
        ShapeService shapeService;
        if(shape == TriangleBySide.class)
            shapeService = context.getBean(TriangleService.class);
        else {
            throw new NoServiceAvailable("No service available for shape "+shape.getClass());
        }
        return shapeService;
    }
}
