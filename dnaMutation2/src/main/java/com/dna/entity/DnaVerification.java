package com.dna.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DnaVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String dnaSequence;

    private boolean isMutated;

    // Getter y Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDnaSequence() {
		return dnaSequence;
	}

	public void setDnaSequence(String dnaSequence) {
		this.dnaSequence = dnaSequence;
	}

	public boolean isMutated() {
		return isMutated;
	}

	public void setMutated(boolean isMutated) {
		this.isMutated = isMutated;
	}   
    
}

