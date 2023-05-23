package com.kb.jjan.domain.mission.userMission;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kb.jjan.domain.mission.mission.Mission;
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
@Table(
        name="user_missions",
        uniqueConstraints={
                @UniqueConstraint(
                        name= "user_mission_uq",
                        columnNames = {"solved_mission_id", "solved_user_id"}
                )
        }
)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userMissionId")
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_MISSIONS")
    @SequenceGenerator(name = "SEQ_USER_MISSIONS", sequenceName = "SEQ_USER_MISSIONS", allocationSize = 1)
    @Column(name = "user_mission_id")
    private Long userMissionId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "solved_mission_id", nullable = false)
    private Mission solvedMission;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "solved_user_id", nullable = false)
    private User solvedUser;

    @Column(length = 1, columnDefinition = "NUMBER(1)", nullable = false)
    private int status;
}