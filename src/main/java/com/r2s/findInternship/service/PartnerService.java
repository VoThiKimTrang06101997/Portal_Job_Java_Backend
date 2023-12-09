package com.r2s.findInternship.service;

import java.util.List;

import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.partner.PartnerCreationDTO;
import com.r2s.findInternship.data.dto.partner.PartnerDTO;
import com.r2s.findInternship.data.dto.show.UniversityPartnerDTOShow;
import com.r2s.findInternship.data.entity.Partner;

public interface PartnerService {

    long count();

    List<PartnerDTO> findAll();

    PaginationDTO findAll(int no, int limit);

    PaginationDTO findAllByUniversityId(int universityId, int no, int limit);

    PartnerDTO create(PartnerDTO partnerDTO);

    PartnerDTO update(PartnerDTO partnerDTO, int id);

    List<UniversityPartnerDTOShow> findByUniversityId(int universityId);

    Partner findByUserId(long userId);

    PartnerDTO findById(Integer id);

    PartnerDTO updateUser(PartnerCreationDTO partnerCreationDTO);

    PartnerCreationDTO readJson(String value);

}
