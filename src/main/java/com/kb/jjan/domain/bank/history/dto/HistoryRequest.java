package com.kb.jjan.domain.bank.history.dto;

import com.kb.jjan.domain.bank.history.History;
import com.kb.jjan.domain.mission.userMission.UserMission;
import com.kb.jjan.domain.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryRequest {
    private Long sendUserId;
    private Long receivedUserId;
    private int price;
    private int dealMsg;

    public History toEntity(User sendUser, User receivedUser) {
        return History.builder()
                .sendUser(sendUser)
                .receivedUser(receivedUser)
                .price(price)
                .dealMsg(dealMsg)
                .build();
    }
}