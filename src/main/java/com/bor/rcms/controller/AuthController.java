package com.bor.rcms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bor.rcms.dto.AuthRequest;
import com.bor.rcms.dto.AuthResponse;
import com.bor.rcms.dto.EmailRequest;
import com.bor.rcms.dto.LoginRequest;
import com.bor.rcms.dto.OTPRequest;
import com.bor.rcms.dto.OtpLoginRequest;
import com.bor.rcms.dto.UserRegistrationRequest;
import com.bor.rcms.entity.CertificateDebator;
import com.bor.rcms.entity.CommisionaryMap;
import com.bor.rcms.entity.RoleEntity;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.CertificatDebatorRepo;
import com.bor.rcms.repository.CommisionaryMapRepo;
import com.bor.rcms.repository.RoleRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.CaseRecodeRes;
import com.bor.rcms.resonse.Casesinform;
import com.bor.rcms.resonse.LoginResponse;
import com.bor.rcms.response.CaptchaResponsed;
import com.bor.rcms.response.StatusRes;
import com.bor.rcms.response.StatusResponse;
import com.bor.rcms.security.EmailService;
import com.bor.rcms.service.NoticeService;
import com.bor.rcms.service.ObjectionService;
import com.bor.rcms.service.UserService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:4200") 

public class AuthController {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ObjectionService objectionService;

	@Autowired
	private CertificatDebatorRepo certificatDebatorRepo;
	
	@Autowired CommisionaryMapRepo commisionaryMapRepo;
	
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.loginAndGenerateToken(request.getUserName(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/login-officer")
    public ResponseEntity<?> loginOfficer(@RequestBody LoginRequest request) {
    	
    	if(request.getEmail()!=null&& request.getAct().equals("PDR"))
    	{
        	LoginResponse   Stringeres  = userService.loginOfficerEmail(request.getEmail(), request.getPassword(),request.getUserType());
            return ResponseEntity.ok(Stringeres);

    	}else {
    	LoginResponse   Stringeres  = userService.loginOfficer(request.getUserName(), request.getPassword(),request.getUserType());
        return ResponseEntity.ok(Stringeres);
    	}
      //  return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_GATEWAY);
    }
    @PostMapping("/findemail")
	public ResponseEntity<?> findemail(@RequestBody Map<String, String> request) {
	    try {
	        String email = request.get("email");  // Extract phone number from the request body
	        UserEntity entity = repository.findByEmail(email);

	        if (entity != null) {
	            // Return a JSON response
	            return ResponseEntity.ok(Map.of("message", "Try another email"));
	        } else {
	            // Return a JSON response
	            return ResponseEntity.ok(Map.of("message", "email  is available"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
    //otpGenerate
    
    
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
    public ResponseEntity<?> loginObjectioner(@RequestBody OTPRequest request) {
    	if(request.getAct().equals("PDR"))
    	{
    	if(request.getEmail()!=null&& !request.getEmail().equals(""))
    	{
    		
        	LoginResponse Stringeres = userService.loginwithEmail(request.getEmail(), request.getOtp());
            return ResponseEntity.ok(Stringeres);

        	
    	}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different email" );

    	}
    	LoginResponse Stringeres = userService.loginObjectioner(request.getUserName(), request.getOtp());
        return ResponseEntity.ok(Stringeres);
    }
    @GetMapping({"/generateCaptcha"})
	   @ResponseBody
	   public CaptchaResponsed generateCaptcha(HttpSession session) {
	      CaptchaResponsed rescap = new CaptchaResponsed();

	      try {
	         rescap = this.userService.generateCaptcha(session);
	      } catch (Exception var4) {
	         var4.printStackTrace();
	      }

	      return rescap;
	   }
    ///Registration Citizen
    
	@PostMapping("/registerCitizen")
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
		//	user.setUserName(request.getUserName());
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

			

			String roleName =null;
					if(request.getRoleName().equals("CERTIFICATE_HOLDER"))
			{
				roleName=request.getRoleName();		
				}
					else {
					roleName=request.getRoleName() != null ? request.getRoleName() : "OBJECTIONER";
					}
			UserEntity registeredUser = userService.registerUser(user, roleName);
			
		    if(registeredUser==null)
		    {
		    	
		    	res.setMessage("something wrong");
				res.setStatus("400");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different credential" + res);


		    	
		    }

			return ResponseEntity.ok(registeredUser);

		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	
	@PostMapping("/registerPDR")
	public ResponseEntity<?> registerUserPDR(@RequestBody UserRegistrationRequest request) {
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
			user.setDepartment(request.getDepartment());
			user.setSector(request.getSector());
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
			user.setBankName(request.getBankName());
			user.setBranchCode(request.getBranchCode());
		//	user.setUserName(request.getUserName());
			StatusRes res = new StatusRes();
			try {
				UserEntity entity = repository.findByEmail(request.getEmail());

				if (entity != null) {
					res.setMessage("try diiferent email ");
					res.setStatus("400");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different" + res);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			
			try {
				UserEntity entity = repository.findByPhoneNumber(request.getPhoneNumber());

				if (entity != null) {
					res.setMessage("try diiferent mobile ");
					res.setStatus("400");
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different" + res);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			

			String roleName =null;
					if(request.getRoleName().equals("CERTIFICATE_HOLDER"))
			{
				roleName=request.getRoleName();		
				}
					else {
					roleName=request.getRoleName() != null ? request.getRoleName() : "OBJECTIONER";
					}
			UserEntity registeredUser = userService.registerUserPDR(user, roleName);
			
		    if(registeredUser==null)
		    {
		    	
		    	res.setMessage("something wrong");
				res.setStatus("400");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different credential" + res);


		    	
		    }

			return ResponseEntity.ok(registeredUser);

		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	
	
	
	@PostMapping("/findMobileNumber")
	public ResponseEntity<?> findMobileNumber(@RequestBody Map<String, String> request) {
	    try {
	        String phoneNumber = request.get("phoneNumber");  // Extract phone number from the request body
	        UserEntity entity = repository.findByPhoneNumber(phoneNumber);

	        if (entity != null) {
	            // Return a JSON response
	            return ResponseEntity.ok(Map.of("message", "Try another mobile"));
	        } else {
	            // Return a JSON response
	            return ResponseEntity.ok(Map.of("message", "Phone number is available"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}


	
	@PostMapping("/findAadharNumber")
	public ResponseEntity<?> findAadharNumber(@RequestBody Map<String, String> request) {
		try {
	        String aadharNumber = request.get("aadharNumber");  // Extract phone number from the request body

			UserEntity entity=repository.findByAdhar(aadharNumber);
			
			if(entity!=null)
			{
				
				  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                          .body(Map.of("message", "Try another Aadhar"));
	        //    return ResponseEntity.notFound(Map.of("message", "Try another aadhar"));

			}
			else {
	            return ResponseEntity.ok(Map.of("message", "aadhar number is available"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());

		}

	
	}
	  @Autowired
	    private EmailService emailService;

	  @PostMapping("/send")
	    public String sendEmail(@RequestBody EmailRequest request) {
	        emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
	        return "Email sent successfully to " + request.getTo();
	    }
	
	  
	  //
	  @PostMapping("debatorLogin")
	    public ResponseEntity<?>  debatorLogin(@RequestBody LoginRequest request) {
      	StatusRes res=new  StatusRes();

			if(request.getMobileNumber()!=null&& request.getAct().equals("PDR"))
	    	{
				
	        CertificateDebator	  entity   = certificatDebatorRepo.findByPhoneNumber(request.getMobileNumber());
	        
	        if(entity==null)
	        {
	        	res.setMessage("try another mobile muber");
	        	res.setStatus("404");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

	        	String   Stringeres  = userService.debatorLoginotp(request.getMobileNumber());
	        	
	        	res.setMessage("success");
	        	res.setStatus("200");
	            return ResponseEntity.ok(res);
	        	
	           // return ResponseEntity.ok(Stringeres);

	    	}else {
	    	
	    		//LoginResponse   Stringeres  = userService.loginOfficer(request.getUserName(), request.getPassword(),request.getUserType());
	        return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
	    	}
	    }
	
	  
	  @PostMapping("/verify-otp-debator")
	    public ResponseEntity<?> logindebator(@RequestBody OTPRequest request) {
	    	if(request.getAct().equals("PDR"))
	    	{
	    	if(request.getPhoneNumber()!=null&& !request.getPhoneNumber().equals(""))
	    	{
	    		
	        	LoginResponse Stringeres = userService.loginwithphoneNumberDebator(request.getPhoneNumber(), request.getOtp());
	        	
	            return ResponseEntity.ok(Stringeres);
	        	
	    	}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(": try different mobile" );

	    	}
			return null;
	    	
	    }
	  
	  //
	  
	  @GetMapping("getcommisonorydistrict")
	    public ResponseEntity<?> getComissioneryDistrict() {
	        StatusResponse<List<String>> response = new StatusResponse<>();

		  try {
			  
			  List<String> commisionaryMap=commisionaryMapRepo.findDistinctCommisonary();
			  
			  if(commisionaryMap!=null)
				  
			  {
				  
				  
				  response.setMessage("success");
				  response.setStatus("200");
				  response.setOption(commisionaryMap);
			        return ResponseEntity.ok(response);
			  }
			  response.setMessage("not found");
			  response.setStatus("404");
			  
			     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		  }
		  catch (Exception e) {
			// TODO: handle exception
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}
		  
		  
	  }
	  
	  
	  @GetMapping("getAlldistrict")
	    public ResponseEntity<?> getAlldistrict() {
	        StatusResponse<List<String>> response = new StatusResponse<>();

		  try {
			  
			  List<String> commisionaryMap=commisionaryMapRepo.findDistinctdistrct();
			  
			  if(commisionaryMap!=null)
				  
			  {
				  
				  
				  response.setMessage("success");
				  response.setStatus("200");
				  response.setOption(commisionaryMap);
			        return ResponseEntity.ok(response);
			  }
			  response.setMessage("not found");
			  response.setStatus("404");
			  
			     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		  }
		  catch (Exception e) {
			// TODO: handle exception
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}
		  
		  
	  }
	  
	  @GetMapping("getdistrictBycommsionary")
	  public ResponseEntity<?> getdistrictBycommsionary(@RequestParam String commissName) {
	        StatusResponse<List<CommisionaryMap>> response = new StatusResponse<>();

	      try {
	          List<CommisionaryMap> district = commisionaryMapRepo.findByCommisonary(commissName);

	          if (!district.isEmpty()) {
	              response.setMessage("success");
	              response.setStatus("200");
	              response.setOption(district);
	              return ResponseEntity.ok(response);
	          }

	          response.setMessage("not found");
	          response.setStatus("404");
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	      } catch (Exception e) {
	          response.setMessage("error occurred");
	          response.setStatus("500");
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	      }
	  }

	  
	//
	
	
}
