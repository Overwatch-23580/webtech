package com.project.lifebank.dto;

import com.project.lifebank.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long Id;
    private String name;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
