package com.usha.gapi.models;

import java.util.Date;

public class ResponseBean {
	String callid ;
	ASCGeocode ascGeocode ;;
	CustomerGeocode custGeocode ;
	String distance ;
	String wsResponseDateTime ;
	
	
	public String getWsResponseDateTime() {
		return wsResponseDateTime;
	}
	public void setWsResponseDateTime(String wsResponseDateTime) {
		this.wsResponseDateTime = wsResponseDateTime;
	}
	public String getCallid() {
		return callid;
	}
	public void setCallid(String callid) {
		this.callid = callid;
	}
	public ASCGeocode getAscGeocode() {
		return ascGeocode;
	}
	public void setAscGeocode(ASCGeocode ascGeocode) {
		this.ascGeocode = ascGeocode;
	}
	public CustomerGeocode getCustGeocode() {
		return custGeocode;
	}
	public void setCustGeocode(CustomerGeocode custGeocode) {
		this.custGeocode = custGeocode;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public static class ASCGeocode{
		String latitude ;
		String longitude ;
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
	public static class CustomerGeocode{
		String latitude ;
		String longitude ;
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
