package com.triple.mytrip;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class TestEntity {

    @Id @GeneratedValue
    private Long id;

}
