package com.keylane;

import com.keylane.constants.AppConstants;
import com.keylane.dto.Shape;
import com.keylane.dto.TriangleBySide;
import com.keylane.exceptions.InvalidTriangleException;
import com.keylane.exceptions.ShapeValidationException;
import com.keylane.services.ShapeAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

/**
 * client of the Shape Analyzer
 */
@SpringBootApplication
public class ShapeAnalyzerApplication {
    public static Scanner scanner = new Scanner(System.in);
    public static ShapeAnalyzer shapeAnalyzer;
    public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(ShapeAnalyzerApplication.class, args);
        shapeAnalyzer = new ShapeAnalyzer(context);

        boolean exit = false;
        System.out.println("**********************************************************************");
        System.out.println("******** Welcome to the shape-analyzer application *******************");
        int input;
        while (!exit) {
            System.out.println("\n****************Available options************");
            System.out.println("Press 1 for shape type Triangle");
            System.out.println("Press 2 for shape type Quadrilateral");
            System.out.println("Press 3 for unique triangles");
            System.out.println("Press q any moment to exit");
            System.out.print("choose your option :");
            try {
                input = Integer.parseInt(getCommandInput());
            } catch (NumberFormatException e) {
                System.out.println("Oops..!, not a valid option ");
                continue;
            }
            if(input == 1)
                processTriangle();
            else if(input == 2)
                processQuadrilateral();
            else if(input == 3)
                publishUniqueTriangleBySides();
            else
                System.out.println("options chosen is not correct");
        }
    }



    public static void processTriangle() {
        double firstSide;
        double secondSide;
        double thirdSide;
        String triangleType;
        try{
            System.out.print("enter length of first side :");
            firstSide = (Double.parseDouble(getCommandInput()));
            System.out.print("enter length of second side :");
            secondSide = Double.parseDouble(getCommandInput());
            System.out.print("enter length of third side :");
            thirdSide = Double.parseDouble(getCommandInput());
            TriangleBySide triangleBySide = new TriangleBySide(firstSide, secondSide, thirdSide);
            triangleType = shapeAnalyzer.analyze(triangleBySide);
            System.out.println("Triangle found of Type :"+triangleType);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid value :(, Please start again");
        } catch (InvalidTriangleException e) {
            System.out.println("Invalid Triangle :(, Please start again");
        } catch (ShapeValidationException e) {
            System.out.println("Invalid Shape :(, Please start again");
        }
    }

    public static void processQuadrilateral() {
        System.out.println("Coming Soon...!");
    }


    public static void publishUniqueTriangleBySides() {
        List<Shape> listTraingleBySide = shapeAnalyzer.getUniqueShapes(AppConstants.Geometries.TRIANGLE_BY_SIDES);
        System.out.println("Printing uniqe triangle by sides in format (first side, second side, third side)");
        listTraingleBySide.forEach(triangle -> System.out.println(triangle));
    }

    public static String getCommandInput() throws NumberFormatException{
        String input  = scanner.next();
        if(input.equalsIgnoreCase("Q"))
            System.exit(0);
        return input;
    }
}
