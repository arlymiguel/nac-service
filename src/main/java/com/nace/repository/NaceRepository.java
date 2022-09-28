package com.nace.repository;

import com.nace.entity.Nace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaceRepository extends JpaRepository<Nace, Long> {

    Optional<Nace> findByOrder(String order);

}
