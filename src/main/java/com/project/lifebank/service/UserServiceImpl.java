package com.project.lifebank.service;

import com.project.lifebank.dto.RoleDto;
import com.project.lifebank.dto.UserDto;
import com.project.lifebank.model.Role;
import com.project.lifebank.model.User;
import com.project.lifebank.repository.RoleRepository;
import com.project.lifebank.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Create a DONOR role and add it to the user's role list
        Role donorRole = roleRepository.findByName("DONOR");
        if (donorRole == null) {
            donorRole = new Role();
            donorRole.setName("DONOR");
            roleRepository.save(donorRole);

        }
        List<Role> roles = new ArrayList<>();
        roles.add(donorRole);
        System.out.println("I'm not null " + donorRole.toString() );
        user.setRoles(roles);
        userRepository.save(user);
    }




    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        List<Role> roles = user.getRoles();
        List<RoleDto> roleDtos = new ArrayList<>();
        for(Role role:roles){
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setName(role.getName());
            roleDtos.add(roleDto);
        }
        userDto.setRoles(roleDtos);
        System.out.println(roleDtos.size());
        return userDto;
    }
}
