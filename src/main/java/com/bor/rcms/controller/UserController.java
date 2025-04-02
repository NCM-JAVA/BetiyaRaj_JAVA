package com.bor.rcms.controller;

import com.bor.rcms.dto.BulkTransferRequest;
import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.FreeHoldIntiate;
import com.bor.rcms.dto.LoginRequest;
import com.bor.rcms.dto.OTPRequest;
import com.bor.rcms.dto.ObjectionVo;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.dto.UserRegistrationRequest;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.AppealCaseCollector;
import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.entity.CaseCommissioner;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.FreeholdIntitalCollector;
import com.bor.rcms.entity.LocationDetails;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.RoleEntity;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.AdmissionRepo;
import com.bor.rcms.repository.CaseCollectorRepo;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.FreeholdIntitalCollectorRepo;
import com.bor.rcms.repository.LoctaionRepo;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.repository.RoleRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.response.CaptchaResponsed;
import com.bor.rcms.response.FreeholdDetailsResponse;
import com.bor.rcms.response.ObjectionStatus;
import com.bor.rcms.response.StatusRes;
import com.bor.rcms.service.AdminService;
import com.bor.rcms.service.CollectorService;
import com.bor.rcms.service.CommissionerService;
import com.bor.rcms.service.NoticeService;
import com.bor.rcms.service.ObjectionService;
import com.bor.rcms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import org.bouncycastle.asn1.DERApplicationSpecific;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.event.FocusEvent.Cause;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.bor.rcms.resonse.Casesinform;
import com.bor.rcms.resonse.CauselistSpecialOfficer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:4200") 


@Transactional

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ObjectionService objectionService;
	@Autowired
	private LoctaionRepo loctionrepo;

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdmissionRepo admissionRepo;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CollectorService collectorService ;
	
	
	@Autowired
	private CommissionerService commissionerService; 
	
	@Autowired
	private CaseCollectorRepo caseCollectorRepo;
	
	@Autowired
	private FreeholdIntitalCollectorRepo freeholdIntitalCollectorRepo;
	
	@Autowired
	private NewObjectionRepo newObjectionRepo;
	
	@GetMapping("/Hello")
	public String HelloApi() {
		return "Hello Api.";
	}
	
	
	

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
		try {
			UserEntity user = new UserEntity();
			// user.setUserName(request.getUserName());
			user.setFullName(request.getFullName());
			user.setEmail(request.getEmail());
			user.setPhoneNumber(request.getPhoneNumber());
			user.setPassword(request.getPassword());
			user.setRelationName(request.getRelation());
			user.setAlternateNumber(request.getAlternatenumber());
			user.setCategory(request.getCategory());
			user.setCity(request.getCity());
			user.setState(request.getState());
			user.setPincode(request.getPincode());
			try {
			user.setGender(request.getGender());
			user.setDob(request.getDob());
			}
			catch (Exception e) {
				// TODO: handle exception
			}

			user.setAdhar(request.getAadhar());
			user.setAddress(request.getAddress());
			user.setStatus(request.getStatus());
			user.setDistrict(request.getDistrict());
			user.setUserName(request.getUserName());
			StatusRes res = new StatusRes();
			try {
				UserEntity entity = repository.findByPhoneNumber(request.getPhoneNumber());

				if (entity != null) {
					res.setMessage("try diiferent mobile Number");
					res.setStatus("400");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different" + res);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			try {
				if (request.getRoleName().equals("Collector")) {
					RoleEntity role = roleRepository.findByRoleName(request.getRoleName());
					if (role == null) {
						role = new RoleEntity(request.getRoleName(), request.getRoleName());
						role = roleRepository.save(role);
					}

					user.setRole(role);

					user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password

					UserEntity usersave = repository.save(user);
					return ResponseEntity.ok(usersave);
				} else if (request.getRoleName().equals("DivCommissioner")) {
					RoleEntity role = roleRepository.findByRoleName(request.getRoleName());
					if (role == null) {
						role = new RoleEntity(request.getRoleName(), request.getRoleName());
						role = roleRepository.save(role);
					}

					user.setRole(role);
					user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password


					UserEntity usersave = repository.save(user);
					return ResponseEntity.ok(usersave);
				}
				
				else if (request.getRoleName().equals("SpecialOfficer")) {
					RoleEntity role = roleRepository.findByRoleName(request.getRoleName());
					if (role == null) {
						role = new RoleEntity(request.getRoleName(), request.getRoleName());
						role = roleRepository.save(role);
					}

					user.setRole(role);
					user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password


					UserEntity usersave = repository.save(user);
					return ResponseEntity.ok(usersave);
				}
			}

			catch (Exception e) {
				// TODO: handle exception
			}

			String roleName = request.getRoleName() != null ? request.getRoleName() : "OBJECTIONER";
			UserEntity registeredUser = userService.registerUser(user, roleName);

			return ResponseEntity.ok(registeredUser);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*
	 * @PostMapping("/findMobileNumber") public ResponseEntity<?>
	 * findMobileNumber(@RequestBody Map<String, String> request) { try { String
	 * phoneNumber = request.get("phoneNumber"); // Extract phone number from the
	 * request body UserEntity entity = repository.findByPhoneNumber(phoneNumber);
	 * 
	 * if (entity != null) { // Return a JSON response return
	 * ResponseEntity.ok(Map.of("message", "Try another mobile")); } else { //
	 * Return a JSON response return ResponseEntity.ok(Map.of("message",
	 * "Phone number is available")); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return
	 * ResponseEntity.badRequest().body(e.getMessage()); } }
	 * 
	 * 
	 * 
	 * @PostMapping("/findAadharNumber") public ResponseEntity<?>
	 * findAadharNumber(@RequestBody Map<String, String> request) { try { String
	 * aadharNumber = request.get("aadharNumber"); // Extract phone number from the
	 * request body
	 * 
	 * UserEntity entity=repository.findByAdhar(aadharNumber);
	 * 
	 * if(entity!=null) { return ResponseEntity.ok(Map.of("message",
	 * "Try another aadhar"));
	 * 
	 * } else { return ResponseEntity.ok(Map.of("message",
	 * "aadhar number is available")); }
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); return
	 * ResponseEntity.badRequest().body(e.getMessage());
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	
	

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
		try {
			UserEntity user = userService.loginUser(loginRequest.getUserName(), loginRequest.getPassword());
			
	      //  String token = userService.loginAndGenerateToken(loginRequest.getUserName(), loginRequest.getPassword());


			if (loginRequest.getUserType().equals("SpecialOfficer")) {
				try {
					List<NewObjection> newObjection = objectionService.findAll();
				//	System.out.println("df==" + newObjection);
					return ResponseEntity.ok(user);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			} else if (loginRequest.getUserType().equals("BOR")) {
				return ResponseEntity.ok(user);

			} else if (loginRequest.getUserType().equals("DivCommissioner")) {
				return ResponseEntity.ok(user);

			} else if (loginRequest.getUserType().equals("Collector") && user.getStatus().equals("Active")) {
				return ResponseEntity.ok(user);

			}

			return (ResponseEntity<?>) ResponseEntity.badRequest();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/login-otp")
	public ResponseEntity<?> loginWithOTP(@RequestBody LoginRequest request) throws IOException {
		try {
			String response = userService.loginWithOTP(request.getUserName(), request.getPassword());

			if (request.getUserType().equals("OBJECTIONER")) {
				StatusRes res = new StatusRes();
				res.setStatus("succes");
				return ResponseEntity.ok().body(res);
			}
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestBody OTPRequest request) {
		try {
			UserEntity response = userService.verifyOTP(request.getUserName(), request.getOtp());
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok("User deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/sendOtp")
	public ResponseEntity<String> sendOtp(@RequestParam("phone") String phoneNumber) {
		// Call OTP service to send the OTP
		try {
			String otp = userService.sendOtprequest(phoneNumber);

			return ResponseEntity.ok("otp successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getobjections")
	public ResponseEntity<?> getObjections(@RequestParam String district) {
		try {
			List<NewObjection> newObjection = objectionService.findpending(district);
			return ResponseEntity.ok(newObjection);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}

	@GetMapping("/getobjectionsapproved")
	public ResponseEntity<?> getObjectionsapproved() {
		try {
			List<NewObjection> newObjection = objectionService.findaproved();
			return ResponseEntity.ok(newObjection);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}
	
	
	
	
	
	@GetMapping("/getobjectionDismis")
	public ResponseEntity<?> getobjectionDismis() {
		try {
			//List<NewObjection> newObjection = objectionService.findaproved();
			
			List<Admission> admissions=objectionService.getfindDismis();
			return ResponseEntity.ok(admissions);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}
	
	

	

	@GetMapping("/getobjectionsAll")
	public ResponseEntity<?> getAllObjections(@RequestParam String userId) {
		try {
			
		//	List<Admission> admissions =admissionRepo.findAll();
		//	List<NewObjection> newObjection = objectionService.findAll();
			
			List<NewObjection> newObjection = newObjectionRepo.findByUserId(Long.valueOf(userId));

			return ResponseEntity.ok(newObjection);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}

	@GetMapping("/vieobjection")
	public ResponseEntity<?> getAllObjections(@RequestParam Long obId) {
		try {
			NewObjection newObjection = objectionService.findbyId(obId).get();

			DocumentEntity documentEntity = objectionService.getObjectionsDocumentdetials(obId);
			return ResponseEntity.ok(newObjection);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}

	@PostMapping("officerstatus")
	public ResponseEntity<?> postMethodName(@RequestBody OfficerStatusVo statusvo) {
		// TODO: process POST request

		try {
			
			String status = objectionService.upadateStatus(statusvo);
			if (status.equals("save")) {
				return ResponseEntity.ok(HttpStatus.ACCEPTED);
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("state")
	public ResponseEntity<?> state() {
		// TODO: process POST request

		try {
			List<String> status = loctionrepo.findDistinctState();
			if (!status.isEmpty()) {
				return ResponseEntity.ok(status);
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("findistrict")
	public ResponseEntity<?> findDistrict(@RequestParam String state) {
		try {
			List<String> districts = loctionrepo.findDistinctDistrict(state);

			if (districts.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No districts found for the given state.");
			}

			return ResponseEntity.ok(districts);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving districts: " + e.getMessage());
		}
	}

	@PostMapping("findobj")
	public ResponseEntity<?> findobj(@RequestParam String userid) {
		try {
			Long userIds = Long.valueOf(userid);
			List<NewObjection> list = objectionService.getObjectionsByUserId(userIds);
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
			}

			return ResponseEntity.ok(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving districts: " + e.getMessage());
		}
	}

	@PostMapping("findcircle")
	public ResponseEntity<?> findCircle(@RequestParam String state, @RequestParam String district) {
		try {
			List<String> circles = loctionrepo.findDistinctCircle(state, district);

			if (circles.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No circles found for the given state and district.");
			}

			return ResponseEntity.ok(circles);

		} catch (Exception e) {
			// Handle exceptions and return an error message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving circles: " + e.getMessage());
		}
	}

	@PostMapping("findhalka")
	public ResponseEntity<?> findHalka(@RequestParam String state, @RequestParam String district,
			@RequestParam String circle) {
		try {
			List<String> halkas = loctionrepo.findDistinctHalka(state, district, circle);

			if (halkas.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No halkas found for the given state, district, and halka.");
			}

			return ResponseEntity.ok(halkas);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving halkas: " + e.getMessage());
		}
	}

	@PostMapping("findmauja")
	public ResponseEntity<?> findMauja(@RequestParam String state, @RequestParam String district,
			@RequestParam String halka, @RequestParam String circle) {
		try {
			List<String> maujas = loctionrepo.findDistinctMauja(state, district, halka, circle);

			if (maujas.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No maujas found for the given state, district, halka, and mauja.");
			}

			return ResponseEntity.ok(maujas);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving maujas: " + e.getMessage());
		}
	}

	@PostMapping("findkhataNumber")
	public ResponseEntity<?> findKhataNumber(@RequestParam String state, @RequestParam String district,
			@RequestParam String halka, @RequestParam String circle, @RequestParam String mauja) {
		try {
			List<String> khataNumbers = loctionrepo.findDistinctKhataNumber(state, district, halka, circle, mauja);

			if (khataNumbers.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT)
						.body("No khata numbers found for the given parameters.");
			}

			return ResponseEntity.ok(khataNumbers);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving khata numbers: " + e.getMessage());
		}
	}

	@PostMapping("findplot")
	public ResponseEntity<?> findPlot(@RequestParam String state, @RequestParam String district,
			@RequestParam String halka, @RequestParam String circle, @RequestParam String mauja,
			@RequestParam String khatano) {
		try {
			List<String> plots = loctionrepo.findDistinctPlotno(state, district, halka, circle, mauja, khatano);

			if (plots.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
			}

			return ResponseEntity.ok(plots);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving plots: " + e.getMessage());
		}
	}

	@PostMapping("submitfileObjection")
	public ResponseEntity<?> fileObjection(@RequestBody ObjectionVo objectionVo) {
		try {
			NewObjection entity = new NewObjection();
			entity.setState(objectionVo.getState());
			entity.setDistrictName(objectionVo.getDistrict());
			entity.setCircleName(objectionVo.getCircle());
			entity.setHalka(objectionVo.getHalka());
			entity.setMozza(objectionVo.getMauja());
			entity.setKhattaNumber(objectionVo.getKhata());
			entity.setPlotNumber(objectionVo.getPlot());
			entity.setAcre(objectionVo.getAcre());
			entity.setDismil(objectionVo.getDismil());
			entity.setObjection(objectionVo.getObjremark());
			UserEntity user = new UserEntity();
			user = userService.getUserById(Long.valueOf(objectionVo.getUserId()));
			entity.setUserId(user);
			NewObjection newObjection = objectionService.savedata(entity);
			return ResponseEntity.ok(newObjection);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error filing objection: " + e.getMessage());
		}
	}

	@PostMapping("findobjection")
	public ResponseEntity<?> findPlot(@RequestParam String userId) {
		Long userIds = Long.valueOf(userId);
		List<NewObjection> list = objectionService.getObjectionsByUserId(userIds);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
		}

		return ResponseEntity.ok(list);

	}

	@GetMapping("getspecialofficer")
	public ResponseEntity<?> findspeciofficergetobject(@RequestParam String userId) {
		Long userIds = Long.valueOf(userId);
		List<NewObjection> list = objectionService.getObjectionsByUserId(userIds);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
		}

		return ResponseEntity.ok(list);

	}

	@PostMapping("getObjectionData")
	public ResponseEntity<?> getobjectionDetails(@RequestParam String userid) {
		Long userIds = Long.valueOf(userid);
		ObjectionStatus obstatus = objectionService.getObjectionsdetials(userIds);

		if (obstatus == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
		}

		return ResponseEntity.ok(obstatus);

	}

	@PostMapping("ObjectionDetailsSpecialOfficer")
	public ResponseEntity<?> getobjectionDetailsSpecialOfficer(@RequestParam String userid,@RequestParam String district) {
		// Long userIds = Long.valueOf(userid);
		ObjectionStatus obstatus = objectionService.ObjectionsdetialsPending(userid,district);

		if (obstatus == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
		}

		return ResponseEntity.ok(obstatus);

	}

	@PostMapping("/submit")
	public ResponseEntity<?> submitObjection(@RequestPart("objection") String objectionJson,
			@RequestPart(value = "files", required = false) MultipartFile[] files,
			@RequestPart("username") String username) {
		try {
			// Debugging the received inputs
			System.out.println("Received objection JSON: " + objectionJson);
			System.out.println("Received username: " + username);
			System.out.println("Number of files: " + (files != null ? files.length : 0));

			// Convert the JSON string to ObjectionEntity
			ObjectMapper objectMapper = new ObjectMapper();
			NewObjection objection = objectMapper.readValue(objectionJson, NewObjection.class);

			// Call the service to handle the objection and files
			String objectionId = null;
					//objectionService.submitObjection(objection, files, username);

			// Respond with the objectionId after submission
			return ResponseEntity.ok(Collections.singletonMap("objectionId", objectionId));
		} catch (Exception e) {
			e.printStackTrace(); // Log the full exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	@PostMapping("findtrackMis")
	public ResponseEntity<?> findtrackMis(@RequestParam String objId) {
		Long userIds = Long.valueOf(objId);

		Mis mis = objectionService.findmistatus(objId);

		return ResponseEntity.ok(mis);

	}

	@PostMapping("/fileObjection")
	public ResponseEntity<?> submitObsjection(@RequestPart("objection") String objectionJson,
			@RequestPart(value = "files", required = false) MultipartFile[] files,     @RequestPart(value = "documentTypes", required = false) String  documentTypes,
			@RequestPart("username") String username) {
		try {
			// Debugging the received inputs
			System.out.println("Received objection JSON: " + objectionJson);
			System.out.println("Received username: " + username);
			System.out.println("Number of files: " + (files != null ? files.length : 0));

			// Convert the JSON string to ObjectionEntity
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectionVo objectionVo = objectMapper.readValue(objectionJson, ObjectionVo.class);

			NewObjection entity = new NewObjection();
			entity.setState(objectionVo.getState());
			entity.setDistrictName(objectionVo.getDistrict());
			entity.setCircleName(objectionVo.getCircle());
			entity.setHalka(objectionVo.getHalka());
			entity.setMozza(objectionVo.getMauja());
			entity.setKhattaNumber(objectionVo.getKhata());
			entity.setPlotNumber(objectionVo.getPlot());
			entity.setAcre(objectionVo.getAcre());
			entity.setDismil(objectionVo.getDismil());
			entity.setObjection(objectionVo.getObjremark());
			entity.setDocumentType(objectionVo.getDocumentType());
			UserEntity user = new UserEntity();
			user = userService.getUserById(Long.valueOf(objectionVo.getUserId()));
			entity.setUserId(user);
			username = String.valueOf(user.getUserId());
			// NewObjection objection = objectionService.savedata(entity);

			// Call the service to handle the objection and files
			String objectionId = objectionService.submitObjection(entity, files, username,documentTypes);

			// Respond with the objectionId after submission
			return ResponseEntity.ok(Collections.singletonMap("objectionId", objectionId));
		} catch (Exception e) {
			e.printStackTrace(); // Log the full exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	private static final String FILE_DIRECTORY = "C:\\Users\\Admin\\Downloads\\l (3)\\l (2)\\l\\all form";

	@GetMapping("/files")
	public ResponseEntity<List<String>> listFiles() throws Exception {
		File folder = new File(FILE_DIRECTORY);
		if (!folder.exists() || !folder.isDirectory()) {
			return ResponseEntity.status(400).body(null);
		}

		// List all files in the directory
		List<String> fileNames = Files.walk(Paths.get(FILE_DIRECTORY)).filter(Files::isRegularFile)
				.map(Path::getFileName).map(Path::toString).collect(Collectors.toList());

		return ResponseEntity.ok(fileNames);
	}

	@Autowired
	private DocumentRepository docrepo;

	@PostMapping("/fileShow")
	public ResponseEntity<?> qrShow(@RequestParam String id) {
		String res = "";
		try {

			DocumentEntity documentEntity = docrepo.findById(Long.valueOf(id)).get();
			// Hardcoded for testing

			String filePath = documentEntity.getFilePath();
			// Ensure that the file exists
			Path imagePath = Paths.get(filePath);
			if (!Files.exists(imagePath)) {
				return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
			}

			// Load the file into a byte array
			byte[] fileBytes = Files.readAllBytes(imagePath);

			String base64String = Base64.getEncoder().encodeToString(fileBytes);

			if (base64String != null && !base64String.isEmpty()) {
				return new ResponseEntity<>(base64String, HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<>("File encoding failed", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//for test not use
	@PostMapping("/pdfshpw")
	public ResponseEntity<?> pdfShow() {
		String res = "";
		try {
			String filePath = "C:\\Users\\Admin\\Pictures\\EVL.pdf"; // Hardcoded for testing with PDF file

			Path pdfPath = Paths.get(filePath);
			if (!Files.exists(pdfPath)) {
				return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
			}

			byte[] fileBytes = Files.readAllBytes(pdfPath);

			String base64String = Base64.getEncoder().encodeToString(fileBytes);

			if (base64String != null && !base64String.isEmpty()) {
				return new ResponseEntity<>(base64String, HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<>("File encoding failed", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("findobjById")
	public ResponseEntity<?> docment(@RequestParam String objid) {
		try {
			Long objids = Long.valueOf(objid);
			NewObjection obj = objectionService.getObjectionsId(objids);
			List<DocumentEntity> documentEntities = obj.getDocuments();
			if (documentEntities.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No plots found for the given parameters.");
			}

			return ResponseEntity.ok(documentEntities);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving districts: " + e.getMessage());
		}
	}

	
	
	
	@PostMapping("findobjByIdAdmision")
	public ResponseEntity<?> Admisiiondata(@RequestParam String objid) {
		try {
			Long objids = Long.valueOf(objid);
			NewObjection obj = objectionService.getObjectionsId(objids);
			
			Admission admission=adminService.getobjectionId(obj);
			
			return ResponseEntity.ok(admission);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving districts: " + e.getMessage());
		}
	}
	
	@PostMapping("caseProcessdetails")
	public ResponseEntity<?> caseprocedetailssace(@RequestBody CaseNotes casenotes) {

		try {
			System.out.println(casenotes.getSelectForm());
			if (casenotes.getAction() == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");

			}
			NewObjection admission = adminService.savecaseDetails(casenotes);
			if (admission == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");
			}

			return ResponseEntity.ok(admission);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue casenotes: " + e.getMessage());
		}

	}

	@GetMapping("casenotes")
	public ResponseEntity<?> getCaseNotes(@RequestParam String objid) {
		try {
			NewObjection obj = adminService.getcaseNOtes(objid);

			// List<NewObjection> list=objectionService.getObjectionsByUserId(1l);

			List<NewObjection> newObjection = objectionService.findAll();

		//	System.out.println(newObjection);
			if (obj == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objection not found for the given objid.");
			}

			// Get the admission object associated with the case
			Admission admission2 = obj.getAdmission();

			if (admission2 == null || admission2.getCaseNotes() == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Case notes not found for the given objection.");
			}

			// Return the case notes if found
			return ResponseEntity.status(HttpStatus.OK).body(admission2.getCaseNotes());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the case notes.");
		}
	}

	@PostMapping("findAllCollector")
	public ResponseEntity<?> getallCollector(@RequestParam String officer) {
		try {
			List<UserEntity> userresult = adminService.userdata(officer);
			if (userresult.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");
			}
			return ResponseEntity.ok(userresult);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue casenotes: " + e.getMessage());
		}

	}
	
	
	//Officer Freehod///
	
//	@PostMapping("officerstatuschangeFreehold")
//	public ResponseEntity<?> officerstatuschangeFreehold(@RequestParam String status, @RequestParam String userId,@RequestParam String 	freeHolreq) {
//		// TODO: process POST request
//
//		try {
//			
//			if(status.equals("Freehold"))
//			
//			 String statusresul = userService.upadateStatusFreehold(status, userId,freeHolreq);
//			if (status.equals("save")) {
//				return ResponseEntity.ok(HttpStatus.ACCEPTED);
//			}
//
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	
	//Officer Freehod       endd----------///

	@PostMapping("officerstatuschange")
	public ResponseEntity<?> officerstatuschange(@RequestParam String status, @RequestParam String userId) {
		// TODO: process POST request

		try {
			
			String statussave = userService.upadateStatus(status, userId);
			if (status.equals("save")) {
				return ResponseEntity.ok(HttpStatus.ACCEPTED);
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("findallDismisobjection")
	public ResponseEntity<?> findallobjection(@RequestParam String userId) {

		try {
			List<Admission> admissions = adminService.getdismisObject(userId);
			List<String> adIds = admissions.stream().map(Admission::getAdmisionCase).collect(Collectors.toList());
			return ResponseEntity.ok(adIds);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
		
		
		
		//appeal for Freehold
		@GetMapping("collectorFreeholdDetails")
		public ResponseEntity<?> collectorFreeholdDetails(@RequestParam String userId) {

			try {
				List<Admission> admissions = adminService.getdismisObjectcollector(userId);
				List<String> adIds = admissions.stream().map(Admission::getAdmisionCase).collect(Collectors.toList());
				
				List<String> collcase=new ArrayList<String>();
				List<CaseCollector> collector=new ArrayList<CaseCollector>();
				for(String caseId:adIds)
				{
			        CaseCollector caseCollector = caseCollectorRepo.findByObjectioId(caseId);
			        collcase.add(caseCollector.getCollectorCase());
				}
				return ResponseEntity.ok(collcase);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

//		try {
//			List<NewObjection> newObjectionlist = objectionService.getdismisObject(userId);
//
//			if (!newObjectionlist.isEmpty()) {
//
//				List<Admission> admissionlist = new ArrayList<Admission>();
//				for (NewObjection obj : newObjectionlist) {
//					Admission admission1 = admissionRepo.findByNewObjection(obj);
//					admissionlist.add(admission1);
//				}
//
//				List<Long> objIds = admissionlist.stream().map(Admission::getAdmissionId).collect(Collectors.toList());
//				return ResponseEntity.ok(objIds);
//			}
//
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		return null;
	}
		///
		//Freeholedetailsrequest
		

		@GetMapping("findcaseByIdFreehold")
		public ResponseEntity<?> findcaseByIdfreeHoldDetails(@RequestParam String caseId) {
			try {
				FreeholdDetailsResponse detailsResponse=new FreeholdDetailsResponse();
				Admission admission= adminService.findByCaseId(caseId);
				CaseCollector caseCollector=caseCollectorRepo.findByCollectorCase(caseId);
				
				NewObjection newObjection=caseCollector.getNewObjection();
				UserEntity entity=caseCollector.getUserId();
				detailsResponse.setAddress(entity.getAddress());
				detailsResponse.setPhoneNumber(entity.getPhoneNumber());
				detailsResponse.setAlternativePhone(entity.getAlternateNumber());
				detailsResponse.setRelation(entity.getRelationName());
				detailsResponse.setDocumentEntity(newObjection.getDocuments());
				detailsResponse.setNewObjection(newObjection);
				return ResponseEntity.ok(detailsResponse);

				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
			
			
		}

	@GetMapping("findcaseById")
	public ResponseEntity<?> findcaseById(@RequestParam String caseId) {

		try {

			Admission admission = adminService.findByCaseId(caseId);

			return ResponseEntity.ok(admission);
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		}
		return null;
	}

	@PostMapping("ApealSubmit")
	public ResponseEntity<?> ApealSubmit(@RequestParam String caseId, @RequestParam String remark) {
		try {

			AppealCaseCollector caseCollector = noticeService.submitApeal(caseId, remark);
			
			Admission admission = adminService.findByCaseId(caseId);

			
			NewObjection newObjection= admission.getNewObjection();
			newObjection.setStatus("Appeal");
			admission.setStatus("Appeal");
			newObjection.setAdmission(admission);
		
			NewObjection newObjection2=newObjectionRepo.save(newObjection);
			
			return ResponseEntity.ok(caseCollector);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		}
		return null;

	}

	private final String FILE_STORAGE_PATH = "C:/Users/Admin/file";

	@PostMapping("/filecaseproceding")
	public ResponseEntity<?> submitObsjection1(@RequestParam("file") MultipartFile file,
			@RequestParam("objId") String objId, @RequestParam("documentName") String documentName) {
		try {
			// Example: Saving the file to a directory (you can customize this)

			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			String filePath = FILE_STORAGE_PATH + fileName;

			// Save the file to disk
			File destinationFile = new File(filePath);
			try {
				file.transferTo(destinationFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NewObjection newObjection = objectionService.findbyId(Long.valueOf(objId)).get();
			DocumentEntity document = new DocumentEntity();
			document.setDocumentName(file.getOriginalFilename());
			document.setFilePath(filePath);
			document.setFileType(file.getContentType());
			document.setFileSize(file.getSize());
			document.setObjection(newObjection); // Associate document with the objection
			document.setUploadedDate(new Date());
			documentRepository.save(document);
			// file.transferTo(new File(filePath));

			// Save file information (objId, documentName) to the database if necessary

			return ResponseEntity.ok("File uploaded successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading file: " + e.getMessage());
		}

	}

	@GetMapping("findAdmitCaseOrHearingCase")
	public ResponseEntity<?> findAdmitCase(@RequestParam String hearingDate) {
		try {

			List<Admission> admission = new ArrayList<Admission>();
			admission = objectionService.getAdmitcaseorHearing(hearingDate);

			List<CauselistSpecialOfficer> causlists = new ArrayList<CauselistSpecialOfficer>();

			for (Admission ad : admission) {
				NewObjection newObjection = ad.getNewObjection();

				CauselistSpecialOfficer causlist = new CauselistSpecialOfficer();
				UserEntity entity = newObjection.getUserId();
				causlist.setBjectionerName(entity.getUserName());
				causlist.setDateOfFilling(hearingDate);
				causlist.setDateOfhearing(ad.getHearingDate());
				if (newObjection.getAdmission() == null) {
					causlist.setObjid(String.valueOf(newObjection.getObjectionId()));

				} else {
					causlist.setObjid(String.valueOf(newObjection.getAdmission().getAdmisionCase()));

				}
				causlist.setObjid(ad.getAdmisionCase());
				causlist.setRespondedname(entity.getAlternateNumber());
				causlist.setHearingTime(ad.getHearingTime());

				causlists.add(causlist);
			}
			return ResponseEntity.ok(causlists);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	// update causelist
	@GetMapping("findAdmitupdateCase")
	public ResponseEntity<?> findAdmitupdateCase(@RequestParam String hearingDate, @RequestParam String caseId) {
		try {

			List<Admission> admission = new ArrayList<Admission>();
			admission = objectionService.getAdmitcaseorHearing(hearingDate);
					//getAdmitcaseorupdateCasHearing(hearingDate, caseId);

			List<CauselistSpecialOfficer> causlists = new ArrayList<CauselistSpecialOfficer>();

			for (Admission ad : admission) {
				NewObjection newObjection = ad.getNewObjection();

				CauselistSpecialOfficer causlist = new CauselistSpecialOfficer();
				UserEntity entity = newObjection.getUserId();
				causlist.setBjectionerName(entity.getUserName());
				causlist.setDateOfhearing(ad.getHearingDate());
				causlist.setDateOfFilling(hearingDate);

				causlist.setDateOfFilling(ad.getAdmissionDate());
				if (newObjection.getAdmission() == null) {
					causlist.setObjid(String.valueOf(newObjection.getObjectionId()));

				} else {
					causlist.setObjid(String.valueOf(newObjection.getAdmission().getAdmisionCase()));

				}

				causlist.setRespondedname(entity.getAlternateNumber());
				causlist.setHearingTime(ad.getHearingTime());

				causlists.add(causlist);
			}
			return ResponseEntity.ok(causlists);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping("/modifyupdateCauseCase")
	public ResponseEntity<?> updateCase(@RequestParam String caseNo, @RequestParam String dateOfHearing,
			@RequestParam String time, @RequestParam String reason, @RequestParam String caseClass,
			@RequestParam String action) {
		try {
			Admission updatedCase = objectionService.modifycause(caseNo, dateOfHearing, time, reason, caseClass,
					action);
			return ResponseEntity.ok(updatedCase); // Return updated case data
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Case not found");
		}
	}

	/// bulk transfer

	@PostMapping("/updateHearingDate")
	public ResponseEntity<?> updateHearingDate(@RequestBody BulkTransferRequest request) {
		try {
			String hearingDate = request.getHearingDate();
			List<String> caseIds = request.getCaseIds();

			if (hearingDate == null || hearingDate.isEmpty() || caseIds == null || caseIds.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Hearing date and case IDs must be provided.");
			}

			if (hearingDate == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Invalid date format. Please use YYYY-MM-DD.");
			}

			System.out.println("Hearing Date: " + hearingDate);
			System.out.println("Case IDs: " + caseIds);

			// Perform the update logic for each case ID
			for (String caseId : caseIds) {
				if(request.getUserType().equals("Collector"))
				{
					
				//	 = objectionService.hearingDatechange(hearingDate, caseId);

					
				}
				NewObjection newObjection = objectionService.hearingDatechange(hearingDate, caseId);

				// Check if the update was successful for each case
				if (newObjection == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Case not found for ID: " + caseId);
				}
			}

			return ResponseEntity.ok("Cases hearing dates successfully updated.");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating hearing dates.");
		}
	}

	/// collector
	@PostMapping("casesinform")
	public ResponseEntity<?> casesinform() {
		try {

			Casesinform casesinforn = noticeService.casesinform();

			return ResponseEntity.ok(casesinforn);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		}
		return null;

	}
	///
	@PostMapping("officerstatuscollector")
	public ResponseEntity<?> admitcolllector(@RequestBody OfficerStatusVo statusvo) {
		// TODO: process POST request

		try {
			
			String status = collectorService.updateStatuscollector(statusvo);
			if (status.equals("save")) {
				return ResponseEntity.ok(HttpStatus.ACCEPTED);
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getAmitdatacollector")
	public ResponseEntity<?> getAmitdatacollector() {
		try {
			//List<NewObjection> newObjection = objectionService.findaproved();
			
			List<CaseCollector> admissions=collectorService.getAmitdatacollector();
			return ResponseEntity.ok(admissions);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}
	
	@PostMapping("caseProcessdetailsCollector")
	public ResponseEntity<?> caseProcessdetailsCollector(@RequestBody CaseNotes casenotes) {

		try {
			System.out.println(casenotes.getSelectForm());
			if (casenotes.getAction() == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");

			}
			NewObjection admission = adminService.savecaseDetailscollector(casenotes);
			if (admission == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");
			}

			return ResponseEntity.ok(admission);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue casenotes: " + e.getMessage());
		}

	}
	//collector CaouseList

	@GetMapping("findAdmitCaseOrHearingCaseCollector")
	public ResponseEntity<?> findAdmitCaseCollector(@RequestParam String hearingDate) {
		try {

			List<CaseCollector> admission = new ArrayList<CaseCollector>();
			admission = collectorService.getAdmitcaseorHearingCaseCollector(hearingDate);

			List<CauselistSpecialOfficer> causlists = new ArrayList<CauselistSpecialOfficer>();

			for (CaseCollector ad : admission) {
				NewObjection newObjection = ad.getNewObjection();

				CauselistSpecialOfficer causlist = new CauselistSpecialOfficer();
				UserEntity entity = newObjection.getUserId();
				causlist.setBjectionerName(entity.getUserName());
				causlist.setDateOfFilling(hearingDate);
				causlist.setDateOfhearing(ad.getHearingDate());
//				if (newObjection.getAdmission() == null) {
//					causlist.setObjid(String.valueOf(newObjection.getObjectionId()));
//
//				} else {
//					causlist.setObjid(String.valueOf(newObjection.getAdmission().getAdmisionCase()));
//
//				}
			//	causlist.setObjid(ad.getAdmisionCase());
				causlist.setObjid(ad.getCollectorCase());
				causlist.setRespondedname(entity.getAlternateNumber());
				causlist.setHearingTime(ad.getHearingTime());

				causlists.add(causlist);
			}
			return ResponseEntity.ok(causlists);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	// update causelist
	@GetMapping("findAdmitupdateCaseCollector")
	public ResponseEntity<?> findAdmitupdateCaseCollector(@RequestParam String hearingDate, @RequestParam String caseId) {
		try {

			List<CaseCollector> admission = new ArrayList<CaseCollector>();
			
			admission = collectorService.getAdmitcaseorupdateCasHearingColector(hearingDate, caseId);

			List<CauselistSpecialOfficer> causlists = new ArrayList<CauselistSpecialOfficer>();

			for (CaseCollector ad : admission) {
				NewObjection newObjection = ad.getNewObjection();

				CauselistSpecialOfficer causlist = new CauselistSpecialOfficer();
				UserEntity entity = newObjection.getUserId();
				causlist.setBjectionerName(entity.getUserName());
				causlist.setDateOfhearing(ad.getHearingDate());
				causlist.setDateOfFilling(hearingDate);

				causlist.setDateOfFilling(ad.getAdmissionDate());
				if (newObjection.getAdmission() == null) {
					causlist.setObjid(String.valueOf(newObjection.getObjectionId()));

				} else {
					causlist.setObjid(String.valueOf(newObjection.getAdmission().getAdmisionCase()));

				}

				causlist.setRespondedname(entity.getAlternateNumber());
				causlist.setHearingTime(ad.getHearingTime());

				causlists.add(causlist);
			}
			return ResponseEntity.ok(causlists);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@PostMapping("/modifyupdateCauseCaseCollector")
	public ResponseEntity<?> updateCaseCollector(@RequestParam String caseNo, @RequestParam String dateOfHearing,
			@RequestParam String time, @RequestParam String reason, @RequestParam String caseClass,
			@RequestParam String action) {
		try {
			CauselistSpecialOfficer updatedCase = collectorService.modifycauseCOllector(caseNo, dateOfHearing, time, reason, caseClass,
					action);
			return ResponseEntity.ok(updatedCase); // Return updated case data
		} catch (Exception e) {
			return ResponseEntity.status(404).body("Case not found");
		}
	}

	/// bulk transfer
	/*
	@PostMapping("/updateHearingDate")
	public ResponseEntity<?> updateHearingDateCollector(@RequestBody BulkTransferRequest request) {
		try {
			String hearingDate = request.getHearingDate();
			List<String> caseIds = request.getCaseIds();

			if (hearingDate == null || hearingDate.isEmpty() || caseIds == null || caseIds.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Hearing date and case IDs must be provided.");
			}

			if (hearingDate == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Invalid date format. Please use YYYY-MM-DD.");
			}

			System.out.println("Hearing Date: " + hearingDate);
			System.out.println("Case IDs: " + caseIds);

			// Perform the update logic for each case ID
			for (String caseId : caseIds) {
				NewObjection newObjection = objectionService.hearingDatechange(hearingDate, caseId);

				// Check if the update was successful for each case
				if (newObjection == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Case not found for ID: " + caseId);
				}
			}

			return ResponseEntity.ok("Cases hearing dates successfully updated.");

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while updating hearing dates.");
		}
	}
*/
	
	
	@GetMapping("findcaseForFreehold")
	public ResponseEntity<?> findcaseForFreehold(@RequestParam String caseId) {
		try {

			Admission admission = new Admission();
			admission = objectionService.findcaseForFreehold(caseId);

		//	List<CauselistSpecialOfficer> causlists = new ArrayList<CauselistSpecialOfficer>();
			return ResponseEntity.ok(admission); // Return updated case data

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();;
		}
		return null;
}
	
	
	
	///FreeHold========================

	
	@PostMapping("/fileFreeHoldInitiate")
	public ResponseEntity<?> sumbitFreeHoldInitiate(@RequestPart("objection") String objectionJson,
			@RequestPart(value = "files", required = false) MultipartFile[] files,
			@RequestPart("username") String username) {
		try {
			// Debugging the received inputs
			System.out.println("Received objection JSON: " + objectionJson);
			System.out.println("Received username: " + username);
			System.out.println("Number of files: " + (files != null ? files.length : 0));

			// Convert the JSON string to ObjectionEntity
			ObjectMapper objectMapper = new ObjectMapper();
			FreeHoldIntiate objectionVo = objectMapper.readValue(objectionJson, FreeHoldIntiate.class);

			FreeholdIntitalCollector entity = new FreeholdIntitalCollector();
			entity.setDistrictName(objectionVo.getDistrict());
			entity.setCircleName(objectionVo.getCircle());
			entity.setHalka(objectionVo.getHalka());
			entity.setMozza(objectionVo.getMauja());
			entity.setKhattaNumber(objectionVo.getKhata());
			entity.setPlotNumber(objectionVo.getPlot());
			entity.setAcre(objectionVo.getAcre());
			entity.setDismil(objectionVo.getDismil());
			entity.setOccupantPhone(objectionVo.getPhone());
			//entity.setObjection(objectionVo.getob());
			//entity.setDocumentType(objectionVo.getDocumentType());
			CaseCollector caseCollector =new CaseCollector();
			caseCollector.setAdmissionDate(objectionVo.getAdmissionDate());
			caseCollector.setAdmissionTime(objectionVo.getAdmissionTime());
			String caseId=generateCollectorCase(entity);
			 String nextCaseI="";
			if(caseId!=null)
			{
				CaseCollector caseCollector2=caseCollectorRepo.findByCollectorCase(caseId);
				try{
				String currentCaseId=caseCollector2.getCollectorCase();
				

		        // Extract the numeric part of the caseId (the part after the last dash)
		        String[] parts = currentCaseId.split("-");

		        // The numeric part is the last part (index 4) of the array
		        String numericPart = parts[4];

		        // Increment the numeric part
		        int currentNumber = Integer.parseInt(numericPart); // Convert the string to an integer
		        int nextNumber = currentNumber + 1; // Increment the number

		        // Format the new number to have 5 digits (i.e., padding with leading zeros)
		        String nextNumericPart = String.format("%05d", nextNumber);

		        // Construct the new case ID with the incremented numeric part
		        nextCaseI= String.format("Case-COLL-%s-%s-%s", parts[1], parts[2], nextNumericPart);

		        System.out.println("Next Case ID: " + nextCaseI);
				}
				catch (Exception e) {
					// TODO: handle exception
					nextCaseI=caseId;

					e.printStackTrace();
				}
				
			
			}
			caseCollector.setCollectorCase(nextCaseI);
			entity.setCaseCollector(caseCollector);
			UserEntity user = new UserEntity();
		//	user =null;userService.getUserById(Long.valueOf(objectionVo.getUserId()));
//			entity.setUserId(user);
//			username = user.getUserName();
			// NewObjection objection = objectionService.savedata(entity);

			// Call the service to handle the objection and files
			String objectionId = null;
					//objectionService.submitObjection(entity, files, username);
			//DocumentSave
			FreeholdIntitalCollector collector=new FreeholdIntitalCollector();
			
			 if (files != null && files.length > 0) {
		            List<DocumentEntity> savedDocuments = new ArrayList<>();
		            
		            // Iterate over files and save them
		            for (MultipartFile file : files) {
		                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		                String filePath = FILE_STORAGE_PATH + fileName;

		                // Save the file to disk
		                File destinationFile = new File(filePath);
		                try {
							file.transferTo(destinationFile);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		                // Create DocumentEntity object and save its metadata
		                DocumentEntity document = new DocumentEntity();
		                document.setDocumentName(file.getOriginalFilename());
		                document.setFilePath(filePath);
		                document.setFileType(file.getContentType());
		                document.setFileSize(file.getSize());
		                document.setUploadedDate(new Date());
		                //document.setDocumentType(objection.getDocumentType());
		                document.setCaseId(caseId);
		                document.setObjection(null);

		                savedDocuments.add(document);
		            }

		            // Save all documents to the database
		            documentRepository.saveAll(savedDocuments);
					 collector=freeholdIntitalCollectorRepo.save(entity);

			 }

			
			///
			

			// Respond with the objectionId after submission
			return ResponseEntity.ok(Collections.singletonMap("objectionId", collector));
		} catch (Exception e) {
			e.printStackTrace(); // Log the full exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	
	
	


public String generateCollectorCase(FreeholdIntitalCollector entity) {
	String caseId;
	    String district = entity.getDistrictName().toUpperCase();  // Assuming district is in the entity
	    String year = LocalDate.now().getYear() % 100 < 10 
	                    ? "0" + (LocalDate.now().getYear() % 100) 
	                    : Integer.toString(LocalDate.now().getYear() % 100);  // Format as two-digit yeartry {
	    CaseCollector caseCollector=new CaseCollector();
	    
	    try {
		int initial = 0001;
	//	int currentYear = Year.now().getValue();
	    String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year

		//String caseId = user.getCaseId();
		// Fetch last inserted user
		
		Optional<CaseCollector> lastInsertedUser = caseCollectorRepo.findTopByDistrictOrderByCurrentDateDesc(district);
		if (lastInsertedUser.isPresent()) {
			String caseId2 = lastInsertedUser.get().getCollectorCase();
			String[] parts = caseId2.split("-");  // Splitting by '-'
			int caseNumber = Integer.parseInt(parts[parts.length - 1]);
			int finalValue = caseNumber +1;
			String formattedValue = String.format("%04d", finalValue);
			caseCollector.setCollectorCase("Case-COLL-"+district+"-"+year+"-"+formattedValue);
		//	System.out.println("Last inserted user: " + lastInsertedUser.get());
		} else {
			String formattedValue = String.format("%04d", initial);
			caseCollector.setCollectorCase("Case-COLL-"+district+"-"+year+"-"+formattedValue);
		}
	//	System.out.println("last result--------->" + lastInsertedUser);

     //   User savedUser = caseIdRepository.save(user);
		caseId=caseCollector.getCollectorCase();
		 return caseId;  // Fixed this line
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null; // Handle exception properly


	}

///Divv


//commisioner
@PostMapping("caseDetailsCommisioner")
public ResponseEntity<?> getcaseDetailsCommisioner(@RequestParam String userid) {
	try {

		Casesinform casesinforn = noticeService.casesinformCollector(userid);

		return ResponseEntity.ok(casesinforn);

	} catch (Exception e) {
		e.printStackTrace();
		ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

	}
	return null;

}



@PostMapping("casesinformDivv")
public ResponseEntity<?> casesinformDivv() {
	try {

		Casesinform casesinforn = noticeService.casesinformDivv();

		return ResponseEntity.ok(casesinforn);

	} catch (Exception e) {
		e.printStackTrace();
		ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

	}
	return null;

}





@PostMapping("/getobjectionCommisonerpending")
public ResponseEntity<?> getobjectionCommisonerpending(@RequestParam String userId) {
	try {
		//List<NewObjection> newObjection = objectionService.findaproved();
		
		List<Admission> admission=admissionRepo.findAlldivdetailsById();
		return ResponseEntity.ok(admission);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error retrieving objections: " + e.getMessage());
	}

}

@PostMapping("officerstatuscommssioner")
public ResponseEntity<?> admitcommissioner(@RequestBody OfficerStatusVo statusvo) {
	// TODO: process POST request

	try {
		
		String status = commissionerService.updateStatuscommsioner(statusvo);
		if (status.equals("save")) {
			return ResponseEntity.ok(HttpStatus.ACCEPTED);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
}


//download 
@GetMapping("/downloadWord")
public ResponseEntity<InputStreamResource> downloadWordFile() throws IOException {
  // Path to the Word file on the server
  File file = new File("C:\\Users\\Administrator\\Downloads\\Service Appeal case 05 of 2022- Niraj Kumar.docx");

  // Check if the file exists
  if (!file.exists()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Create an InputStream from the file
  InputStream inputStream = new FileInputStream(file);

  // Set headers to prompt the file download
  HttpHeaders headers = new HttpHeaders();
  headers.add("Content-Disposition", "attachment; filename=" + file.getName());
  headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

  // Return the file as a stream in the response
  return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
}


@GetMapping("/getAmitdataCommisioner")
public ResponseEntity<?> getAmitdataCommisioner(@RequestParam String userId ) {
	try {
		//List<NewObjection> newObjection = objectionService.findaproved();
		
		List<CaseCommissioner> admissions=commissionerService.getAmitdatacommissioner();
		return ResponseEntity.ok(admissions);
	} catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error retrieving objections: " + e.getMessage());
	}

}



}
