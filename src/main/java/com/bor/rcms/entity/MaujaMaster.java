package com.bor.rcms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class MaujaMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String  distLgdCode;
 
	    private String distCode;
	    private String distName;
	    private String circleLgdCode;
	    private String circleCode;
	    private String circleName;
	    private String halkaCode;
	    private String halkaName;
	    private String maujaCode;
	    private String maujaName;
	    private String maujaLgdCode;
	
		public String getDistLgdCode() {
			return distLgdCode;
		}
		public void setDistLgdCode(String distLgdCode) {
			this.distLgdCode = distLgdCode;
		}
		public String getDistCode() {
			return distCode;
		}
		public void setDistCode(String distCode) {
			this.distCode = distCode;
		}
		public String getDistName() {
			return distName;
		}
		public void setDistName(String distName) {
			this.distName = distName;
		}
		public String getCircleLgdCode() {
			return circleLgdCode;
		}
		public void setCircleLgdCode(String circleLgdCode) {
			this.circleLgdCode = circleLgdCode;
		}
		public String getCircleCode() {
			return circleCode;
		}
		public void setCircleCode(String circleCode) {
			this.circleCode = circleCode;
		}
		public String getCircleName() {
			return circleName;
		}
		public void setCircleName(String circleName) {
			this.circleName = circleName;
		}
		public String getHalkaCode() {
			return halkaCode;
		}
		public void setHalkaCode(String halkaCode) {
			this.halkaCode = halkaCode;
		}
		public String getHalkaName() {
			return halkaName;
		}
		public void setHalkaName(String halkaName) {
			this.halkaName = halkaName;
		}
		public String getMaujaCode() {
			return maujaCode;
		}
		public void setMaujaCode(String maujaCode) {
			this.maujaCode = maujaCode;
		}
		public String getMaujaName() {
			return maujaName;
		}
		public void setMaujaName(String maujaName) {
			this.maujaName = maujaName;
		}
		public String getMaujaLgdCode() {
			return maujaLgdCode;
		}
		public void setMaujaLgdCode(String maujaLgdCode) {
			this.maujaLgdCode = maujaLgdCode;
		}
		@Override
		public String toString() {
			return "MaujaMaster [ distLgdCode=" + distLgdCode + ", distCode=" + distCode + ", distName="
					+ distName + ", circleLgdCode=" + circleLgdCode + ", circleCode=" + circleCode + ", circleName="
					+ circleName + ", halkaCode=" + halkaCode + ", halkaName=" + halkaName + ", maujaCode=" + maujaCode
					+ ", maujaName=" + maujaName + ", maujaLgdCode=" + maujaLgdCode + "]";
		}
		public MaujaMaster(Long id, String distLgdCode, String distCode, String distName, String circleLgdCode,
				String circleCode, String circleName, String halkaCode, String halkaName, String maujaCode,
				String maujaName, String maujaLgdCode) {
			super();
			this.distLgdCode = distLgdCode;
			this.distCode = distCode;
			this.distName = distName;
			this.circleLgdCode = circleLgdCode;
			this.circleCode = circleCode;
			this.circleName = circleName;
			this.halkaCode = halkaCode;
			this.halkaName = halkaName;
			this.maujaCode = maujaCode;
			this.maujaName = maujaName;
			this.maujaLgdCode = maujaLgdCode;
		}

	    
	    
}
