package com.bor.rcms.controller;
import org.springframework.web.bind.annotation.*;

import com.bor.rcms.dto.UserTrackingDto;
import com.bor.rcms.service.UserTrackingService;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class UserTrackingController {
    
    private final UserTrackingService trackingService;
    
    public UserTrackingController(UserTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping
    public void logActivity(@RequestBody UserTrackingDto dto) {
        trackingService.logActivity(dto);
    }

    @GetMapping("/user/{userId}")
    public List<UserTrackingDto> getUserActivities(@PathVariable Long userId) {
        return trackingService.getActivitiesByUser(userId);
    }
}