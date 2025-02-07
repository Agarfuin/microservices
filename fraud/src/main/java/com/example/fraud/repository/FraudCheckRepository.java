package com.example.fraud.repository;

import com.example.fraud.entity.FraudCheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckRepository extends JpaRepository<FraudCheckEntity, Long> {}
