package com.r2s.findInternship.service.impl;

import com.r2s.findInternship.common.MessageResponse;
import com.r2s.findInternship.common.enumeration.Estatus;
import com.r2s.findInternship.data.dto.CompanyDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.RateDTO;
import com.r2s.findInternship.data.entity.CompanyRate;
import com.r2s.findInternship.data.mapper.RateMapper;
import com.r2s.findInternship.data.mapper.UserMapper;
import com.r2s.findInternship.exception.CustomException;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.CompanyService;
import com.r2s.findInternship.service.RateService;
import com.r2s.findInternship.service.StatusService;
import com.r2s.findInternship.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");
    @Override
    public List<RateDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    @Override
    public List<RateDTO> findAllActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllActive'");
    }
    @Override
    public RateDTO findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public RateDTO findByCompanyIdAndUsername(int companyId, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCompanyIdAndUsername'");
    }
    @Override
    public RateDTO findActiveByUsernameAndCompanyId(int companyId, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findActiveByUsernameAndCompanyId'");
    }
    @Override
    public PaginationDTO findAllByCompanyId(int companyId, int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByCompanyId'");
    }
    @Override
    public RateDTO create(RateDTO rateDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
    @Override
    public RateDTO update(int id, RateDTO rateDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    @Override
    public MessageResponse deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    @Override
    public MessageResponse recoverById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recoverById'");
    }

    // @Override
    // public List<RateDTO> findAll() {
    //     return this.rateRepository.findAll().stream().map(item -> this.rateMapper.toDTO(item))
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public List<RateDTO> findAllActive() {
    //     return this.rateRepository.findAllActive().stream().map(item -> this.rateMapper.toDTO(item))
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public RateDTO findById(int id) {
    //     return this.rateMapper.toDTO(this.rateRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Rate", "id", String.valueOf(id))));
    // }

    // @Override
    // public RateDTO findByCompanyIdAndUsername(int companyId, String username) {
    //     return rateMapper.toDTO(this.rateRepository.findByUsernameAndCompanyId(username, companyId)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                     "Rate", "(username, companyId)", "(" + username + ", " + companyId + ")")));
    // }

    // @Override
    // public RateDTO findActiveByUsernameAndCompanyId(int companyId, String username) {
    //     return this.rateMapper.toDTO(rateRepository.findActiveByUsernameAndCompanyId(username, companyId)
    //             .orElseThrow(() -> new ResourceNotFoundException(
    //                     "Rate", "(username, companyId)", "(" + username + ", " + companyId + ")")));
    // }

    // @Override
    // public PaginationDTO findAllByCompanyId(int companyId, int no, int limit) {
    //     Pageable page = PageRequest.of(no, limit);
    //     List<Object> rates = this.rateRepository.findAllActiveByCompanyId(companyId, page)
    //             .toList().stream().map(item -> rateMapper.toDTO(item)).collect(Collectors.toList());
    //     Page<CompanyRate> pageRate = this.rateRepository.findAllActiveByCompanyId(companyId, page);
    //     return new PaginationDTO(rates, pageRate.isFirst(), pageRate.isLast(),
    //             pageRate.getTotalPages(), pageRate.getTotalElements(), pageRate.getSize(), pageRate.getNumber());
    // }

    // @Override
    // public RateDTO create(RateDTO rateDTO) {
    //     String action = "created rate";
    //     CompanyDTO companyDTO = this.companyService.findById(rateDTO.getCompanyDTO().getId());
    //     if (companyDTO.getStatusDTO().getName().equals(Estatus.Disable.name()))
    //         throw new CustomException(this.messageSource.getMessage("error.companyStatusDisable", null, null));
    //     UserDTO userDTO = userMapper.toDTO(this.userService.findByUsername(rateDTO.getUserDTO().getUsername()));
    //     Optional<CompanyRate> optionalRate = this.rateRepository.findByUsernameAndCompanyId(
    //             rateDTO.getUserDTO().getUsername(), rateDTO.getCompanyDTO().getId());
    //     CompanyRate rate;
    //     int rateId = 0;
    //     // if present record in database
    //     if (optionalRate.isPresent()) {
    //         rate = optionalRate.get();
    //         // if rate is active then throw an exception
    //         if (rate.getStatus().getName().equals(Estatus.Active.name()))
    //             throw new CustomException(this.messageSource.getMessage("error.commentOnce", null, null));
    //         action = "set rate active";
    //         rateId = rate.getId();
    //     }

    //     rateDTO.setCompanyDTO(companyDTO);
    //     rateDTO.setUserDTO(userDTO);
    //     rateDTO.setTitle(
    //             (rateDTO.getTitle() == null || rateDTO.getTitle().isEmpty()) ? "Đóng góp ý kiến" : rateDTO.getTitle());
    //     rateDTO.setCreatedDate(LocalDateTime.now());
    //     rate = rateMapper.toEntity(rateDTO);
    //     rate.setId(rateId);
    //     rate.setStatus(this.statusService.findByName(Estatus.Active));
    //     // save rate
    //     rate = this.rateRepository.save(rate);
    //     // write log
    //     LOGGER.info(String.format("Candidate %s %s with company %s successfully", rateDTO.getUserDTO().getUsername(),
    //             action, rateDTO.getCompanyDTO().getName()));
    //     return this.rateMapper.toDTO(rate);
    // }

    // @Override
    // public RateDTO update(int id, RateDTO rateDTO) {
    //     CompanyRate foundRate = this.rateRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Rate", "id", String.valueOf(id)));
    //     if (foundRate.getStatus().getName().equals(Estatus.Disable.name()))
    //         throw new CustomException(this.messageSource.getMessage("error.updateWhenActive", null, null));
    //     CompanyRate newRate = this.rateMapper.toEntityUpdate(foundRate, rateDTO);
    //     newRate = rateRepository.save(newRate);
    //     LOGGER.info(String.format("Candidate %s updated the rate with company %s successfully",
    //             foundRate.getUser().getUsername(), foundRate.getCompany().getName()));
    //     return this.rateMapper.toDTO(newRate);
    // }

    // @Override
    // public MessageResponse deleteById(int id) {
    //     CompanyRate rate = this.rateRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Rate", "id", String.valueOf(id)));
    //     rate.setStatus(this.statusService.findByName(Estatus.Disable));
    //     this.rateRepository.save(rate);
    //     LOGGER.info(String.format("Candidate %s removed the rate with company %s successfully",
    //             rate.getUser().getUsername(), rate.getCompany().getName()));
    //     return new MessageResponse(200, String.format(this.messageSource.getMessage("info.deleteRate", null, null),
    //             rate.getUser().getUsername(), rate.getCompany().getName()), null);
    // }

    // @Override
    // public MessageResponse recoverById(int id) {
    //     CompanyRate rate = this.rateRepository.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Rate", "id", String.valueOf(id)));
    //     rate.setStatus(this.statusService.findByName(Estatus.Active));
    //     this.rateRepository.save(rate);
    //     LOGGER.info(String.format("Candidate %s recover the rate with company %s successfully",
    //             rate.getUser().getUsername(), rate.getCompany().getName()));
    //     return new MessageResponse(200, String.format(this.messageSource.getMessage("info.recoverRate", null, null),
    //             rate.getUser().getUsername(), rate.getCompany().getName()), null);
    // }

}
