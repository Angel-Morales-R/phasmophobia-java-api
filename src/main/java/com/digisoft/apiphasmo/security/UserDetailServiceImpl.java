package com.digisoft.apiphasmo.security;

import com.digisoft.apiphasmo.models.UsersModel;
import com.digisoft.apiphasmo.repositories.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUsersRepository iUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        UsersModel user = iUsersRepository
                .findOneByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("Email User" + email + "Not found"));
        return  new UserDetailsImpl(user);
    }

}
