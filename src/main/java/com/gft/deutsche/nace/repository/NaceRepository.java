package com.gft.deutsche.nace.repository;

import com.gft.deutsche.nace.entity.Nace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaceRepository extends JpaRepository<Nace, Long> {

    Optional<Nace> findByOrder(String order);

}
