package com.kb.jjan.domain.mission.mission.service;


import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.dto.MissionQuizRequest;
import com.kb.jjan.domain.mission.mission.dto.MissionVodRequest;
import com.kb.jjan.domain.mission.mission.exception.DuplicateMission;
import com.kb.jjan.domain.mission.mission.exception.NotFoundMission;
import com.kb.jjan.domain.mission.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MissionService  {

    private final MissionRepository missionRepository;

    public void registerQuiz(MissionQuizRequest missionQuizRequest) {
        Mission mission = missionQuizRequest.toEntity();
        if(missionRepository.existsByMapNumAndMissionNum(mission.getMapNum(), mission.getMissionNum()))
            throw new DuplicateMission();
        missionRepository.save(mission);
    }

    public void registerVod(MissionVodRequest missionVodRequest) {
        Mission mission = missionVodRequest.toEntity();
        if(missionRepository.existsByMapNumAndMissionNum(mission.getMapNum(), mission.getMissionNum()))
            throw new DuplicateMission();
        missionRepository.save(mission);
    }

    public Mission findMissionInfo(long missionId) {
        Mission mission =  missionRepository.findById(missionId).orElse(null);
        if (mission == null) throw new NotFoundMission();
        return mission;
    }

    public Mission findMissionInfo(int mapNum, int missionNum) {
        Mission mission =  missionRepository.findMissionByMapNumAndMissionNum(mapNum, missionNum);
        if (mission == null) throw new NotFoundMission();
        return mission;
    }

}
