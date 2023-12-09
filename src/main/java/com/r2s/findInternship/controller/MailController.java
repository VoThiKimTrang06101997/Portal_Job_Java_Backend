package com.r2s.findInternship.controller;

 import com.r2s.findInternship.constant.ApiURL;
 import com.r2s.findInternship.service.MailService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

 @CrossOrigin(origins = "*", maxAge = 3600)
 @RestController
 @RequestMapping(ApiURL.MAIL)
 public class MailController {
     @Autowired
     private MailService mailService;
    
 	  @GetMapping("/active-user")
     public ResponseEntity<?> sendMailActive(@RequestParam String email){
       return  ResponseEntity.ok(mailService.sendMailActive(email));
     }
 }
