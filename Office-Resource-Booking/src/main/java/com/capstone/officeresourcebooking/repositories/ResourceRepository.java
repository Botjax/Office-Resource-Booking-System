package com.capstone.officeresourcebooking.repositories;

import com.capstone.officeresourcebooking.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query(value = "SELECT * FROM resources WHERE resource_name = :resourceName", nativeQuery = true)
    Optional<Resource> findByResourceName(@Param("resourceName") String resourceName);

    @Query("SELECT r.id FROM Resource r WHERE r.resourceName = :resourceName")
    Optional<Integer> findResourceIdByName(@Param("resourceName") String resourceName);

    @Query(value = "SELECT * FROM resources WHERE is_available = 1", nativeQuery = true)
    List<Resource> findAvailableResources();

    @Query(value = "SELECT * FROM resources WHERE resource_type = :resourceType", nativeQuery = true)
    List<Resource> findByResourceType(@Param("resourceType") String resourceType);

    @Query(value = "SELECT resource_name FROM resources", nativeQuery = true)
    List<String> findAllResourceNames();
}
