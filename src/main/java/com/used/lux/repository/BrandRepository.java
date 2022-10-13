package com.used.lux.repository;

import com.used.lux.dto.BrandDto;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface BrandRepository extends JpaRepositoryImplementation<BrandDto, Long> {
}
