package com.dna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.entity.DnaVerification;

@Repository
public interface DnaVerificationRepository extends JpaRepository<DnaVerification, Long>{
	
	long countByMutated(boolean mutated);

}
