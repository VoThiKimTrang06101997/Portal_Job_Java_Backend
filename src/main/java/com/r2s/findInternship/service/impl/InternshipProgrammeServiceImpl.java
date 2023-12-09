package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.r2s.findInternship.data.mapper.InternshipProgrammeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.common.FileUpload;
import com.r2s.findInternship.common.util.UpdateFile;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.InternshipProgrammeFilterDTO;
import com.r2s.findInternship.data.dto.InternshipProgrammeDTO;
import com.r2s.findInternship.data.dto.show.UniversityDemandDTOShow;
import com.r2s.findInternship.data.entity.Position;
import com.r2s.findInternship.data.entity.Schedule;
import com.r2s.findInternship.data.entity.InternshipSchedule;
import com.r2s.findInternship.data.entity.Major;
import com.r2s.findInternship.data.entity.InternshipMajor;
import com.r2s.findInternship.data.entity.Partner;
import com.r2s.findInternship.data.entity.InternshipPosition;
import com.r2s.findInternship.data.entity.InternshipProgramme;
import com.r2s.findInternship.data.repository.JobPositionRepository;
import com.r2s.findInternship.data.repository.InternshipScheduleRepository;
import com.r2s.findInternship.data.repository.ScheduleRepository;
import com.r2s.findInternship.data.repository.InternshipMajorRepository;
import com.r2s.findInternship.data.repository.MajorRepository;
import com.r2s.findInternship.data.repository.PartnerRepository;
import com.r2s.findInternship.data.repository.StatusRepository;
import com.r2s.findInternship.data.repository.InternshipProgrammeRepository;
import com.r2s.findInternship.data.repository.UniversityRepository;
import com.r2s.findInternship.data.repository.specification.DemandSpecification;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.InternshipProgrammeService;
import com.r2s.findInternship.service.UniversityService;

@Service
public class InternshipProgrammeServiceImpl implements InternshipProgrammeService {
    @Autowired
    private InternshipProgrammeRepository universityDemandRepository;
    @Autowired
    private InternshipProgrammeMapper internshipProgrammeMapper;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private ScheduleRepository jobTypeRepository;
    @Autowired
    private UpdateFile updateFile;
    @Autowired
    private InternshipMajorRepository majorDemandRepository;
    @Autowired
    private InternshipScheduleRepository jobTypeDemandRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override //check
    public PaginationDTO findAllActive(int no, int limit) {
        Page<Object> page = universityDemandRepository.findAllActive(PageRequest.of(no, limit))
                     .map(item -> internshipProgrammeMapper.toDTOShow(item));
             return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                     page.getTotalElements(), page.getSize(), page.getNumber());
    }

     @Override
     public UniversityDemandDTOShow create(InternshipProgrammeDTO universityDemandDTO) {
         InternshipProgramme entity = internshipProgrammeMapper.toEntity(universityDemandDTO);
         entity.setStartDate(universityDemandDTO.getStartDate());
         entity.setEndDate(universityDemandDTO.getEndDate());
         entity.setStatus(statusRepository.findById(universityDemandDTO.getStatusDTO().getId())
                 .orElseThrow(() -> new ResourceNotFoundException("Status", "id",
                         String.valueOf(universityDemandDTO.getStatusDTO().getId()))));
//         entity = setPartner(entity, universityDemandDTO);
//         entity = setMajor(entity, universityDemandDTO);
//         entity = setJobType(entity, universityDemandDTO);
//         entity = setPosition(entity, universityDemandDTO);
//         entity = setNameFile(entity, universityDemandDTO);
         InternshipProgramme savedDemand = universityDemandRepository.save(entity);
         UniversityDemandDTOShow dtoShow = new UniversityDemandDTOShow();
         try {
             dtoShow = this.internshipProgrammeMapper.toDTOShow(savedDemand);
         } catch (Exception e) {
             System.out.println(entity);
         }
//         LOGGER.info(
//                 "Admin has create demand university with name "
//                         + savedDemand.getPartner().getUniversity().getName());
         return dtoShow;
     }

     @Override
     public List<UniversityDemandDTOShow> findAll() {
         return this.universityDemandRepository.findAll().stream()
                 .map(item -> this.internshipProgrammeMapper.toDTOShow(item))
                 .collect(Collectors.toList());
     }

     @Override
     public PaginationDTO filter(InternshipProgrammeFilterDTO internshipProgrammeFilterDTO, int no, int limit) {
         Page<UniversityDemandDTOShow> page = universityDemandRepository
                 .findAll(DemandSpecification.filter(internshipProgrammeFilterDTO), PageRequest.of(no, limit))
                 .map(item -> internshipProgrammeMapper.toDTOShow(item));
         return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
                 page.getTotalPages(),
                 page.getTotalElements(), page.getSize(),
                 page.getNumber());
     }

    @Override
     public PaginationDTO findAllByNameLike(String name, int no, int limit) {
         Page<UniversityDemandDTOShow> page = this.universityDemandRepository
                 .findAllByTitleLike(name, PageRequest.of(no, limit))
                 .map(item -> internshipProgrammeMapper.toDTOShow(item));
         return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
                 page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
     }

     @Override
     public InternshipProgrammeDTO readJson(String content, MultipartFile file) {
         InternshipProgrammeDTO demand = new InternshipProgrammeDTO();
         if (file != null)
             demand.setFile(file);
         else
             throw new InternalServerErrorException(this.messageSource.getMessage("error.FileNull", null, null));
         return demand;
     }

     @Override
     public UniversityDemandDTOShow findById(int id) {
         return this.internshipProgrammeMapper.toDTOShow(this.universityDemandRepository.findById((long) id)
                 .orElseThrow(() -> new ResourceNotFoundException("University demand", "id", String.valueOf(id))));
     }

     @Override
     public UniversityDemandDTOShow update(InternshipProgrammeDTO universityDemandDTO, int id) {
         InternshipProgramme entity = internshipProgrammeMapper.toEntity(universityDemandDTO);
         // CHECK PARTNER
//         entity = setPartner(entity, universityDemandDTO);
//         entity = setMajor(entity, universityDemandDTO);
//         entity = setNameFile(entity, universityDemandDTO);
//         entity = setJobType(entity, universityDemandDTO);
//         entity = setPosition(entity, universityDemandDTO);
         entity.setId(id);
         // entity.setUpdatedDate(LocalDateTime.now());
         // entity.setStatus(mapperStatus.toEntity(universityDemandDTO.getStatusDTO()));
         // entity.setEndDate(universityDemandDTO.getEnd());
//         LOGGER.info("University demand updated by " + entity.getPartner().getUser().getUsername());
         return this.internshipProgrammeMapper.toDTOShow(this.universityDemandRepository.save(entity));
     }

//     private InternshipProgramme setJobType(InternshipProgramme universityDemand, InternshipProgrammeDTO universityDemandDTO) {
//         if (universityDemandDTO.getJobTypeDTOs().size() > 0) {
//             List<InternshipSchedule> demandJobTypes = new ArrayList<>();
//             for (ScheduleDTO jobTypeDTO : universityDemandDTO.getJobTypeDTOs()) {
//                 InternshipSchedule demandJobType = new InternshipSchedule();
//                 demandJobType.setInternshipProgramme(universityDemand);
//
//                 Schedule jobType = jobTypeRepository.findById(jobTypeDTO.getId())
//                         .orElseThrow(() -> new ResourceNotFoundException("JobType", "id",
//                                 String.valueOf(jobTypeDTO.getId())));
//                 demandJobType.setSchedule(jobType);
//                 demandJobType = jobTypeDemandRepository.save(demandJobType);
//                 demandJobTypes.add(demandJobType);
//             }
////             universityDemand.setJobTypeDemands(demandJobTypes);
//             universityDemand.setInternshipSchedules(demandJobTypes);
//         } else {
//             throw new InternalServerErrorException(this.messageSource.getMessage("error.jobTypeMissing", null, null));
//         }
//         return universityDemand;
//     }

//     private InternshipProgramme setPartner(InternshipProgramme universityDemand, InternshipProgrammeDTO universityDemandDTO) {
//         Optional<Partner> partner = partnerRepository.findById(universityDemandDTO.getPartnerDTO().getId());
//         if (!partner.isPresent())
//             throw new ResourceNotFoundException("Partner", "id",
//                     String.valueOf(universityDemandDTO.getPartnerDTO().getId()));
////         universityDemand.setPartner(partner.get());
//         return universityDemand;
//     }

//     private InternshipProgramme setMajor(InternshipProgramme universityDemand, InternshipProgrammeDTO universityDemandDTO) {
//         if (universityDemandDTO.getMajorDTOs().size() > 0) {
//             List<InternshipMajor> demandMajors = new ArrayList<>();
//             for (MajorDTO majorDto : universityDemandDTO.getMajorDTOs()) {
//                 InternshipMajor demandMajor = new InternshipMajor();
//                 demandMajor.setInternshipProgramme(universityDemand);
//                 Major major = majorRepository.findById(majorDto.getId());
//                 demandMajor.setMajor(major);
//                 demandMajor = majorDemandRepository.save(demandMajor);
//                 demandMajors.add(demandMajor);
//             }
//             universityDemand.setInternshipMajors(demandMajors);
//         } else {
//             throw new InternalServerErrorException(this.messageSource.getMessage("error.majorMissing", null, null));
//         }
//         return universityDemand;
//     }

//     private InternshipProgramme setPosition(InternshipProgramme universityDemand, InternshipProgrammeDTO universityDemandDTO) {
//         if (universityDemandDTO.getJobPositionDTOs().size() > 0) {
//             List<InternshipPosition> demandPositions = new ArrayList<>();
//             for (PositionDTO jobPositionDTO : universityDemandDTO.getJobPositionDTOs()) {
//                 InternshipPosition demandPosition = new InternshipPosition();
//                 demandPosition.setInternshipProgramme(universityDemand);
//
//                 Position jobPosition = jobPositionRepository.findById(jobPositionDTO.getId())
//                         .orElseThrow(() -> new ResourceNotFoundException("JobPosition", "id",
//                                 String.valueOf(jobPositionDTO.getId())));
//                 demandPosition.setPosition(jobPosition);
//                 demandPosition = demandPositionRepository.save(demandPosition);
//                 demandPositions.add(demandPosition);
//             }
//             universityDemand.setInternshipPositions(demandPositions);
//         } else {
//             throw new InternalServerErrorException(this.messageSource.getMessage("error.positionMissing", null, null));
//         }
//         return universityDemand;
//     }

//     private InternshipProgramme setNameFile(InternshipProgramme universityDemand, InternshipProgrammeDTO universityDemandDTO) // UPLOAD
//                                                                                                                      // FILE
//     {
//         if (universityDemandDTO.getFile() != null) {
//             String originFile = universityDemandDTO.getFile().getOriginalFilename();
//             String attribute = originFile.substring(originFile.lastIndexOf('.') + 1);
//             if (!attribute.equals("xlsx"))// CHECK FILE
//             {
//                 throw new InternalServerErrorException(messageSource.getMessage("error.File", null, null));
//             }
//             FileUpload file = new FileUpload();
//             file.setFile(universityDemandDTO.getFile());
//             if (universityDemand.getStudents() != null) {
//                 this.updateFile.deleteFile(universityDemand.getStudents());
//             }
//             this.updateFile.uploadExcel(file);
//             universityDemand.setStudents(file.getOutput());
//         }
//         return universityDemand;
//     }

     @Override
     public void deleteById(int id) {
         InternshipProgramme entity = this.universityDemandRepository.findById((long) id)
                 .orElseThrow(() -> new ResourceNotFoundException("Demand", "id", String.valueOf(id)));
         entity.setStatus(statusRepository.findById(2).get());
//         WARNING_LOGGER.warn("University demand " + entity.getName() + " deleted");
     }

     @Override
     public PaginationDTO findAllActiveByUniversityId(int universityId, int no, int limit) {
         boolean isUniversity = this.universityService.existsById(universityId);
         if (!isUniversity)
             throw new ResourceNotFoundException("University", "id", String.valueOf(universityId));
         Page<Object> page = universityDemandRepository.findAllActiveByUniversityId(universityId, PageRequest.of(no, limit))
                 .map(item -> internshipProgrammeMapper.toDTOShow(item));
         return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                 page.getTotalElements(), page.getSize(), page.getNumber());
    }

//     private Set<Schedule> checkJobTypes(Set<Schedule> jobTypes) {
//         Set<Schedule> list = new HashSet<Schedule>();
//         for (Schedule item : jobTypes) {
//             Schedule jobdto = this.jobTypeRepository.findById(item.getId())
//                     .orElseThrow(() -> new ResourceNotFoundException("Job type", "id", String.valueOf(item.getId())));
//             list.add(jobdto);
//         }
//         return list;
//     }

     @Override
     public UniversityDemandDTOShow updateStatus(InternshipProgrammeDTO internshipProgrammeDTO) {
         InternshipProgramme universityDemand = this.universityDemandRepository.findById((long) internshipProgrammeDTO.getId())
                 .orElseThrow(() -> new ResourceNotFoundException("University demand", "id",
                         String.valueOf(internshipProgrammeDTO.getId())));
         universityDemand.setStatus(statusRepository.getById(internshipProgrammeDTO.getStatusDTO().getId()));
         universityDemand = universityDemandRepository.save(universityDemand);
         return this.internshipProgrammeMapper.toDTOShow(universityDemand);
     }
}
