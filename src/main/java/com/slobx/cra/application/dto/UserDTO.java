package com.slobx.cra.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String phoneNumber;
}
