package com.r2s.findInternship.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.entity.CandidateMajor;
import com.r2s.findInternship.data.entity.Major;
import com.r2s.findInternship.data.repository.CandidateMajorRepository;
import com.r2s.findInternship.service.CandidateMajorService;

@Service
public class CandidateMajorServiceImpl implements CandidateMajorService{
    @Autowired
    private CandidateMajorRepository candidateMajorRepository;

	@Override
	public boolean update(long candidateId, List<MajorDTO> majorDTOs) {
		Queue<CandidateMajor> oldCandidateMajors = new LinkedList<>();
        oldCandidateMajors.addAll(candidateMajorRepository.findAllByCandidate_Id(candidateId));

        for (MajorDTO newMajorDTO : majorDTOs) {
            if (oldCandidateMajors.isEmpty()) {
                CandidateMajor newCandidateMajor = new CandidateMajor();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);
                Major major = new Major();
                major.setId(newMajorDTO.getId());

                newCandidateMajor.setCandidate(candidate);
                newCandidateMajor.setMajor(major);
                candidateMajorRepository.save(newCandidateMajor);
            }else{
                CandidateMajor candidateMajor = oldCandidateMajors.poll();
                Major newMajor = new Major();
                newMajor.setId(newMajorDTO.getId());
                candidateMajor.setMajor(newMajor);
                candidateMajorRepository.save(candidateMajor);
            }
        }
        while (!oldCandidateMajors.isEmpty()) {
            candidateMajorRepository.delete(oldCandidateMajors.poll());
        }
        
        return true;
	}
    
}
