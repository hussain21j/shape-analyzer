package com.keylane.services;

import com.keylane.dto.TriangleBySide;
import com.keylane.exceptions.NoServiceAvailableException;
import org.springframework.context.ApplicationContext;

/**
 * Factory to get the service for a specific type of shape
 */
public class ShapeServiceFactory {
    ApplicationContext context;
    TriangleBySideService triangleService;

    /**
     * constructor of the class, initializes the bean of all the all concrete shape services
     */
    public ShapeServiceFactory( ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    /**
     * Finds and returns the service for a shape
     */
    public ShapeService getShapeService(Class shape) throws NoServiceAvailableException {
        ShapeService shapeService;
        if(shape == TriangleBySide.class)
            shapeService = context.getBean(TriangleBySideService.class);
        else {
            throw new NoServiceAvailableException("No service available for shape "+shape.getClass());
        }
        return shapeService;
    }
}
