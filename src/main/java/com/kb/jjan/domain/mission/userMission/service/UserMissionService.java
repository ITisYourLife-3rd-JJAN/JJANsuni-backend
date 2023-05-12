package com.kb.jjan.domain.mission.userMission.service;

import com.kb.jjan.domain.mission.userMission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

}
