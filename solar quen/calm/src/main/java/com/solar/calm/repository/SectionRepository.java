package com.solar.calm.repository;

import com.solar.calm.domain.Section;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Section entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectionRepository extends MongoRepository<Section, String>, QuerydslPredicateExecutor<Section> {

}
