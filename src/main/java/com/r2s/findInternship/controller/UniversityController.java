package com.r2s.findInternship.controller;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.UniversityDTO;
import com.r2s.findInternship.data.entity.University;
import com.r2s.findInternship.service.MajorService;
import com.r2s.findInternship.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.UNIVERSITY)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.universityService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UniversityDTO universityDTO) {
        return ResponseEntity.ok(this.universityService.create(universityDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.universityService.deleteById(id));
    }
}
