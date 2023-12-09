package com.r2s.findInternship.controller.partner;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.r2s.findInternship.constant.ApiURL;
// import com.r2s.findInternship.data.dto.PartnerCreationDTO;
// import com.r2s.findInternship.data.dto.PartnerDTO;
// import com.r2s.findInternship.data.dto.UniversityCreationDTO;
// import com.r2s.findInternship.data.entity.Partner;
// import com.r2s.findInternship.data.mapper.PartnerMapper;
// import com.r2s.findInternship.service.PartnerService;
// import com.r2s.findInternship.service.UniversityService;

// @CrossOrigin(maxAge = 3600, origins = "*")
// @RestController
// @RequestMapping(ApiURL.PARTNER)

// public class PartnerController {
// 	@Autowired
// 	private UniversityService universityService;
// 	@Autowired
// 	private PartnerService partnerService;
// 	@Autowired
// 	private PartnerMapper partnerMapper;

// 	// ADD WITH USER
// 	@PostMapping(value = "/university/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
// 			MediaType.MULTIPART_FORM_DATA_VALUE })
// 	public ResponseEntity<?> createFirst(@RequestPart("university") String universityJson,
// 			@RequestPart("partner") String partnerJson,
// 			@RequestPart(name = "avatar", required = false) MultipartFile avatarUser,
// 			@RequestPart(name = "logo", required = false) MultipartFile avatarUniversity) {
// 		// Read LocalDateTime from json
// 		UniversityCreationDTO universityDTO = this.universityService.readJson(universityJson, partnerJson,
// 				avatarUser, avatarUniversity);
// 		return new ResponseEntity<>(this.universityService.createFirst(universityDTO), HttpStatus.CREATED);
// 	}

// 	@GetMapping("/{id}")
// 	public ResponseEntity<?> findById(@PathVariable int id) {
// 		PartnerDTO entity = this.partnerService.findById(id);
// 		return ResponseEntity.ok(entity);
// 	}

// 	@GetMapping("user/{id}")
// 	public ResponseEntity<?> findByUserId(@PathVariable int id) {
// 		Partner entity = this.partnerService.findByUserId(id);
// 		PartnerDTO dto = partnerMapper.toDTO(entity);
// 		return ResponseEntity.ok(dto);
// 	}

// 	@PutMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
// 	public ResponseEntity<?> update(@RequestPart("partner") String partnerCreateDTOJson) {
// 		// Transfer from String ( JSON ) to PartnerDTO
// 		PartnerCreationDTO partner = partnerService.readJson(partnerCreateDTOJson);
// 		return ResponseEntity.ok(partnerService.updateUser(partner));
// 	}

// }
