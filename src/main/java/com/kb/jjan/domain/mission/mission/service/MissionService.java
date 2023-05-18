package com.kb.jjan.domain.mission.mission.service;


import com.kb.jjan.domain.mission.mission.Mission;
import com.kb.jjan.domain.mission.mission.dto.MissionQuizRequest;
import com.kb.jjan.domain.mission.mission.dto.MissionVodRequest;
import com.kb.jjan.domain.mission.mission.exception.NotFoundMission;
import com.kb.jjan.domain.mission.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionService  {

    private final MissionRepository missionRepository;

    public void registerQuiz(MissionQuizRequest missionQuizRequest) {
        Mission mission = missionQuizRequest.toEntity();
        missionRepository.save(mission);
    }

    public void registerVod(MissionVodRequest missionVodRequest) {
        Mission mission = missionVodRequest.toEntity();
        missionRepository.save(mission);
    }

    public Optional<Mission> getMissionInfo(long missionId) {
        return Optional.ofNullable(missionRepository.findById(missionId).orElseThrow(NotFoundMission::new));
    }

}
