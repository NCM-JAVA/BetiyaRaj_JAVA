package com.bor.rcms.config;

public class CourtFeeSlab {
	
	 private int from;
	    private int to;
	    private int fee;
		public int getFrom() {
			return from;
		}
		public void setFrom(int from) {
			this.from = from;
		}
		public int getTo() {
			return to;
		}
		public void setTo(int to) {
			this.to = to;
		}
		public int getFee() {
			return fee;
		}
		public void setFee(int fee) {
			this.fee = fee;
		}
		public CourtFeeSlab(int from, int to, int fee) {
			super();
			this.from = from;
			this.to = to;
			this.fee = fee;
		}
		public CourtFeeSlab() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "CourtFeeSlab [from=" + from + ", to=" + to + ", fee=" + fee + "]";
		}
	    
	    

}
