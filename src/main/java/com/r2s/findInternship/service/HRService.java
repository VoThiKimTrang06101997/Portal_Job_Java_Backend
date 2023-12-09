package com.r2s.findInternship.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.hr.HRCreationDTO;
import com.r2s.findInternship.data.dto.hr.HRDTO;
import com.r2s.findInternship.data.dto.hr.HRProfileDTO;

public interface HRService {
	
	PaginationDTO findAll(int no, int limit);

	HRDTO findById(long id);

	HRDTO findByUserId(long userId);

	@Transactional
	HRDTO create(HRCreationDTO hrCreationDTO, MultipartFile fileAvatar);

//	@Transactional
//	HRDTO update(long id, HRProfileDTO hrProfileDTO, MultipartFile fileAvatar);

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
    //@Transactional
	HRDTO updateHRInfo(HRProfileDTO hrProfileDTO, MultipartFile fileAvatar);
}
