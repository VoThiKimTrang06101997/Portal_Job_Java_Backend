package com.r2s.findInternship.controller;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.data.dto.JobCareDTO;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.service.MajorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiURL.MAJOR)
@CrossOrigin(origins = "*", maxAge = 3600)
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.majorService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody MajorDTO majorDTO) {
        return ResponseEntity.ok(this.majorService.create(majorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.majorService.deleteById(id));
    }
}
