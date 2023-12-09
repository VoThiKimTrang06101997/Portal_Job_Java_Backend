package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import com.r2s.findInternship.common.JwtUtils;
import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.EAuthenticationProvider;
import com.r2s.findInternship.common.enumeration.ERole;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.common.util.Validation;
import com.r2s.findInternship.data.dto.ChangePasswordByTokenDTO;
import com.r2s.findInternship.data.dto.ChangePasswordDTO;
import com.r2s.findInternship.data.dto.JwtResponseDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.user.UserCreationDTO;
import com.r2s.findInternship.data.dto.user.UserDTO;
import com.r2s.findInternship.data.dto.user.UserProfileDTO;
import com.r2s.findInternship.data.entity.Status;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.data.mapper.RoleMapper;
import com.r2s.findInternship.data.mapper.UserMapper;
import com.r2s.findInternship.data.repository.RoleRepository;
import com.r2s.findInternship.data.repository.StatusRepository;
import com.r2s.findInternship.data.repository.UserRepository;
import com.r2s.findInternship.common.util.oauth.OAuth2UserInfo;
import com.r2s.findInternship.service.FileService;
import com.r2s.findInternship.service.RoleService;
import com.r2s.findInternship.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.InvalidOldPasswordException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private Validation validation;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuditorAware<Long> auditorAware;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StatusService statusService;

    public final Logger logger = LoggerFactory.getLogger("info");

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.build(findByEmail(email));
    }

    @Override
    public void updateTokenForgetPassword(String email, String token) {
        User user = this.findByEmail(email);
        user.setPasswordForgotToken(token);
        userRepository.save(user);
    }

    @Override
    public void updateTokenActive(String email, String token) {
        User user = this.findByEmail(email);
        user.setTokenActive(token);
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> authenticateUser(User userOAuth, Authentication authentication) {
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userOAuth.getEmail(), userOAuth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        long id = userOAuth.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new ResponseEntity<JwtResponseDTO>(
                new JwtResponseDTO(jwt, user.getEmail(), roles.get(0), userOAuth.getAvatar(), id,
                        null),
                HttpStatus.CREATED);

    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        Page<UserDTO> page = this.userRepository.findAll(PageRequest.of(no, limit)).map(u -> userMapper.toDTO(u));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public String encodePass(String pass) {
        return this.passwordEncoder.encode(pass);
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new InternalServerErrorException(messageSource.getMessage("error.userAuthen",
                        null, null)));
        if (checkValidOldPassword(user.getPassword(), changePasswordDTO.getOldPassword())) {
            if (!validation.passwordValid(changePasswordDTO.getNewPassword()))
                throw new InternalServerErrorException(messageSource.getMessage("error.passwordRegex",
                        null, null));
            user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new InvalidOldPasswordException(messageSource.getMessage("error.passwordIncorrect",
                    null, null));
        }
        return true;
    }

    @Override
    public void changePasswordByToken(ChangePasswordByTokenDTO changePasswordByTokenDTO) {

        User user = this.userRepository.findByPasswordForgotToken(changePasswordByTokenDTO.getToken()).orElseThrow(
                () -> new InternalServerErrorException(this.messageSource.getMessage("error.token", null, null)));
        boolean check = this.checkTimeTokenChangePassword(changePasswordByTokenDTO.getToken());
        if (!check) {
            throw new InternalServerErrorException(this.messageSource.getMessage("error.tokenIsExpired", null, null));
        }
        if (!this.validation.passwordValid(changePasswordByTokenDTO.getNewPassword())) // Check Password is strong
            throw new InternalServerErrorException(messageSource.getMessage("error.passwordRegex", null, null));
        user.setPassword(new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10)
                .encode(changePasswordByTokenDTO.getNewPassword()));
        // user.setTokenForgetPassword("");
        this.userRepository.save(user);
    }

    @Override
    public String checkStatusUser(Status status) {
        String message = "";
        switch (status.getId()) {
            case 1:
                message = "";
                break;
            case 2:
                message = messageSource.getMessage("error.notAvailable", null, null);
                break;
            case 3:
                message = messageSource.getMessage("error.lockUser", null, null);
                break;
            case 4:
                message = messageSource.getMessage("error.disableUser", null, null);
                break;
        }
        return message;
    }

    @Override
    public UserProfileDTO findById(long id) {
        // User user = this.userRepository.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("User", "id",
        // String.valueOf(id)));
        // return this.userMapper.toDetailsDTO(user);
        return null;

    }

    @Override
    public MessageResponse updateMailReceive(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));;
        user.setMailReceive(!user.isMailReceive());
        userRepository.save(user);

        return new MessageResponse(200, null,  null);
    }

    @Override
    public boolean checkTimeTokenChangePassword(String time) {
        String[] parts = time.split("%");
        String timeToken = parts[1] + " " + parts[2];
        LocalDateTime localDateTime = LocalDateTime.parse(timeToken,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return LocalDateTime.now().isBefore(localDateTime.plusMinutes(10));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new InternalServerErrorException(
                        this.messageSource.getMessage("error.emailFormat", null, null)));
    }

    @Override
    public boolean checkValidOldPassword(String oldPass, String confirmPass) {
        return passwordEncoder.matches(confirmPass, oldPass);
    }

    @Override
    public List<Object[]> getGenderStatistics() {
        // return userRepository.getGenderStatistics();
        return null;

    }

    @Override
    public Long countByCreatedDate(Date from, Date to) {
        // return userRepository.countByCreatedDateBetween(from, to);
        return null;
    }

    @Override
    public List<Object[]> getStatusStatistics() {
        // return userRepository.getStatusStatistics();
        return null;

    }

    @Override
    public List<Object[]> getNewStatistics() { // created date within 1 month
        // return userRepository
        // .getNewStatistics(DateTimeHelper.getEarliestTimeOfDate(DateTimeHelper.getDateTimeOfMonthAgo(1)));
        return null;

    }

    @Override
    public List<Object[]> getRoleStatistics() {
        // return userRepository.getRoleStatistics();
        return null;

    }

    @Override
    public PaginationDTO findAllByEmailLike(String email, int no, int limit) {
        Pageable page = PageRequest.of(no, limit);
        Page<User> userPage = userRepository.findALLByEmailLike(email, page);
        List<UserDTO> users = userPage.getContent().stream().map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
        return new PaginationDTO(users, userPage.isFirst(), userPage.isLast(), userPage.getTotalPages(),
                userPage.getTotalElements(), userPage.getSize(), userPage.getNumberOfElements());
    }

    @Override
    public User createAfterLoginOAuth(OAuth2UserInfo oAuth2UserInfo, EAuthenticationProvider provider) {
         UUID uuid = UUID.randomUUID();
         User user = new User();
         //create with google
         if (oAuth2UserInfo.getClientName().equals("Google")) {
         user.setEmail(oAuth2UserInfo.getEmail());
         user.setLastName(oAuth2UserInfo.getLastName());
         user.setFirstName(oAuth2UserInfo.getFistName());
         user.setAvatar(oAuth2UserInfo.getAvatar());
         user.setPassword(
         new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10)
         .encode(String.valueOf(uuid)));
        // user.setUsername(oAuth2UserInfo.getEmail() + uuid);
         user.setCreatedDate(new Date());
         user.setAuthProvider(String.valueOf(provider));
         user.setRole(roleMapper.toEntity(roleService.findByName("Role_Candidate")));
         user.setStatus(this.statusService.findByName(Estatus.Active));
         user.setMailReceive(true);
         user.setLocation(oAuth2UserInfo.getLocale());
//         user.setBirthDay(new Date());
//         user.setGender(true);
         return user;
         //create with facebook
//         } else {
        // user.setEmail(oAuth2UserInfo.getEmail());
        // user.setLastName("");
        // user.setFirstName(oAuth2UserInfo.getName());
        // user.setAvatar(oAuth2UserInfo.getAvatar());
        // user.setPassword(
        // new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 10)
        // .encode(String.valueOf(uuid)));
        // user.setUsername(oAuth2UserInfo.getEmail() + uuid);
        // user.setCreatedDate(LocalDateTime.now());
        // user.setAuthProvider(String.valueOf(EAuthenticationProvider.FACEBOOK));
        // user.setRole(roleService.findById(3));
        // user.setStatus(this.statusService.findByName(Estatus.Active));
        // return user;
         }
        return null;
    }

    @Override
    public void updateAfterLoginOAuth(User user, OAuth2UserInfo oAuth2UserInfo, EAuthenticationProvider provider) {

         if (oAuth2UserInfo.getClientName().equals("Google")) {
         user.setStatus(this.statusService.findByName(Estatus.Active));
         user.setLastName(oAuth2UserInfo.getLastName());
         user.setFirstName(oAuth2UserInfo.getFistName());
         user.setAvatar(oAuth2UserInfo.getAvatar());
         user.setAuthProvider(String.valueOf(provider));
         user.setCreatedDate(new Date());
         userRepository.save(user);
        // } else {
        // user.setStatus(this.statusService.findByName(Estatus.Active));
        // user.setLastName("");
        // user.setFirstName(oAuth2UserInfo.getName());
        // user.setAvatar(oAuth2UserInfo.getAvatar());
        // user.setAuthProvider(String.valueOf(EAuthenticationProvider.FACEBOOK));
        // user.setCreatedDate(LocalDateTime.now());
        // userRepository.save(user);
         }
    }

    // =======================================================================================//

    @Override
    public Long getCurrentUserId() {
        return auditorAware.getCurrentAuditor().orElse(null);
    }

    @Override
    public UserDTO create(UserCreationDTO userCreationDTO, MultipartFile fileAvatar, ERole eRole) {
        // check existing user info
        Map<String, String> errors = new HashMap<String, String>();
        if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
            errors.put("Email", messageSource.getMessage("error.emailExists", null, null));
        }
        if (errors.size() > 0) {
            throw new InternalServerErrorException(errors);
        }

        // set info for user
        User user = userMapper.toEntity(userCreationDTO);
        user.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
        user.setAvatar(fileService.uploadFile(fileAvatar));
        // set default role and status
        user.setRole(
                roleRepository.findByName(eRole.toString())
                        .orElseThrow(() -> new ResourceNotFoundException("Role", "name", eRole.toString())));
        user.setStatus(
                statusRepository.findByName(Estatus.Not_Active.toString())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Status", "name", Estatus.Not_Active.toString())));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));

        // check existing user info in another one
        Map<String, String> errors = new HashMap<String, String>();
//        if (userRepository.existsByIdNotAndEmail(id, userProfileDTO.getEmail())) {
//            errors.put("Email", messageSource.getMessage("error.emailExists", null, null));
//        }

        if (errors.size() > 0) {
            throw new InternalServerErrorException(errors);
        }

        User updateUser = userMapper.toEntity(userProfileDTO);
        updateUser.setId(oldUser.getId());
        updateUser.setEmail(oldUser.getEmail());
        updateUser.setPassword(oldUser.getPassword());
        if (oldUser.getAvatar() != null && !oldUser.getAvatar().isBlank()) {
            fileService.deleteFile(oldUser.getAvatar());
        }
        updateUser.setAvatar(fileService.uploadFile(fileAvatar));
        updateUser.setRole(oldUser.getRole());
        updateUser.setStatus(oldUser.getStatus());

        return userMapper.toDTO(userRepository.save(updateUser));
    }

    @Transactional
    @Override
    public UserDTO updateHRInfo(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar) {
//        User oldUser = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
//
//        User updateUser = userMapper.toEntity(userProfileDTO);
//        updateUser.setId(oldUser.getId());
//        updateUser.setEmail(oldUser.getEmail());
//        updateUser.setPassword(oldUser.getPassword());
//        if (oldUser.getAvatar() != null && !oldUser.getAvatar().isBlank()) {
//            fileService.deleteFile(oldUser.getAvatar());
//        }
//        updateUser.setAvatar(fileService.uploadFile(fileAvatar));
//        updateUser.setRole(oldUser.getRole());
//        updateUser.setStatus(oldUser.getStatus());
//        updateUser.setMailReceive(oldUser.isMailReceive());
//
//        return userMapper.toDTO(userRepository.save(updateUser));
        return null;
    }

}