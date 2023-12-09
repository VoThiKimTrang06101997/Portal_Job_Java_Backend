package com.r2s.findInternship.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.data.dto.PositionDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.entity.CandidatePosition;
import com.r2s.findInternship.data.entity.Position;
import com.r2s.findInternship.data.repository.CandidatePositionRepository;
import com.r2s.findInternship.service.CandidatePositionService;

@Service
public class CandidatePositionServiceImpl implements CandidatePositionService {
    @Autowired
    private CandidatePositionRepository candidatePositionRepository;

    @Override
	public boolean update(long candidateId, List<PositionDTO> positionDTOs) {
		Queue<CandidatePosition> oldCandidatePositions = new LinkedList<>();
        oldCandidatePositions.addAll(candidatePositionRepository.findAllByCandidate_Id(candidateId));

        for (PositionDTO newPositionDTO : positionDTOs) {
            if (oldCandidatePositions.isEmpty()) {
                CandidatePosition newCandidatePosition = new CandidatePosition();

                Candidate candidate = new Candidate();
                candidate.setId(candidateId);
                Position position = new Position();
                position.setId(newPositionDTO.getId());

                newCandidatePosition.setCandidate(candidate);
                newCandidatePosition.setPosition(position);
                candidatePositionRepository.save(newCandidatePosition);
            }else{
                CandidatePosition candidatePosition = oldCandidatePositions.poll();
                Position newPosition = new Position();
                newPosition.setId(newPositionDTO.getId());
                candidatePosition.setPosition(newPosition);
                candidatePositionRepository.save(candidatePosition);
            }
        }
        while (!oldCandidatePositions.isEmpty()) {
            candidatePositionRepository.delete(oldCandidatePositions.poll());
        }
        
        return true;
	}

}
