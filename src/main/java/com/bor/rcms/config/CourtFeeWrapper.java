package com.bor.rcms.config;

import java.util.List;
public class CourtFeeWrapper {
    private List<CourtFeeSlab> court_fees;

    public CourtFeeWrapper() {
        // Required by Jackson
    }

    public CourtFeeWrapper(List<CourtFeeSlab> court_fees) {
        this.court_fees = court_fees;
    }

    public List<CourtFeeSlab> getCourt_fees() {
        return court_fees;
    }

    public void setCourt_fees(List<CourtFeeSlab> court_fees) {
        this.court_fees = court_fees;
    }

    @Override
    public String toString() {
        return "CourtFeeWrapper [court_fees=" + court_fees + "]";
    }

	
	


}
