package com.kb.jjan.domain.mission;

import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "missions")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MISSIONS")
    @SequenceGenerator(name = "SEQ_MISSIONS", sequenceName = "SEQ_MISSIONS", allocationSize = 1)
    @Column(name = "mission_id")       // column 이름
    private Long missionId;

    @Column(name = "mission_type", nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String missionType;

    @Column(name = "map_num", nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int mapNum;

    @Column(name = "mission_num", nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int missionNum;

    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String vodUrl;

    @Column(length = 500)
    private String explain;

    @Column(columnDefinition = "CHAR(2)", length = 2)
    private String answer;

    @OneToMany(mappedBy = "solvedMissionId", cascade = CascadeType.ALL)
    private List<UserMission> solvedMission;

}
