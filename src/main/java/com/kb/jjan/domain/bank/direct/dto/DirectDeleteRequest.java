package com.kb.jjan.domain.bank.direct.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DirectDeleteRequest {
    private Long autoSendUserId;
    private Long autoReceivedUserId;
}
