package shop.ojtprojectdemoshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.ojtprojectdemoshop.model.dto.request.FormLogin;
import shop.ojtprojectdemoshop.model.dto.request.FormRegister;
import shop.ojtprojectdemoshop.model.dto.response.JwtResponse;
import shop.ojtprojectdemoshop.model.entity.Role;
import shop.ojtprojectdemoshop.model.entity.RoleName;
import shop.ojtprojectdemoshop.model.entity.User;
import shop.ojtprojectdemoshop.repository.IRoleRepository;
import shop.ojtprojectdemoshop.repository.IUserRepository;
import shop.ojtprojectdemoshop.security.jwt.JwtProvider;
import shop.ojtprojectdemoshop.security.priciple.UserDetailCustom;
import shop.ojtprojectdemoshop.service.IUserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(FormRegister formRegister) {
        User user = User.builder()
                .email(formRegister.getEmail())
                .fullName(formRegister.getFullName())
                .username(formRegister.getUsername())
                .password(passwordEncoder.encode(formRegister.getPassword()))
                .status(true)
                .build();
        if (formRegister.getRoles() != null && !formRegister.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();

            formRegister.getRoles().forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role Not Found")));
                    case "MANAGER":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_MANAGER).orElseThrow(() -> new RuntimeException("Role Not Found")));
                    case "USER":
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Role Not Found")));
                    default:
                        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Role Not Found")));
                }
            });
            user.setRoles(roles);
        } else {
            // mac dinh la User
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Role Not Found")));
            user.setRoles(roles);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public JwtResponse login(FormLogin formLogin) {
        Authentication authentication = null;
        try {
            authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication Error");
        }
        UserDetailCustom detailCustom = (UserDetailCustom) authentication.getPrincipal();
        String accessToken = jwtProvider.generateAccessToken(detailCustom);
        return JwtResponse.builder()
                .email(detailCustom.getUser().getEmail())
                .fullName(detailCustom.getUser().getFullName())
                .roles(detailCustom.getAuthorities())
                .status(detailCustom.getUser().isStatus())
                .accessToken(accessToken)
                .build();
    }

}
