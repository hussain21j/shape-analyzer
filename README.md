# Shape Analyzer

## Thought process behind my solution
I designed the solution in such a way that, it should be able to handle other types of geometries, not just triangle with sides.
The focus has been made on design to handle any geometrical shape type (Triangle with angles, Quadrilateral, pentagon etc).

The `ShapeAnalyzer` class in the `services` package act as the entry point to the shape analyzer application. if the solution is scaled up (i.e. if new geometries has are added), The client will be having no impact, so no change is required at the consumer side.

In simple terms just give the object of any shape to the `ShapeAnalyzer`, it will find the shape type, save it in the database.

SO if we have to add more geometries then we need to write the implementation of the `Shape` class, corresponding entity, repository, service and one entry in the serviceFactory.

The Service class for any shape (for e.g. TriangleBySideService) is responsible for implementing the save in database and finding unique shapes of there type

For saving the Shapes H2 database has been chosen, to make it more robust. Spring data has been used as JPA.

## Assumptions
Two triangles with size of sides same, in any order are same.

## Technologies chosen
Spring boot, in memorey h2 database

