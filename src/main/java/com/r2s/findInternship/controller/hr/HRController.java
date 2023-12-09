package com.r2s.findInternship.controller.hr;

import com.r2s.findInternship.data.dto.candidate.CandidateCreationDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.data.dto.hr.HRCreationDTO;
import com.r2s.findInternship.data.dto.hr.HRProfileDTO;
import com.r2s.findInternship.service.HRService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.HR)
public class HRController {
    @Autowired
    private HRService hrService;

    @GetMapping("")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = PageDefault.NO) int no,
                                     @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(hrService.findAll(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(hrService.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") long userId) {
        return ResponseEntity.ok(hrService.findByUserId(userId));
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> create(@Valid @RequestPart HRCreationDTO hrCreationDTO,
                                    @RequestPart(name = "fileAvatar", required = false) MultipartFile fileAvatar) {
        return new ResponseEntity<>(hrService.create(hrCreationDTO, fileAvatar), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @PutMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateHRInfo(@Valid @RequestPart HRProfileDTO hrProfileDTO,
            @RequestPart(name = "fileAvatar", required = false) MultipartFile fileAvatar) {
        return ResponseEntity.ok(hrService.updateHRInfo(hrProfileDTO, fileAvatar));
    }
}
