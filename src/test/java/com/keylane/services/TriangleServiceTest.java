package com.keylane.service;

import com.keylane.dto.Shape;
import com.keylane.dto.TriangleBySide;
import com.keylane.entity.TriangleBySideEntity;
import com.keylane.entity.TriangleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TriangleServiceTest {
    @Mock
    TriangleRepository repository;

    @InjectMocks
    TriangleService service;

    @Test
    public void testSave() throws Exception {
        //Given
        TriangleBySideEntity  entity = TriangleBySideEntity.builder().
                id(1L).
                firstSide(100.00).
                secondSide(150.00).
                thirdSide(200.00).build();
        Shape shape =  new TriangleBySide(100.00, 150.00, 200.00);

        Mockito.when(repository.save(ArgumentMatchers.any())).
                thenReturn(entity);
        //When
        TriangleBySideEntity savedEntity =  service.save(shape);
        //Then
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getId()).isEqualTo(1L);
    }

    @Test
    public void testFindUniqueShapes() throws Exception {
        //Given
        List<TriangleBySideEntity> listEntity = Arrays.asList(TriangleBySideEntity.builder().firstSide(100.00).secondSide(150.00).thirdSide(200.00).build(),
                TriangleBySideEntity.builder().firstSide(100.00).secondSide(150.00).thirdSide(200.00).build(),
                TriangleBySideEntity.builder().firstSide(101.00).secondSide(150.00).thirdSide(200.0).build());
        Mockito.when(repository.findAll()).thenReturn(listEntity);
        //When
        List<Shape> listUniqueShape =  service.findUniqueShapes();
        //Then
        Assertions.assertThat(listUniqueShape).hasSize(2);
    }

}
