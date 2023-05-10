package com.kb.jjan.domain.bank.autoDebit;


import com.kb.jjan.domain.user.User;
import com.kb.jjan.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name="SEQ_DEBITS", //시퀀스 제너레이터 이름
        sequenceName="DEBIT_ID", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
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

