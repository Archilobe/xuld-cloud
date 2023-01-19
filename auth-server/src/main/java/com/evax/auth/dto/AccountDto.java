package com.evax.auth.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AccountDto {
    @NotEmpty(message = "username不能为空")
    private String username;
    @NotEmpty(message = "password不能为空")
    private String password;
}
