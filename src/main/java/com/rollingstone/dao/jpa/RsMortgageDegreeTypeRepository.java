package com.rollingstone.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.DegreeType;



public interface RsMortgageDegreeTypeRepository extends PagingAndSortingRepository<DegreeType, Long> {
    Page findAll(Pageable pageable);
}
