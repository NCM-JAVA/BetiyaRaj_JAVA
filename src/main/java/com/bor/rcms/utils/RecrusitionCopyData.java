package com.bor.rcms.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bor.rcms.dto.DebatorVo;
import com.bor.rcms.dto.FileRequeistionVo;
import com.bor.rcms.dto.LegalRepersentativeVo;
import com.bor.rcms.entity.CertificateDebator;
import com.bor.rcms.entity.CertificateGuaranter;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.LegalRepresentative;
@Service
public class RecrusitionCopyData {

	public List<CertificateDebator> debtorCopyfilereq(List<DebatorVo> debatorVos) {
		// TODO Auto-generated method stub
		List<CertificateDebator> debatorlist = new ArrayList<CertificateDebator>();

		
		for (DebatorVo debatorVo : debatorVos) {
			CertificateDebator debator = new CertificateDebator();
			debator.setDebatorName(debatorVo.getDebtorName());
			debator.setAddress(debatorVo.getDebtorAddress());
			debator.setEmail(debatorVo.getDebtorEmail());
			debator.setPhoneNumber(debatorVo.getDebtorPhoneNumber());
			debator.setAddress1(debatorVo.getDebtorAddress1());

			debator.setState(debatorVo.getDebtorState());
			debator.setCity(debatorVo.getDebtorCity());
			debator.setDistrict(debatorVo.getDebtorDistrict());

			debator.setPolicestation(debatorVo.getDebtorpolicestation());
			debator.setCircle(debatorVo.getCircle());
			debator.setFatherNames(debatorVo.getDebtorfatherNames());
			debator.setSubDivision(debatorVo.getDebtorubDivision());

			debator.setPincode(debatorVo.getDebtorPincode());
			debatorlist.add(debator);

		}
		
		return debatorlist;
	}

	public List<LegalRepresentative> legalCopyfilereq(List<LegalRepersentativeVo> legalRepersentativeVos) {
		// TODO Auto-generated method stub
		List<LegalRepresentative> legalRepersentativelist = new ArrayList<LegalRepresentative>();

		for (LegalRepersentativeVo legalRepersentativeVo : legalRepersentativeVos) {

			LegalRepresentative legalRepersentative = new LegalRepresentative();

			legalRepersentative.setAddress(legalRepersentativeVo.getLegaladdress());
			legalRepersentative.setAddress(legalRepersentativeVo.getLegaladdress1());
			legalRepersentative.setAddress2(legalRepersentativeVo.getLegaladdress2());
			legalRepersentative.setApartmentNumber(legalRepersentativeVo.getLegalapartmentNumber());
			legalRepersentative.setCircle(legalRepersentativeVo.getLegalcircle());
			legalRepersentative.setCity(legalRepersentativeVo.getLegalcity());
			legalRepersentative.setDistrict(legalRepersentativeVo.getLegaldistrict());
			legalRepersentative.setEmail(legalRepersentativeVo.getLegalemail());
			legalRepersentative.setFatherNames(legalRepersentativeVo.getLegalfatherNames());
			legalRepersentative.setLegalName(legalRepersentativeVo.getLegalName());
			legalRepersentative.setPhoneNumber(legalRepersentativeVo.getLegalphoneNumber());
			legalRepersentative.setPincode(legalRepersentativeVo.getLegalpincode());
			legalRepersentative.setPolicestation(legalRepersentativeVo.getLegalpolicestation());
			legalRepersentative.setState(legalRepersentativeVo.getLegalstate());
			legalRepersentative.setSubDivision(legalRepersentativeVo.getLegalsubDivision());
			legalRepersentativelist.add(legalRepersentative);

		}
		return legalRepersentativelist;

	}

	

	public CertificateGuaranter granterCopyfilereq(FileRequeistionVo vo) {
		CertificateGuaranter granter=new CertificateGuaranter();
		granter.setGranterName(vo.getGuarantorName());

		granter.setPolicestation(vo.getGuarantorpolicestation());
		granter.setCircle(vo.getGuarantorcircle());
		granter.setFatherName(vo.getGuarantorfatherNames());
		granter.setSubDivision(vo.getGuarantorsubDivision());
		granter.setAddress(vo.getGuarantorAddress());
		granter.setEmail(vo.getGuarantorEmail());
		granter.setPhoneNumber(vo.getGuarantorPhoneNumber());
		granter.setAddress1(vo.getGuarantorAddress1());
		granter.setState(vo.getGuarantorState());
		granter.setCity(vo.getGuarantorCity());
		granter.setDistrict(vo.getGuarantorDistrict());
		granter.setPincode(vo.getGuarantorPincode());
		return granter;
	}

	public FileRequeistion reqrisitionCopyfilereq(FileRequeistionVo vo) {
		
		FileRequeistion requisition =new FileRequeistion();
		requisition.setFinancialYear(vo.getFinancialYear());
		requisition.setReason(vo.getreason());

		requisition.setInterestDueForm(vo.getInterestDueForm());
		requisition.setMissllenousFee(Double.parseDouble(vo.getMissllenousFee()));
		requisition.setPaidCourFee(Double.parseDouble(vo.getPaidCourFee()));
		requisition.setTotalCourtFee(Double.parseDouble(vo.getTotalCourtFee()));
		requisition.setTotalDemand(Double.parseDouble(vo.getTotalDemand()));
		requisition.setTotalInterestRate(Double.parseDouble(vo.getTotalInterestRate()));
		requisition.setTotalOutstandingAmmount(Double.parseDouble(vo.getTotalOutstandingAmmount()));
		return requisition;
		
	
	}

	public FileRequeistionVo granterviewCopyfilereq(CertificateGuaranter grantor) {
		// TODO Auto-generated method stub
		FileRequeistionVo vo=new FileRequeistionVo();
		vo.setGuarantorName(grantor.getGranterName());
		vo.setGuarantorAddress(grantor.getAddress());
		vo.setGuarantorAddress1(grantor.getAddress1());
		vo.setGuarantorAddress2(grantor.getAddress2());
		vo.setGuarantorState(grantor.getState());
		vo.setGuarantorCity(grantor.getCity());
		vo.setGuarantorDistrict(grantor.getDistrict());
		vo.setGuarantorPincode(grantor.getPincode());
		vo.setGuarantorPhoneNumber(grantor.getPhoneNumber());
		// vo.setGuarantorStatePhoneNumber(g.get);
		vo.setGuarantorEmail(grantor.getEmail());
		vo.setGuarantorfatherNames(grantor.getFatherName());
		vo.setGuarantorcircle(grantor.getCircle());
		vo.setGuarantorpolicestation(grantor.getPolicestation());
		vo.setCreatedDate(grantor.getCreatedDate());
		vo.setModifiedDate(grantor.getModifiedDate());
		return vo;
	}

	public DebatorVo viewDebtor(CertificateDebator debator) {
		// TODO Auto-generated method stub
		DebatorVo dvo=new DebatorVo();
		dvo.setDebtorName(debator.getDebatorName());
		dvo.setDebtorAddress(debator.getAddress());
		dvo.setDebtorAddress1(debator.getAddress1());
		dvo.setDebtorAddress2(debator.getAddress2());
		dvo.setDebtorState(debator.getState());
		dvo.setDebtorCity(debator.getCity());
		dvo.setDebtorDistrict(debator.getDistrict());
		dvo.setDebtorPincode(debator.getPincode());
		dvo.setDebtorPhoneNumber(debator.getPhoneNumber());
		dvo.setDebtorStatePhoneNumber(debator.getState()); // Check if this is correct
		dvo.setDebtorEmail(debator.getEmail());
		dvo.setDebtorfatherNames(debator.getFatherNames());
		dvo.setDebtorubDivision(debator.getSubDivision());
		dvo.setDebtorcircle(debator.getCircle());
		dvo.setDebtorpolicestation(debator.getPolicestation());
		dvo.setCircle(debator.getCircle());
		
		return dvo;
	}

	public LegalRepersentativeVo viewlegal(LegalRepresentative legalRepersentativeVo) {
		// TODO Auto-generated method stub
		LegalRepersentativeVo legalRepersentative=new LegalRepersentativeVo();
		
		legalRepersentative.setLegaladdress(legalRepersentativeVo.getAddress());
		legalRepersentative.setLegaladdress1(legalRepersentativeVo.getAddress1());
		legalRepersentative.setLegaladdress2(legalRepersentativeVo.getAddress2());
		legalRepersentative.setLegalapartmentNumber(legalRepersentativeVo.getApartmentNumber());
		legalRepersentative.setLegalcircle(legalRepersentativeVo.getCircle());
		legalRepersentative.setLegalcity(legalRepersentativeVo.getCity());
		legalRepersentative.setLegaldistrict(legalRepersentativeVo.getDistrict());
		legalRepersentative.setLegalemail(legalRepersentativeVo.getEmail());
		legalRepersentative.setLegalfatherNames(legalRepersentativeVo.getFatherNames());
		legalRepersentative.setLegalName(legalRepersentativeVo.getLegalName());
		legalRepersentative.setLegalphoneNumber(legalRepersentativeVo.getPhoneNumber());
		legalRepersentative.setLegalpincode(legalRepersentativeVo.getPincode());
		legalRepersentative.setLegalpolicestation(legalRepersentativeVo.getPolicestation());
		legalRepersentative.setLegalstate(legalRepersentativeVo.getState());
		legalRepersentative.setLegalsubDivision(legalRepersentativeVo.getSubDivision());
		return legalRepersentative;
	}

}