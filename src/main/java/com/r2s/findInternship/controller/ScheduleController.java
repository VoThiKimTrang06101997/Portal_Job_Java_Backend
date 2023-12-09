package com.r2s.findInternship.controller;

import com.r2s.findInternship.constant.ApiURL;
import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.service.PositionService;
import com.r2s.findInternship.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURL.SCHEDULE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.scheduleService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(this.scheduleService.create(scheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.scheduleService.deleteById(id));
    }
}
