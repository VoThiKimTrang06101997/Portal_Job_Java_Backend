package com.r2s.findInternship.controller.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.data.dto.CandidateFilterByHRDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateCreationDTO;
import com.r2s.findInternship.data.dto.candidate.CandidateProfileDTO;
import com.r2s.findInternship.service.CandidateService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.CANDIDATE)
public class CandidateController {
	@Autowired
	private CandidateService candidateService;
	@Autowired
	MessageSource messageSource;

	@GetMapping("/active")
	public ResponseEntity<?> activeAccountCandidate(@RequestParam(name = "activeToken") String token) {
		RedirectView redirectView = new RedirectView();
		try {
			this.candidateService.activeCandidate(token);
			String redirectUrl = "http://localhost:3000/auth/confirmActive?status=success&message="
					+ messageSource.getMessage("error.activeUserSuccessfull", null, null);

			redirectView.setUrl(redirectUrl);
			return ResponseEntity.ok(redirectView);
		} catch (Exception ex) {
			String redirectUrl = "http://localhost:3000/auth/confirmActive?status=failed&message=" + ex.getMessage();

			redirectView.setUrl(redirectUrl);
			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable long id) {
		return ResponseEntity.ok(candidateService.findByUserId(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return ResponseEntity.ok(candidateService.findById(id));
	}

	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@RequestBody CandidateCreationDTO candidateCreationDTO) {
		return new ResponseEntity<>(candidateService.create(candidateCreationDTO, null), HttpStatus.CREATED);
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize(value = "hasAuthority('Role_Candidate')")
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestPart(name = "candidateProfileDTO") String candidateProfileDTOJson,
			@RequestPart(name = "fileAvatar", required = false) MultipartFile fileAvatar,
			@RequestPart(name = "fileCV", required = false) MultipartFile fileCV) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CandidateProfileDTO candidateProfileDTO = objectMapper.readValue(
					candidateProfileDTOJson, CandidateProfileDTO.class);
			return ResponseEntity.ok(candidateService.update(id, candidateProfileDTO, fileAvatar, fileCV));
		} catch (JsonProcessingException e) {
			// Handle JSON processing exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Invalid JSON payload");
		}
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize(value = "hasAuthority('Role_Candidate')")
	@PutMapping("/{id}/searchable")
	public ResponseEntity<?> updateSearchable(@PathVariable long id) {
		return ResponseEntity.ok(candidateService.updateSearchable(id));
	}

	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize(value = "hasAuthority('Role_HR')")
	@PostMapping("/filter")
	public ResponseEntity<?> filterByHr(@RequestBody CandidateFilterByHRDTO candidateFilterByHRDTO,
			@RequestParam(defaultValue = PageDefault.NO) int no,
			@RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
		return ResponseEntity.ok(candidateService.filterByHR(candidateFilterByHRDTO, no, limit));
	}

}
