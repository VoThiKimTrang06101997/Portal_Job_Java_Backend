//package com.pts.findInternship.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.context.MessageSource;
//
//import com.r2s.findInternship.common.MessageResponse;
//import com.r2s.findInternship.data.dto.CandidateDTO;
//import com.r2s.findInternship.data.dto.CareDTO;
//import com.r2s.findInternship.data.dto.JobDTO;
//import com.r2s.findInternship.data.dto.UserDetailsDTO;
//import com.r2s.findInternship.data.entity.Candidate;
//import com.r2s.findInternship.data.entity.JobCare;
//import com.r2s.findInternship.data.entity.Job;
//import com.r2s.findInternship.data.entity.User;
//import com.r2s.findInternship.data.mapper.CandidateMapper;
//import com.r2s.findInternship.data.mapper.CareMapper;
//import com.r2s.findInternship.data.mapper.JobMapper;
//import com.r2s.findInternship.data.repository.CandidateRepository;
//import com.r2s.findInternship.data.repository.JobCareRepository;
//import com.r2s.findInternship.data.repository.JobRepository;
//import com.r2s.findInternship.exception.CustomException;
//import com.r2s.findInternship.exception.ResourceNotFoundException;
//import com.r2s.findInternship.service.impl.CareServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//public class CaresServiceTest {
//    @Mock
//    private MessageSource messageSource;
//    @Mock
//    private CareMapper careMapper;
//    @Mock
//    private CandidateMapper mapperCandidate;
//    @Mock
//    private JobMapper mapperJob;
//    @Mock
//    private JobCareRepository careRepository;
//    @Mock
//    private CandidateRepository candidateRepository;
//    @Mock
//    private JobRepository jobRepository;
//    @InjectMocks
//    private CareServiceImpl CaresService;
//    private static final List<Job> jobs = new ArrayList<>();
//    private static final List<JobDTO> jobDTOs = new ArrayList<>();
//    private static final List<Candidate> candidates = new ArrayList<>();
//    private static final List<CandidateDTO> candidateDTOs = new ArrayList<>();
//    private static final List<JobCare> cares = new ArrayList<>();
//    private static final List<CareDTO> careDTOs = new ArrayList<>();
//
//    private void assertActualWithExpected(CareDTO actual, CareDTO expected) {
//        assertThat(actual).isNotNull();
//        assertThat(actual.getId()).isEqualTo(expected.getId());
//        assertThat(actual.getNote()).isEqualTo(expected.getNote());
//        assertThat(actual.getJobDTO().getId()).isEqualTo(expected.getJobDTO().getId());
//    }
//
//    @BeforeAll
//    static void setUp() {
//        for (int i = 1; i <= 3; ++i) {
//            User user = new User(); user.setId(i); user.setUsername("user" + i);
//            Candidate candidate = new Candidate(); candidate.setId(i); candidate.setUser(user);
//            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(); userDetailsDTO.setId(i); userDetailsDTO.setUsername("user" + i);
//            CandidateDTO candidateDTO = new CandidateDTO(); candidateDTO.setId(i); candidateDTO.setUserDetailsDTO(userDetailsDTO);
//            candidates.add(candidate);
//            candidateDTOs.add(candidateDTO);
//        }
//        for (int i = 1; i <= 3; ++i) {
//            Job job = new Job(); job.setId(i); job.setName("job" + i);
//            job.setCreatedDate(LocalDateTime.of(2022, 7, 13,5,5)); job.setStartDate(LocalDateTime.of(2022, 8, 13,5,5)); job.setEndDate(LocalDateTime.of(2022, 10, 13,5,5));
//            JobDTO jobDTO = new JobDTO(); jobDTO.setId(i); jobDTO.setName("job" + i);
//            jobs.add(job);
//            jobDTOs.add(jobDTO);
//        }
//        int id = 1;
//        for (int i = 0; i < candidates.size(); ++i) {
//            for (int j = 0; j < jobs.size(); ++j) {
//                //cares.add(new Care(id, jobs.get(j), candidates.get(i), "care" + id));
//                careDTOs.add(new CareDTO(id, jobDTOs.get(j), candidateDTOs.get(i), LocalDateTime.now(), "care" + id));
//                id++;
//            }
//        }
//    }
//
//    // when passed
//    @DisplayName("JUnit test for find by Id method")
//    @Test
//    void testFindById_shouldReturnCareDTO_whenCallingFindByIdSuccesfully() {
//        // fake data
//        JobCare care = cares.get(0);
//        CareDTO careDTO = careDTOs.get(0);
//        // cover the service
//        Mockito.when(careRepository.findById(1)).thenReturn(Optional.of(care));
//        Mockito.when(careMapper.toDTO(care)).thenReturn(careDTO);
//        // get the actual result
//        CareDTO actualResult = CaresService.findById(1);
//        // check the service
//        assertActualWithExpected(actualResult, careDTO);
//    }
//
//    @DisplayName("JUnit test for create method")
//    @Test
//    void testCreateACare_shouldReturnCareDTO_whenAllConditionIsSatisfiedAndCallingSaveSuccesfully() {
//        // fake data
//        Candidate candidate = candidates.get(0);
//        CandidateDTO candidateDTO = candidateDTOs.get(0);
//        JobDTO jobDTO = jobDTOs.get(0);
//        Job job = jobs.get(0);
//        JobCare care = cares.get(0);
//        CareDTO careDTO = careDTOs.get(0);
//        // cover the service
//        Mockito.when(candidateRepository.findById(anyInt())).thenReturn(Optional.of(candidate));
//        Mockito.when(jobRepository.findById(anyInt())).thenReturn(Optional.of(job));
//        Mockito.when(mapperCandidate.toDTO(any(Candidate.class))).thenReturn(candidateDTO);
//        Mockito.when(mapperJob.toDTO(any(Job.class))).thenReturn(jobDTO);
//        Mockito.when(careRepository.existsByUsernameAndJobId(any(String.class), anyInt())).thenReturn(false);
//        Mockito.when(careMapper.toEntity(any(CareDTO.class))).thenReturn(care);
//        Mockito.when(careRepository.save(any(JobCare.class))).thenReturn(care);
//        Mockito.when(careMapper.toDTO(any(JobCare.class))).thenReturn(careDTO);
//        // get the actual result
//        CareDTO actualResult = CaresService.save(careDTO);
//        // check the service with assert
//        assertActualWithExpected(actualResult, careDTO);
//    }
//
//    @DisplayName("JUnit test for find all method")
//    @Test
//    void testFindAll_shouldReturnCareDTOList_whenCallingFindAllSuccesfully() {
//        Mockito.when(careRepository.findAll()).thenReturn(cares);
//        for (int i = 0; i < cares.size(); ++i)
//            Mockito.when(careMapper.toDTO(cares.get(i))).thenReturn(careDTOs.get(i));
//
//        List<CareDTO> actualResult = CaresService.findAll();
//        assertThat(actualResult).isNotNull();
//        assertThat(actualResult.size()).isEqualTo(careDTOs.size());
//        for (int i = 0; i < actualResult.size(); ++i)
//            assertActualWithExpected(actualResult.get(i), careDTOs.get(i));
//    }
//
//    @DisplayName("JUnit test for update method")
//    @Test
//    void testUpdateCare_shouldReturnCareDTO_whenAllConditionIsSatisfiedAndCallingUpdateSuccesfully() {
//        JobCare oldCare = cares.get(0);
//        JobCare newCare = cares.get(1);
//        newCare.setId(1);
//        CareDTO newCareDTO = careDTOs.get(1);
//        newCareDTO.setId(1);
//
//        Mockito.when(careRepository.findById(anyInt())).thenReturn(Optional.of(oldCare));
//        Mockito.when(careRepository.save(any(JobCare.class))).thenReturn(newCare);
//        Mockito.when(careMapper.toDTO(any(JobCare.class))).thenReturn(newCareDTO);
//
//        CareDTO actualResult = CaresService.update(1, newCareDTO);
//
//        assertActualWithExpected(actualResult, newCareDTO);
//
//        newCare.setId(2);
//        newCareDTO.setId(2);
//        newCareDTO.setId(2);
//    }
//
//    @DisplayName("JUnit test for find by username method")
//    @Test
//    void testFindByUsername_shouldReturnCareDTO_whenCallingFindByUsernameSuccesfully() {
//        List<JobCare> careByUsers = new ArrayList<>();
//        List<CareDTO> careByUserDTOShows = new ArrayList<>();
//        for (int i = 0; i < cares.size(); ++i) {
//            if (cares.get(i).getCandidate().getUser().getUsername().equals("user1")) {
//                careByUsers.add(cares.get(i));
//                careByUserDTOShows.add(careDTOs.get(i));
//            }
//        }
//
//        Mockito.when(careRepository.findAllByUsername(any(String.class))).thenReturn(careByUsers);
//        for (int i = 0; i < careByUsers.size(); ++i)
//            Mockito.when(careMapper.toDTO(careByUsers.get(i))).thenReturn(careByUserDTOShows.get(i));
//
//        List<CareDTO> actualResult = CaresService.findByUsername("user1");
//
//        assertThat(actualResult.size()).isEqualTo(careByUserDTOShows.size());
//        for (int i = 0; i < actualResult.size(); ++i)
//            assertActualWithExpected(actualResult.get(i), careByUserDTOShows.get(i));
//    }
//
//    @DisplayName("JUnit test for find by username and job id method")
//    @Test
//    void testFindByUsernameAndJobId_shouldReturnCareDTO_whenAllConditionsSatisfiedAndCallingFindByUsernameAndJobIdSuccesfully() {
//        JobCare care = cares.get(0);
//        CareDTO careDTOShow = careDTOs.get(0);
//
//        Mockito.when(careRepository.findByUsernameAndJobId(anyString(), anyInt())).thenReturn(Optional.of(care));
//        Mockito.when(careMapper.toDTO(any(JobCare.class))).thenReturn(careDTOShow);
//
//        CareDTO actualResult = CaresService.findByUsernameAndJobId("user1", 1);
//
//        assertActualWithExpected(actualResult, careDTOShow);
//    }
//
//    @DisplayName("JUnit test for delete method")
//    @Test
//    void testDeleteById_shouldReturnMessageResponse_whenAllConditionsSatisfiedAndCallingDeleteByIdSuccesfully() {
//        JobCare care = cares.get(0);
//        String contextMessage = "Candidate %s đã xóa một công việc khỏi danh sách quan tâm";
//        String message = String.format(contextMessage, care.getCandidate().getUser().getUsername());
//        MessageResponse expectedResponse = new MessageResponse(200, message,null);
//
//        Mockito.when(careRepository.findById(anyInt())).thenReturn(Optional.of(care));
//        Mockito.doNothing().when(careRepository).deleteById(anyInt());
//        Mockito.when(messageSource.getMessage(any(), any(), any())).thenReturn(message);
//
//        MessageResponse responeMessage = CaresService.deleteById(1);
//
//        Mockito.verify(careRepository, Mockito.times(1)).deleteById(1);
//        assertThat(responeMessage.getMessage()).isEqualTo(expectedResponse.getMessage());
//    }
//
//    // when fail
//    @DisplayName("JUnit test for find by id and throw an exception")
//    @Test
//    void testFindById_shouldThrowException_whenFindByIdThrowException() {
//        String exceptionNotFoundWithId = "Cares not found with id: 1";
//        Mockito.when(careRepository.findById(10)).thenThrow(new ResourceNotFoundException("Cares", "id", "1"));
//        Throwable actualException = assertThrows(ResourceNotFoundException.class, () -> CaresService.findById(10));
//        assertThat(actualException).isNotNull();
//        assertThat(actualException.toString()).isEqualTo(exceptionNotFoundWithId);
//    }
//
//    @DisplayName("JUnit test for find by username and job id then throw an exception")
//    @Test
//    void testFindByUsernameAndJobId_shouldThrowException_whenFindByUsernameAndJobIdCalled() {
//        String exceptionNotFoundByUsernameAndJobId = "Cares not found with (username, jobId): (abcdef, 10)";
//        Mockito.when(careRepository.findByUsernameAndJobId("abcdef", 10))
//                .thenThrow(new ResourceNotFoundException("Cares", "(username, jobId)", "(abcdef, 10)"));
//        Throwable actualException = assertThrows(ResourceNotFoundException.class,
//                () -> CaresService.findByUsernameAndJobId("abcdef", 10));
//        assertThat(actualException).isNotNull();
//        assertThat(actualException.toString()).isEqualTo(exceptionNotFoundByUsernameAndJobId);
//    }
//
//    @DisplayName("JUnit test for save when find by candidate Id then throw an exception")
//    @Test
//    void testSaveCare_shouldReturnException_whenFindByCandidateIdFailed() {
//        CandidateDTO candidateDTO = new CandidateDTO();
//        candidateDTO.setId(10);
//        JobDTO jobDTO = new JobDTO();
//        jobDTO.setId(1);
//        CareDTO careDTO = new CareDTO();
//        careDTO.setCandidateDTO(candidateDTO);
//        careDTO.setJobDTO(jobDTO);
//        Mockito.when(candidateRepository.findById(10))
//                .thenThrow(new ResourceNotFoundException("Candidate", "id", "10"));
//        assertThrows(ResourceNotFoundException.class, () -> CaresService.save(careDTO));
//    }
//
//    @DisplayName("JUnit test for add a care and throw an exception when find the job after found the candidate")
//    @Test
//    void testSaveCare_shouldReturnException_whenFindByCandidateIdSuccessAndFindByJobIdFailed() {
//        String exception = "Job not found with id: 10";
//
//        CandidateDTO candidateDTO = new CandidateDTO();
//        candidateDTO.setId(1);
//        JobDTO jobDTO = new JobDTO();
//        jobDTO.setId(10);
//        Candidate candidate = new Candidate();
//        candidate.setId(1);
//
//        CareDTO careDTO = new CareDTO();
//        careDTO.setCandidateDTO(candidateDTO);
//        careDTO.setJobDTO(jobDTO);
//
//        Mockito.when(candidateRepository.findById(1)).thenReturn(Optional.of(candidate));
//        Mockito.when(jobRepository.findById(10)).thenThrow(new ResourceNotFoundException("Job", "id", "10"));
//        Throwable actualException = assertThrows(ResourceNotFoundException.class, () -> CaresService.save(careDTO));
//        assertThat(actualException).isNotNull();
//        assertThat(actualException.toString()).isEqualTo(exception);
//    }
//
//    @DisplayName("JUnit test for add a care and throw an exception")
//    @Test
//    void testAddCare_shouldThrowAnException_whenFindByUsernameAndJobIdFailed() {
//        Job job = new Job();
//        job.setId(1);
//        User user = new User();
//        user.setUsername("user1");
//        Candidate candidate = new Candidate();
//        candidate.setId(1);
//        candidate.setUser(user);
//
//        JobDTO jobDTO = new JobDTO();
//        jobDTO.setId(1);
//        UserDetailsDTO userDTO = new UserDetailsDTO();
//        userDTO.setUsername("user1");
//        CandidateDTO candidateDTO = new CandidateDTO();
//        candidateDTO.setId(1);
//        candidateDTO.setUserDetailsDTO(userDTO);
//        CareDTO careDTO = new CareDTO();
//        careDTO.setJobDTO(jobDTO);
//        careDTO.setCandidateDTO(candidateDTO);
//        JobCare care = new JobCare();
//        care.setJob(job);
//        care.setCandidate(candidate);
//
//        Mockito.when(candidateRepository.findById(1)).thenReturn(Optional.of(candidate));
//        Mockito.when(jobRepository.findById(1)).thenReturn(Optional.of(job));
//        Mockito.when(careRepository.existsByUsernameAndJobId("user1", 1)).thenReturn(true);
//
//        assertThrows(CustomException.class, () -> CaresService.save(careDTO));
//        Mockito.verify(careRepository, Mockito.times(0)).save(care);
//    }
//
//    @DisplayName("JUnit test for find a care by username and job id then throw an exception")
//    @Test
//    void testUpdateCare_shouldThrowAnException_whenFindByIdCareFailed() {
//        // wrong data
//        CareDTO CaresDTO = new CareDTO();
//        // cover the find by id
//        Mockito.when(careRepository.findById(10)).thenThrow(new ResourceNotFoundException("Cares", "id", "10"));
//        assertThrows(ResourceNotFoundException.class, () -> CaresService.update(10, CaresDTO));
//    }
//}
