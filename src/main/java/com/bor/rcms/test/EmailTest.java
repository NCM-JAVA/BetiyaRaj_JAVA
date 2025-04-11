package com.bor.rcms.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailTest {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail(String toEmail, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("your_email@gmail.com"); // should match your spring.mail.username
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//        System.out.println("✅ Email sent successfully to " + toEmail);
//    }
//    public static void main(String[] args) {
//    	
//    	EmailTest emailTest=new EmailTest();
//    	emailTest.sendEmail(
//                ".com", 
//                "📧 Test Subject from Spring Boot", 
//                "This is a test email sent from Spring Boot using Gmail SMTP."
//            );
//		
//	}
}
