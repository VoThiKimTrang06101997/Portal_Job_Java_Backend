package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.r2s.findInternship.data.entity.Major;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.util.UpdateFile;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.UniversityDTO;
import com.r2s.findInternship.data.entity.University;
import com.r2s.findInternship.data.mapper.RoleMapper;
import com.r2s.findInternship.data.mapper.StatusMapper;
import com.r2s.findInternship.data.mapper.UniversityMapper;
import com.r2s.findInternship.data.mapper.UniversityTypeMapper;
import com.r2s.findInternship.data.repository.PartnerRepository;
import com.r2s.findInternship.data.repository.UniversityRepository;
import com.r2s.findInternship.service.MailService;
import com.r2s.findInternship.service.RoleService;
import com.r2s.findInternship.service.StatusService;
import com.r2s.findInternship.service.UniversityService;
import com.r2s.findInternship.service.UniversityTypeService;
import com.r2s.findInternship.service.UserService;

@Service
public class UniversityServiceImpl implements UniversityService {
	@Autowired
	private UniversityTypeService universityTypeService;
	@Autowired
	private UniversityRepository universityRepository;
	@Autowired
	private UniversityTypeMapper universityTypeMapper;
	@Autowired
	private UniversityMapper universityMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private UpdateFile updateFile;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private StatusService statusService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private MailService mailService;
	@Autowired
	private StatusMapper statusMapper;
	@Autowired
	private RoleMapper roleMapper;

	public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public UniversityDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<University> findAll(Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<UniversityDTO> findAllByNameLike(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByNameLike'");
    }

    @Override
    public List<UniversityDTO> findAllByShortNameLike(String shortName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByShortNameLike'");
    }

    @Override
    public List<UniversityDTO> findAll() {
        return this.universityRepository.findAll().stream().map(item -> this.universityMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public PaginationDTO findAllByNameLike(String name, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByNameLike'");
    }

    @Override
    public PaginationDTO findAllByShortNameLike(String shortName, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByShortNameLike'");
    }

    @Override
    public University getById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }


    @Override
    public List<UniversityDTO> findAllSort(String field) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllSort'");
    }

    @Override
    public MessageResponse deleteById(int id) {
        this.universityRepository.delete(this.universityRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));
        return new MessageResponse(200, null, null);
    }

    @Override
    public MessageResponse create(UniversityDTO universityDTO) {
        University university = universityMapper.toEntity(universityDTO);
        if (universityRepository.existsByName(university.getName()))
            throw new InternalServerErrorException(
                    String.format("Exists major named %s", university.getName()));
        universityRepository.save(university);
        return new MessageResponse(200, null, null);
    }

    @Override
    public void recoverById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recoverById'");
    }

    @Override
    public List<Object[]> getNewStatistics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNewStatistics'");
    }

    @Override
    public Long countByCreatedDate(LocalDateTime from, LocalDateTime to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByCreatedDate'");
    }

    @Override
    public List<Object[]> getStatusStatistics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatusStatistics'");
    }

    @Override
    public PaginationDTO findAllByProvinceId(int provinceId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByProvinceId'");
    }

    @Override
    public UniversityDTO changeStatus(int id, UniversityDTO universityDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeStatus'");
    }

	// @Override
	// public UniversityDTO create(UniversityCreationDTO universityCreationDTO) {
	// 	universityCreationDTO = checkHandlerValid(universityCreationDTO);
	// 	// save avatar of university
	// 	if (universityCreationDTO.getFile() != null) {
	// 		FileUpload file = new FileUpload();
	// 		file.setFile(universityCreationDTO.getFile());
	// 		this.updateFile.update(file);
	// 		universityCreationDTO.setAvatar(file.getOutput());
	// 	} else
	// 		universityCreationDTO.setAvatar("");
	// 	// mapping data to dto
	// 	University university = this.universityMapper.toEntity(universityCreationDTO);
	// 	university.setCreatedDate(LocalDateTime.now());
	// 	university.setStatus(statusService.findByName(Estatus.Active));
	// 	university.setUniversityType(
	// 			universityTypeMapper.toEntity(
	// 					universityTypeService.findById(
	// 							universityCreationDTO.getUniversityTypeDTO().getId())));
	// 	university = universityRepository.save(university);
	// 	if (!universityCreationDTO.getLocationDTOs().isEmpty()) {
	// 		List<UniversityLocation> universityLocations = new ArrayList<>();
	// 		for (LocationDTO item : universityCreationDTO.getLocationDTOs()) {
	// 			UniversityLocation universityLocation = new UniversityLocation();
	// 			universityLocation.setUniversity(university);
	// 			// save location
	// 			Location location = locationMapper.toEntity(item);
	// 			location = locationRepository.save(location);
	// 			universityLocation.setLocation(location);
	// 			// save university location
	// 			universityLocation = this.universityLocationRepository.save(universityLocation);
	// 			universityLocations.add(universityLocation);
	// 		}
	// 		university.setUniversityLocations(universityLocations);
	// 	} else {
	// 		throw new InternalServerErrorException(this.messageSource.getMessage("error.locationMust", null, null));
	// 	}
	// 	university = universityRepository.save(university);
	// 	LOGGER.info(String.format("Admin has create %s successfully with ", university.getName()));
	// 	return this.universityMapper.toDTO(university);
	// }

	// @Override
	// public PaginationDTO findAllByProvinceId(int provinceId, int no, int limit) {
	// 	Page<UniversityDTO> page = this.universityRepository
	// 			.findAllByProvinceId(provinceId, PageRequest.of(no, limit))
	// 			.map(item -> this.universityMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(),
	// 			page.getNumber());
	// }

	// @Override
	// public List<Object[]> getNewStatistics() { // created date within 1 month
	// 	return universityRepository.getNewStatistics(
	// 			DateTimeHelper.getEarliestTimeOfDate(
	// 					DateTimeHelper.getDateTimeOfMonthAgo(1)));
	// }

	// @Override
	// public List<UniversityDTO> findAll() {
	// 	return universityRepository.findAll().stream().map(item -> this.universityMapper.toDTO(item))
	// 			.collect(Collectors.toList());
	// }

	// @Override
	// public PaginationDTO findAll(int no, int limit) {
	// 	Page<UniversityDTO> page = this.universityRepository.findAll(PageRequest.of(no, limit))
	// 			.map(item -> this.universityMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(),
	// 			page.getNumber());
	// }

	// @Override
	// public PaginationDTO findAllByNameLike(String name, int no, int limit) {
	// 	Page<UniversityDTO> page = this.universityRepository
	// 			.findAllByNameLike(name, PageRequest.of(no, limit))
	// 			.map(item -> this.universityMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(),
	// 			page.getNumber());
	// }

	// @Override
	// public PaginationDTO findAllByShortNameLike(String shortName, int no, int limit) {
	// 	Page<UniversityDTO> page = this.universityRepository
	// 			.findAllByShortNameLike(shortName, PageRequest.of(no, limit))
	// 			.map(item -> this.universityMapper.toDTO(item));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
	// 			page.getTotalPages(), page.getTotalElements(), page.getSize(),
	// 			page.getNumber());
	// }

	// @Override
	// public List<University> findAll(Sort sort) {
	// 	return universityRepository.findAll(sort);
	// }

	// @Override
	// public UniversityDTO findById(Integer id) {
	// 	University entity = universityRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "id", String.valueOf(id)));
	// 	UniversityDTO dto = this.universityMapper.toDTO(entity);

	// 	return dto;
	// }

	// @Override
	// public boolean existsById(Integer id) {
	// 	return universityRepository.existsById(id);
	// }

	// @Override
	// public long count() {
	// 	return universityRepository.count();
	// }

	// // PROCESS: SAVE USER -> UNIVERSITY -> PARTNER
	// @Override
	// public UniversityDTO createFirst(UniversityCreationDTO universityCreationDTO)// SAVE FOR USER REGISTER
	// {
	// 	UniversityCreationDTO dto = this.checkHandlerValid(universityCreationDTO);
	// 	PartnerCreationDTO partnerDTO = dto.getPartnerCreationDTO();
	// 	UserCreationDTO userCreationDTO = partnerDTO.getUserCreationDTO();
	// 	UserDTO userDto = null;
	// 	if (userCreationDTO != null) {
	// 		userCreationDTO.setRoleDTO(this.roleMapper.toDTO(this.roleService.findByName(ERole.Partner)));
	// 		userCreationDTO.setStatusDTO(this.statusMapper.toDTO(this.statusService.findByName(Estatus.Disable)));
	// 		userDto = this.userService.create(userCreationDTO);// SAVE USERNAME AND PASSWORD
	// 	} else
	// 		throw new InternalServerErrorException("Xu ly khong co USER !");
	// 	// UNIVERSITY
	// 	University entity = universityMapper.toEntity(dto);
	// 	// Save partner

	// 	if (dto.getFile() != null) {
	// 		FileUpload file = new FileUpload();
	// 		file.setFile(dto.getFile());
	// 		this.updateFile.update(file);
	// 		entity.setAvatar(file.getOutput());
	// 	} else
	// 		entity.setAvatar("");
	// 	if (dto.getId() == 0) {
	// 		entity.setUniversityType(
	// 				universityTypeMapper.toEntity(universityTypeService.findById(dto.getUniversityTypeDTO().getId())));

	// 		if (universityRepository.existsByEmail(entity.getEmail()))
	// 			throw new InternalServerErrorException(this.messageSource.getMessage("error.emailExists", null, null));
	// 		if (!dto.getLocationDTOs().isEmpty()) {
	// 			List<UniversityLocation> universityLocations = new ArrayList<>();
	// 			for (LocationDTO item : dto.getLocationDTOs()) {
	// 				UniversityLocation universityLocation = new UniversityLocation();
	// 				universityLocation.setUniversity(entity);
	// 				universityLocation.setLocation(locationMapper.toEntity(item));
	// 				universityLocations.add(universityLocation);
	// 				this.universityLocationRepository.save(universityLocation);
	// 			}
	// 			entity.setUniversityLocations(universityLocations);
	// 		} else {
	// 			throw new InternalServerErrorException(this.messageSource.getMessage("error.locationMust", null, null));
	// 		}

	// 		entity.setStatus(this.statusService.findByName(Estatus.Active)); // not available
	// 		entity.setCreatedDate(LocalDateTime.now());
	// 	}
	// 	universityRepository.save(entity);// SAVE UNIVERSITY

	// 	Partner entityPartner = new Partner();
	// 	entityPartner.setUniversity(entity);
	// 	entityPartner.setPosition(partnerDTO.getPosition());
	// 	entityPartner.setUser(userService.findByUsername(userDto.getUsername()));

	// 	entityPartner = this.partnerRepository.save(entityPartner);
	// 	String fullName = entityPartner.getUser().getFirstName() + " " + entityPartner.getUser().getLastName();
	// 	LOGGER.info(String.format("%s created successful university wit name: %s", fullName, userDto.getUsername()));
	// 	return this.universityMapper.toDTO(entity);
	// }

	// @Override
	// public University getById(Integer id) {
	// 	return universityRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "id", String.valueOf(id)));
	// }

	// public UniversityDTO save(UniversityDTO universityDTO) {
	// 	if (universityDTO.getAvatar() != null) {
	// 		// Upload file
	// 	}
	// 	return null;

	// }

	// public UniversityDTO findByName(String name) {
	// 	University university = universityRepository.findByName(name)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "name", name));
	// 	return this.universityMapper.toDTO(university);
	// }

	// @Override
	// public List<UniversityDTO> findAllByNameLike(String name) {
	// 	if (name.isEmpty())
	// 		return this.findAll();
	// 	return universityRepository.findAllByNameLike(name).stream().map(item -> this.universityMapper.toDTO(item))
	// 			.collect(Collectors.toList());
	// }

	// @Override
	// public List<UniversityDTO> findAllByShortNameLike(String shortName) {
	// 	return universityRepository.findAllByShortNameLike(shortName).stream()
	// 			.map(item -> this.universityMapper.toDTO(item)).collect(Collectors.toList());
	// }

	// @Override
	// public UniversityDTO update(UniversityCreationDTO universityCreationDTO, int id) {
	// 	universityCreationDTO.setId(id);
	// 	UniversityCreationDTO dto = this.checkHandlerValid(universityCreationDTO);
	// 	UniversityDTO universityDTO = this.findById(id);
	// 	University university = this.universityMapper.toEntity(dto);
	// 	if (dto.getFile() != null) {
	// 		FileUpload file = new FileUpload();
	// 		file.setFile(dto.getFile());
	// 		this.updateFile.deleteFile(universityDTO.getAvatar());
	// 		this.updateFile.update(file);
	// 		dto.setAvatar(file.getOutput());
	// 	} else {
	// 		dto.setAvatar(universityDTO.getAvatar());
	// 	}
	// 	university.setAvatar(dto.getAvatar());

	// 	university = this.universityRepository.save(university);
	// 	LOGGER.info("Update University with name: " + university.getName());
	// 	return this.universityMapper.toDTO(university);
	// }

	// @Override
	// public UniversityCreationDTO readJson(String university, String partner, MultipartFile fileUser,
	// 		MultipartFile fileAvatar) {
	// 	UniversityCreationDTO universityDTO = null;
	// 	PartnerCreationDTO partnerDTO = null;
	// 	ObjectMapper objectMapper = new ObjectMapper();
	// 	try {
	// 		universityDTO = objectMapper.readValue(university, UniversityCreationDTO.class);
	// 		if (partner != "") {
	// 			partnerDTO = objectMapper.readValue(partner, PartnerCreationDTO.class);
	// 		}
	// 		if (fileAvatar != null) {
	// 			universityDTO.setFile(fileAvatar);
	// 		}
	// 		if (partnerDTO != null) {
	// 			if (fileUser != null) {
	// 				partnerDTO.getUserCreationDTO().setFileAvatar(fileUser);
	// 			}
	// 			universityDTO.setPartnerCreationDTO(partnerDTO);
	// 		}
	// 	} catch (JsonProcessingException ex) {
	// 		ex.printStackTrace();
	// 	} catch (Exception e) {
	// 		throw new InternalServerErrorException(this.messageSource.getMessage("error.readJson", null, null));
	// 	}
	// 	return universityDTO;
	// }

	// @Override
	// public List<UniversityDTO> findAllSort(String field) {
	// 	Sort sort = Sort.by(field);
	// 	return this.universityRepository.findAll(sort).stream().map(item -> this.universityMapper.toDTO(item))
	// 			.collect(Collectors.toList());
	// }

	// public MessageResponse deleteById(int id) {
	// 	University u = this.universityRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "id", String.valueOf(id)));
	// 	u.setStatus(this.statusService.findByName(Estatus.Disable));
	// 	LOGGER.info(String.format("%s deleted.", u.getName()));
	// 	this.universityRepository.save(u);
	// 	return new MessageResponse(200,
	// 			String.format(this.messageSource.getMessage("info.deleteUniversity", null, null), u.getName()), null);

	// }

	// public UniversityCreationDTO checkHandlerValid(UniversityCreationDTO universityCreationDTO) {

	// 	Map<String, String> maps = new HashMap<String, String>();
	// 	// nhập vào thông tin thì không gửi kèm id, còn có id thì không gửi thêm thông
	// 	// tin khác
	// 	if (universityCreationDTO.getId() != 0) {
	// 		Optional<University> u = this.universityRepository.findById(universityCreationDTO.getId());
	// 		University entity = u.get();

	// 		universityCreationDTO.setAvatar(entity.getAvatar());
	// 		universityCreationDTO.setDescription(entity.getDescription());
	// 		universityCreationDTO.setEmail(entity.getEmail());
	// 		universityCreationDTO.setName(entity.getName());
	// 		universityCreationDTO.setPhone(entity.getPhone());
	// 		universityCreationDTO.setShortName(entity.getShortName());
	// 		universityCreationDTO.setStatusDTO(statusMapper.toDTO(entity.getStatus()));
	// 		universityCreationDTO.setUniversityTypeDTO(universityTypeMapper.toDTO(entity.getUniversityType()));
	// 		universityCreationDTO.setWebsite(entity.getWebsite());
	// 		// if (!dto.getShortName().equals(entity.getShortName())) {
	// 		// if (this.universityRepository.existsByShortName(dto.getShortName()))
	// 		// maps.put("Short Name",
	// 		// messageSource.getMessage("error.UniversityExistsShortName", null, null));
	// 		// }
	// 		// if (!dto.getWebsite().equals(entity.getWebsite())) {
	// 		// if (this.universityRepository.existsByWebsite(dto.getWebsite()))
	// 		// maps.put("Website", messageSource.getMessage("error.UniversityExistsWebsite",
	// 		// null, null));
	// 		// }
	// 		// if (!dto.getEmail().equals(entity.getEmail())) {
	// 		// if (this.universityRepository.existsByEmail(dto.getEmail())) {
	// 		// maps.put("Email", messageSource.getMessage("error.UniversityExistsEmail",
	// 		// null, null));
	// 		// }
	// 		// }
	// 		// if (!dto.getName().equals(entity.getName())) {
	// 		// if (this.universityRepository.existsByName(dto.getName())) {
	// 		// maps.put("Name", messageSource.getMessage("error.UniversityExistsName", null,
	// 		// null));
	// 		// }
	// 		// }

	// 	} else {
	// 		if (this.universityRepository.existsByShortName(universityCreationDTO.getShortName()))
	// 			maps.put("Short Name", messageSource.getMessage("error.UniversityExistsShortName", null, null));
	// 		if (this.universityRepository.existsByWebsite(universityCreationDTO.getWebsite()))
	// 			maps.put("Website", messageSource.getMessage("error.UniversityExistsWebsite", null, null));
	// 		if (this.universityRepository.existsByName(universityCreationDTO.getName())) {
	// 			maps.put("Name", messageSource.getMessage("error.UniversityExistsName", null, null));
	// 		}
	// 		if (this.universityRepository.existsByEmail(universityCreationDTO.getEmail()))
	// 			maps.put("Email", messageSource.getMessage("error.UniversityExistsEmail", null, null));
	// 	}
	// 	if (maps.size() > 0)
	// 		throw new InternalServerErrorException(maps);
	// 	return universityCreationDTO;
	// }

	// @Override
	// public void recoverById(int id) {
	// 	University u = universityRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "id", String.valueOf(id)));
	// 	u.setStatus(statusService.findByName(Estatus.Active));
	// 	LOGGER.info(String.format("%s has been restored.", u.getName()));
	// 	this.universityRepository.save(u);
	// }

	// @Override
	// public Long countByCreatedDate(LocalDateTime from, LocalDateTime to) {
	// 	if (from != null && to != null) {
	// 		return universityRepository.countByCreatedDateBetween(from, to);
	// 	} else {
	// 		return universityRepository.count();
	// 	}
	// }

	// @Override
	// public List<Object[]> getStatusStatistics() {
	// 	return universityRepository.getStatusStatistics();
	// }

	// public void active(int id) {
	// 	University entity = this.universityRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("University", "id", String.valueOf(id)));
	// 	Status status = this.statusService.findByName(Estatus.Active);
	// 	entity.setStatus(status);
	// 	this.universityRepository.save(entity);
	// 	MailResponse mailResponse = new MailResponse();
	// 	mailResponse.setTo(entity.getEmail());
	// 	mailResponse.setTypeMail(EMailType.ActiveUniversity);
	// 	mailService.send(mailResponse);
	// }

	// @Override
	// public UniversityDTO changeStatus(int id, UniversityDTO universityDTO) {
	// 	UniversityDTO dto = findById(id);
	// 	StatusDTO statusDTO = statusService.findById(dto.getStatusDTO().getId());
	// 	dto.setStatusDTO(statusDTO);
	// 	University university = universityMapper.toEntity(dto);
	// 	university.setId(id);
	// 	university = universityRepository.save(university);
	// 	LOGGER.info(String.format("Update status of job with %s successfully", id));
	// 	return universityMapper.toDTO(university);
	// }
}
