package com.usha.gapi;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.usha.gapi.models.ResponseBean;
import com.usha.gapi.models.ResponseBean.ASCGeocode;
import com.usha.gapi.models.ResponseBean.CustomerGeocode;
import com.usha.gapi.models.RequestBean;



/**
 * Root resource (exposed at "myresource" path)
 */
@Path("getDistance")
public class RequestResource {
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response. */
     
    @POST  
    @Consumes(MediaType.APPLICATION_JSON)  
    public Response getReq(RequestBean inputReq) {
    	String originString = "" ;
    	String destinationString = "" ;
    	String custAddressString = "" ;
    	String ascAddressString = "" ;
    	ResponseBean responseBean = new ResponseBean() ;
    	responseBean.setCallid(inputReq.getCallid());
    	ASCGeocode ASCDETAIL = new ASCGeocode();
    	if(inputReq.getCustomerdetail().getLatitude() != "" && inputReq.getCustomerdetail().getLongitude() != "") {
    		ASCDETAIL.setLatitude(inputReq.getAscdetail().getLatitude());
        	ASCDETAIL.setLongitude(inputReq.getAscdetail().getLongitude()); 
        	destinationString = inputReq.getAscdetail().getLatitude() + "," + inputReq.getAscdetail().getLongitude() ;
        	
    	}else {
    		ascAddressString = inputReq.getAscdetail().getAddress() + ", "+inputReq.getAscdetail().getCity() + ", " + inputReq.getAscdetail().getState() + " " + inputReq.getAscdetail().getPincode() ;
    		destinationString = calculateGeoCodeFromAddr(ascAddressString);
    		if(destinationString == "") {
    			ascAddressString = inputReq.getAscdetail().getCity() + ", " + inputReq.getAscdetail().getState() + " " + inputReq.getAscdetail().getPincode() ;
    			destinationString = calculateGeoCodeFromAddr(ascAddressString);
        		if(destinationString == "") {
        			ascAddressString = inputReq.getCustomerdetail().getState() + " " + inputReq.getCustomerdetail().getPincode() ;
        			destinationString = calculateGeoCodeFromAddr(ascAddressString);
        		}
    			
    		}
    		String[] ascCordinates = destinationString.split(",");
    		ASCDETAIL.setLatitude(ascCordinates[0]);
        	ASCDETAIL.setLongitude(ascCordinates[1]); 

    	}
    	   	
    	CustomerGeocode CUSTOMERDETAIL = new CustomerGeocode() ;
    	if(inputReq.getCustomerdetail().getLatitude() != "" && inputReq.getCustomerdetail().getLongitude() != "") {
    		CUSTOMERDETAIL.setLatitude(inputReq.getCustomerdetail().getLatitude());
        	CUSTOMERDETAIL.setLongitude(inputReq.getCustomerdetail().getLongitude());
        	originString = inputReq.getCustomerdetail().getLatitude() + "," + inputReq.getCustomerdetail().getLongitude() ;
        	
    	}else {
    		custAddressString = inputReq.getCustomerdetail().getAddress() + ", "+inputReq.getCustomerdetail().getCity() + ", " + inputReq.getCustomerdetail().getState() + " " + inputReq.getCustomerdetail().getPincode() ;
    		originString = calculateGeoCodeFromAddr(custAddressString);
    		if(originString == "") {
    			custAddressString = inputReq.getCustomerdetail().getCity() + ", " + inputReq.getCustomerdetail().getState() + " " + inputReq.getCustomerdetail().getPincode() ;
        		originString = calculateGeoCodeFromAddr(custAddressString);
        		if(originString == "") {
        			custAddressString = inputReq.getCustomerdetail().getState() + " " + inputReq.getCustomerdetail().getPincode() ;
            		originString = calculateGeoCodeFromAddr(custAddressString);
        		}
    			
    		}
    		String[] custCordinates = originString.split(",");
    		CUSTOMERDETAIL.setLatitude(custCordinates[0]);
        	CUSTOMERDETAIL.setLongitude(custCordinates[1]);
        	
    	}
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
    	Date date = new Date();
    	String distanceString = getDistance(originString,destinationString);
    	responseBean.setDistance(distanceString);
    	responseBean.setCustGeocode(CUSTOMERDETAIL);
    	responseBean.setAscGeocode(ASCDETAIL);
    	responseBean.setWsResponseDateTime(formatter.format(date));
    	
    	
        return Response
        	      .status(Response.Status.OK)
        	      .entity(responseBean).type((MediaType.APPLICATION_JSON))
        	      .build();
    }
   
    private String calculateGeoCodeFromAddr(String addressString) {
		// TODO Auto-generated method stub
    	String originString = "" ;
    	Client client = ClientBuilder.newClient() ;
    	Response response =  client.target("https://maps.googleapis.com/maps/api/place/findplacefromtext/json").queryParam("input", addressString)
    			.queryParam("inputtype", "textquery")
    			.queryParam("fields", "geometry")
    			.queryParam("key", "AIzaSyCQByq-LjpPGq686SWNG0AsvFnGs7UdMTw")
    			.request().get();
    	String jsonString = response.readEntity(String.class);
    	try {
    		JSONParser parse = new JSONParser();
    		JSONObject jsonObject = (JSONObject) parse.parse(jsonString);
    		JSONArray jsonArray =  (JSONArray) jsonObject.get("candidates");
    		if(jsonArray.size() > 0 ) { 
    			 JSONObject jsonObject1 = (JSONObject) jsonArray.get(0) ; 
    			 JSONObject jsonObject2 = (JSONObject) jsonObject1.get("geometry") ;
	    		 JSONObject jsonObject3 = (JSONObject) jsonObject2.get("location");
	    		 String lat =  jsonObject3.get("lat").toString() ;
	    		 String lng =  jsonObject3.get("lng").toString() ;
	    		 originString = lat + "," + lng ;
    		}
    		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return originString;
	}

	public String getDistance(String originString, String destinationString) {
    	String dist = "0 Km" ;
    	Client client = ClientBuilder.newClient() ;
    	Response response =  client.target("https://maps.googleapis.com/maps/api/distancematrix/json").queryParam("origins", originString)
			    			.queryParam("destinations", destinationString)
			    			.queryParam("key", "AIzaSyBV24tNqUQZW9QgoT860RBHUCda8dc2RYc")
			    			.request().get();
    	String jsonString = response.readEntity(String.class);
    	
    	try {
    		JSONParser parse = new JSONParser();
    		JSONObject jsonObject = (JSONObject) parse.parse(jsonString);
    		JSONArray jsonArray =  (JSONArray) jsonObject.get("rows");
    		JSONObject jsonObject1 = (JSONObject) jsonArray.get(0) ;
    		JSONArray jsonArray2 = (JSONArray)jsonObject1.get("elements");
    		JSONObject jsonObject2 = (JSONObject) jsonArray2.get(0) ;
    		JSONObject jsonObject3 = (JSONObject) jsonObject2.get("distance");
    		 dist = (String) jsonObject3.get("text") ;
    		System.out.println(dist);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	 
		
    	return dist ;
    }
}

