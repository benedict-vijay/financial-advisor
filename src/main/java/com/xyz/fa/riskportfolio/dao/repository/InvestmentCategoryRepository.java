package com.xyz.fa.riskportfolio.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.fa.riskportfolio.dao.entity.InvestmentCategoryEntity;

public interface InvestmentCategoryRepository extends JpaRepository<InvestmentCategoryEntity, Long>  {

}
