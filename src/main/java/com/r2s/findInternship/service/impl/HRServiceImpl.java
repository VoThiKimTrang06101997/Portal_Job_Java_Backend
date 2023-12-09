package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.data.mapper.UserMapper;
import com.r2s.findInternship.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.common.enumeration.ERole;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.hr.HRCreationDTO;
import com.r2s.findInternship.data.dto.hr.HRDTO;
import com.r2s.findInternship.data.dto.hr.HRProfileDTO;
import com.r2s.findInternship.data.dto.user.UserDTO;
import com.r2s.findInternship.data.entity.HR;
import com.r2s.findInternship.data.mapper.HRMapper;
import com.r2s.findInternship.data.repository.HRRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.CompanyService;
import com.r2s.findInternship.service.HRService;
import com.r2s.findInternship.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class HRServiceImpl implements HRService {

    @Autowired
    private HRRepository hrRepository;
    @Autowired
    private HRMapper hrMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public PaginationDTO findAll(int no, int limit) {
        Page<HRDTO> page = hrRepository.findAll(PageRequest.of(no, limit)).map(hr -> hrMapper.toDTO(hr));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public HRDTO findById(long id) {
        return hrMapper.toDTO(
                hrRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("HR", "id", String.valueOf(id))));
    }

    @Override
    public HRDTO findByUserId(long userId) {
        return hrMapper.toDTO(
                hrRepository.findByUser_Id(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("HR", "user id", String.valueOf(userId))));
    }

    @Override
    public HRDTO create(HRCreationDTO hrCreationDTO, MultipartFile fileAvatar) {
        // save user first
        UserDTO createdUserDTO = userService.create(hrCreationDTO.getUserCreationDTO(), fileAvatar, ERole.HR);

        // if not exists hr's company when hr register, create new company first
        if (hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO().getId() == null) {
            hrCreationDTO.getHrOtherInfoDTO()
                    .setCompanyDTO(companyService.create(hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO(), null));
        } else {
            hrCreationDTO.getHrOtherInfoDTO()
                    .setCompanyDTO(companyService.findById(hrCreationDTO.getHrOtherInfoDTO().getCompanyDTO().getId()));
        }

        HR hr = hrMapper.toEntity(hrCreationDTO);
        hr.getUser().setId(createdUserDTO.getId());

        HRDTO hrDTO = hrMapper.toDTO(hrRepository.save(hr));
        hrDTO.setUserDTO(createdUserDTO);
        return hrDTO;
    }

    //    @Override
//    public HRDTO update(long id, HRProfileDTO hrProfileDTO, MultipartFile fileAvatar) {
//        HR oldHR = hrRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("HR", "id", String.valueOf(id)));
//
//        // update user
//        UserDTO updatedUserDTO = userService.update(
//                oldHR.getUser().getId(), hrProfileDTO.getUserProfileDTO(), fileAvatar);
//
//        HR updateHR = hrMapper.toEntity(hrProfileDTO);
//        updateHR.setId(oldHR.getId());
//        updateHR.getUser().setId(updatedUserDTO.getId());
//        updateHR.setCompany(oldHR.getCompany());
//
//        HRDTO updatedHRDTO = hrMapper.toDTO(hrRepository.save(updateHR));
//        updatedHRDTO.setUserDTO(updatedUserDTO);
//        return updatedHRDTO;
//    }
    @Override
    public HRDTO updateHRInfo(HRProfileDTO hrProfileDTO, MultipartFile fileAvatar) {
        Map<String, String> errors = new HashMap<String, String>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        UserDTO updatedUserDTO = userService.updateHRInfo(
                hr.getUser().getId(), hrProfileDTO.getUserProfileDTO(), fileAvatar);

        HR updateHR = hrMapper.toEntity(hrProfileDTO);
        updateHR.setId(hr.getId());
        updateHR.getUser().setId(updatedUserDTO.getId());
        updateHR.setCompany(hr.getCompany());

        HRDTO updatedHRDTO = hrMapper.toDTO(hrRepository.save(updateHR));
        //updatedHRDTO.setUserDTO(updatedUserDTO);
        return updatedHRDTO;
    }

}
