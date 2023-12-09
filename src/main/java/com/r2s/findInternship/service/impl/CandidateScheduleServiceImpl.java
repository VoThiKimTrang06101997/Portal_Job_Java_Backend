package com.r2s.findInternship.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.ScheduleDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.entity.CandidateSchedule;
import com.r2s.findInternship.data.entity.Schedule;
import com.r2s.findInternship.data.repository.CandidateScheduleRepository;
import com.r2s.findInternship.service.CandidateScheduleService;

@Service
public class CandidateScheduleServiceImpl implements CandidateScheduleService{
    @Autowired
    private CandidateScheduleRepository candidateScheduleRepository;

	@Override
	public boolean update(long candidateId, List<ScheduleDTO> scheduleDTOs) {
		Queue<CandidateSchedule> oldCandidateSchedules = new LinkedList<>();
        oldCandidateSchedules.addAll(candidateScheduleRepository.findAllByCandidate_Id(candidateId));

        for (ScheduleDTO newScheduleDTO : scheduleDTOs) {
            if (oldCandidateSchedules.isEmpty()) {
                CandidateSchedule newCandidateSchedule = new CandidateSchedule();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);
                Schedule schedule = new Schedule();
                schedule.setId(newScheduleDTO.getId());

                newCandidateSchedule.setCandidate(candidate);
                newCandidateSchedule.setSchedule(schedule);
                candidateScheduleRepository.save(newCandidateSchedule);
            }else{
                CandidateSchedule candidateSchedule = oldCandidateSchedules.poll();
                Schedule newSchedule = new Schedule();
                newSchedule.setId(newScheduleDTO.getId());
                candidateSchedule.setSchedule(newSchedule);
                candidateScheduleRepository.save(candidateSchedule);
            }
        }
        while (!oldCandidateSchedules.isEmpty()) {
            candidateScheduleRepository.delete(oldCandidateSchedules.poll());
        }
        
        return true;
	}
    
}
