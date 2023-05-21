package com.kb.jjan.domain.bank.direct.repository;

import com.kb.jjan.domain.bank.direct.Direct;
import com.kb.jjan.domain.bank.direct.dto.DirectUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectRepository extends JpaRepository<Direct, Long> {
    @Query("SELECT d FROM Direct d WHERE d.debitCycle = :cycle")
    List<Direct> findByDebitCycle(@Param("cycle") int cycle);


//    @Query("SELECT DirectUserDTO(d.autoSendUser.userId, d.autoReceivedUser.userId, d.price, d.debitDate, d.debitCycle) FROM Direct d WHERE d.debitCycle = :cycle")
//    List<DirectUserDTO> findUsersByDebitCycle(@Param("cycle") int cycle);

    @Query(value = "SELECT * FROM directs  WHERE debit_cycle = :cycle", nativeQuery = true)
    List<Direct> findUsersByDebitCycle(@Param("cycle") int cycle);

}
