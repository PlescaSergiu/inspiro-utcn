package com.inspiro.data.security.registration;


import com.inspiro.data.entity.User;
import com.inspiro.data.service.AuthService;
import com.inspiro.data.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@AllArgsConstructor
public class RegistrationService {


    private final UserServiceImp userServiceImp;
    private final MailSender mailSender;


    public String register(String username, String email, String password) {
        String  activationToken = RandomStringUtils.randomAlphanumeric(32);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String  encode = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encode);
        user.setActivationCode(activationToken);
        user.setImage(setRandomPhoto());
        userServiceImp.createUser(user);

        String text = "http://localhost:8081/activate?code=" + activationToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@example.com");
        message.setSubject("Confirmation email");
        message.setText(text);
        message.setTo(email);
        mailSender.send(message);

        return activationToken;
    }


    public void activate(String activationCode) throws AuthService.AuthException {
        User user = userServiceImp.getByActivationCode(activationCode);
        if (user != null) {
            user.setActive(true);
            userServiceImp.save(user);
        } else {
            throw new AuthService.AuthException();
        }
    }

    public String setRandomPhoto(){
        int number =  new Random().nextInt(100)+1;
        return "https://randomuser.me/api/portraits/women/"+number+".jpg";
    }
}
