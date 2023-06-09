package com.kb.jjan.domain.mission.mission;

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
@Table(
        name="missions",
        uniqueConstraints={
                @UniqueConstraint(
                        name= "mission_uq",
                        columnNames = {"map_num", "mission_num"}
                )
        }
)
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MISSIONS")
    @SequenceGenerator(name = "SEQ_MISSIONS", sequenceName = "SEQ_MISSIONS", allocationSize = 1)
    @Column(name = "mission_id")       // column 이름
    private Long missionId;

    @Column(name = "mission_type", nullable = false, length = 2)
    private String missionType;

    @Column(name = "map_num", nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int mapNum;

    @Column(name = "mission_num", nullable = false, length = 2, columnDefinition = "NUMBER(2)")
    private int missionNum;

    @Column(length = 700)
    private String title;

    @Column(length = 1000)
    private String vodUrl;

    @Column(length = 1000)
    private String explain;

    @Column(length = 2)
    private String answer;

    @OneToMany(mappedBy = "solvedMission", cascade = CascadeType.ALL)
    private List<UserMission> solvedMissions;

}
