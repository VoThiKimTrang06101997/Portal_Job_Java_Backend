package com.r2s.findInternship.controller;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.POSITION)
@CrossOrigin(origins = "*", maxAge = 3600)
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.positionService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PositionDTO positionDTO) {
        return ResponseEntity.ok(this.positionService.create(positionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.positionService.deleteById(id));
    }
}
