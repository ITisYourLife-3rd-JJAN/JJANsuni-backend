package com.kb.jjan.domain.bank.autoDebit;


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
@Table(name = "auto_debits")
public class AutoDebit extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator="DEBIT_ID"
    )
    @Column(name = "debit_id")
    private Long debitId;

    @ManyToOne
    @JoinColumn(name = "auto_send_user", nullable = false)
    private User autoSendUser;

    @ManyToOne
    @JoinColumn(name = "auto_received_user", nullable = false)
    private User autoReceivedUser;

    @Column(length = 7, columnDefinition = "NUMBER(7)", nullable = false)
    private int price;

    @Column(name = "debit_msg", length = 20)
    private int debitMsg;
}

