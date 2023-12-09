package com.r2s.findInternship.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.data.dto.StatusDTO;
import com.r2s.findInternship.data.entity.Status;
import com.r2s.findInternship.data.mapper.StatusMapper;
import com.r2s.findInternship.data.repository.StatusRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private StatusMapper statusMapper;

	@Override
	public StatusDTO findById(int id) {
		return statusMapper.toDTO(
				statusRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "id", String.valueOf(id))));
	}

	@Override
	public List<StatusDTO> findAll() {
		return statusRepository.findAll().stream().map(r -> statusMapper.toDTO(r)).collect(Collectors.toList());
	}

	@Override
	public Status findByName(Estatus e) {
		Status status = null;
		switch (e) {
			case Active:
				status = this.statusRepository.findById(1)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;

			case Delete:
				status = this.statusRepository.findById(2)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;

			case Lock:
				status = this.statusRepository.findById(3)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;

			case Disable:
				status = this.statusRepository.findById(4)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;

			case Not_Active:
				status = this.statusRepository.findById(5)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;

			default:
				status = this.statusRepository.findById(4)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", "Not Available"));
				break;
		}
		return status;
	}

	@Override
	public StatusDTO findByName(String name) {
		return statusMapper.toDTO(
				statusRepository.findByName(name)
						.orElseThrow(() -> new ResourceNotFoundException("Status", "name", name)));

	}

	@Override
	public Status findByNameEntity(String name){
		return statusRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Status", "name", name));
	}



	@PostConstruct
	public void init() {
		List<Status> statusToSave = new ArrayList<>();
		for (Estatus eStatus : Estatus.values()) {
			if (!statusRepository.existsByName(eStatus.toString())) {
				Status status = new Status();
				status.setName(eStatus.toString());
				statusToSave.add(status);
			}
		}
		if (!statusToSave.isEmpty()) {
			statusRepository.saveAll(statusToSave);
		}
	}
}
