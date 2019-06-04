package com.keylane.entity;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = TriangleRepository.class)
@EntityScan(basePackageClasses = TriangleBySideEntity.class)
public class TriangleRepositoryTest {
    @Autowired
    TriangleRepository repository;

    @Test
    public void testFindAll() throws Exception {
        //Given
        TriangleBySideEntity triangle1 = getTriangleBySideEntityObject(100.00,200.00, 210.00);
        TriangleBySideEntity triangle2 = getTriangleBySideEntityObject(200.00,300.00, 310.00);
        TriangleBySideEntity triangle3 = getTriangleBySideEntityObject(300.00,400.00, 410.00);
        repository.save(triangle1);
        repository.save(triangle2);
        repository.save(triangle3);
        //When
        List<TriangleBySideEntity> listTriangle =  repository.findAll();
        //Then
        Assertions.assertThat(listTriangle).hasSize(3);
        Assertions.assertThat(listTriangle.get(0).getFirstSide()).isEqualTo(100.00);
    }

    @Test
    public void testSave() {
        //Given
        TriangleBySideEntity triangleBySideEntity = getTriangleBySideEntityObject(100.00,150.00,200.00);
        //When
        TriangleBySideEntity savedEnity  = repository.save(triangleBySideEntity);
        //Then
        Assertions.assertThat(savedEnity).isNotNull();
        Assertions.assertThat(savedEnity.getFirstSide()).isEqualTo(100.0);

    }

    private TriangleBySideEntity getTriangleBySideEntityObject(double firstSide, double secondSide, double thirdSide) {
        TriangleBySideEntity triangle = new TriangleBySideEntity();
        triangle.setFirstSide(firstSide);
        triangle.setSecondSide(secondSide);
        triangle.setThirdSide(thirdSide);
        triangle.setType("EQUI");
        return triangle;
    }

}
