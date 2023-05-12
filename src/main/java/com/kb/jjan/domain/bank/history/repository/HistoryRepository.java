package com.kb.jjan.domain.bank.history.repository;

import com.kb.jjan.domain.bank.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
