package com.r2s.findInternship.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.r2s.findInternship.common.DateTime;
import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;
import com.r2s.findInternship.data.dto.CompanyDTO;
import com.r2s.findInternship.service.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.COMPANY)
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<?> findAllActive(@RequestParam(defaultValue = PageDefault.NO) int no,
            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.companyService.findAllActive(no, limit));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findAllByNameLike(@PathVariable String name,
            @RequestParam(defaultValue = PageDefault.NO) int no,
            @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.companyService.findAllByNameLike(name, no, limit));
    }

    @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> create(@Valid @RequestPart CompanyDTO companyDTO,
    @RequestPart(name = "fileLogo", required = false) MultipartFile fileLogo) {
        return new ResponseEntity<>(companyService.create(companyDTO, fileLogo), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestPart CompanyDTO companyDTO,
            @RequestPart(name = "fileLogo", required = false) MultipartFile fileLogo) {
        return ResponseEntity.ok(this.companyService.update(id, companyDTO, fileLogo));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(this.companyService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        this.companyService.deleteById(id);
        return ResponseEntity.ok("DELETED");
    }

    // @SecurityRequirement(name = "Bearer Authentication")
    // @PreAuthorize("hasRole('Admin')")
    // @GetMapping("/count")
    // public ResponseEntity<?> countByCreatedDate(@RequestParam LocalDateTime from,
    // @RequestParam LocalDateTime to) {
    // return ResponseEntity.ok(this.companyService.countByCreatedDate(from, to));
    // }

    // @SecurityRequirement(name = "Bearer Authentication")
    // @PreAuthorize("hasRole('Admin')")
    // @GetMapping("/statistics/new")
    // public ResponseEntity<?> getNewStatistics() {
    // return ResponseEntity.ok(this.companyService.getNewStatistics());
    // }

    // @SecurityRequirement(name = "Bearer Authentication")
    // @PreAuthorize("hasRole('Admin')")
    // @GetMapping("/statistics/status")
    // public ResponseEntity<?> getStatusStatistics() {
    // return ResponseEntity.ok(this.companyService.getStatusStatistics());
    // }

}
