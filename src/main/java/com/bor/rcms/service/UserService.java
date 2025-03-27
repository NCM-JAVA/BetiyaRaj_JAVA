package com.bor.rcms.service;

import okhttp3.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.RoleEntity;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.RoleRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.LoginResponse;
import com.bor.rcms.response.CaptchaResponsed;
import com.bor.rcms.security.CustomUserDetailsService;
import com.bor.rcms.security.JwtUtil;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public UserEntity registerUser(UserEntity user, String roleName) {
		if (userRepository.existsByUserName(user.getUserName())) {
			throw new RuntimeException("Username already exists. Please choose a different username.");
		}

		// Assign default role if none provided
		RoleEntity role = roleRepository.findByRoleName(roleName);
		if (role == null) {
			role = new RoleEntity(roleName, roleName);
			role = roleRepository.save(role);
		}

		user.setRole(role);

		// user.setPassword(user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password

		user.setCreatedDate(new Date());
		UserEntity user1 = userRepository.findByPhoneNumber(user.getPhoneNumber());
		String otp = generateOTPs();
		String apiKey = "5c20a6b499570"; // Example API key
		String sender = "NCMSMS"; // Sender ID
		String mobileNo = user.getPhoneNumber(); // Recipient mobile number
		String text = otp
				+ " is mobile number verification OTP. Do not share with others Team JDA (NETCREATIVEMIND SOLUTIONS)."; // SMS
																														// content

		// Build the request URL with dynamic parameters
		String url = String.format("https://www.mysmsapp.in/api/push.json?apikey=%s&sender=%s&mobileno=%s&text=%s",
				apiKey, sender, mobileNo, text);

		OkHttpClient client = new OkHttpClient();
		// Create the request body (empty in this case, since the data is in the URL)
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");

		// Build the request
		Request request = new Request.Builder().url(url).method("POST", body).build();

		// Execute the request
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				System.out.println("SMS sent successfully!");
				user.setOtp(otp);
				UserEntity userres = userRepository.save(user);

				return userres;

				// return "OTP sent successfully to " + userName + ". " +
				// response.body().string();

			} else {
				System.out.println("Error sending SMS: " + response.message());
			}
		} catch (Exception e) {
			System.out.println("Request failed: " + e.getMessage());
			// return "Failed to send OTP: ";

		}

		return null;
	}

	public UserEntity getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	}

	public void deleteUser(Long userId) {
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		userRepository.delete(user);
	}

	public UserEntity loginUser(String userName, String password) {

		UserEntity user = new UserEntity();
		try {

			// user=userRepository.findByPhoneNumber(userName);

			user = userRepository.findByUserName(userName).get();
//    		
//        	Optional<UserEntity> =	userRepository.findByUserName(userName);
//                .orElseThrow(() -> new RuntimeException("Invalid Username or Password"));

//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Invalid Username or Password");
//        }

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// return "Login successful! Welcome " + usr.getFullName();
		return user;
	}

	// private final String SMS_API_URL =
	// "https://smsprovider.com/send?mobile={mobile}&message={message}";

	public String loginWithOTP(String userName, String password) throws IOException {
		UserEntity user = userRepository.findByPhoneNumber(userName);
		String otp = generateOTPs();
		String apiKey = "5c20a6b499570"; // Example API key
		String sender = "NCMSMS"; // Sender ID
		String mobileNo = userName; // Recipient mobile number
		String text = otp
				+ " is mobile number verification OTP. Do not share with others Team JDA (NETCREATIVEMIND SOLUTIONS)."; // SMS
																														// content

		// Build the request URL with dynamic parameters
		String url = String.format("https://www.mysmsapp.in/api/push.json?apikey=%s&sender=%s&mobileno=%s&text=%s",
				apiKey, sender, mobileNo, text);

		OkHttpClient client = new OkHttpClient();
		// Create the request body (empty in this case, since the data is in the URL)
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");

		// Build the request
		Request request = new Request.Builder().url(url).method("POST", body).build();

		// Execute the request
		try (Response response = client.newCall(request).execute()) {
			if (response.isSuccessful()) {
				System.out.println("SMS sent successfully!");
				user.setOtp(otp);
				userRepository.save(user);

				return "OTP sent successfully to " + userName + ". " + response.body().string();

			} else {
				System.out.println("Error sending SMS: " + response.message());
			}
		} catch (Exception e) {
			System.out.println("Request failed: " + e.getMessage());
			return "Failed to send OTP: ";

		}
		return null;

	}

	public UserEntity verifyOTP(String userName, String enteredOTP) {
		UserEntity user = userRepository.findByPhoneNumber(userName);
		// .orElseThrow(() -> new RuntimeException("User not found"));

		if (user.getOtp() == null || !user.getOtp().equals(enteredOTP)) {
			throw new RuntimeException("Invalid or expired OTP");
		}

		// **Clear OTP after successful login**
		// user.setOtp(null);
		userRepository.save(user);

		return user;
	}

	private String generateOTP() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(999999));
	}

	private String sendOTP(String mobileNumber, String otp) {
		String smsMessage = "Your login OTP is: " + otp;

		// return restTemplate.getForObject(SMS_API_URL, String.class);
		return null;
	}

	public String generateOTPs() {
		int otp = (int) (Math.random() * 9000) + 1000; // generates a 4-digit OTP
		return String.valueOf(otp);
	}

	public String sendOtprequest(String phoneNumber) {
		String otp = generateOTP();
		UserEntity user = userRepository.findByPhoneNumber(phoneNumber);
		// **Store OTP in DB**
		user.setOtp(otp);
		userRepository.save(user);

		// **Send OTP via SMS**
		String smsResponse = sendOTP(user.getPhoneNumber(), otp);

		return "OTP sent successfully to " + user.getPhoneNumber() + ". " + smsResponse;
	}

	public String upadateStatus(String status, String userId) {
		UserEntity userEntity = userRepository.findById(Long.valueOf(userId)).orElse(null);
		if (status.equals("Inactive")) {
			userEntity.setStatus("Inactive");
			UserEntity user = userRepository.save(userEntity);
			if (user != null) {
				return "save";
			}
		}
			else if (status.equals("Active")) {
				userEntity.setStatus("Active");
				UserEntity user1 = userRepository.save(userEntity);
				if (user1 != null) {
					return "save";
				}
		

		}
		return userId;
	}
	   private int expectedSolution;

	public CaptchaResponsed generateCaptcha(HttpSession session) {
	      CaptchaResponsed capres = new CaptchaResponsed();

	      try {
	         Random random = new Random();
	         int num1 = random.nextInt(10);
	         int num2 = random.nextInt(10);
	         int var10000 = num1 + num2;
	         capres.setFirstValue(num1);
	         capres.setSecondValue(num2);
	         capres.setCaptchStore(num1 + " + " + num2 + " = ?");
	         session.setAttribute("captcha", capres);
	         return capres;
	      } catch (Exception var7) {
	         var7.printStackTrace();
	         return null;
	      }
	}

	public String upadateStatusFreehold(String status, String userId, String freeHolreq) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String loginAndGenerateToken(String userName, String password) {
	    Optional<UserEntity> userOpt = userRepository.findByUserName(userName);

	    if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
	        throw new RuntimeException("Invalid username or password");
	    }

	    // Generate token
	    UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
	    return jwtUtil.generateToken(userDetails.getUsername());
	}
	
	public LoginResponse loginOfficer(String userName, String password, String usertype) {
	    UserEntity user = userRepository.findByUserName(userName)
	        .orElseThrow(() -> new RuntimeException("Invalid username"));

	    if (!passwordEncoder.matches(password, user.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }
	    RoleEntity role=user.getRole();
	    
	    if (usertype.equals(role.getRoleName())) {
			try {
				
				String token= jwtUtil.generateToken(user.getPhoneNumber()); 
				//List<NewObjection> newObjection = objectionService.findAll();
			//	System.out.println("df==" + newObjection);
				LoginResponse loginResponse=new LoginResponse();
				loginResponse.setDistrict(user.getDistrict());
				loginResponse.setFullName(user.getFullName());
				loginResponse.setUserId(user.getUserId());
				loginResponse.setToken(token);
				loginResponse.setRole(role);
			    return loginResponse;

				//return ResponseEntity.ok(user);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }

//		} else if (usertype.equals("BOR")) {
//		    return jwtUtil.generateToken(user.getPhoneNumber()); // or userName if preferred
//
//		} else if (usertype.equals("DivCommissioner")) {
//		    return jwtUtil.generateToken(user.getPhoneNumber()); // or userName if preferred
//
//		} else if (usertype.equals("Collector") && user.getStatus().equals("Active")) {
//		    return jwtUtil.generateToken(user.getPhoneNumber()); // or userName if preferred
//
//		}

	    return null;
	    
	 //   return jwtUtil.generateToken(user.getPhoneNumber()); // or userName if preferred
	}
	
	public String loginObjectioner(String mobileNumber, String otp) {
	    UserEntity user = userRepository.findByPhoneNumber(mobileNumber);

	    if (user == null || user.getOtp() == null || !user.getOtp().equals(otp)) {
	        throw new RuntimeException("Invalid mobile number or OTP");
	    }

	    // Optional: clear OTP after use
	    user.setOtp(null);
	    userRepository.save(user);

	    return jwtUtil.generateToken(user.getPhoneNumber());
	}


}
