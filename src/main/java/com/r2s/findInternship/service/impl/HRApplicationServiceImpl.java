package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.r2s.findInternship.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.common.MailResponse;
import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.common.enumeration.EMailType;
import com.r2s.findInternship.data.dto.HRApplicationDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.HRApplication;
import com.r2s.findInternship.data.mapper.HRApplicationMapper;
import com.r2s.findInternship.data.repository.HRApplicationRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.HRApplicationService;
import com.r2s.findInternship.service.MailService;

@Service
public class HRApplicationServiceImpl implements HRApplicationService {
	@Autowired
	private StatusService statusService;

	@Autowired
	HRApplicationRepository hrApplicationRepository;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private HRApplicationMapper hrApplicationMapper;
	@Autowired
	private MailService mailService;
	public final static Logger LOGGER = LoggerFactory.getLogger("info");
    @Override
    public HRApplicationDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<HRApplicationDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public PaginationDTO findAllActive(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllActive'");
    }
    @Override
    public HRApplicationDTO create(HRApplicationDTO hrApplicationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public HRApplicationDTO update(int id, HRApplicationDTO hrApplicationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public HRApplication getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    @Override
    public MessageResponse deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

	// @Override
	// public HRApplicationDTO findById(Integer id) {
	// 	return this.hrApplicationMapper.toDTO(this.getById(id));
	// }

	// @Override
	// public List<HRApplicationDTO> findAll() {
	// 	return hrApplicationRepository.findAll().stream()
	// 			.map(hrApplication -> this.hrApplicationMapper.toDTO(hrApplication)).collect(Collectors.toList());
	// }

	// @Override
	// public PaginationDTO findAll(int no, int limit) {
	// 	Page<HRApplicationDTO> page = hrApplicationRepository.findAll(PageRequest.of(no, limit))
	// 			.map(hra -> hrApplicationMapper.toDTO(hra));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	// }

	// @Override
	// public PaginationDTO findAllActive(int no, int limit) {
	// 	Page<HRApplicationDTO> page = hrApplicationRepository.findAllActive(PageRequest.of(no, limit))
	// 			.map(hra -> hrApplicationMapper.toDTO(hra));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	// }

	// @Override
	// public HRApplicationDTO create(HRApplicationDTO dto) {
	// 	dto.setCreatedDate(LocalDateTime.now());
	// 	HRApplication entity = this.hrApplicationMapper.toEntity(dto);
	// 	// SEND MAIL FOR PARTNER
	// 	MailResponse mailResponse = new MailResponse();
	// 	mailResponse.setCv(entity.getInternshipProgramme().getStudents());
	// 	mailResponse.setTo(entity.getHr().getUser().getEmail());
	// 	mailResponse.setTypeMail(EMailType.HRApply);
	// 	String message = this.messageSource.getMessage("info.addHRApplication", null, null);
	// 	mailResponse.setSubject(String.format(message,
	// 			entity.getHr().getUser().getFirstName() + " " + entity.getHr().getUser().getLastName(),
	// 			entity.getInternshipProgramme().getName()));
	// 	mailResponse.setNamePost(entity.getInternshipProgramme().getName());
	// 	mailResponse
	// 			.setNameReceive(entity.getHr().getUser().getFirstName() + " " + entity.getHr().getUser().getLastName());
	// 	this.mailService.send(mailResponse);

	// 	entity.setStatus(statusService.findByName(Estatus.Active));
	// 	entity = hrApplicationRepository.save(entity);
	// 	LOGGER.info(String.format(this.messageSource.getMessage("info.addHRApplication", null, null), "TEN DEMAND",
	// 			"TEN HR"));
	// 	return this.hrApplicationMapper.toDTO(entity);
	// }

	// @Override
	// public HRApplicationDTO update(int id, HRApplicationDTO hrApplicationDTO) {
	// 	HRApplication entity = this.getById(id);
	// 	hrApplicationDTO.setId(id);
	// 	if (hrApplicationDTO.getModifiedDate() == null) {
	// 		hrApplicationDTO.setModifiedDate(entity.getModifiedDate());
	// 	}
	// 	entity = hrApplicationMapper.toEntity(hrApplicationDTO);
	// 	hrApplicationRepository.save(entity);
	// 	return hrApplicationMapper.toDTO(entity);
	// }

	// @Override
	// public HRApplication getById(Integer id) {
	// 	return hrApplicationRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("HRApplication", "id", String.valueOf(id)));
	// }

	// @Override
	// public boolean deleteById(Integer id) {
	// 	try {
	// 		HRApplication entity = this.getById(id);
	// 		hrApplicationRepository.save(entity);
	// 	} catch (Exception e) {
	// 		throw new ResourceNotFoundException("HRApplication", "id", String.valueOf(id));
	// 	}
	// 	return true;
	// }

	// @Override
	// public MessageResponse deleteById(int id) {
	// 	// HRApplicationDTO foundHRApplication = this.findById(id);
	// 	// foundHRApplication.setId(id);
	// 	// foundHRApplication.setStatus(statusService.findByName(EStatus.Disable));
	// 	// HRApplication hrApplication = hrApplicationMapper.toEntity(foundHRApplication);
	// 	// hrApplicationRepository.save(hrApplication);
	// 	// String fullName = foundHRApplication.getHrDTO().getUserCreationDTO().getLastName() + " "
	// 	// 		+ foundHRApplication.getHrDTO().getUserCreationDTO().getFirstName();
	// 	// LOGGER.info("HR " + fullName + " has been unapplication demand "
	// 	// 		+ foundHRApplication.getUniversityDemandDTO().getName() + " successfully!");
	// 	// return new MessageResponse(200,
	// 	// 		String.format(this.messageSource.getMessage("info.deleteHRApplication", null, null), fullName,
	// 	// 				foundHRApplication.getUniversityDemandDTO().getName()),
	// 	// 		null);
	// 	return null;
	// }
}
