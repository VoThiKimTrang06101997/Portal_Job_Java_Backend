package com.r2s.findInternship.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.service.CandidateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE_STATISTICS)
public class CandidateStatisticsController {
    @Autowired
    private CandidateService candidateService;

// 	@GetMapping("/major")
// 	public ResponseEntity<?> getMajorStatistics() {
// 		return ResponseEntity.ok(this.candidateService.getMajorStatistics());
// 	}
}
