package com.api.avris.service.impl;

import com.api.avris.jpa.Users;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JavaMailSender emails;


    @Override
    public Users login(String email) {
        String code = getRandomNumberString();
        Optional<Users> users = usersRepository.login(email);
        String token = UUID.randomUUID().toString();
        Users custom= users.get();
        custom.setToken(token);
        custom.setCode(code);
        sendEmail(email, code);
        return  usersRepository.save(custom);
    }

    @Override
    public Users postCode(String code) {
        Optional<Users> users = usersRepository.postCode(code);
        String token = UUID.randomUUID().toString();
        Users custom= users.get();
        custom.setToken(token);
        return  usersRepository.save(custom);
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<Users> users= usersRepository.findByToken(token);
        if(users.isPresent()){
            Users customer1 = users.get();
            User user= new User(customer1.getHandphone(), customer1.getEmail(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }


    @Override
    public Users save(Users user) {
        String token = UUID.randomUUID().toString();
        Users newUser = new Users();
        newUser.setFullName(user.getFullName());
        newUser.setNoIdentity(user.getNoIdentity());
        newUser.setUnit(user.getUnit());
        newUser.setHandphone(user.getHandphone());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setRole(user.getRole());
        newUser.setToken(token);

        return usersRepository.save(newUser);
    }


    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }

    void sendEmail(String email, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("KODE VERIFIKASI URBAN HEIGHT APPS");
        msg.setText(code);
        emails.send(msg);
    }

}

