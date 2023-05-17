package com.example.discordexa.discord.security;

import com.example.discordexa.discord.bean.User;
import com.example.discordexa.discord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUtilisateur = userRepository.findByEmail(email);

        if(optionalUtilisateur.isEmpty()){
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }



        return new MyUserDetails(optionalUtilisateur.get());
    }
}
