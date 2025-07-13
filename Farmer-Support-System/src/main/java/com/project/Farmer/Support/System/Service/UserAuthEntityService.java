package com.project.Farmer.Support.System.Service;

import com.project.Farmer.Support.System.Entity.UserAuthEntity;
import com.project.Farmer.Support.System.Exception.UserNotFoundException;
import com.project.Farmer.Support.System.Repository.UserAuthEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserAuthEntityService implements UserDetailsService {
    private final static Logger logger= LoggerFactory.getLogger(UserAuthEntityService.class);

    private final UserAuthEntityRepository userAuthEntityRepository;

    UserAuthEntityService(UserAuthEntityRepository userAuthEntityRepository){
        this.userAuthEntityRepository=userAuthEntityRepository;
    }

    public UserDetails save(UserAuthEntity userAuth){
        return userAuthEntityRepository.save(userAuth);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserAuthEntity> userAuthEntity = userAuthEntityRepository.findByUsername(username);
              if(userAuthEntity.isPresent()){
                logger.info("Fetched user details from DB: {}",userAuthEntity);
                  return userAuthEntity.get();
              }else{
                  logger.warn("User not found with username: {}", username);
                  throw new UsernameNotFoundException("User Not Found with username: " + username);
              }
    }
}
