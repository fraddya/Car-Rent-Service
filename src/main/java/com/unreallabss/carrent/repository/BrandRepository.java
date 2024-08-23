package com.unreallabss.carrent.repository;


import com.unreallabss.carrent.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, QuerydslPredicateExecutor<Brand> {
    Brand findByName(String name);
}