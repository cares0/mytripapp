package com.triple.mytrip;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestEntityTest {

    @Autowired
    EntityManager em;

    @Test
    void test() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        TestEntity testEntity = new TestEntity();
        em.persist(testEntity);


        TestEntity find = em.find(TestEntity.class, testEntity.getId());
        Assertions.assertThat(find).isEqualTo(testEntity);

    }

}