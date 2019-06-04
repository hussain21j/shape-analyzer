package com.keylane.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Todo: rename it to triangle by side
 */
public class TriangleBySideEntity {
    @Id
    @GeneratedValue
    private Long id;
    private double firstSide;
    private double secondSide;
    private double thirdSide;
    private String type;
}
