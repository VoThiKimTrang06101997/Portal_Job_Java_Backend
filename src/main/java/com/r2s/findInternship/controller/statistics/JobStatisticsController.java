package com.r2s.findInternship.controller.statistics;

 import java.time.LocalDateTime;

 import io.swagger.v3.oas.annotations.security.SecurityRequirement;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

 import com.r2s.findInternship.constant.ApiURL;
 import com.r2s.findInternship.service.JobService;

 @CrossOrigin(origins = "*", maxAge = 3600)
 @RestController
 @RequestMapping(ApiURL.JOB_STATISTIC)
 @SecurityRequirement(name = "Bearer Authentication")
 @PreAuthorize(value = "hasRole('Admin')")
 public class JobStatisticsController {
 	@Autowired
 	private JobService jobService;

 	@GetMapping("/new")
 	public ResponseEntity<?> getNewStatistics() {
 		return ResponseEntity.ok(this.jobService.getNewStatistics());
 	}

 	@GetMapping("/count")
     public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
         return ResponseEntity.ok(this.jobService.countByCreatedDate(from, to));
     }

// 	@GetMapping("/status")
// 	public ResponseEntity<?> getStatusStatistics() {
// 		return ResponseEntity.ok(this.jobService.getStatusStatistics());
// 	}

// 	@GetMapping("/major")
// 	public ResponseEntity<?> getMajorStatistics() {
// 		return ResponseEntity.ok(this.jobService.getMajorStatistics());
// 	}
//
// 	@GetMapping("/job-position")
// 	public ResponseEntity<?> getPositionStatistics() {
// 		return ResponseEntity.ok(this.jobService.getPositionStatistics());
// 	}

 }
