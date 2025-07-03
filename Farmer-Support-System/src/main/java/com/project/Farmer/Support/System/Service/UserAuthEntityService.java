package com.project.Farmer.Support.System.Service;

import com.project.Farmer.Support.System.Entity.UserAuthEntity;
import com.project.Farmer.Support.System.Exception.UserNotFoundException;
import com.project.Farmer.Support.System.Repository.UserAuthEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserAuthEntityService implements UserDetailsService {

    private final UserAuthEntityRepository userAuthEntityRepository;

    UserAuthEntityService(UserAuthEntityRepository userAuthEntityRepository){
        this.userAuthEntityRepository=userAuthEntityRepository;
    }

    public UserDetails save(UserAuthEntity userAuth){
        return userAuthEntityRepository.save(userAuth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAuthEntityRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
    }
}
