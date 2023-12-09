package com.r2s.findInternship.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.UniversityDTO;
import com.r2s.findInternship.data.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.data.entity.Partner;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.data.mapper.PartnerMapper;
import com.r2s.findInternship.data.repository.PartnerRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.PartnerService;
import com.r2s.findInternship.service.UniversityService;
import com.r2s.findInternship.service.UserService;

@Service
public class PartnerServiceImpl implements PartnerService {
	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PartnerMapper partnerMapper;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UniversityService universityService;
    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    @Override
    public List<com.r2s.findInternship.data.dto.partner.PartnerDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public PaginationDTO findAllByUniversityId(int universityId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByUniversityId'");
    }
    @Override
    public com.r2s.findInternship.data.dto.partner.PartnerDTO create(
            com.r2s.findInternship.data.dto.partner.PartnerDTO partnerDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public com.r2s.findInternship.data.dto.partner.PartnerDTO update(
            com.r2s.findInternship.data.dto.partner.PartnerDTO partnerDTO, int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public List<UniversityPartnerDTOShow> findByUniversityId(int universityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUniversityId'");
    }
    @Override
    public Partner findByUserId(long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }
    @Override
    public com.r2s.findInternship.data.dto.partner.PartnerDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public com.r2s.findInternship.data.dto.partner.PartnerDTO updateUser(
            com.r2s.findInternship.data.dto.partner.PartnerCreationDTO partnerCreationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
    @Override
    public com.r2s.findInternship.data.dto.partner.PartnerCreationDTO readJson(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readJson'");
    }

	// @Override
	// public PartnerDTO create(PartnerDTO partnerDTO) {
	// 	if (!userService.existsByUsername(partnerDTO.getUserDetailsDTO().getUsername())) {
	// 		throw new InternalServerErrorException(messageSource.getMessage("error.usernameIncorrect", null, null));
	// 	}

	// 	Partner p = this.partnerMapper.toEntity(partnerDTO);
	// 	p.setUniversity(universityService.getById(partnerDTO.getUniversityDTO().getId()));
	// 	return this.partnerMapper.toDTO(this.partnerRepository.save(p));
	// }

	// @Override
	// public List<PartnerDTO> findAll() {
	// 	return partnerRepository.findAll().stream().map(item -> this.partnerMapper.toDTO(item))
	// 			.collect(Collectors.toList());
	// }

	// @Override
	// public PaginationDTO findAll(int no, int limit) {
	// 	Page<PartnerDTO> page = partnerRepository.findAll(PageRequest.of(no, limit))
	// 			.map(item -> partnerMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	// }

	// @Override
	// public PaginationDTO findAllByUniversityId(int universityId, int no, int limit) {
	// 	Page<PartnerDTO> page = partnerRepository.findAllByUniversityId(universityId,PageRequest.of(no, limit))
	// 			.map(item -> partnerMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	// }

	// @Override
	// public long count() {
	// 	return partnerRepository.count();
	// }

	// @Override
	// public PartnerDTO update(PartnerDTO partnerDTO, int id) {
	// 	if (!this.partnerRepository.existsById(id)) {
	// 		throw new ResourceNotFoundException("Partner", "id", String.valueOf(id));
	// 	}
	// 	if (!userService.existsByUsername(partnerDTO.getUserDetailsDTO().getUsername())) {
	// 		throw new InternalServerErrorException(messageSource.getMessage("error.usernameIncorrect", null, null));
	// 	}

	// 	Partner p = this.partnerMapper.toEntity(partnerDTO);

	// 	p.setUniversity(universityService.getById(partnerDTO.getUniversityDTO().getId()));
	// 	p.setId(id);
	// 	return this.partnerMapper.toDTO(this.partnerRepository.save(p));
	// }

	// @Override
	// public List<UniversityPartnerDTOShow> findByUniversityId(int universityId) {
	// 	UniversityDTO dto = this.universityService.findById(universityId);
	// 	return partnerRepository.findAllByUniversityId(dto.getId()).stream()
	// 			.map(item -> this.partnerMapper.toEntityUniversityPartnerShow(item))
	// 			.collect(Collectors.toList());
	// }

	// @Override
	// public Partner findByUserId(long userId) {
	// 	return this.partnerRepository.findByUserId(userId)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Partner", "id", String.valueOf(userId)));
	// }

	// @Override
	// public PartnerDTO findById(Integer id) {
	// 	Partner partner = partnerRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Partner", "id", String.valueOf(id)));
	// 	return this.partnerMapper.toDTO(partner);
	// }

	// @Override
	// public PartnerDTO updateUser(PartnerCreationDTO partnerCreateDTO) {
	// 	UserCreationDTO userCreationDTO = partnerCreateDTO.getUserCreationDTO();
	// 	if (userCreationDTO == null) {
	// 		throw new InternalServerErrorException(messageSource.getMessage("error.usernameNotNull", null, null));
	// 	}
	// 	Partner partner = partnerRepository.findById(partnerCreateDTO.getId()).get();
	// 	partner.setPosition(partnerCreateDTO.getPosition());
	// 	User userEntity = partner.getUser();
	// 	userEntity.setFirstName(userCreationDTO.getFirstName());
	// 	userEntity.setLastName(userCreationDTO.getLastName());
	// 	userEntity.setGender(userCreationDTO.getGender());
	// 	userEntity.setPhone(userCreationDTO.getPhone());
	// 	userEntity.setEmail(userCreationDTO.getEmail());
	// 	partner.setUser(userEntity);
	// 	partner = this.partnerRepository.save(partner);
	// 	return this.partnerMapper.toDTO(partner);
	// }

	// @Override
	// public PartnerCreationDTO readJson(String value) {
	// 	PartnerCreationDTO partner = null;
	// 	try {
	// 		ObjectMapper ob = new ObjectMapper();
	// 		partner = new PartnerCreationDTO();
	// 		partner = ob.readValue(value, PartnerCreationDTO.class);
	// 	} catch (JsonProcessingException ex) {
	// 		ex.printStackTrace();
	// 	} catch (Exception e) {
	// 		throw new InternalServerErrorException(this.messageSource.getMessage("error.readJson", null, null));
	// 	}
	// 	return partner;
	// }

}
