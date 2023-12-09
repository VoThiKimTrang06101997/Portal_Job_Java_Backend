package com.r2s.findInternship.service.impl;

import java.time.LocalDateTime;
import javax.servlet.ServletContext;

import com.r2s.findInternship.data.dto.candidate.CandidateDTO;
import com.r2s.findInternship.data.entity.Candidate;
import com.r2s.findInternship.data.entity.User;
import com.r2s.findInternship.data.repository.UserRepository;
import com.r2s.findInternship.exception.ResourceNotFoundException;
import com.r2s.findInternship.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2s.findInternship.common.util.UpdateFile;
import com.r2s.findInternship.data.dto.CandidateApplicationDTO;
import com.r2s.findInternship.data.dto.JobDTO;
import com.r2s.findInternship.data.dto.PaginationDTO;
import com.r2s.findInternship.data.dto.show.ApplicationDTONotShowJob;
import com.r2s.findInternship.data.entity.CandidateApplication;
import com.r2s.findInternship.data.mapper.CandidateApplicationMapper;
import com.r2s.findInternship.data.repository.CandidateApplicationRepository;
import com.r2s.findInternship.exception.InternalServerErrorException;
import com.r2s.findInternship.service.CandidateApplicationService;
import com.r2s.findInternship.service.CandidateService;
import com.r2s.findInternship.service.JobService;

@Service
public class CandidateApplicationServiceImpl implements CandidateApplicationService {
    @Autowired
    private FileService fileService;
    @Autowired
    private CandidateApplicationRepository candidateApplicationRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateApplicationMapper candidateApplicationMapper;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UpdateFile updateFile;
    @Autowired
    ServletContext context;
    @Autowired
    private UserRepository userRepository;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");

    @Override
    public PaginationDTO findAll(int no, int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    //    @Override
//    public PaginationDTO findAllByCandidateId(long candidateId, int no, int limit) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'findAllByCandidateId'");
//    }
//    @Override
//    public PaginationDTO findAllByJobId(long jobId, int no, int limit) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'findAllByJobId'");
//    }
//    @Override
//    public PaginationDTO findAllByUsername(String username, int no, int limit) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'findAllByUsername'");
//    }

    @Override
    public CandidateApplicationDTO findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public CandidateApplication getById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

//    @Override
//    public boolean existsByJobIdAndCandidateId(long jobId, long candidateId) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'existsByJobIdAndCandidateId'");
//    }

    //    @Override
//    public CandidateApplicationDTO create(CandidateApplicationDTO candidateApplicationDTO) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'create'");
//    }
    @Override
    public CandidateApplicationDTO update(long id, CandidateApplicationDTO candidateApplicationDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean deleteById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
//    @Override
//    public CandidateApplicationDTO readJson(String value, MultipartFile fileLogo) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'readJson'");
//    }


	// @Override
	// public CandidateApplicationDTO findById(Integer id) {
	// 	return this.candidateApplicationMapper.toDTO(this.getById(id));
	// }

    @Override
    public PaginationDTO findAllByCandidateId(long candidateID, int no, int limit) {
        Page<CandidateApplicationDTO> applicationPages = this.candidateApplicationRepository
                .findAllByCandidateIdOrderByCreatedDateDesc(candidateID, PageRequest.of(no, limit))
                .map(a -> this.candidateApplicationMapper.toDTO(a));
        if(applicationPages.getTotalElements()==0)
            throw new ResourceNotFoundException("Candidate not found!");
        return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
                applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
                applicationPages.getNumber());
    }

    @Override
    public PaginationDTO findAllByJobId(long jobId, int no, int limit) {
        Page<ApplicationDTONotShowJob> applicationPages = this.candidateApplicationRepository
                .findAllByJobId(jobId, PageRequest.of(no, limit))
                .map(a -> this.candidateApplicationMapper.toDTONotShowJob(a));
        if(applicationPages.getTotalElements()==0)
            throw new ResourceNotFoundException("Job not found!");
        return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
                applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
                applicationPages.getNumber());
    }

	// @Override
	// public PaginationDTO findAllByUsername(String username, int no, int limit) {
	// 	Page<CandidateApplicationDTO> applicationPages = this.candidateApplicationRepository
	// 			.findAllByUsername(username, PageRequest.of(no, limit))
	// 			.map(a -> this.candidateApplicationMapper.toDTO(a));
	// 	return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
	// 			applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
	// 			applicationPages.getNumber());
	// }

	// @Override
	// public PaginationDTO findAll(int no, int limit) {
	// 	Page<CandidateApplicationDTO> applicationPages = this.candidateApplicationRepository
	// 			.findAll(PageRequest.of(no, limit)).map(a -> this.candidateApplicationMapper.toDTO(a));
	// 	return new PaginationDTO(applicationPages.getContent(), applicationPages.isFirst(), applicationPages.isLast(),
	// 			applicationPages.getTotalPages(), applicationPages.getTotalElements(), applicationPages.getSize(),
	// 			applicationPages.getNumber());
	// }

    @Override
    public CandidateApplicationDTO create(CandidateApplicationDTO candidateApplicationDTO) {
        //Get cadidate from current login user
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email).orElseThrow();
        CandidateDTO candidateDTO = candidateService.findByUserId(user.getId());

	 	// check apply
	 	if (this.existsByJobIdAndCandidateId(candidateApplicationDTO.getJobDTO().getId(),
	 			candidateDTO.getId())) {
	 		throw new InternalServerErrorException("");
	 	}

	 	// check job apply
	 	JobDTO jobDTO = jobService.findById(Math.toIntExact(candidateApplicationDTO.getJobDTO().getId()));
	 	candidateApplicationDTO.setJobDTO(jobDTO);
        if(!jobService.isAppliable(jobDTO))
            throw new InternalServerErrorException("");



        // set value candidate apply
	 	candidateApplicationDTO.setCandidateDTO(candidateDTO);
        candidateApplicationDTO.setEmail(candidateDTO.getUserDTO().getEmail());
        candidateApplicationDTO.setFullName(candidateDTO.getUserDTO().getLastName() + " " +candidateDTO.getUserDTO().getFirstName());
        candidateApplicationDTO.setPhone(candidateDTO.getUserDTO().getPhone());

        // map value to enetity to save
	 	CandidateApplication candidateApplication =  candidateApplicationMapper.toEntity(candidateApplicationDTO);
	 	candidateApplication.setCreatedDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
	 	candidateApplication.setLastModifiedDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        candidateApplication.setId(0);
	 	candidateApplication =	candidateApplicationRepository.save(candidateApplication);
        CandidateApplicationDTO applicationDTO = candidateApplicationMapper.toDTO(candidateApplication);
        applicationDTO.setJobDTO(jobDTO);
	 	return applicationDTO;
    }

    // // Hàm lấy tên file theo CV của candidate
    // public String getFileName(String pathFile) {
    // 	String fileName = new String();
    // 	int position = pathFile.lastIndexOf("/");
    // 	fileName = pathFile.substring(position);
    // 	return fileName;
    // }

    // @Override
    // public CandidateApplicationDTO update(int id, CandidateApplicationDTO candidateApplicationDTO) {
    // 	CandidateApplication entity = this.getById(id);
    // 	candidateApplicationDTO.setId(id);
    // 	CandidateDTO candidateDTO = candidateService.findById(candidateApplicationDTO.getCandidateDTO().getId());
    // 	candidateApplicationDTO.setCandidateDTO(candidateDTO);
    // 	JobDTO jobDTO = jobService.findById(candidateApplicationDTO.getJobDTO().getId());
    // 	candidateApplicationDTO.setJobDTO(jobDTO);
    // 	entity = candidateApplicationMapper.toEntity(candidateApplicationDTO);

    // 	try {
    // 	} catch (Exception e) {
    // 		throw new InternalServerErrorException(this.messageSource.getMessage("error.appliesSave", null, null));
    // 	}
    // 	candidateApplicationRepository.save(entity);
    // 	LOGGER.info(String.format("Application id: %s update successfully", candidateApplicationDTO.getId()));
    // 	return this.candidateApplicationMapper.toDTO(entity);

    // }

    // // Set CVApplication from profile
    // public CandidateApplicationDTO setCVApplicationFromProfile(CandidateApplicationDTO candidateApplicationDTO) {

    // 	// Path of CVApplication
    // 	String folderCVApplication = "/CVApplication";
    // 	String fileCVApplicationPath = context.getRealPath("/") + folderCVApplication;
    // 	String folderParent = String.valueOf(LocalDate.now().getYear());
    // 	// CHECK file is exists. IF IT'S NOT EXISTS, I CREATE FOLDER
    // 	File file = new File(fileCVApplicationPath + String.format("/%s/", folderParent));
    // 	if (!file.exists()) {
    // 		file.mkdir();
    // 	}
    // 	// Path save CVApplication by this year
    // 	String srcPath = fileCVApplicationPath + String.format("/%s/", folderParent);
    // 	String urlStr = "http://localhost:8085" + candidateApplicationDTO.getCandidateDTO().getCV();
    // 	// Name fileCV of Candidate
    // 	String fileName = getFileName(candidateApplicationDTO.getCandidateDTO().getCV());
    // 	// Check name file
    // 	Path myPath = Paths.get(srcPath + fileName);
    // 	int i = 1;
    // 	String applicationCV = null;
    // 	String fileNameTemp = null;
    // 	try {
    // 		// RENAME FILE WITH STRING
    // 		while (Files.exists(myPath)) {
    // 			applicationCV = fileName.substring(0, fileName.lastIndexOf("."));
    // 			applicationCV = applicationCV + "(" + i + ")";
    // 			applicationCV += fileName.substring(fileName.lastIndexOf("."));
    // 			fileNameTemp = applicationCV;
    // 			myPath = Paths.get(srcPath + fileNameTemp);
    // 			i++;
    // 		}

    // 		URL url = new URL(urlStr);
    // 		// Lấy file Cv từ Candidate
    // 		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
    // 		if (fileNameTemp == null) {
    // 			FileOutputStream fos = new FileOutputStream(srcPath + fileName);
    // 			candidateApplicationDTO.setCV(folderCVApplication + String.format("/%s", folderParent) + fileName);
    // 			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    // 			fos.close();
    // 			rbc.close();
    // 		} else {
    // 			FileOutputStream fos = new FileOutputStream(srcPath + fileNameTemp);
    // 			candidateApplicationDTO.setCV(folderCVApplication + String.format("/%s", folderParent) + fileNameTemp);
    // 			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    // 			fos.close();
    // 			rbc.close();

    // 		}
    // 	} catch (FileNotFoundException e) {
    // 		throw new ResourceNotFoundException("File CV", "id of candidate",
    // 				String.valueOf(candidateApplicationDTO.getCandidateDTO().getId()));
    // 	} catch (IOException e) {
    // 		e.printStackTrace();
    // 	}
    // 	LOGGER.info(String.format("Application %s save the CV %s successfully",
    // 			candidateApplicationDTO.getCandidateDTO().getId(), candidateApplicationDTO.getCV()));
    // 	return candidateApplicationDTO;
    // }

    // @Override
    // public CandidateApplication getById(Integer id) {
    // 	return candidateApplicationRepository.findById(id)
    // 			.orElseThrow(() -> new ResourceNotFoundException("applies", "id", String.valueOf(id)));

    // }

    // @Override
    // public boolean deleteById(Integer id) {
    // 	try {
    // 		candidateApplicationRepository.deleteById(id);
    // 	} catch (Exception e) {
    // 		throw new ResourceNotFoundException("Application", "id", String.valueOf(id));
    // 	}
    // 	LOGGER.info(String.format("Application id : %s successfully delete ", id.toString()));
    // 	return true;
    // }

    @Override
    public CandidateApplicationDTO readJson(String value, MultipartFile fileCV) {
        CandidateApplicationDTO candidateApplicationDTO = new CandidateApplicationDTO();
        try {
            ObjectMapper ob = new ObjectMapper();
            candidateApplicationDTO = ob.readValue(value, CandidateApplicationDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(this.messageSource.getMessage("error.readJson", null, null));
        }

        if (fileCV != null) {// Set file Logo
            String fileCVName = fileService.uploadFile(fileCV);
			candidateApplicationDTO.setCV(fileCVName);
        }

        return candidateApplicationDTO;
    }

     @Override
     public boolean existsByJobIdAndCandidateId(long jobId, long candidateId) {

     	return candidateApplicationRepository.existsByCandidateIdAndJobId(candidateId, jobId);
     }

}
