package com.bor.rcms.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bor.rcms.config.CourtFeeSlab;
import com.bor.rcms.config.CourtFeeWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CourtFeeService {


	public Optional<CourtFeeSlab> getFeeForAmount(double amount) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("court_fee_slabs.json");
            CourtFeeWrapper wrapper = mapper.readValue(is, CourtFeeWrapper.class);

            return wrapper.getCourt_fees().stream()
                    .filter(slab -> amount >= slab.getFrom() && amount < slab.getTo())
                    .findFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
	}
}