package com.kb.jjan.domain.mission.mission.repository;

import com.kb.jjan.domain.mission.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Mission findMissionByMapNumAndMissionNum(int mapNum, int missionNum);

    boolean existsByMapNumAndMissionNum(int mapNum, int missionNum);
}
