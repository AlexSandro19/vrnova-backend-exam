package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/profile")
public class UserController {

    private UserService userService;
    private PasswordEncoder encoder;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserController(UserService userService, PasswordEncoder encoder, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.encoder = encoder;
        this.javaMailSender = javaMailSender;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdate userUpdate) {
        System.out.println("user from frontend= " + userUpdate);
        User newUser = userService.findById((long) Integer.parseInt(userUpdate.getUserId()));
        if (!userUpdate.getPassword().equals("")) {
            String encodedPassword = encoder.encode(userUpdate.getPassword());
            newUser.setPassword(encodedPassword);
            SendEmail sendEmail = new SendEmail(newUser.getEmail());
            sendEmail.start();
        }
        userService.save(newUser);
        return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
    }

    @PutMapping("/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestBody UserUpdate userUpdate) /*throws ResourceNotFoundException */{
        System.out.println("user from frontend to be deactivated= " + userUpdate.userId);
        long userID = Integer.parseInt(userUpdate.getUserId());
        userService.delete(userID);
        return ResponseEntity.ok(new MessageResponse("Profile deactivated successfully!"));
    }

    /*
        Data-transfer-object to send user data from frontend.
    */
    private static class UserUpdate {
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("password")
        private String password;

        public UserUpdate() {
        }

        public UserUpdate(String userId) {
            this.userId = userId;
        }

        public UserUpdate(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "UserUpdate{" +
                    "userId='" + userId + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    /*
        This thread class is responsible for sending the email.
        I created a thread because it takes around 8 seconds to send the email, and
        the user is not allowed to log in with the new password in the meantime.
    */
    class SendEmail extends Thread {

        private String email;

        public SendEmail(String email) {
            this.email = email;
        }

        public String htmlToString() throws IOException {
            StringBuilder bldr = new StringBuilder();
            String str;

            BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("email.html")));
            while((str = in.readLine())!=null)
                bldr.append(str);
            in.close();
            return bldr.toString();
        }

        public void run(){
            System.out.println("This code is running in a thread");
            MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws Exception {
                    mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));
                    mimeMessage.setFrom(new InternetAddress("support@vrnova.dk"));
                    mimeMessage.setSubject("Your password has been changed");
                    mimeMessage.setContent(htmlToString(), "text/html");
                    mimeMessage.saveChanges();
                }
            };
            javaMailSender.send(messagePreparator);
            System.out.println("Email sent!");
        }
    }
}
