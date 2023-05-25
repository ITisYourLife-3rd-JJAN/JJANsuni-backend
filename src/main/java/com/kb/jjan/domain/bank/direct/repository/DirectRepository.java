package com.kb.jjan.domain.bank.direct.repository;

import com.kb.jjan.domain.bank.direct.Direct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectRepository extends JpaRepository<Direct, Long> {

    @Query(value = "SELECT * FROM directs  WHERE debit_cycle = :cycle", nativeQuery = true)
    List<Direct> findUsersByDebitCycle(@Param("cycle") int cycle);

    @Query(value = "select * FROM directs WHERE auto_send_user = :userId", nativeQuery = true)
    List<Direct> findByAutoSendUser(@Param("userId") long userId);

    @Modifying()
    @Query(value = "update directs set price = :price, debit_msg = :debitMsg, debit_date = :debitDate, debit_cycle = :debitCycle where auto_send_user = :autoSendUser and auto_received_user = :autoReceivedUser", nativeQuery = true)
    int updateDirect(@Param("autoSendUser") long autoSendUser, @Param("autoReceivedUser") long autoReceivedUser, @Param("price") int price, @Param("debitMsg") String debitMsg, @Param("debitDate") int debitDate, @Param("debitCycle") int debitCycle);

    @Modifying
    @Query(value = "delete from directs where auto_send_user = :autoSendUser and auto_received_user = :autoReceivedUser", nativeQuery = true)
    int deleteDirect(@Param("autoSendUser") long autoSendUser, @Param("autoReceivedUser") long autoReceivedUser);

    @Modifying
    @Query(value = "insert into debits (send_user, received_user, price, deal_msg) values (:autoSendUser, :autoReceivedUser, :price, :debitMsg)", nativeQuery = true)
    int insertDebit(@Param("autoSendUser") long autoSendUser, @Param("autoReceivedUser") long autoReceivedUser, @Param("price") int price, @Param("debitMsg") String debitMsg);
}
