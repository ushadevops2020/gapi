package com.usha.gapi.models;



public class RequestBean {
	String callid ;
	ASCDETAIL ascdetail ;
	CUSTOMERDETAIL customerdetail;
	public String getCallid() {
		return callid;
	}
	public void setCallid(String callid) {
		this.callid = callid;
	}
	public ASCDETAIL getAscdetail() {
		return ascdetail;
	}
	public void setAscdetail(ASCDETAIL ascdetail) {
		this.ascdetail = ascdetail;
	}
	public CUSTOMERDETAIL getCustomerdetail() {
		return customerdetail;
	}
	public void setCustomerdetail(CUSTOMERDETAIL customerdetail) {
		this.customerdetail = customerdetail;
	}
	public static class ASCDETAIL {
		String address;
		String state ;
		String city;
		String pincode ;
		String latitude ;
		String longitude ;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		
		
		
		
	}
	public static class CUSTOMERDETAIL {
		String address;
		String state ;
		String city;
		String pincode ;
		String latitude ;
		String longitude ;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		
		
		
		
	}
		

}
