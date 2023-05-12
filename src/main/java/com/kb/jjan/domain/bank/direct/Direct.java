package com.kb.jjan.domain.bank.direct;


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
@Table(name = "directs")
public class Direct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIRECT")
    @SequenceGenerator(name = "SEQ_DIRECT", sequenceName = "SEQ_DIRECT", allocationSize = 1)
    @Column(name = "direct_id")
    private Long directId;

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

    @Column(name = "debit_date", length = 3, columnDefinition = "NUMBER(3)")
    private int debitDate;

    @Column(name = "debit_cycle", length = 1, columnDefinition = "NUMBER(1)")
    private int debitCycle;
}

