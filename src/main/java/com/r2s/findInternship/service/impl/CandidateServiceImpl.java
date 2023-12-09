package com.r2s.findInternship.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.data.repository.UserRepository;
import com.r2s.findInternship.service.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.r2s.findInternship.common.enumeration.ERole;
import com.r2s.findInternship.data.dto.CandidateFilterByHRDTO;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.data.dto.user.UserDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.mapper.CandidateMapper;
import com.r2s.findInternship.data.repository.CandidateRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;

@Service
public class CandidateServiceImpl implements CandidateService {
        @Autowired
        private CandidateRepository candidateRepository;
        @Autowired
        private UserService userService;
        @Autowired
        private CandidateMapper candidateMapper;
        @Autowired
        private FileService fileService;
        @Autowired
        private StatusService statusService;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private CandidatePositionService candidatePositionService;
        @Autowired
        private CandidateMajorService candidateMajorService;
        @Autowired
        private CandidateScheduleService candidateScheduleService;

        @Override
        public boolean isCurrentAuthor(Long id) {
                Long candidateUserId = candidateRepository.findById(id).map(c -> c.getId()).orElse(null);
                Long currentUserId = userService.getCurrentUserId();
                return (candidateUserId != null) && (currentUserId != null) && (candidateUserId == currentUserId);
        }

        @Override
        public CandidateDTO create(CandidateCreationDTO candidateCreationDTO, MultipartFile fileAvatar) {
                UserDTO createdUserDTO = userService.create(
                                candidateCreationDTO.getUserCreationDTO(), fileAvatar, ERole.Candidate);

                Candidate candidate = candidateMapper.toEntity(candidateCreationDTO);
                candidate.getUser().setId(createdUserDTO.getId());

                CandidateDTO createdCandidateDTO = candidateMapper.toDTO(candidateRepository.save(candidate));
                createdCandidateDTO.setUserDTO(createdUserDTO);
                return createdCandidateDTO;
        }

        @Override
        public CandidateDTO update(long id, CandidateProfileDTO candidateProfileDTO,
                        MultipartFile fileAvatar, MultipartFile fileCV) {

                Candidate oldCandidate = candidateRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "id",
                                                String.valueOf(id)));

                UserDTO updatedUserDTO = userService.update(oldCandidate.getUser().getId(),
                                candidateProfileDTO.getUserProfileDTO(), fileAvatar);

                Candidate updateCandidate = candidateMapper.toEntity(candidateProfileDTO);
                updateCandidate.setId(oldCandidate.getId());
                updateCandidate.getUser().setId(updatedUserDTO.getId());
                if (oldCandidate.getCV() != null && !oldCandidate.getCV().isBlank()) {
                        fileService.deleteFile(oldCandidate.getCV());
                }
                updateCandidate.setCV(fileService.uploadFile(fileCV));

                if (candidateProfileDTO.getCandidateOtherInfoDTO().getPositionDTOs() == null) {
                        candidateProfileDTO.getCandidateOtherInfoDTO().setPositionDTOs(new ArrayList<PositionDTO>());
                }
                candidatePositionService.update(updateCandidate.getId(),
                                candidateProfileDTO.getCandidateOtherInfoDTO().getPositionDTOs());

                if (candidateProfileDTO.getCandidateOtherInfoDTO().getMajorDTOs() == null) {
                        candidateProfileDTO.getCandidateOtherInfoDTO().setMajorDTOs(new ArrayList<MajorDTO>());
                }
                candidateMajorService.update(updateCandidate.getId(),
                                candidateProfileDTO.getCandidateOtherInfoDTO().getMajorDTOs());

                if (candidateProfileDTO.getCandidateOtherInfoDTO().getScheduleDTOs() == null) {
                        candidateProfileDTO.getCandidateOtherInfoDTO().setScheduleDTOs(new ArrayList<ScheduleDTO>());
                }
                candidateScheduleService.update(updateCandidate.getId(),
                                candidateProfileDTO.getCandidateOtherInfoDTO().getScheduleDTOs());

                CandidateDTO updatedCandidateDTO = candidateMapper.toDTO(candidateRepository.save(updateCandidate));
                updatedCandidateDTO.setUserDTO(updatedUserDTO);
                updatedCandidateDTO.getCandidateOtherInfoDTO()
                                .setPositionDTOs(candidateProfileDTO.getCandidateOtherInfoDTO().getPositionDTOs());
                updatedCandidateDTO.getCandidateOtherInfoDTO()
                                .setMajorDTOs(candidateProfileDTO.getCandidateOtherInfoDTO().getMajorDTOs());
                updatedCandidateDTO.getCandidateOtherInfoDTO()
                                .setScheduleDTOs(candidateProfileDTO.getCandidateOtherInfoDTO().getScheduleDTOs());
                return updatedCandidateDTO;
        }

        @Override
        public CandidateDTO findByUserId(long userId) {
                return candidateMapper.toDTO(
                                candidateRepository.findByUserId(userId)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Candidate", "user id", String.valueOf(userId))));
        }

        @Override
        public CandidateDTO findById(long id) {
                return candidateMapper.toDTO(
                                candidateRepository.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "id",
                                                                String.valueOf(id))));
        }

        @Override
        public MessageResponse updateSearchable(long id) {
                Candidate candidate = candidateRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
                candidate.setSearchable(!candidate.isSearchable());
                candidateRepository.save(candidate);

                return new MessageResponse(200, null, null);
        }

        // ====================================================================================================//

        // @Override
        // public PaginationDTO findByJobId(int jobId, int no, int limit) {
        // Page<CandidateDTO> page = candidateRepository.findByJobId(jobId,
        // PageRequest.of(no, limit))
        // .map(c -> candidateMapper.toDTO(c));
        // return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
        // page.getTotalPages(),
        // page.getTotalElements(), page.getSize(), page.getNumber());
        // }

        // @Override
        // public PaginationDTO findAllByMajorId(int majorId, int no, int limit) {
        // Page<CandidateDTO> page = candidateRepository.findAllByMajorId(majorId,
        // PageRequest.of(no, limit))
        // .map(c -> candidateMapper.toDTO(c));
        // return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
        // page.getTotalPages(),
        // page.getTotalElements(), page.getSize(), page.getNumber());
        // }

        // @Override
        // public void deleteById(Integer id) {
        // CandidateDTO candidateDTO = this.findById(id);
        // Candidate candidate = this.candidateMapper.toEntity(candidateDTO);
        // User user =
        // this.userService.findByUsername(candidate.getUser().getUsername());
        // user.setStatus(statusService.findByName(Estatus.Disable));
        // candidate.setUser(user);
        // LOGGER.info(String.format("Delete Candidate with username: %s",
        // user.getUsername()));
        // this.candidateRepository.save(candidate);
        // }

        // @Override
        // public boolean exists(CandidateDTO candidateDTO) {
        // return candidateRepository.existsById(candidateDTO.getId());
        // }

        // @Override
        // public long count() {
        // return candidateRepository.count();
        // }

        // @Override
        // public boolean existsById(Integer id) {
        // return this.candidateRepository.existsById(id);
        // }

        // @Override
        // public void flush() {

        // }

        // @Override
        // public CandidateDTO findById(Integer id) {
        // Candidate candidate = this.candidateRepository.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("Candidate", "Id",
        // String.valueOf(id)));
        // return this.candidateMapper.toDTO(candidate);
        // }

        // @Override
        // public Candidate getById(Integer id) {
        // return this.candidateRepository.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("Candidate", "Id",
        // String.valueOf(id)));
        // }

        // @Override
        // public List<CandidateDTO> findAll() {
        // return this.candidateRepository.findAll().stream().map(item ->
        // this.candidateMapper.toDTO(item))
        // .collect(Collectors.toList());
        // }

        // @Override
        // public CandidateDTO create(CandidateCreationDTO candidateCreationDTO) // LUU
        // VOI FRONT END CO CANDIDATE VA USER
        // // INFORMATION
        // {
        // UserCreationDTO userCreationDTO = candidateCreationDTO.getUserCreationDTO();
        // if (userCreationDTO == null)// Khong co user thi throw loi
        // {
        // throw new
        // InternalServerErrorException(this.messageSource.getMessage("error.userMust",
        // null, null));
        // }
        // userCreationDTO =
        // userService.handlerValid(candidateCreationDTO.getUserCreationDTO());

        // if (userCreationDTO.getFileAvatar() != null)// TRUONG HOP CO FILE UPLOAD
        // {
        // FileUpload fileAva = new FileUpload();
        // fileAva.setFile(userCreationDTO.getFileAvatar());
        // this.updateFile.update(fileAva);
        // userCreationDTO.setAvatar(fileAva.getOutput());
        // }
        // if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
        // throw new
        // InternalServerErrorException(messageSource.getMessage("error.emailExists",
        // null, null));
        // }

        // // userCreationDTO.setGender(null);
        // User user = userMapper.toEntity(userCreationDTO);
        // String password = userCreationDTO.getPassword();
        // user.setUsername(user.getEmail());
        // user.setPassword(userService.encodePass(password));
        // user.setStatus(this.statusService.findByName(Estatus.Deleted));
        // user.setRole(roleService.findByName(ERole.Candidate));
        // user.setCreatedDate(DateTime.getInstances());
        // user.setModifiedDate(DateTime.getInstances());
        // user = userRepository.save(user);
        // Candidate candidate = this.candidateMapper.toEntity(candidateCreationDTO);
        // candidate.setUser(user);

        // if (candidateCreationDTO.getFileCV() != null)// UPLOAD FILE CV
        // {
        // FileUpload file = new FileUpload();
        // file.setFile(candidateCreationDTO.getFileCV());
        // this.updateFile.uploadCV(file);
        // candidate.setCV(file.getOutput());
        // }
        // Optional<Candidate> entity =
        // this.candidateRepository.findByUsername(candidate.getUser().getUsername());
        // if (entity.isPresent()) {
        // candidate.setId(entity.get().getId());
        // }

        // candidate.setCreatedDate(DateTime.getInstances());
        // candidate.setModifiedDate(DateTime.getInstances());
        // candidate.setCreatedBy(candidate.getUser().getId());
        // candidate.setModifiedBy(candidate.getUser().getId());
        // candidate = this.candidateRepository.save(candidate);

        // LOGGER.info(String.format(this.messageSource.getMessage("info.addCandidate",
        // null, null),
        // candidate.getUser().getUsername()));
        // System.out.println(candidate);
        // return this.candidateMapper.toDTO(candidate);
        // }

        // @Override
        // public CandidateDTO findByUsername(String username) {
        // Candidate candidate = this.candidateRepository.findByUsername(username)
        // .orElseThrow(() -> new ResourceNotFoundException("User", "username",
        // username));
        // return this.candidateMapper.toDTO(candidate);
        // }

        // public Candidate findByUserId(long id) {
        // System.out.println(id);
        // return candidateRepository.findByUserId(id)
        // .orElseThrow(() -> new ResourceNotFoundException("User", "Id",
        // String.valueOf(id)));
        // }

        // @Override
        // public List<CandidateMajor> updateMajorCandidate(Candidate candidate,
        // List<MajorDTO> majorDTOs) {
        // List<CandidateMajor> majorCandidates = new ArrayList<>();

        // for (CandidateMajor majorCandidate :
        // majorCandidateRepository.findAllByCandidate(candidate)) {

        // majorCandidateRepository.deleteById(majorCandidate.getId());
        // }
        // for (MajorDTO majorDTO : majorDTOs) {
        // CandidateMajor majorCandidate = new CandidateMajor();
        // Major major = majorRepository.findById(majorDTO.getId())
        // .orElseThrow(() -> new ResourceNotFoundException("major", "id",
        // String.valueOf(majorDTO.getId())));
        // if (major != null) {
        // majorCandidate.setMajor(major);
        // majorCandidate.setCandidate(candidate);
        // majorCandidate = majorCandidateRepository.save(majorCandidate);
        // majorCandidates.add(majorCandidate);
        // }
        // }
        // return majorCandidates;
        // }

        // @Override
        // public List<CandidateSchedule> updateJobTypeCandidate(Candidate candidate,
        // List<ScheduleDTO> jobTypeDTOs) {
        // List<CandidateSchedule> jobTypeCandidates = new ArrayList<>();
        // for (CandidateSchedule mj :
        // jobTypeCandidateRepository.findAllByCandidate(candidate)) {
        // jobTypeCandidateRepository.deleteById(mj.getId());
        // }
        // for (ScheduleDTO jobTypeDTO : jobTypeDTOs) {
        // Schedule jobType =
        // jobTypeRepository.findById(jobTypeDTO.getId()).orElseThrow(
        // () -> new ResourceNotFoundException("jobType", "id",
        // String.valueOf(jobTypeDTO.getId())));
        // if (jobType != null) {
        // CandidateSchedule jobTypeCandidate = new CandidateSchedule();
        // jobTypeCandidate.setJobType(jobType);
        // jobTypeCandidate.setCandidate(candidate);
        // jobTypeCandidate = jobTypeCandidateRepository.save(jobTypeCandidate);
        // jobTypeCandidates.add(jobTypeCandidate);
        // }
        // }
        // return jobTypeCandidates;
        // }

        // @Override
        // public List<CandidatePosition> updateJobPositionCandidate(Candidate
        // candidate,
        // List<PositionDTO> jobPositions) {
        // List<CandidatePosition> jobPositionCandidates = new ArrayList<>();
        // for (CandidatePosition mj :
        // jobPositionCandidateRepository.findAllByCandidate(candidate)) {
        // jobPositionCandidateRepository.deleteById(mj.getId());
        // }
        // for (PositionDTO jobPositionDTO : jobPositions) {
        // CandidatePosition jobPositionCandidate = new CandidatePosition();
        // Position jobPosition =
        // jobPositionRepository.findById(jobPositionDTO.getId()).orElseThrow(
        // () -> new ResourceNotFoundException("jobPosition", "id",
        // String.valueOf(jobPositionDTO.getId())));
        // if (jobPosition != null) {
        // jobPositionCandidate.setJobPosition(jobPosition);
        // jobPositionCandidate.setCandidate(candidate);
        // jobPositionCandidate =
        // jobPositionCandidateRepository.save(jobPositionCandidate);
        // jobPositionCandidates.add(jobPositionCandidate);
        // }
        // }
        // return jobPositionCandidates;
        // }

        // @Override
        // public CandidateDTO update(CandidateCreationDTO candidateCreationDTO) {
        // UserCreationDTO userCreationDTO = candidateCreationDTO.getUserCreationDTO();
        // if (userCreationDTO == null)// check user create, if user not null throw
        // exception
        // throw new
        // InternalServerErrorException(messageSource.getMessage("error.readJson", null,
        // null));
        // Candidate candidate = this.findByUserId(userCreationDTO.getId());

        // if (candidateCreationDTO.getFileCV() != null) // UPLOAD FILE
        // {
        // FileUpload fileUpload = new FileUpload();
        // fileUpload.setFile(candidateCreationDTO.getFileCV());
        // String urlCV = candidate.getCV();
        // if (urlCV != null)
        // this.updateFile.deleteFile(urlCV);
        // this.updateFile.uploadCV(fileUpload);
        // candidate.setCV(fileUpload.getOutput());
        // candidate.setOriginalNameCV(candidateCreationDTO.getFileCV().getOriginalFilename());
        // } else {
        // candidate.setCV(candidate.getCV());
        // }

        // List<MajorDTO> majorDTOs = candidateCreationDTO.getMajorDTOs();
        // List<ScheduleDTO> jobTypeDTOs = candidateCreationDTO.getJobTypeDTOs();
        // List<PositionDTO> jobPositionDTOs =
        // candidateCreationDTO.getJobPositionDTOs();

        // if (majorDTOs != null && majorDTOs.size() > 0) {

        // candidate.setCandidateMajors(this.updateMajorCandidate(candidate,
        // majorDTOs));
        // }

        // if (jobTypeDTOs != null && jobTypeDTOs.size() > 0) {

        // candidate.setCandidateSchedules(this.updateJobTypeCandidate(candidate,
        // jobTypeDTOs));

        // }
        // if (jobPositionDTOs != null) {

        // candidate.setCandidatePositions(this.updateJobPositionCandidate(candidate,
        // jobPositionDTOs));
        // }
        // if (candidateCreationDTO.getLocationDTO() != null) {
        // if (candidate.getLocation() != null) {

        // Location location = locationService.getById(candidate.getLocation().getId());
        // try {

        // District district = districtService
        // .getById(candidateCreationDTO.getLocationDTO().getDistrictDTO().getId());
        // Province province = provinceService
        // .getById(candidateCreationDTO.getLocationDTO().getDistrictDTO().getProvinceDTO().getId());
        // district.setProvince(province);
        // location.setDistrict(district);
        // location.setAddress(candidateCreationDTO.getLocationDTO().getAddress());
        // location.setModifiedDate(DateTime.getInstances());
        // candidate.setLocation(location);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // } else {
        // Location newLocation = new Location();
        // newLocation.setAddress(candidateCreationDTO.getLocationDTO().getAddress());
        // newLocation.setNote(candidateCreationDTO.getLocationDTO().getNote());
        // District district = districtService
        // .getById(candidateCreationDTO.getLocationDTO().getDistrictDTO().getId());
        // Province province = provinceService
        // .getById(candidateCreationDTO.getLocationDTO().getDistrictDTO().getProvinceDTO().getId());
        // district.setProvince(province);
        // newLocation.setDistrict(district);
        // // newLocation.setCreatedBy(UserContext.getId());
        // // newLocation.setModifiedBy(UserContext.getId());
        // newLocation.setCreatedDate(DateTime.getInstances());
        // newLocation.setModifiedDate(DateTime.getInstances());
        // locationRepository.save(newLocation);
        // candidate.setLocation(newLocation);
        // }

        // }
        // User userEntity = candidate.getUser();
        // if (userCreationDTO.getBirthday() != null) {

        // userEntity.setBirthDay(userCreationDTO.getBirthday());
        // }

        // if (!userEntity.getEmail().equals(userCreationDTO.getEmail())) {

        // if (userService.existsByEmail(userCreationDTO.getEmail())) {
        // throw new
        // InternalServerErrorException(this.messageSource.getMessage("error.emailExists",
        // null, null));
        // }
        // }
        // userEntity.setEmail(userCreationDTO.getEmail());
        // userEntity.setFirstName(userCreationDTO.getFirstName());
        // userEntity.setLastName(userCreationDTO.getLastName());
        // userEntity.setGender(userCreationDTO.getGender());
        // userEntity.setPhone(userCreationDTO.getPhone());
        // userEntity.setModifiedDate(DateTime.getInstances());

        // if (userCreationDTO.getFileAvatar() != null)// TRUONG HOP CO FILE UPLOAD
        // {
        // FileUpload fileAvatar = new FileUpload();
        // fileAvatar.setFile(userCreationDTO.getFileAvatar());
        // String urlAvatar = candidate.getUser().getAvatar();
        // if (urlAvatar != null) {
        // this.updateFile.deleteFile(urlAvatar);
        // }
        // this.updateFile.update(fileAvatar);
        // userEntity.setAvatar(fileAvatar.getOutput());
        // } else {
        // userEntity.setAvatar(userEntity.getAvatar());
        // }
        // candidate.setUser(userEntity);
        // candidate.setDesiredJob(candidateCreationDTO.getDesiredJob());

        // if (candidateCreationDTO.getWorkProvinceDTO() != null
        // && candidateCreationDTO.getWorkProvinceDTO().getId() != null) {
        // Province Workprovince =
        // provinceService.getById(candidateCreationDTO.getWorkProvinceDTO().getId());
        // candidate.setWorkProvince(Workprovince);
        // }
        // if (candidateCreationDTO.getUniversityDTO() != null
        // && candidateCreationDTO.getUniversityDTO().getId() != null) {

        // int idUnivetsity = candidateCreationDTO.getUniversityDTO().getId();
        // University university = universityRepository.findById(idUnivetsity)
        // .orElseThrow(() -> new ResourceNotFoundException("university", "id",
        // String.valueOf(idUnivetsity)));
        // candidate.setUniversity(university);
        // }
        // candidate.setReferenceLetter(candidateCreationDTO.getLetter());
        // // candidate.setModifiedBy(UserContext.getId());
        // candidate.setModifiedDate(DateTime.getInstances());
        // candidate = this.candidateRepository.save(candidate);
        // return this.candidateMapper.toDTO(candidate);
        // }

        // @Override
        // public CandidateCreationDTO readJson(String value, MultipartFile file,
        // MultipartFile fileAvatar) {
        // CandidateCreationDTO candidate = null;
        // try {
        // ObjectMapper ob = new ObjectMapper();
        // candidate = new CandidateCreationDTO();
        // candidate = ob.readValue(value, CandidateCreationDTO.class);
        // } catch (JsonProcessingException ex) // LOG ERROR
        // {
        // ex.printStackTrace();
        // } catch (Exception e) {
        // throw new
        // InternalServerErrorException(this.messageSource.getMessage("error.readJson",
        // null, null));
        // }
        // if (file != null)
        // candidate.setFileCV(file);
        // if (fileAvatar != null)
        // candidate.getUserCreationDTO().setFileAvatar(fileAvatar);
        // return candidate;

        // }

        // @Override
        // public List<CandidateDTO> findAllByMajorId(int majorId) {
        // // this.majorService.findById(majorId);
        // // return this.candidateRepository.findAllByMajorId(majorId).stream()
        // // .map(item ->
        // this.candidateMapper.toDTO(item)).collect(Collectors.toList());
        // return null;
        // }

        // @Override
        // public CandidateDTO findByUserId(int userId) {
        // return candidateMapper.toDTO(this.candidateRepository.findByUserId(userId)
        // .orElseThrow(() -> new ResourceNotFoundException("Candidate", "id",
        // String.valueOf(userId))));
        // }

        // @Override
        // public void SaveWithAddUser(Candidate candidate)// Truong hop add lan dau
        // trong flutter
        // {
        // this.candidateRepository.save(candidate);
        // }

        // @Override
        // public List<Object[]> getMajorStatistics() {
        // return candidateRepository.getMajorStatistics();
        // }

        // @Override
        // public PaginationDTO findAll(int no, int limit) {
        // // TODO Auto-generated method stub
        // return null;
        // }

        // @Override
        // public CandidateDTO setJobStatus(int id, Boolean jobStatus) {
        // Candidate candidate = this.getById(id);
        // boolean allNotNull = ObjectUtils.allNotNull(candidate.getDesiredJob(),
        // candidate.getWorkProvince(),
        // candidate.getCandidateMajors(), candidate.getCandidateSchedules(),
        // candidate.getCandidatePositions(),
        // candidate.getCV());

        // if (allNotNull) {
        // candidate.setJobStatus(jobStatus);
        // candidateRepository.save(candidate);

        // } else {
        // candidate.setJobStatus(false);
        // candidateRepository.save(candidate);
        // throw new
        // InternalServerErrorException(this.messageSource.getMessage("error.jobStatus",
        // null, null));

        // }

        // return candidateMapper.toDTO(candidate);

        // }

        @Override
        public void activeCandidate(String token) {

                User user = this.userRepository.findByTokenActive(token);
                // .orElseThrow(
                // () -> new
                // InternalServerErrorException(this.messageSource.getMessage("error.token",
                // null, null)));

                user.setStatus(this.statusService.findByName(Estatus.Active));
                user.setTokenActive("");

                this.userRepository.save(user);

        }

        // @Override
        // public PaginationDTO filterByHr(RequestCandidateDTO requestCandidateDTO, int
        // no, int limit) {

        // Pageable pageable = PageRequest.of(no, limit);

        // List<Object> candidates = candidateRepository
        // .findAll(CandidateSpecification.filter(requestCandidateDTO), pageable)
        // .toList().stream().map(item ->
        // candidateMapper.toDTO(item)).collect(Collectors.toList());
        // Page<Candidate> candidatePage =
        // candidateRepository.findAll(CandidateSpecification.filter(requestCandidateDTO),
        // pageable);
        // return new PaginationDTO(candidates, candidatePage.isFirst(),
        // candidatePage.isLast(),
        // candidatePage.getTotalPages(), candidatePage.getTotalElements(),
        // candidatePage.getSize(),
        // candidatePage.getNumber());
        // }

        @Override
        public PaginationDTO filterByHR(CandidateFilterByHRDTO candidateFilterByHRDTO, int no, int limit) {
                if (candidateFilterByHRDTO.getDesiredJob() != null
                                && candidateFilterByHRDTO.getDesiredJob().isBlank()) {
                        candidateFilterByHRDTO.setDesiredJob(null);
                }
                if (candidateFilterByHRDTO.getDesiredWorkingProvince() != null
                                && candidateFilterByHRDTO.getDesiredWorkingProvince().isBlank()) {
                        candidateFilterByHRDTO.setDesiredWorkingProvince(null);
                }
                if (candidateFilterByHRDTO.getScheduleIds() != null
                                && candidateFilterByHRDTO.getScheduleIds().isEmpty()) {
                        candidateFilterByHRDTO.setScheduleIds(null);
                }
                if (candidateFilterByHRDTO.getPositionIds() != null
                                && candidateFilterByHRDTO.getPositionIds().isEmpty()) {
                        candidateFilterByHRDTO.setPositionIds(null);
                }
                if (candidateFilterByHRDTO.getMajorIds() != null
                                && candidateFilterByHRDTO.getMajorIds().isEmpty()) {
                        candidateFilterByHRDTO.setMajorIds(null);
                }

                Page<CandidateDTO> page = candidateRepository.filterByHr(
                                candidateFilterByHRDTO.getDesiredJob(),
                                candidateFilterByHRDTO.getDesiredWorkingProvince(),
                                candidateFilterByHRDTO.getScheduleIds(),
                                candidateFilterByHRDTO.getPositionIds(),
                                candidateFilterByHRDTO.getMajorIds(),
                                PageRequest.of(no, limit))
                                .map(c -> candidateMapper.toDTO(c));
                return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                                page.getTotalElements(), page.getSize(), page.getNumber());
        }

}
