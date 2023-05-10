package com.kb.jjan.domain.mission;

import lombok.*;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name="SEQ_MISSION", //시퀀스 제너레이터 이름
        sequenceName="MISSION_ID", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MISSION_ID" //식별자 생성기를 설정해놓은  USER_ID으로 설정
    )
    @Column(name = "mission_id")       // column 이름
    private Long missionId;

    @Column(nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String missionType;

    @Column(nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int mapNum;

    @Column(nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int missionNum;

    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String vodUrl;

    @Column(length = 500)
    private String explain;

    @Column(columnDefinition = "CHAR(2)", length = 2)
    private String answer;

}
