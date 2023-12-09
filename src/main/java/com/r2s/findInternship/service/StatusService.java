package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.data.dto.StatusDTO;
import com.r2s.findInternship.data.entity.Status;

public interface StatusService {

	StatusDTO findById(int id);

	List<StatusDTO> findAll();

	StatusDTO findByName(String name);

	Status findByName(Estatus e);

	Status findByNameEntity(String name);

}
