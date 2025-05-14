//package com.bor.rcms.service;
//
//import java.sql.Date;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import com.bor.rcms.dto.UserTrackingDTO;
//import com.bor.rcms.entity.UserTracking;
//import com.bor.rcms.repository.UserTrackingRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserTrackingServiceImpl implements UserTrackingService {
//	@Autowired
//	private final UserTrackingRepository repository;
//	@Autowired
//	private final ModelMapper modelMapper;
//	
//	public UserTrackingServiceImpl(UserTrackingRepository repository, ModelMapper modelMapper) {
//        this.repository = repository;
//        this.modelMapper = modelMapper;
//	}
//	@Override
//	@Async
//	public void logActivity(UserTrackingDTO trackingDto) {
//		UserTracking tracking = modelMapper.map(trackingDto, UserTracking.class);
//	//tracking.setCreatedate(LocalDateTime.now());
//		repository.save(tracking);
//	}
//
////	@Override
////	public List<UserTrackingDTO> getActivitiesByUser(Long userId) {
////		return repository.findByUserId(userId).stream().map(entity -> modelMapper.map(entity, UserTrackingDto.class))
////				.collect(Collectors.toList());
////	}
////}
