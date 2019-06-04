package com.keylane.service;

import com.keylane.dto.TriangleBySide;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

@RunWith(MockitoJUnitRunner.class)
public class ShapeServiceFactoryTest {

    @Mock
    ApplicationContext context;

    @InjectMocks
    ShapeServiceFactory serviceFactory;

    @Test
    //todo: write assertion to check if shape service is of required type
    public void getShapeService() throws Exception {
        //Given
        TriangleService triangleService = new TriangleService();
        Mockito.when(context.getBean(TriangleService.class)).thenReturn(triangleService);
        //When
        ShapeService shapeService =  serviceFactory.getShapeService(TriangleBySide.class);
        //Then
        Assertions.assertThat(shapeService).isNotNull();
    }

}
