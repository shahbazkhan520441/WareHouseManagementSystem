package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.StorageType;

public interface StorageTypeRepository extends JpaRepository<StorageType, Integer>{
	boolean existsByLengthInMetersAndBreadthInMetersAndHeightInMetersAndCapacityWeightInKg(
            double lengthInMeters,
            double breadthInMeters,
            double heightInMeters,
            double capacityWeightInKg);
}
