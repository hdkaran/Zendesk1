/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import javax.swing.JOptionPane;

/**
 *
 * @author karan
 */
public class HTTPRequest {

    public String URL = "https://softcroissant.zendesk.com/api/v2/tickets"; //made public to be changed by other methods
    static int responseCode = 0;
    private String uName;
    private String pass;

    HTTPRequest(String uName, String pass) {
        this.uName = uName;
        this.pass = pass;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String makeRequest() {
        String lineRead = "";
        BufferedReader httpResponseReader = null;
        String usernameColonPassword = uName + ":" + pass;
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
        HttpURLConnection urlConnection = null;

        try {
            // Connect to the web server endpoint
            URL serverUrl = new URL(URL);
            urlConnection = (HttpURLConnection) serverUrl.openConnection();

            // Set HTTP method as GET
            urlConnection.setRequestMethod("GET");

            // Include the HTTP Basic Authentication payload
            urlConnection.addRequestProperty("Authorization", basicAuthPayload);
            // Read response from web server, which will trigger HTTP Basic Authentication request to be sent.
            httpResponseReader
                    = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //responseCode=urlConnection.getResponseCode();
            lineRead = httpResponseReader.readLine();
        } catch (IOException e1) //development-catch only. UI Message will be displayed from TicketList printErrorMessage method
        {
            System.out.println("An error has occured while connecting. Error: " + e1.getMessage());
        }
        try {
            responseCode = urlConnection.getResponseCode();
        } catch (IOException e1) {
                Zendesk1.frame.dialogBoxMessageDisplay("Lost Connection to the server. Please Check Your Connection and try again.");
            
        }
        return lineRead;
    }
}
