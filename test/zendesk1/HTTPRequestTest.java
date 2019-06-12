/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

import org.json.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karanbhatia
 */
public class HTTPRequestTest {

    /**
     * Test of makeRequest method, of class HTTPRequest.
     * Simple Make Request(GET) will null parameters. Expected result -> ""
     */
    @Test
    public void testMakeRequest() {
        System.out.println("Send request with null parameters.");
        HTTPRequest instance = new HTTPRequest(null, null);
        String expResult = "";
        String result = instance.makeRequest();
        assertEquals(expResult, result);
        if (expResult.equals(result)) {
            System.out.println("-----------Make request with empty parameters Test Passed------------");
        }
    }

    /*
    *Make request with invalid credentials. Expected result -> ""
    */
    @Test
    public void testMakeRequestWithInvalidCredentials() {
        System.out.println("Send request with invalid credentials");
        HTTPRequest instance = new HTTPRequest("karanbhatia0161", "Lalaland");
        String expResult = "";
        String result = instance.makeRequest();
        assertEquals(expResult, result);
        if (expResult.equals(result)) {
            System.out.println("-----------Invalid Credentials Test Passed------------");
        }
    }

    /*
    *Make request with invalid URL. Expected result -> ""
    */
    @Test
    public void testMakeRequestWithInvalidURL() {
        System.out.println("Send request with invalid URL");
        HTTPRequest instance = new HTTPRequest("karanbhatia0161@gmail.com", "theSoftCroissant");
        instance.URL = "https://softcroissant.zendesk.com/api/v2/nottickets";
        String expResult = "";
        String result = instance.makeRequest();
        assertEquals(expResult, result);
        if (expResult.equals(result)) {
            System.out.println("-----------Invalid-URL Test Passed------------");
        }
    }

    /*
    *Make request with valid credentials. Expected result -> jsonString
    */
    @Test
    public void testPerfectMultiTicketMakeRequest() {
        System.out.println("Send multi-ticket request.");
        HTTPRequest instance = new HTTPRequest("karanbhatia0161@gmail.com", "theSoftCroissant");
        instance.URL = "https://softcroissant.zendesk.com/api/v2/tickets.json";
        boolean expResult = true;
        String result = instance.makeRequest();
        assertEquals(expResult, isJSONValid(result));
        if (expResult == isJSONValid(result)) {
            System.out.println("-----------Multi ticket test passed------------");
        }
    }
    
    /*
    *Make request with valid credentials. Expected result -> jsonString
    */

    @Test
    public void testPerfectSingleTicketRequest() {
        System.out.println("Send single-ticket request.");
        HTTPRequest instance = new HTTPRequest("karanbhatia0161@gmail.com", "theSoftCroissant");
        instance.URL = "https://softcroissant.zendesk.com/api/v2/tickets/3.json";
        boolean expResult = true;
        String result = instance.makeRequest();
        assertEquals(expResult, isJSONValid(result));
        if (expResult == isJSONValid(result)) {
            System.out.println("-----------Single ticket test passed------------");
        }
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
