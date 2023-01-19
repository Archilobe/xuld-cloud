package com.evax.auth.vo;

import lombok.Data;

@Data
public class CurrentUser {
    private Long userId;
    private String username;
    private String nickname;
}
