package shop.ojtprojectdemoshop.security.priciple;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop.ojtprojectdemoshop.model.entity.Role;
import shop.ojtprojectdemoshop.model.entity.User;

import java.util.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class UserDetailCustom implements UserDetails {
    User user;
    Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

//    public static UserDetails build(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        Set<Role> roleSet = user.getRoles();
//        for (Role role : roleSet) {
//            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName().name());
//            authorities.add(authority);
//        }
//        return UserDetailCustom.builder()
//                .user.setId(user.getId())
//                .;
//
//    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
