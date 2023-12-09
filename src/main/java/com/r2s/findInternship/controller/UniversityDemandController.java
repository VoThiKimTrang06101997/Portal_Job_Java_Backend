package com.r2s.findInternship.controller;

import java.util.List;

import com.r2s.findInternship.service.impl.InternshipProgrammeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.constant.PageDefault;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiURL.INTERNSHIP_PROGRAMME)
public class UniversityDemandController {
    @Autowired
    private InternshipProgrammeServiceImpl internshipProgrammeService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/active")
    public ResponseEntity<?> findAllActive(@RequestParam(defaultValue = PageDefault.NO) int no,
                                           @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.internshipProgrammeService.findAllActive(no, limit));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAuthority('Role_HR')")
    @GetMapping("/active/university/{id}")
    public ResponseEntity<?> findAllActiveByUniversityId(@PathVariable("id") int companyId,
                                                         @RequestParam(defaultValue = PageDefault.NO) int no,
                                                         @RequestParam(defaultValue = PageDefault.LIMIT) int limit) {
        return ResponseEntity.ok(this.internshipProgrammeService.findAllActiveByUniversityId(companyId, no, limit));
    }

//    @SecurityRequirement(name = "Bearer Authentication")
//    @PreAuthorize("hasAuthority('Role_HR')")
//    @GetMapping("/filter")
//    public ResponseEntity<?> filterByKey(
//            @RequestParam(defaultValue = PageDefault.NO) int no,
//            @RequestParam(defaultValue = PageDefault.LIMIT) int limit,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) Integer universityId,
//            @RequestParam(required = false) List<String> universityTypeIds,
//            @RequestParam(required = false) List<String> majorIds,
//            @RequestParam(required = false) String order) {
//
//        DemandFilterDTO demandFilterDTO = new DemandFilterDTO(name, universityTypeIds, majorIds, universityId, order);
//        return ResponseEntity.ok(this.universityDemandService.filter(demandFilterDTO, no, limit));
//
//    }


}
