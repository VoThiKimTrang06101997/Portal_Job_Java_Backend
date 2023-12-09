package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.JobCareDTO;
import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateDTO;
import com.r2s.findInternship.data.entity.JobCare;
import com.r2s.findInternship.data.mapper.JobCareMapper;
import com.r2s.findInternship.data.repository.CandidateRepository;
import com.r2s.findInternship.data.repository.JobCareRepository;
import com.r2s.findInternship.data.repository.JobRepository;
import com.r2s.findInternship.exception.CustomException;
import com.r2s.findInternship.exception.InvalidOldPasswordException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.CandidateService;
import com.r2s.findInternship.service.JobCareService;
import com.r2s.findInternship.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobCareServiceImpl implements JobCareService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobServiceImpl jobServiceImpl;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JobCareMapper jobCareMapper;
    @Autowired
    private JobCareRepository jobCareRepository;
    @Autowired
    private UserService userService;
    
    public final static int ActiveStatic = 1;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public PaginationDTO findAllByCandidateId(long candidateId, int no, int limit) {
        Page<JobCareDTO> page = this.jobCareRepository
                .findAllByCandidateId((int) candidateId, PageRequest.of(no, limit))
                .map(item -> this.jobCareMapper.toDTO(item));
        return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
                page.getTotalElements(), page.getSize(), page.getNumber());

    }

    @Override
    public List<Integer> findJobSaveOfCandidateID(long candidateId) {
        List<Integer> listJobId = this.jobCareRepository.finJobSave(candidateId);
        if (listJobId.isEmpty())
            return null;
        return listJobId;

    }

    @Override
    public PaginationDTO findAllByJobId(int jobId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByJobId'");
    }

    @Override
    public List<JobCareDTO> findAll() {
        return this.jobCareRepository.findAll().stream().map(item -> this.jobCareMapper.toDTO(item))
                .collect(Collectors.toList());

    }

    @Override
    public JobCareDTO findById(int id) {
        return this.jobCareMapper.toDTO(this.jobCareRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Job care", "id",
                        String.valueOf(id))));

    }

    @Override
    public JobCareDTO findByCandidateIdAndJobId(int candidateId, int jobId) {
        return this.jobCareMapper.toDTO(this.jobCareRepository.findByCandidateIdAndJobId(candidateId, jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job care", "(candidateId, jobId)",
                        "(" + candidateId + ", " + jobId + ")")));

    }

    @Override
    public MessageResponse create(long idJob) {

        JobDTO jobDTO = jobServiceImpl.findById(idJob);
        CandidateDTO candidateDTO = candidateService.findByUserId(userService.getCurrentUserId());
        // verify that candidate and job in database

        if (jobDTO.getStatusDTO().getId() != ActiveStatic)
            throw new IllegalArgumentException();
        JobCareDTO jobCareDTO = new JobCareDTO();
        jobCareDTO.setJobDTO(jobDTO);
        jobCareDTO.setCandidateDTO(candidateDTO);

        JobCare care = jobCareMapper.toEntity(jobCareDTO);
        jobCareRepository.save(care);

        return new MessageResponse(200, null, null);
    }

    @Override
    public MessageResponse deleteById(long idJobCare) {
        JobCareDTO jobCareDTO = findById((int) idJobCare);
        CandidateDTO candidateDTO = candidateService.findByUserId(userService.getCurrentUserId());
        if(Objects.isNull(jobCareDTO))
            throw new InvalidOldPasswordException("BAD_REQUEST");

        if(candidateDTO.getId() == jobCareDTO.getCandidateDTO().getId()){
            this.jobCareRepository.delete(this.jobCareRepository.findById((long) idJobCare)
                    .orElseThrow(() -> new ResourceNotFoundException("Job care", "id",
                            String.valueOf(idJobCare))));
            return new MessageResponse(200, null, null);
        }else {
            throw new CustomException();
        }
    }

    // @Override
    // public List<JobCareDTO> findAll() {
    //         return this.jobCareRepository.findAll().stream().map(item -> this.jobCareMapper.toDTO(item))
    //                         .collect(Collectors.toList());
    // }

    // @Override
    // public JobCareDTO findById(int id) {
    //         return this.jobCareMapper.toDTO(this.jobCareRepository.findById(id)
    //                         .orElseThrow(() -> new ResourceNotFoundException("Job care", "id",
    //                                         String.valueOf(id))));
    // }

    // @Override
    // public JobCareDTO findByCandidateIdAndJobId(int candidateId, int jobId) {
    //         return this.jobCareMapper.toDTO(this.jobCareRepository.findByCandidateIdAndJobId(candidateId, jobId)
    //                         .orElseThrow(() -> new ResourceNotFoundException("Job care", "(candidateId, jobId)",
    //                                         "(" + candidateId + ", " + jobId + ")")));
    // }

    // @Override
    // public PaginationDTO findAllByCandidateId(int candidateId, int no, int limit) {
    //         Page<JobCareDTO> page = this.jobCareRepository
    //                         .findAllByCandidateId(candidateId, PageRequest.of(no, limit))
    //                         .map(item -> this.jobCareMapper.toDTO(item));
    //         return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    //                         page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public PaginationDTO findAllByJobId(int jobId, int no, int limit) {
    //         Page<JobCareDTO> page = this.jobCareRepository
    //                         .findAllByJobId(jobId, PageRequest.of(no, limit))
    //                         .map(item -> this.jobCareMapper.toDTO(item));
    //         return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
    //                         page.getTotalElements(), page.getSize(), page.getNumber());
    // }

    // @Override
    // public JobCareDTO create(JobCareDTO jobCareDTO) {
    //         // verify that candidate and job in database
    //         if (!candidateRepository.existsById(jobCareDTO.getCandidateDTO().getId())) {
    //                 throw new ResourceNotFoundException("Candidate", "id",
    //                                 String.valueOf(jobCareDTO.getCandidateDTO().getId()));
    //         }
    //         if (!jobRepository.existsById(jobCareDTO.getJobDTO().getId())) {
    //                 throw new ResourceNotFoundException("Job", "id",
    //                                 String.valueOf(jobCareDTO.getCandidateDTO().getId()));
    //         }
    //         // if candidate has care the job, but want to care again, then throw error
    //         if (this.jobCareRepository
    //                         .existsByCandidateIdAndJobId(jobCareDTO.getCandidateDTO().getId(),
    //                                         jobCareDTO.getJobDTO().getId()))
    //                 throw new CustomException(this.messageSource.getMessage("error.careOnce", null, null));
    //         return this.jobCareMapper.toDTO(jobCareRepository.save(jobCareMapper.toEntity(jobCareDTO)));
    // }

    // @Override
    // public JobCareDTO update(int id, JobCareDTO jobCareDTO) {
    //         JobCare care = this.jobCareRepository.findById(id)
    //                         .orElseThrow(() -> new ResourceNotFoundException("Care list", "id",
    //                                         String.valueOf(id)));
    //         care.setId(id);
    //         care.setNote(jobCareDTO.getNote());
    //         care.setCreatedDate(LocalDateTime.now());
    //         care = this.jobCareRepository.save(care);
    //         LOGGER.info(String.format("Candidate %s update note about job %s successfully!",
    //                         care.getCandidate().getUser().getUsername(), care.getJob().getName()));
    //         return this.jobCareMapper.toDTO(care);
    // }

    // @Override
    // public MessageResponse deleteById(int id) {
    //         this.jobCareRepository.delete(this.jobCareRepository.findById(id)
    //                         .orElseThrow(() -> new ResourceNotFoundException("Job care", "id",
    //                                         String.valueOf(id))));
    //         return new MessageResponse(200, "Deleted job care with id = " + String.valueOf(id), null);
    // }

}