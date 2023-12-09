package com.r2s.findInternship.controller.candidate;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.data.dto.CandidateApplicationDTO;
import com.r2s.findInternship.service.CandidateApplicationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(ApiURL.CANDIDATE_APPLICATION)
public class CandidateApplicationController {
	@Autowired
	private CandidateApplicationService candidateApplicationService;

	@Autowired
	MessageSource messageSource;

	@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/candidate/{id}")
	public ResponseEntity<?> findAllByCandidateId(@PathVariable int id,
			@RequestParam(defaultValue = PageDefault.NO) int no,
			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
		return ResponseEntity.ok(this.candidateApplicationService.findAllByCandidateId(id, no, limit));
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAuthority('Role_HR')")
	@GetMapping("/job/{id}")
	public ResponseEntity<?> findAllByJobId(@PathVariable int id, @RequestParam(defaultValue = PageDefault.NO) int no,
			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
		return ResponseEntity.ok(this.candidateApplicationService.findAllByJobId(id, no, limit));
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAuthority('Role_Candidate')")
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> create(@RequestPart("candidateApplication") String candidateApplication,
			@RequestPart(name = "fileCV", required = false) MultipartFile fileCV) {

		CandidateApplicationDTO candidateApplicationDTO = candidateApplicationService.readJson(candidateApplication,
				fileCV);

		// create Date
		// applyListDTO.setCreatedDate(LocalDateTime.now());
		// MailResponse mailResponse = new MailResponse();

		// mailResponse.setTo(job.getHrDTO().getUserCreationDTO().getEmail());
		// mailResponse.setTypeMail(EMailType.ApplyJob);
		// mailResponse.setCv(applyListDTO.getCV());
		// mailResponse.setApply();
		// tam thoi khong gui mail
		// mailService.send(mailResponse);
		return new ResponseEntity<>(this.candidateApplicationService.create(candidateApplicationDTO),
				HttpStatus.CREATED);
	}

}
