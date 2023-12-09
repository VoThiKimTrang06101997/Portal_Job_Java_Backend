package com.r2s.findInternship.service;

import java.util.Date;
import java.util.List;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.EAuthenticationProvider;
import com.r2s.findInternship.common.enumeration.ERole;
import com.r2s.findInternship.common.util.oauth.OAuth2UserInfo;
import com.r2s.findInternship.data.entity.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.ChangePasswordByTokenDTO;
import com.r2s.findInternship.data.dto.ChangePasswordDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.user.UserCreationDTO;
import com.r2s.findInternship.data.dto.user.UserDTO;
import com.r2s.findInternship.data.dto.user.UserProfileDTO;
import com.r2s.findInternship.data.entity.User;

public interface UserService extends UserDetailsService {
    boolean existsById(long id);

    UserProfileDTO findById(long id);

    MessageResponse updateMailReceive(long id);

    ResponseEntity<?> authenticateUser(User userOAuth, Authentication authentication);

    void updateTokenForgetPassword(String email, String token);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    String checkStatusUser(Status status);

    boolean checkTimeTokenChangePassword(String time);

    PaginationDTO findAll(int no, int limit);

    boolean changePassword(ChangePasswordDTO changePasswordDTO);

    boolean checkValidOldPassword(String oldPass, String newPass);

    Long countByCreatedDate(Date from, Date to);

    List<Object[]> getGenderStatistics();

    List<Object[]> getRoleStatistics();

    List<Object[]> getStatusStatistics();

    List<Object[]> getNewStatistics();

    boolean existsByEmail(String email);

    String encodePass(String pass);

    User findByEmail(String email);

    PaginationDTO findAllByEmailLike(String username, int no, int limit);

    User createAfterLoginOAuth(OAuth2UserInfo oAuth2UserInf, EAuthenticationProvider provider);

    void updateAfterLoginOAuth(User user, OAuth2UserInfo oAuth2UserInfo, EAuthenticationProvider provider);

    void changePasswordByToken(ChangePasswordByTokenDTO changePasswordByTokenDTO);

    void updateTokenActive(String email, String token);

    // ===============================================================================//

    Long getCurrentUserId();

    UserDTO create(UserCreationDTO userCreationDTO, MultipartFile fileAvatar, ERole eRole);

    UserDTO update(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar);

    UserDTO updateHRInfo(long id, UserProfileDTO userProfileDTO, MultipartFile fileAvatar);
}
