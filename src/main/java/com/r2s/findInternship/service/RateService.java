package com.r2s.findInternship.service;

import java.util.List;


import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.RateDTO;

public interface RateService {
    // USE FOR TEST
    List<RateDTO> findAll();

    List<RateDTO> findAllActive();

    RateDTO findById(int id);

    RateDTO findByCompanyIdAndUsername(int companyId, String username);

    // USE FOR FLUTTER AND WEB
    RateDTO findActiveByUsernameAndCompanyId(int companyId, String username);

    PaginationDTO findAllByCompanyId(int companyId, int no, int limit);

    RateDTO create(RateDTO rateDTO);

    RateDTO update(int id, RateDTO rateDTO);

    MessageResponse deleteById(int id);

    MessageResponse recoverById(int id);
    
}
