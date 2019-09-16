package com.xyz.fa.riskportfolio.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.fa.riskportfolio.dao.entity.RiskPortfolioEntity;

public interface RiskPortfolioRepository extends JpaRepository<RiskPortfolioEntity, Long>  {

}
