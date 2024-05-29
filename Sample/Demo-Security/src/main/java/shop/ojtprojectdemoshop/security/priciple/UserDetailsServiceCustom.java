package shop.ojtprojectdemoshop.security.priciple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.ojtprojectdemoshop.model.entity.Role;
import shop.ojtprojectdemoshop.model.entity.User;
import shop.ojtprojectdemoshop.repository.IUserRepository;
import shop.ojtprojectdemoshop.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // viết ko sd stream
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            Set<Role> roleSet = user.get().getRoles();
//            for (Role role : roleSet) {
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName().name());
//                grantedAuthorities.add(grantedAuthority);
//            }
//            return UserDetailCustom.builder()
//                    .user(user.get())
//                    .authorities(grantedAuthorities)
//                    .build();

            return UserDetailCustom.builder()
                    .user(user)
                    .authorities(user.getRoles()
                            .stream()
                            .map(iterm -> new SimpleGrantedAuthority(iterm.getRoleName().name()))
                            .collect(Collectors.toSet()))
                    .build();
    }
}
