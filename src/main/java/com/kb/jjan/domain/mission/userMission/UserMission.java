package com.kb.jjan.domain.mission.userMission;

import com.kb.jjan.domain.mission.Mission;
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
@Table(name = "user_missions")
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_MISSION_ID"
    )
    @Column(name = "user_mission_id")
    private Long userMissionId;

    @ManyToOne
    @JoinColumn(name = "solved_mission_id", nullable = false)
    private Mission solvedMissionId;

    @ManyToOne
    @JoinColumn(name = "solved_user_id", nullable = false)
    private User solvedUserId;

    @Column(length = 1, columnDefinition = "NUMBER(1)", nullable = false)
    private int status;


}