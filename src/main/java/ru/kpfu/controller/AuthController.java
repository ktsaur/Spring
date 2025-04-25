package ru.kpfu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.entity.User;
import ru.kpfu.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setConfirmationToken(UUID.randomUUID().toString());

        userRepository.save(user);

        sendConfirmationEmail(user);

        return "redirect:/confirm-info";
    }

    private void sendConfirmationEmail(User user) {
        String subject = "Подтверждение регистрации";
        String confirmationUrl = "http://localhost:8080/confirm?token=" + user.getConfirmationToken();
        String message = "Здравствуйте, " + user.getUsername() + "!\n\n"
                + "Пожалуйста, подтвердите регистрацию, перейдя по ссылке:\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getUsername());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }

    @GetMapping("/confirm")
    public String confirmAccount(@RequestParam("token") String token) {
        Optional<User> optionalUser = userRepository.findAll().stream()
                .filter(u -> token.equals(u.getConfirmationToken()))
                .findFirst();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setConfirmationToken(null);
            userRepository.save(user);
            return "redirect:/login?confirmed";
        } else {
            return "redirect:/login?error=invalidToken";
        }
    }


    @GetMapping("/confirm-info")
    public String confirmationInfo() {
        return "confirm-info";
    }
}