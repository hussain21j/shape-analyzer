package com.keylane.services;

import com.keylane.constants.AppConstants;
import com.keylane.dto.Shape;
import com.keylane.dto.TriangleBySide;
import com.keylane.services.ShapeService;
import com.keylane.services.ShapeServiceFactory;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.keylane.constants.AppConstants.TriangleTypes.SCALENE;

@RunWith(MockitoJUnitRunner.class)
public class ShapeAnalyzerTest {
    @Mock
    ShapeServiceFactory serviceFactory;

    @Mock
    ShapeService service;

    @InjectMocks
    ShapeAnalyzer analyzer;

    @Test
    public void analyze() throws Exception {
        //Given
        ShapeAnalyzer spyShapeAnalyzer  = Mockito.spy(analyzer);
        Mockito.doReturn(serviceFactory).when(spyShapeAnalyzer).getServiceFactory();
        Shape shape = new TriangleBySide(100.00, 150.00, 200.00);
        Mockito.when(serviceFactory.getShapeService(TriangleBySide.class)).thenReturn(service);
        Mockito.when(service.save(ArgumentMatchers.any())).thenReturn(null);

        //When
        String shapeType = spyShapeAnalyzer.analyze(shape);

        //Then
        Assertions.assertThat(shapeType).isEqualToIgnoringCase(SCALENE.getType());
    }

    @Test
    public void getUniqueShapes() throws Exception {
        //Given
        //Given
        ShapeAnalyzer spyShapeAnalyzer  = Mockito.spy(analyzer);
        Mockito.doReturn(serviceFactory).when(spyShapeAnalyzer).getServiceFactory();
        List<Shape> listShape = new ArrayList<>();
        listShape.add(new TriangleBySide(100.00, 150.00, 200.00));
        Mockito.when(serviceFactory.getShapeService(TriangleBySide.class)).thenReturn(service);
        Mockito.when(service.findUniqueShapes()).thenReturn(listShape);
        //When
        List<Shape> receivedShapeList =  spyShapeAnalyzer.getUniqueShapes(AppConstants.Geometries.TRIANGLE_BY_SIDES);
        //Then
        Assertions.assertThat(receivedShapeList).hasSize(1);
        Assertions.assertThat( ((TriangleBySide)receivedShapeList.get(0)).getFirstSide()).isEqualTo(100.00);
    }

}
