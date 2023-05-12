package com.kb.jjan.domain.bank.direct.repository;

import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.DirectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectRepository extends JpaRepository<Direct, DirectId> {

}
