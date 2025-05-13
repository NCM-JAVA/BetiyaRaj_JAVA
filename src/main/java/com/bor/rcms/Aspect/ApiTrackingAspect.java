//package com.bor.rcms.Aspect;
//
//import com.bor.rcms.dto.UserTrackingDto;
//import com.bor.rcms.service.UserTrackingService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.LocalDateTime;
//import java.time.Instant;
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Aspect
//@Component
//public class ApiTrackingAspect {
//
//    @Autowired
//    private UserTrackingService trackingService;
//
//    // Track all public methods in controllers
//    @Around("execution(public * com.bor.rcms.controller..*.*(..))")
//    public Object trackAllControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        String methodName = signature.getMethod().getName();
//        String className = pjp.getTarget().getClass().getSimpleName();
//        
//        // Get request info if available (for web requests)
//        HttpServletRequest request = null;
//        try {
//            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        } catch (Exception e) {
//            // Not a web request, proceed with null request
//        }
//
//        // Record start time
//        Instant startInstant = Instant.now();
//        Timestamp startTimestamp = Timestamp.from(startInstant);
//        long startMillis = System.currentTimeMillis();
//
//        // Build tracking DTO
//        UserTrackingDto dto = new UserTrackingDto();
//        dto.setUserId(getCurrentUserId());
//        dto.setModule(className);
//        dto.setAction(methodName);
//        
//        if (request != null) {
//            dto.setApiEndpoint(request.getRequestURI());
//            dto.setHttpMethod(request.getMethod());
//        } else {
//            dto.setApiEndpoint(className + "." + methodName);
//        }
//        
//        dto.setStatus("STARTED");
//        dto.setCreatedate(LocalDateTime.now());
//        dto.setStartTime(startTimestamp);
//        
//        // Capture method arguments (consider sanitizing sensitive data in production)
//        String args = Arrays.stream(pjp.getArgs())
//                .map(arg -> arg != null ? arg.toString() : "null")
//                .collect(Collectors.joining(", "));
//        dto.setParameters(args.length() > 500 ? args.substring(0, 500) + "..." : args);
//
//        try {
//            Object result = pjp.proceed();
//            
//            // Record success
//            Instant endInstant = Instant.now();
//            long executionTime = System.currentTimeMillis() - startMillis;
//            
//            dto.setStatus("SUCCESS");
//            dto.setEndTime(Timestamp.from(endInstant));
//            dto.setExecutiontime(executionTime);
//            
//            // Capture result type (but not large data)
//            if (result != null) {
//                String resultType = result.getClass().getSimpleName();
//                if (result instanceof ResponseEntity) {
//                    ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
//                    resultType += "<" + (responseEntity.getBody() != null ? 
//                            responseEntity.getBody().getClass().getSimpleName() : "null") + ">";
//                    dto.setStatus(String.valueOf(responseEntity.getStatusCodeValue()));
//                }
//                dto.setResultType(resultType);
//            }
//            
//            trackingService.logActivity(dto);
//            return result;
//            
//        } catch (Exception e) {
//            // Record failure
//            Instant endInstant = Instant.now();
//            long executionTime = System.currentTimeMillis() - startMillis;
//            
//            dto.setStatus("FAILED: " + e.getClass().getSimpleName());
//            dto.setEndTime(Timestamp.from(endInstant));
//            dto.setExecutiontime(executionTime);
//            dto.setErrorMessage(e.getMessage());
//            
//            trackingService.logActivity(dto);
//            throw e;
//        }
//    }
//
//    private Long getCurrentUserId() {
//        // Implement your actual user ID retrieval logic
//        // Example: return SecurityContextHolder.getContext().getAuthentication().getName();
//        return 1L; // Temporary placeholder
//    }
//}