package com.kb.jjan.domain.bank.debit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kb.jjan.domain.user.User;
import com.kb.jjan.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "debits")
public class Debit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEBITS")
    @SequenceGenerator(name = "SEQ_DEBITS", sequenceName = "SEQ_DEBITS", allocationSize = 1)
    @Column(name = "debit_id")
    private Long debitId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "send_user", nullable = false)
    private User sendUser;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "received_user", nullable = false)
    private User receivedUser;

    @Column(length = 7, columnDefinition = "NUMBER(7)", nullable = false)
    private int price;

    @Column(name = "deal_msg", length = 20)
    private String dealMsg;
}
