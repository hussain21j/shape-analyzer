package com.keylane;

import com.keylane.constants.AppConstants;
import com.keylane.dto.Shape;
import com.keylane.dto.TriangleBySide;
import com.keylane.exception.InvalidTriangleException;
import com.keylane.exception.ShapeValidationException;
import com.keylane.service.ShapeAnalyzer;
import com.keylane.service.TriangleService;
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
            switch (input) {
            case 1:
                processTriangle();
            case 2:
                processQuadrilateral();
            case 3:
                publishUniqueTriangleBySides();
            default:
                System.out.println("options chosen is not correct");
            }
        }
    }



    public static void processTriangle() throws ShapeValidationException, InvalidTriangleException {
        double firstSide;
        double secondSide;
        double thirdSide;
        System.out.print("enter length of first side :");
        firstSide = (Double.parseDouble(getCommandInput()));
        System.out.print("enter length of second side :");
        secondSide = Double.parseDouble(getCommandInput());
        System.out.print("enter length of third side :");
        thirdSide = Double.parseDouble(getCommandInput());
        TriangleBySide triangleBySide = new TriangleBySide(firstSide, secondSide, thirdSide);
        shapeAnalyzer.analyze(triangleBySide);
    }

    public static void processQuadrilateral() {
        System.out.println("Not supported yet, Coming Soon....!");
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
