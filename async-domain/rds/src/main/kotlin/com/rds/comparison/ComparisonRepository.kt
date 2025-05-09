package com.rds.comparison

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComparisonRepository: JpaRepository<Comparison, Long>