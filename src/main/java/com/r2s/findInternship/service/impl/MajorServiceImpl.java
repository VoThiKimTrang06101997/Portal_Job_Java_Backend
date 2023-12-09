package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.r2s.findInternship.common.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.r2s.findInternship.service.MajorService;
import com.r2s.findInternship.data.dto.MajorDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.entity.Major;
import com.r2s.findInternship.data.mapper.MajorMapper;
import com.r2s.findInternship.data.repository.MajorRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.exception.ResourceNotFoundException;

@Service
public class MajorServiceImpl implements MajorService {
	@Autowired
	private MajorRepository majorRepository;
	@Autowired
	private MajorMapper majorMapper;
    @Override
    public MajorDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public List<MajorDTO> findAll() {
        return this.majorRepository.findAll().stream().map(item -> this.majorMapper.toDTO(item))
                .collect(Collectors.toList());
    }
    @Override
    public MessageResponse create(MajorDTO majorDTO) {
        Major major = majorMapper.toEntity(majorDTO);
        if (majorRepository.existsByName(major.getName()))
             		throw new InternalServerErrorException(
             				String.format("Exists major named %s", major.getName()));
        majorRepository.save(major);
        return new MessageResponse(200, null, null);
    }


    @Override
    public MessageResponse deleteById(Integer id) {
        this.majorRepository.delete(this.majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id",
                        String.valueOf(id))));
        return new MessageResponse(200, null, null);
    }

	// @Override
	// public MajorDTO create(MajorDTO majorDTO) {
	// 	Major major = this.majorMapper.toEntity(majorDTO);
	// 	major.setCreatedDate(LocalDateTime.now());
	// 	if (this.existsByName(major.getName()))
	// 		throw new InternalServerErrorException(
	// 				String.format("Exists major named %s", major.getName()));
	// 	major = majorRepository.save(major);

	// 	return this.majorMapper.toDTO(major);
	// }

	// @Override
	// public PaginationDTO findAll(int no, int limit) {
	// 	Page<MajorDTO> page = majorRepository.findAll(PageRequest.of(no, limit)).map(m -> majorMapper.toDTO(m));
	// 	return new PaginationDTO(page.getContent(), page.isFirst(), page.isLast(), page.getTotalPages(),
	// 			page.getTotalElements(), page.getSize(), page.getNumber());
	// }

	// public Page<Major> findAll(Pageable pageable) {
	// 	return majorRepository.findAll(pageable);
	// }

	// public List<Major> findAll(Sort sort) {
	// 	return majorRepository.findAll(sort);
	// }

	// public List<Major> findAllById(Iterable<Integer> ids) {
	// 	return majorRepository.findAllById(ids);
	// }

	// @Override
	// public MajorDTO findById(Integer id) {
	// 	return this.majorMapper.toDTO(this.getById(id));
	// }

	// public boolean existsById(Integer id) {
	// 	return majorRepository.existsById(id);
	// }

	// @Override
	// public void deleteById(Integer id) {
	// 	if (this.getById(id) != null)
	// 		this.majorRepository.deleteById(id);
	// }

	// public <S extends Major> List<S> findAll(Example<S> example) {
	// 	return majorRepository.findAll(example);
	// }

	// @Override
	// public MajorDTO update(int id, MajorDTO majorDTO) {
	// 	Major major = this.getById(id);
	// 	major.setName(majorDTO.getName());
	// 	if (this.existsByName(major.getName()))
	// 		throw new InternalServerErrorException(
	// 				String.format("Exists major named %s", major.getName()));
	// 	return this.majorMapper.toDTO(this.majorRepository.save(major));
	// }

	// @Override
	// public Major getById(Integer id) {
	// 	return majorRepository.findById(id)
	// 			.orElseThrow(() -> new ResourceNotFoundException("Major", "id", String.valueOf(id)));
	// }

	// public boolean existsByName(String name) {
	// 	return majorRepository.existsByName(name);
	// }

}
