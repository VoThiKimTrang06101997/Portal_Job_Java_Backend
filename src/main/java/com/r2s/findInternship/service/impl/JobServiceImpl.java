package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.common.util.DateTimeHelper;
import com.r2s.findInternship.data.dto.*;
import com.r2s.findInternship.data.entity.*;
import com.r2s.findInternship.data.mapper.*;
import com.r2s.findInternship.data.repository.*;
import com.r2s.findInternship.data.repository.specification.JobSpecification;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private StatusService statusService;
    @Autowired
    private JobPositionService jobPositionService;
    @Autowired
    private JobTypeService jobTypeService;
    @Autowired
    private MajorService majorService;

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private JobMajorRepository jobMajorRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private JobScheduleRepository jobScheduleRepository;

    @Autowired
    private HRRepository hrRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobMajorMapper jobMajorMapper;

    @Autowired
    private JobPositionMapper jobPositonMapper;

    @Autowired
    private JobScheduleMapper jobScheduleMapper;
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger("info");

    public static final int JOB_STATUS_ACTIVE_ID = 1;

    @Override
    public PaginationDTO filterJob(JobFilterDTO jobFilterDTO, int no, int limit) {
        Page<JobShowDTO> page = this.jobRepository
                .findAll(JobSpecification.filterJobForCandidate(jobFilterDTO), PageRequest.of(no, limit))
                .map(item -> toListJobShowDTO(item));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(), page.getSize(),
                page.getNumber());
    }

    public List<MajorDTO> toListMajorDTO(List<JobMajor> jobMajors) {
        List<MajorDTO> majorDTOS = new ArrayList<>();
        for (JobMajor jobMajor : jobMajors) {
            majorDTOS.add(jobMajorMapper.toMajorDto(jobMajor));
        }
        return majorDTOS;
    }

    public List<PositionDTO> toListPositionDTO(List<JobPosition> jobPositions) {
        List<PositionDTO> positionDTOS = new ArrayList<>();
        for (JobPosition jobPosition : jobPositions) {
            positionDTOS.add(jobPositonMapper.toPositionDto(jobPosition));
        }
        return positionDTOS;
    }

    public List<ScheduleDTO> toListScheduleDTO(List<JobSchedule> jobSchedules) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (JobSchedule jobSchedule : jobSchedules) {
            scheduleDTOS.add(jobScheduleMapper.toScheduleDto(jobSchedule));
        }
        return scheduleDTOS;
    }

    public JobShowDTO toListJobShowDTO(Job job) {
        JobShowDTO jobShowDTO = new JobShowDTO();
        jobShowDTO = jobMapper.toDTOShow(job);
        jobShowDTO.setMajorDTOs(toListMajorDTO(job.getJobMajors()));
        jobShowDTO.setPositionDTOs(toListPositionDTO(job.getJobPositions()));
        jobShowDTO.setScheduleDTOs(toListScheduleDTO(job.getJobSchedules()));
        return jobShowDTO;
    }

    //update status job when start program
//    @PostConstruct
//    public void updateListJobStatusAtStart() {
//        updateListJobStatus();
//    }
//
//    // set schedule for update status endDate once a day (0h00p)
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void updateListJobStatus() {
//        List<Job> jobs = jobRepository.findJobActive();
//
//        for (Job job : jobs) {
//            if (job.getStatus().getName().equals(String.valueOf(Estatus.Active)) && job.getEndDate().before(new Date())) {
//                job.setStatus(statusService.findByNameEntity(String.valueOf(Estatus.Disable)));
//                jobRepository.save(job);
//            }
//        }
//    }

    @Override
    public JobDTO create(JobCreationDTO jobCreationDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () ->  new AccessDeniedException("FORBIDDEN"));

        Job newJob = this.jobMapper.toEntity(jobCreationDTO);
        newJob.setStatus(statusService.findByName(Estatus.Active));
        newJob.setCompany(hr.getCompany());
        newJob = jobRepository.save(newJob);

        createJobMajors(newJob, jobCreationDTO.getMajorDTOs());
        createJobPositions(newJob, jobCreationDTO.getPositionDTOs());
        createJobSchedules(newJob, jobCreationDTO.getScheduleDTOs());

        LOGGER.info("Post job successfully");
        return this.jobMapper.toDTO(jobRepository.save(newJob));
    }

    @Override
    public JobDTO update(long id, JobDTO jobUpdateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        Job oldJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", String.valueOf(id)));

        if (oldJob.getCompany().getId() == hr.getCompany().getId()) {
            if (oldJob.getStatus().getId() == JOB_STATUS_ACTIVE_ID) {
                Job newJob = jobMapper.jobUpdateDTOToJob(jobUpdateDTO);
                newJob.setId(oldJob.getId());
                newJob.setStatus(oldJob.getStatus());
                newJob.setCompany(oldJob.getCompany());
                newJob.setJobMajors(updateJobMajors(newJob, jobUpdateDTO.getMajorDTOs()));
                newJob.setJobPositions(updateJobPositions(newJob, jobUpdateDTO.getPositionDTOs()));
                newJob.setJobSchedules(updateJobSchedules(newJob, jobUpdateDTO.getScheduleDTOs()));
                newJob = this.jobRepository.save(newJob);
                return this.jobMapper.toDTO(newJob);
            } else {
                throw new IllegalArgumentException("BAD_REQUEST");
            }
        } else {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    @Override
    public JobDTO replicate(long id, JobDTO jobDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        HR hr = this.hrRepository.findByUsername(username).orElseThrow(
                () -> new AccessDeniedException("FORBIDDEN"));

        Job oldJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", String.valueOf(id)));

        if (oldJob.getCompany().getId() == hr.getCompany().getId()) {
            if (oldJob.getStatus().getId() == JOB_STATUS_ACTIVE_ID) {
                Job newJob = jobMapper.jobUpdateDTOToJob(jobDTO);
                newJob.setStatus(statusService.findByName(Estatus.Active));
                newJob.setCompany(oldJob.getCompany());
                newJob = jobRepository.save(newJob);
                createJobMajors(newJob, jobDTO.getMajorDTOs());
                createJobPositions(newJob, jobDTO.getPositionDTOs());
                createJobSchedules(newJob, jobDTO.getScheduleDTOs());

                return this.jobMapper.toDTO(newJob);
            } else {
                throw new IllegalArgumentException("BAD_REQUEST");
            }
        } else {
            throw new AccessDeniedException("FORBIDDEN");
        }
    }

    private void createJobMajors(Job job, List<MajorDTO> majorDTOs) {

        if (majorDTOs != null && majorDTOs.size() > 0) {
            for (MajorDTO majorDTO : majorDTOs) {
                Major existingMajor = majorRepository.findById(majorDTO.getId());
                JobMajor newJobMajor = new JobMajor();
                newJobMajor.setMajor(existingMajor);
                newJobMajor.setJob(job);
                job.getJobMajors().add(newJobMajor);
                jobMajorRepository.save(newJobMajor);
            }
        }
    }

    private void createJobPositions(Job job, List<PositionDTO> positionDTOs) {
        if (positionDTOs != null && positionDTOs.size() > 0) {
            for (PositionDTO positionDTO : positionDTOs) {
                Position existingPosition = positionRepository.findById((positionDTO.getId()));
                JobPosition newJobPosition = new JobPosition();
                newJobPosition.setPosition(existingPosition);
                newJobPosition.setJob(job);
                job.getJobPositions().add(newJobPosition);
                jobPositionRepository.save(newJobPosition);
            }
        }

    }

    private void createJobSchedules(Job job, List<ScheduleDTO> scheduleDTOs) {
        if (scheduleDTOs != null && scheduleDTOs.size() > 0) {
            for (ScheduleDTO scheduleDTO : scheduleDTOs) {
                Schedule existingSchedule = scheduleRepository.findById(scheduleDTO.getId());
                JobSchedule newJobSchedule = new JobSchedule();
                newJobSchedule.setSchedule(existingSchedule);
                newJobSchedule.setJob(job);
                job.getJobSchedules().add(newJobSchedule);
                jobScheduleRepository.save(newJobSchedule);
            }
        }

    }

    private List<JobMajor> updateJobMajors(Job job, List<MajorDTO> majorDTOs) {
        Queue<JobMajor> newJobMajors = new LinkedList<>();
        for (MajorDTO majorDTO : majorDTOs) {
            Major existingMajor = majorRepository.findById(majorDTO.getId());

            JobMajor newJobMajor = new JobMajor();
            newJobMajor.setJob(job);
            newJobMajor.setMajor(existingMajor);
            newJobMajors.add(newJobMajor);
            job.getJobMajors().add(newJobMajor);
        }
        List<JobMajor> oldJobMajors = jobMajorRepository.findAllByJob_Id(job.getId());
        Iterator<JobMajor> iterator = oldJobMajors.iterator();
        while (iterator.hasNext()) {
            JobMajor oldJobMajor = iterator.next();
            JobMajor newJobMajor = newJobMajors.poll();
            if (newJobMajor == null) {
                jobMajorRepository.deleteById(oldJobMajor.getId());
            } else {
                newJobMajor.setId(oldJobMajor.getId());
                jobMajorRepository.save(newJobMajor);
                iterator.remove();
            }
        }
        while (!newJobMajors.isEmpty()) {
            jobMajorRepository.save(newJobMajors.poll());
        }
        List<JobMajor> jobMajorList = new ArrayList<>();
        jobMajorList.addAll(newJobMajors);
        return jobMajorList;
    }

    private List<JobPosition> updateJobPositions(Job job, List<PositionDTO> positionDTOs) {
        Queue<JobPosition> newJobPositions = new LinkedList<>();
        for (PositionDTO positionDTO : positionDTOs) {
            Position existingPosition = positionRepository.findById(positionDTO.getId());

            JobPosition newJobPosition = new JobPosition();
            newJobPosition.setJob(job);
            newJobPosition.setPosition(existingPosition);
            newJobPositions.add(newJobPosition);
            job.getJobPositions().add(newJobPosition);
        }
        List<JobPosition> oldJobPositions = jobPositionRepository.findAllByJob_Id(job.getId());
        Iterator<JobPosition> iterator = oldJobPositions.iterator();
        while (iterator.hasNext()) {
            JobPosition oldJobPosition = iterator.next();
            JobPosition newJobPosition = newJobPositions.poll();
            if (newJobPosition == null) {
                jobPositionRepository.deleteById(oldJobPosition.getId());
            } else {
                newJobPosition.setId(oldJobPosition.getId());
                jobPositionRepository.save(newJobPosition);
                iterator.remove();
            }
        }
        while (!newJobPositions.isEmpty()) {
            jobPositionRepository.save(newJobPositions.poll());
        }
        List<JobPosition> jobPositionList = new ArrayList<>();
        jobPositionList.addAll(jobPositionList);
        return jobPositionList;
    }

    private List<JobSchedule> updateJobSchedules(Job job, List<ScheduleDTO> scheduleDTOs) {
        Queue<JobSchedule> newJobSchedules = new LinkedList<>();
        for (ScheduleDTO scheduleDTO : scheduleDTOs) {
            Schedule existingSchedule = scheduleRepository.findById(scheduleDTO.getId());

            JobSchedule newJobSchedule = new JobSchedule();
            newJobSchedule.setJob(job);
            newJobSchedule.setSchedule(existingSchedule);
            newJobSchedules.add(newJobSchedule);
            job.getJobSchedules().add(newJobSchedule);
        }
        List<JobSchedule> oldJobSchedules = jobScheduleRepository.findAllByJob_Id(job.getId());
        Iterator<JobSchedule> iterator = oldJobSchedules.iterator();
        while (iterator.hasNext()) {
            JobSchedule oldJobSchedule = iterator.next();
            JobSchedule newJobSchedule = newJobSchedules.poll();
            if (newJobSchedule == null) {
                jobScheduleRepository.deleteById(oldJobSchedule.getId());
            } else {
                newJobSchedule.setId(oldJobSchedule.getId());
                jobScheduleRepository.save(newJobSchedule);
                iterator.remove();
            }
        }
        while (!newJobSchedules.isEmpty()) {
            jobScheduleRepository.save(newJobSchedules.poll());
        }
        List<JobSchedule> jobScheduleList = new ArrayList<>();
        jobScheduleList.addAll(jobScheduleList);
        return jobScheduleList;
    }

    // @Override
    // public JobDTO changeStatus(int id, JobDTO dto) {
    // JobDTO jobDTO = this.findById(id);
    // StatusDTO statusDTO = statusService.findById(dto.getStatusDTO().getId());
    // jobDTO.setStatusDTO(statusDTO);
    // Job newJob = jobMapper.toEntity(jobDTO);
    // newJob.setId(id);
    // newJob = jobRepository.save(newJob);
    // LOGGER.info(String.format("Update status of job with %s successfully", id));
    // return jobMapper.toDTO(newJob);
    // }

    // @Override
    // public List<JobDTO> findAll() {
    // 	return jobRepository.findAll().stream().map(job -> this.jobMapper.toDTO(checkOutOfDate(job)))
    // 			.collect(Collectors.toList());
    // }

    // @Override
    // public List<JobDTO> findAllActive() {
    // 	return jobRepository.findAllActive().stream()
    // 			.filter(job -> checkOutOfDate(job).getStatus().getName().equals(Estatus.Active))
    // 			.map(job -> this.jobMapper.toDTO(job))
    // 			.collect(Collectors.toList());
    // }

//    @Override
//    public PaginationDTO findAllActive(int no, int limit) {
//        return null;
//    }

    @Override
    public JobDTO findById(long id) {
        return this.jobMapper.toDTO(
                checkOutOfDate(
                        jobRepository.findById(Long.valueOf(id))
                                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", String.valueOf(id)))));
    }

    // @Override
    // public JobDTOShow findByIdToShow(Integer id) {
    // 	return this.jobMapper.toDTOShow(checkOutOfDate(jobRepository.findById(id)
    // 			.orElseThrow(() -> new ResourceNotFoundException("Job", "id", String.valueOf(id)))));
    // }

    // @Override
    // public boolean existsById(Integer id) {
    // 	return jobRepository.existsById(id);
    // }

    // @Override
    // public long count() {
    // 	return jobRepository.count();
    // }

    // @Override
    // public MessageResponse deleteById(Integer id) {
    // 	if (!this.existsById(id))
    // 		throw new ResourceNotFoundException("Job", "id", String.valueOf(id));
    // 	jobRepository.deleteById(id);
    // 	LOGGER.info(String.format("Job with id %s deleted successfully", id));
    // 	return new MessageResponse(200, messageSource.getMessage("info.deleteJob", null, null), null);
    // }

     @Override
     public PaginationDTO findAllActive(int no, int limit) {
     	Page<JobDTO> page = jobRepository.findAllActive(PageRequest.of(no, limit)).map(item -> jobMapper.toDTO(item));
     	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
     			page.getTotalElements(), page.getSize(), page.getNumber());
     }

    // @Override
    // public PaginationDTO findAllActiveByNameLikeAndProvinceName(String name, String provinceName, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository
    // 			.findAllActiveByNameLikeAndProvinceName(name, provinceName, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByHrId(int hrId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAllActiveByHrId(hrId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAll(int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAll(PageRequest.of(no, limit)).map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByCompanyIdShowForHr(int companyId, int no, int limit) {
    // 	Page<Object> page = jobRepository.findAllActiveByCompanyId(companyId, PageRequest.of(no, limit))
    // 			.map(j -> jobMapper.toDTOShow(j));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllDisableByCompanyIdShowForHr(int companyId, int no, int limit) {
    // 	Page<Object> page = jobRepository.findAllDisableByCompanyId(companyId, PageRequest.of(no, limit))
    // 			.map(j -> jobMapper.toDTOShow(j));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllByCompanyIdShowForHr(int companyId, int no, int limit) {
    // 	Page<Object> page = jobRepository.findAllByCompanyId(companyId, PageRequest.of(no, limit))
    // 			.map(j -> jobMapper.toDTOShow(j));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public long countAllActiveByCompanyIdShowForHr(int companyId) {
    // 	return jobRepository.countAllActiveByCompanyId(companyId);
    // }

    // @Override
    // public long countAllDisableByCompanyIdShowForHr(int companyId) {
    // 	return jobRepository.countAllDisableByCompanyId(companyId);
    // }

    // @Override
    // public long countAllByCompanyId(int companyId) {
    // 	return jobRepository.countAllByCompanyId(companyId);
    // }

    // @Override
    // public PaginationDTO findAllActiveByUserId(long userId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAllActiveByUserId(userId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllByUserId(long userId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAllByUserId(userId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllDisableByUserId(long userId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAllDisableByUserId(userId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByUsername(String username, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAllActiveByUsername(username, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByDistrictNameAndProvinceName(String districtName, String provinceName, int no,
    // 		int limit) {
    // 	Page<JobDTO> page = jobRepository
    // 			.findAllActiveByDistrictNameAndProvinceName(districtName, provinceName, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByNameLikeAndAppliedCandidateId(String name, int candidateId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository
    // 			.findAllActiveByNameLikeAndAppliedCandidateId(name, candidateId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO filterByKeywords(String name, Integer provinceId, List<String> jobTypeNames,
    // 		List<String> jobPositionNames, List<String> majorNames, String order, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository.findAll(JobSpecification.filterJobForCandidate(name, provinceId,
    // 			jobPositionNames, jobTypeNames, majorNames, order), PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllActiveByNameLikeAndCaredCandidateId(String name, int candidateId, int no, int limit) {
    // 	Page<JobDTO> page = jobRepository
    // 			.findAllActiveByNameLikeAndCaredCandidateId(name, candidateId, PageRequest.of(no, limit))
    // 			.map(item -> jobMapper.toDTO(item));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    @Override
    public Long countByCreatedDate(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return jobRepository.countByCreatedDateBetween(from, to);
        } else {
            return jobPositionService.count();
        }
    }

    // @Override
    // public List<JobDTO> findAllActiveByCompanyId(Integer companyId) {
    // 	// check companyId exits
    // 	Company company = companyService.getById(companyId);
    // 	if (jobRepository.findAllActiveByCompanyId(companyId).size() == 0) {
    // 		throw new ResourceNotFoundException("Job", "id of Company", String.valueOf(companyId));
    // 	}
    // 	return jobRepository.findAllActiveByCompanyId(companyId).stream().map(job -> this.jobMapper.toDTO(job))
    // 			.collect(Collectors.toList());

    // }

    // @Override
    // public List<JobDTO> findAllByUserId(long userId) {
    // 	if (jobRepository.findAllByUserId(userId).size() == 0) {
    // 		throw new ResourceNotFoundException("Job", "id of User", String.valueOf(userId));
    // 	}
    // 	return jobRepository.findAllByUserId(userId).stream().map(job -> this.jobMapper.toDTO(job))
    // 			.collect(Collectors.toList());

    // }

    // @Override
    // public List<JobDTO> findAllByUsername(String username) {
    // 	if (jobRepository.findAllByUsername(username).size() == 0) {
    // 		throw new ResourceNotFoundException("Job", "name of User", username);
    // 	}
    // 	return jobRepository.findAllByUsername(username).stream().map(job -> this.jobMapper.toDTO(job))
    // 			.collect(Collectors.toList());

    // }

    @Override
    public List<Object[]> getNewStatistics() { // created date within 1 month
        return jobRepository
                .getNewStatistics(DateTimeHelper.getEarliestTimeOfDate(DateTimeHelper.getDateTimeOfMonthAgo(1)));
    }

    // @Override
    // public List<Object[]> getStatusStatistics() {
    // 	return jobRepository.getStatusStatistics();
    // }

    // @Override
    // public List<Object[]> getPositionStatistics() {
    // 	return jobRepository.getPositionStatistics();
    // }

    // @Override
    // public List<Object[]> getMajorStatistics() {
    // 	return jobRepository.getMajorStatistics();
    // }

    private Job checkOutOfDate(Job job) {
        if (job.getStatus().getName().equals(String.valueOf(Estatus.Active)) && job.getEndDate().before(new Date())) {
            job.setStatus(statusService.findByNameEntity(String.valueOf(Estatus.Disable)));
            return jobRepository.save(job);
        }
        return job;
    }

    @Override
    public boolean isAppliable(JobDTO jobDTO) {
        return jobDTO.getStatusDTO().getName().equals(String.valueOf(Estatus.Active));
    }

    // @Override
    // public PaginationDTO filterAllPostedJobOfCompanyShowForHr(int companyId,
    // 		PostedJobOfCompanyFilterByHrDTO filterInfo, int no, int limit) {
    // 	Page<Object> page = jobRepository.filterPostedJobOfCompanyByHr(companyId,
    // 			filterInfo.getQuickSearch(), filterInfo.getProvinceName(), filterInfo.getEndDate(),
    // 			filterInfo.getStatusId(), PageRequest.of(no, limit))
    // 			.map(j -> jobMapper.toDTOShow(j));
    // 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    // 			page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // private JobDTO validateReferencesExist(JobDTO jobDTO) {
    // 	// if (!hrRepository.existsById(jobDTO.getHrDTO().getId())) {
    // 	// 	throw new ResourceNotFoundException("HR", "id", String.valueOf(jobDTO.getHrDTO().getId()));
    // 	// }
    // 	if (!majorRepository.existsById(jobDTO.getMajorDTO().getId())) {
    // 		throw new ResourceNotFoundException("Major", "id", String.valueOf(jobDTO.getMajorDTO().getId()));
    // 	}
    // 	if (!jobPositionRepository.existsById(jobDTO.getJobPositionDTO().getId())) {
    // 		throw new ResourceNotFoundException("Job position", "id",
    // 				String.valueOf(jobDTO.getJobPositionDTO().getId()));
    // 	}
    // 	if (!jobTypeRepository.existsById(jobDTO.getJobTypeDTO().getId())) {
    // 		throw new ResourceNotFoundException("Job type", "id",
    // 				String.valueOf(jobDTO.getJobTypeDTO().getId()));
    // 	}
    // 	// replaces multiple whitespace with single whitespace in address of location
    // 	// and removes whitespace from both ends of address of location
    // 	jobDTO.getLocationDTO().setAddress(
    // 			jobDTO.getLocationDTO().getAddress().replaceAll("\\s+", " ").trim());
    // 	// check location is existing, save new if not
    // 	Location existingLocation = locationRepository
    // 			.findByAddressAndDistrictId(jobDTO.getLocationDTO().getAddress(),
    // 					jobDTO.getLocationDTO().getDistrictDTO().getId());
    // 	if (existingLocation == null) {
    // 		jobDTO.setLocationDTO(
    // 				locationMapper.toDTO(locationRepository.save(
    // 						locationMapper.toEntity(jobDTO.getLocationDTO()))));
    // 	} else {
    // 		jobDTO.setLocationDTO(locationMapper.toDTO(existingLocation));
    // 	}
    // 	return jobDTO;
    // }

}
