package com.r2s.findInternship.controller;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.constant.PageDefault;
// import com.r2s.findInternship.data.dto.JobCreationDTO;
// import com.r2s.findInternship.data.dto.JobDTO;
// import com.r2s.findInternship.data.dto.PostedJobOfCompanyFilterByHrDTO;
// import com.r2s.findInternship.service.JobService;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.data.dto.JobCreationDTO;
import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.JobFilterDTO;
import com.r2s.findInternship.service.JobService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.JOB)
public class JobController {
    @Autowired
    private JobService jobService;

 	@GetMapping("/active")
 	public ResponseEntity<?> findAllActive(@RequestParam(defaultValue = PageDefault.NO) int no,
 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
 		return ResponseEntity.ok(jobService.findAllActive(no, limit));
 	}

 	@GetMapping("/{id}")
 	public ResponseEntity<?> findById(@PathVariable int id) {
 		return ResponseEntity.ok(this.jobService.findById(id));
 	}

// 	@GetMapping("/user/{id}")
// 	public ResponseEntity<?> findAllByUserId(@PathVariable int id) {
// 		return ResponseEntity.ok(this.jobService.findAllByUserId(id));
// 	}

// 	@GetMapping("active/user/{id}")
// 	public ResponseEntity<?> findAllActiveByUserId(@PathVariable("id") long userId,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllActiveByUserId(userId, no, limit));
// 	}

// 	@GetMapping("/disable/user/{id}")
// 	public ResponseEntity<?> findAllDisableByUserId(@PathVariable long id,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllDisableByUserId(id, no, limit));
// 	}

// 	@GetMapping("active/user/{username}")
// 	public ResponseEntity<?> findAllActiveByUsername(@PathVariable String username,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllActiveByUsername(username, no, limit));
// 	}

// 	@GetMapping("/search")
// 	public ResponseEntity<?> findAllActiveByNameLikeAndAppliedCandidateId(@RequestParam String name,
// 			@RequestParam int candidateId, @RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity
// 				.ok(this.jobService.findAllActiveByNameLikeAndAppliedCandidateId(name, candidateId, no, limit));
// 	}


    @GetMapping("/filter") // filter in home page
    public ResponseEntity<?> filter(@RequestParam int no, @RequestParam int limit,
                                    @RequestParam(required = false) List<String> schedule,
                                    @RequestParam(required = false) List<String> position,
                                    @RequestParam(required = false) List<String> major,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String provinceName) {
        JobFilterDTO jobFilterDTO = new JobFilterDTO(name, position, schedule, major, provinceName);
        return ResponseEntity
                .ok(jobService.filterJob(jobFilterDTO, no, limit));
    }

// 	@GetMapping("statistics/newJob")
// 	public ResponseEntity<?> getNewStatis() {
// 		return ResponseEntity.ok(this.jobService.getNewStatistics());
// 	}

// 	@GetMapping("statistics/countAll")
// 	public ResponseEntity<?> getCountAll() {
// 		return ResponseEntity.ok(this.jobService.count());
// 	}

// 	@GetMapping("/count")
// 	public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
// 		return ResponseEntity.ok(this.jobService.countByCreatedDate(from, to));
// 	}

// 	@GetMapping("statistics/status")
// 	public ResponseEntity<?> getStatusStatistics() {
// 		return ResponseEntity.ok(this.jobService.getStatusStatistics());
// 	}

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody JobCreationDTO jobCreationDTO) {
        return new ResponseEntity<>(this.jobService.create(jobCreationDTO), HttpStatus.CREATED);
    }

 	 @SecurityRequirement(name = "Bearer Authentication")
 	 @PreAuthorize("hasAuthority('Role_HR')")
 	 @PutMapping("/{id}")
 	 public ResponseEntity<?> update(@PathVariable long id, @RequestBody JobDTO jobDTO) {
 	 	return ResponseEntity.ok(this.jobService.update(id, jobDTO));
 	 }

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAuthority('Role_HR')")
	@PutMapping("/replicate/{id}")
	public ResponseEntity<?> replicate(@PathVariable long id, @RequestBody JobDTO jobDTO) {
		return ResponseEntity.ok(this.jobService.replicate(id, jobDTO));
	}

// 	@DeleteMapping("/{id}")
// 	public ResponseEntity<?> deleteById(@PathVariable int id) {
// 		this.jobService.deleteById(id);
// 		return ResponseEntity.ok("DELETED");
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/active/hr/company/{id}")
// 	public ResponseEntity<?> findAllActiveByCompanyIdShowForHr(@PathVariable("id") int companyId,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllActiveByCompanyIdShowForHr(companyId, no, limit));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/disable/hr/company/{id}")
// 	public ResponseEntity<?> findAllDisableByCompanyIdShowForHr(@PathVariable("id") int companyId,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllDisableByCompanyIdShowForHr(companyId, no, limit));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/hr/company/{id}")
// 	public ResponseEntity<?> findAllByCompanyIdShowForHr(@PathVariable("id") int companyId,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity.ok(this.jobService.findAllByCompanyIdShowForHr(companyId, no, limit));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/active/hr/company/{id}/count")
// 	public ResponseEntity<?> countAllActiveByCompanyId(@PathVariable("id") int companyId) {
// 		return ResponseEntity.ok(this.jobService.countAllActiveByCompanyIdShowForHr(companyId));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/disable/hr/company/{id}/count")
// 	public ResponseEntity<?> countAllDisableByCompanyId(@PathVariable("id") int companyId) {
// 		return ResponseEntity.ok(this.jobService.countAllDisableByCompanyIdShowForHr(companyId));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@GetMapping("/hr/company/{id}/count")
// 	public ResponseEntity<?> countAllByCompanyId(@PathVariable("id") int companyId) {
// 		return ResponseEntity.ok(this.jobService.countAllByCompanyId(companyId));
// 	}

// 	@SecurityRequirement(name = "Bearer Authentication")
// 	@PreAuthorize("hasAuthority('Role_HR')")
// 	@PostMapping("/hr/company/{id}/filter")
// 	public ResponseEntity<?> filterPostedOfCompanyShowForHr(@PathVariable("id") int companyId,
// 			@RequestBody PostedJobOfCompanyFilterByHrDTO filterInfo,
// 			@RequestParam(defaultValue = PageDefault.NO) int no,
// 			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
// 		return ResponseEntity
// 				.ok(this.jobService.filterAllPostedJobOfCompanyShowForHr(companyId, filterInfo, no, limit));
// 	}

// 	// @SecurityRequirement(name = "Bearer Authentication")
// 	// @PreAuthorize("hasAuthority('Role_HR')")
// 	// @PutMapping("/status/{id}")
// 	// public ResponseEntity<?> changeStatus(@PathVariable int id, @RequestBody JobDTO jobDTO) {
// 	// 	return ResponseEntity.ok(this.jobService.changeStatus(id, jobDTO));
// 	// }

// 	// Get job by company for Candidate and Guest
// 	@GetMapping("/active/company/{id}")
// 	public ResponseEntity<?> findAllActiveByCompanyId(@PathVariable("id") int companyId) {
// 		return ResponseEntity.ok(this.jobService.findAllActiveByCompanyId(companyId));
// 	}

}
