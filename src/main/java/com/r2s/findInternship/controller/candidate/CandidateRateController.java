package com.r2s.findInternship.controller.candidate;

 import com.r2s.findInternship.constant.ApiURL;
 import io.swagger.v3.oas.annotations.security.SecurityRequirement;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.DeleteMapping;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.PutMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

 import com.r2s.findInternship.data.dto.RateDTO;
 import com.r2s.findInternship.service.RateService;

 @RestController
 @RequestMapping(ApiURL.CANDIDATE_RATE)
 @SecurityRequirement(name = "Bearer Authentication")
 @PreAuthorize("isAuthenticated()")
 public class CandidateRateController {
    // @Autowired
     private RateService rateService;

     @GetMapping("/{id}")
     public ResponseEntity<?> findById(@PathVariable  int id) {
         return ResponseEntity.ok(this.rateService.findById(id));
     }

     @GetMapping("?companyId={id}&&username={username}")
     public ResponseEntity<?> findByCompanyIdAndUsername(@PathVariable("id") int companyId,
             @PathVariable  String username) {
         return ResponseEntity.ok(this.rateService.findByCompanyIdAndUsername(companyId, username));
     }

     @GetMapping("")
     public ResponseEntity<?> findAll() {
         return ResponseEntity.ok(this.rateService.findAll());
     }

     @GetMapping("/active")
     public ResponseEntity<?> findAllActive() {
         return ResponseEntity.ok(this.rateService.findAllActive());
     }

     @GetMapping("/company/{id}")
     public ResponseEntity<?> findAllByCompanyId(@PathVariable("id") int companyId,
             @RequestParam  int no, @RequestParam  int limit) {
         return ResponseEntity.ok(this.rateService.findAllByCompanyId(companyId, no, limit));
     }

     @GetMapping("/company/{id}/user/{username}/active")
     public ResponseEntity<?> findByCompanyIdAndUsernameActive(@PathVariable("id") int companyId,
             @PathVariable("username") String username) {
         return ResponseEntity.ok(this.rateService.findActiveByUsernameAndCompanyId(companyId, username));
     }

     @PostMapping("")
     public ResponseEntity<?> create(@RequestBody RateDTO rateDTO) {
         return new ResponseEntity<RateDTO>(this.rateService.create(rateDTO), HttpStatus.CREATED);
     }

     @PutMapping("/{id}")
     public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody RateDTO rateDTO) {
         return ResponseEntity.ok(this.rateService.update(id, rateDTO));
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
         return ResponseEntity.ok(this.rateService.deleteById(id));
     }

 }
