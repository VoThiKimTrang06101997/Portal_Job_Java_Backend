package com.r2s.findInternship.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.EMailType;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.data.repository.UserRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.r2s.findInternship.common.MailResponse;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.service.MailService;
import com.r2s.findInternship.service.UserService;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private ServletContext context;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserRepository userRepository;
    private List<MimeMessage> queue = new ArrayList<MimeMessage>();
    @Value("${url.redirect.path}")
    private String urlRedirect;

    @Override
    public void send(MailResponse response) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd%HH:mm:ss"));

        UUID uuid = UUID.randomUUID();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(response.getTo());

            switch (response.getTypeMail()) {
                case ConfirmMail:
                    response.setSubject("Xác thực email cho tài khoản Jobsit.vn");

                    userService.updateTokenActive(response.getTo(), String.valueOf(uuid));
                    response.createMailConfirm(urlRedirect, String.valueOf(uuid));
                    break;

                case ApplyJob:// SEND MAIL HR
                    // response.createTemplate();//Paramater: Name candidate, Name job
                    response.setSubject("Thông Báo Ứng Viên Ứng Tuyển");
                    if (response.getCv() != null) {
                        File file = new File(context.getRealPath("/") + response.getCv());
                        helper.addAttachment("File CV", file);
                    }
                    response.createTemplate();
                    break;
                case ForgotPassword:
                    response.setSubject("Yêu cầu đổi mật khẩu tài khoản trên Jobsit.vn");
                    // String newPass = userService.resetPassword(response.getTo()); //Change
                    // Password
                    String token = String.valueOf(uuid) + "%" + formattedDate;
                    userService.updateTokenForgetPassword(response.getTo(), token);
                    response.createMailForgotPassword(urlRedirect, token);
                    break;
                case ForgotPasswordOTP:
                    response.setSubject("Yêu cầu đổi mật khẩu tài khoản trên App Jobsit");

                    Random random = new Random();
                    int randomNumber = random.nextInt(9000) + 1000;
                    response.setSubject(randomNumber + " Yêu cầu đổi mật khẩu tài khoản trên App Jobsit");

                    userService.updateTokenForgetPassword(response.getTo(), String.valueOf(randomNumber));
                    response.createMailForgotPasswordOTP(urlRedirect, randomNumber);
                    break;
                case HRApply:
                    if (response.getCv() != null) {
                        File file = new File(context.getRealPath("/") + response.getCv());
                        helper.addAttachment("File", file);
                    }
                    response.createTemplateHRApply();
                    break;
                case ActiveUniversity:
                    // todo send mail active university
                    response.setSubject("KÍCH HOẠT TÀI KHOẢN THÀNH CÔNG");
                    response.createTemplateActive();
                    break;
                case ActiveCompany:
                    // todo send mail active Company
                    response.setSubject("KÍCH HOẠT TÀI KHOẢN THÀNH CÔNG");
                    break;
                default:
                    throw new InternalServerErrorException("Type mail is incorect!");
            }
            helper.setSubject(response.getSubject());
            helper.setText(response.getMailTemplate(), true);
            // File logo = new File(application.getRealPath("/") + "images/logoR2S.png");
            // String
            // path="https://firebasestorage.googleapis.com/v0/b/storageimg-36153.appspot.com/o/images%2FlogoR2S.png?alt=media&token=275a3386-2251-4329-b732-4781ecbd8886";
            // URL url = new URL(path);
            // BufferedImage img = ImageIO.read(url);
            // File file = new File(application.getRealPath("/") + "images/logoR2SS.png");
            // if(!file.isFile()) {
            // ImageIO.write(img, "png", file);
            // }
            //
            // helper.addInline("R2S", file);
            queue.add(message);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }


    @Override
    public MessageResponse sendMailActive(String email) {
        User user = userService.findByEmail(email);
        if (user.getStatus().getName().equals(Estatus.Active.toString()))
            throw new InternalServerErrorException(messageSource.getMessage("error.alreadyActive", null, null));
        MailResponse mail = new MailResponse();
        mail.setTo(email);// Set email to reset password! Get User by Email => Change Password
        mail.setTypeMail(EMailType.ConfirmMail);
        this.send(mail);
        return new MessageResponse(200, "SEND MAIL", null);
    }

    @Override
    public MessageResponse sendMailForgotPassword(String email, boolean flag) {
        if (!userRepository.existsByEmail(email))
            throw new ResourceNotFoundException("User", "email", email);
        MailResponse mail = new MailResponse();
        mail.setTo(email);// Set email to reset password! Get User by Email => Change Password

        if (!flag)
            mail.setTypeMail(EMailType.ForgotPassword);
        else
            mail.setTypeMail(EMailType.ForgotPasswordOTP);
        this.send(mail);
        return new MessageResponse(200, "SEND MAIL", null);
    }

    @Scheduled(fixedDelay = 10000) // DELAY 10s
    public void run() {
        boolean flag = false;
        while (!queue.isEmpty()) {
            MimeMessage message = queue.remove(0);
            try {
                javaMailSender.send(message);
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flag)
            System.out.println("Send mail successfully");
    }

}
