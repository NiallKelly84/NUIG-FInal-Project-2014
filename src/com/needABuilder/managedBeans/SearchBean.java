package com.needABuilder.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.needABuilder.dao.SearchDAO;
import com.needABuilder.entities.BusinessAccount;
import com.needABuilder.entities.Locations;
import com.needABuilder.entities.Projects;
/*
 * 
 * Managed Bean class for user search of database for contractors
 * 
 */
@ManagedBean //Declares the class to be a managed bean and lists it in the persistence.xml
@SessionScoped   //Defines the scope of the managed bean
public class SearchBean implements Serializable {

	//declare class variables
	private static final long serialVersionUID = -2107387060867715013L;
	private int countyId;
	private List<BusinessAccount> contractorList = new ArrayList<BusinessAccount>();
	private List<BusinessAccount> myList = new ArrayList<BusinessAccount>();
	private int companyId;
	private String companyName;
	private String companyAddress1;
	private String companyAddress2;
	private String companyTown;
	private String companyCounty;
	private String companyPhone;
	private String companyEmail;
	private String companyDescription;
	private List<Locations> locationsList = new ArrayList<Locations>();
	private List<Projects> projectList = new ArrayList<Projects>();
	private SearchDAO s = new SearchDAO();

	//method to list accounts with work locations matching the county id choosen by the user
	public List<BusinessAccount> searchContractors(int countyId) {

		String countyName = s.showSelectedCounty(countyId);//converts county id to county name
		contractorList = s.listCompaniesInSearchArea(countyName);	//returns a list of companies with work locations in this county

		return contractorList;
	}

	//getters and setters
	public void setLocationsList(List<Locations> locationsList) {
		this.locationsList = locationsList;
	}

	public List<Locations> getLocationsList() {

		return locationsList;
	}

	public List<Projects> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Projects> projectList) {
		this.projectList = projectList;
	}

	public int getCountyId() {
		return countyId;
	}

	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

	public List<BusinessAccount> getContractorList() {
		return contractorList;
	}

	public void setContractorList(List<BusinessAccount> contractorList) {
		this.contractorList = contractorList;
	}

	public List<BusinessAccount> getMyList() {
		return myList;
	}

	public void setMyList(List<BusinessAccount> myList) {
		this.myList = myList;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyAddress1() {
		return companyAddress1;
	}

	public String getCompanyAddress2() {
		return companyAddress2;
	}

	public String getCompanyTown() {
		return companyTown;
	}

	public String getCompanyCounty() {
		return companyCounty;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	//create FacesContext object to pass parameters between JSF pages
	public String displayCompanyName() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.setCompanyId(Integer.parseInt(getCompIdParam(fc)));
		this.setCompanyName(getCompanyNameParam(fc));
		this.setCompanyAddress1(getCompanyAddress1Param(fc));
		this.setCompanyAddress2(getCompanyAddress2Param(fc));
		this.setCompanyTown(getCompanyTownParam(fc));
		this.setCompanyCounty(getCompanyCountyParam(fc));
		this.setCompanyPhone(getCompanyPhoneParam(fc));
		this.setCompanyEmail(getCompanyEmailParam(fc));

		this.setCompanyDescription(getCompanyDescriptionParam(fc));

		return "success";
	}

	//FacesContext getters and setters for attributes of accounts. Can be accessed by JSF pages
	public String getCompIdParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compId");

	}

	public String getCompanyNameParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compName");

	}

	public String getCompanyAddress1Param(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compAddress1");

	}

	public String getCompanyAddress2Param(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compAddress2");

	}

	public String getCompanyTownParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compTown");

	}

	public String getCompanyCountyParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compCounty");

	}

	public String getCompanyPhoneParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compPhone");

	}

	public String getCompanyEmailParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compEmail");

	}

	public String getCompanyDescriptionParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compDescription");

	}

	public String getCompanyLocationsParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		return params.get("compLocations");

	}

	private void setCompanyName(String companyNameParam) {
		this.companyName = companyNameParam;
	}

	private void setCompanyAddress1(String companyAddress1Param) {
		this.companyAddress1 = companyAddress1Param;
	}

	private void setCompanyAddress2(String companyAddress2Param) {
		this.companyAddress2 = companyAddress2Param;
	}

	private void setCompanyTown(String companyTownParam) {
		this.companyTown = companyTownParam;
	}

	private void setCompanyCounty(String companyCountyParam) {
		this.companyCounty = companyCountyParam;
	}

	private void setCompanyPhone(String companyPhoneParam) {
		this.companyPhone = companyPhoneParam;
	}

	private void setCompanyEmail(String companyEmailParam) {
		this.companyEmail = companyEmailParam;
	}

	private void setCompanyDescription(String companyDescriptionParam) {
		this.companyDescription = companyDescriptionParam;
	}

}
