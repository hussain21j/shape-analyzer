package com.keylane.dto;

import com.keylane.exception.InvalidTriangleException;
import com.keylane.exception.ShapeValidationException;
import com.keylane.service.TriangleService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.DecimalMin;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.keylane.constants.AppConstants.Geometry.EQUILATERAL;
import static com.keylane.constants.AppConstants.Geometry.ISOSCELES;
import static com.keylane.constants.AppConstants.Geometry.SCALENE;

@Getter
@Setter
@Slf4j
public class TriangleBySide implements Shape {
    @DecimalMin(value = "0.0", inclusive = false)
    private double firstSide;

    @DecimalMin(value = "0.0", inclusive = false)
    private double secondSide;

    @DecimalMin(value = "0.0", inclusive = false)
    private double thirdSide;

    private String type;

    private TriangleService triangleService;

    public TriangleBySide(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    /**
     * Finds the shape type of the Triangle by side
     * @return
     */
    @Override
    public String findShapeType() {
        log.info("finding the shape type");
        String shapeType;
        if(!validate(this)) {
            throw new ShapeValidationException("Sides of triangle are not correct");
        }

        if(this.firstSide >= this.secondSide + this.thirdSide ||
                this.secondSide >= this.firstSide + this.thirdSide ||
                this.thirdSide >= this.firstSide + this.secondSide) {
            throw new InvalidTriangleException("This is not a valid triangle");
        }

        if(this.firstSide == this.secondSide && this.secondSide == thirdSide)
            shapeType = EQUILATERAL.getType();
        else if (this.firstSide == this.secondSide ||
                this.firstSide == this.thirdSide ||
                this.secondSide == this.thirdSide)
            shapeType = ISOSCELES.getType();
        else
            shapeType = SCALENE.getType();
        this.type = shapeType;

        //saving the triangle
        //TriangleEntity entity =  triangleService.save(this);
        //log.info("Triangle is saved with id :"+entity.getId());

        return shapeType;
    }

    /**
     * validates the triangle based on annotation on triangle sides
     */
    public Boolean validate(Shape shape) {
        TriangleBySide triangleBySide = (TriangleBySide) shape;
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator =  validatorFactory.getValidator();
        Set<ConstraintViolation<TriangleBySide>> constraintViolations = validator.validate(triangleBySide);
        constraintViolations.forEach(voilation -> System.out.println(voilation.getMessage()));
        return constraintViolations.size() > 0 ? false : true;
    }

    /**
     * For the comparison of two TriangleBySide object
     * two triangles if have same size of sides in any order they are treated same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        TriangleBySide triangle =  (TriangleBySide)obj;
        List<Double> triangleSides = Arrays.asList(triangle.firstSide, triangle.secondSide, triangle.thirdSide);
        List<Double> thisTriangleSides = Arrays.asList(this.firstSide, this.secondSide, this.thirdSide);
        triangleSides.sort(Comparator.naturalOrder());
        thisTriangleSides.sort(Comparator.naturalOrder());
        return triangleSides.equals(thisTriangleSides);
    }

    /**
     * For the comparison of two TriangleBySide object
     * todo: how to write hashcode function
     */
    @Override
    public int hashCode() {
        int hashNumber = 7;
        hashNumber = 13 * hashNumber + (int)Math.round(this.firstSide);
        return hashNumber;
    }

    /**
     * display the TriangleBySide in a specific format
     */
    @Override
    public String toString() {
        return "("+this.firstSide+" ,"+this.secondSide+" ,"+this.thirdSide+")";
    }
}
