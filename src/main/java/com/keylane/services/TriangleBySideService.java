package com.keylane.services;

import com.keylane.dto.Shape;
import com.keylane.dto.TriangleBySide;
import com.keylane.entity.TriangleBySideEntity;
import com.keylane.entity.TriangleBySideRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TriangleBySideService implements ShapeService {
    @Autowired
    TriangleBySideRepository triangleRepository;

    /**
     *saves a triangle in the data bse
     */
    @Override
    @Transactional
    public TriangleBySideEntity save(Shape shape) {
        TriangleBySide triangle = (TriangleBySide) shape;
        TriangleBySideEntity entity =  TriangleBySideEntity.builder().
                firstSide(triangle.getFirstSide()).
                secondSide(triangle.getSecondSide()).
                thirdSide(triangle.getThirdSide()).build();

        TriangleBySideEntity savedTriangle = triangleRepository.save(entity);
        log.info("Triangle is saved in the DB with id :"+savedTriangle.getId());
        return savedTriangle;
    }

    /**
     * fetch all the triangles from the database and identify the unique triangles
     */
    @Override
    public List<Shape> findUniqueShapes() {
        List<TriangleBySideEntity> listTringleEntity = triangleRepository.findAll();
        List<Shape> listTriangle = new ArrayList<>();
        for (TriangleBySideEntity entity: listTringleEntity) {
                Shape triangle = new TriangleBySide(entity.getFirstSide(), entity.getSecondSide(), entity.getThirdSide());
                listTriangle.add(triangle);
        }

        listTriangle = listTriangle.stream().distinct().collect(Collectors.toList());
        return listTriangle;
    }
}
