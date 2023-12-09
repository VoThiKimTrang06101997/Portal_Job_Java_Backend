package com.r2s.findInternship.controller.candidate;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.data.dto.JobCareDTO;
import com.r2s.findInternship.service.JobCareService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiURL.CANDIDATE_JOB_CARE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class CandidateJobCareController {
    @Autowired
    private JobCareService jobCareService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.jobCareService.findAll());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(this.jobCareService.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping("/job-save/{id}")
    public ResponseEntity<?> findAllJobSave(@PathVariable int id){
        return ResponseEntity.ok(this.jobCareService.findJobSaveOfCandidateID(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> findAllByCandidateId(@PathVariable("id") long candidateId,
                                                  @RequestParam(defaultValue = PageDefault.NO) int no,
                                                  @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.jobCareService.findAllByCandidateId(candidateId, no, limit));
    }

//    @GetMapping("/job/{id}")
//    public ResponseEntity<?> findAllByJobId(@PathVariable("id") int jobId,
//                                            @RequestParam(defaultValue = PageDefault.NO) int no,
//                                            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
//        return ResponseEntity.ok(this.jobCareService.findAllByJobId(jobId, no, limit));
//    }

//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasAuthority('Role_Candidate')")
//    @PostMapping("")
//    public ResponseEntity<?> create(@RequestBody JobCareDTO jobCareDTO) {
//        return ResponseEntity.ok(this.jobCareService.create(jobCareDTO));
//    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @PostMapping("{id}")
    public ResponseEntity<?> create(@PathVariable("id") long idJob) {
        return ResponseEntity.ok(this.jobCareService.create(idJob));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_Candidate')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long idJobCare) {
        return ResponseEntity.ok(this.jobCareService.deleteById(idJobCare));
    }
}


