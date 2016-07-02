package com.thoughtworks.security;

import com.thoughtworks.model.Account;
import com.thoughtworks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountUserDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if (account == null){
            throw new UsernameNotFoundException("Invalid credentials");
        }

        if (account.getRoles() == null || account.getRoles().isEmpty()){
            throw new UsernameNotFoundException("Invalid credentials");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        account.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode())));
        return new User(
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                !account.isExpired(),
                !account.isCredentialsExpired(),
                !account.isLocked(),
                grantedAuthorities);
    }

}
