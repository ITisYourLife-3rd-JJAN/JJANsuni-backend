package com.kb.jjan.domain.bank.direct;

import com.kb.jjan.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectId implements Serializable {
    private User autoSendUser;
    private User autoReceivedUser;
}