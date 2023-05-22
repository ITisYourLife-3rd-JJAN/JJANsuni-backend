package com.kb.jjan.domain.mission.userMission.repository;

import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query(value = "SELECT * FROM user_missions WHERE solved_user_id = :id", nativeQuery = true)
    List<UserMission> findBySolvedUserId(@Param("id") long id);

    List<UserMission> findUserMissionsBySolvedUserUserIdAndAndSolvedMission_MapNum(long userId, int mapNum);


}

