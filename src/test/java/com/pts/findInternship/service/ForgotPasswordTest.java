//package com.pts.findInternship.service;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//@ExtendWith(MockitoExtension.class)
//class ForgotPasswordTest {
	
//	@Mock
//	private static BCryptPasswordEncoder passwordEncoder;
//	
//	@InjectMocks
//	private static final List<Candidate> candidates = new ArrayList<>();
//	private static final List<User> users = new ArrayList<>();
//	
//	@BeforeAll
//	static void setup() {
//		for (int i = 0; i < 3; i++) {
//			User user = new User(); user.setId(i++); user.setUsername("user"+ i++); user.setPassword(passwordEncoder.encode("Test1234"));
//			Candidate candidate = new Candidate(); candidate.setId(i++); candidate.setUser(user);
//			candidates.add(candidate);
//			users.add(user);
//		}
//	}
//	
//	@DisplayName("JUnit test for forgot password then login to candidate")
//	@Test
//	void testForgotPasswordThenSignin_shouldReturnToken_whenPassword() {
//		User user = users.get(0);
//		assertActualWithExpected(user.getPassword(), user)
//	}
	
//}
