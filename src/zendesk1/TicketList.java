/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

/**
 *
 * @author karan
 */
public class TicketList {
    JsonHandler jsonHandler = new JsonHandler(); //handles json content
    Ticket singleTicket;    //to store a single ticket
    public Ticket[] ticketListArray; //to store ticket array
    private boolean nextPage; //to store if next page exists or not
    private boolean prevPage; //to store if prev page exists or not
    
    HTTPRequest pageRequest; //Object of HTTP request
    UserInterface frame=Zendesk1.frame; 

    public TicketList(String uName, String pass) {
        pageRequest = new HTTPRequest(uName, pass);//initializes the HTTP request variable
    }

    public boolean getSingleTicketById(long id) {
        if (id <= 0) { //if by any chance if gets to 0 error message is displayed
            frame.dialogBoxMessageDisplay("Sorry, this is an invalid id. Id cannot be 0 or negative.");
        }
         
        pageRequest.URL = pageRequest.URL + "/" + id + ".json"; //changes the URL to get ticket by id
        String singleTicketJsonString = pageRequest.makeRequest(); //request is sent here and response stored in singleTicketJsonString
        singleTicket = jsonHandler.parseSingleTicket(singleTicketJsonString); //singleTicketJsonString is parsed using jsonHandler
        if (singleTicket != null) { //if single ticket has content then fill it
            this.fillSingleTicketUI(singleTicket);
            return true;  //exit here or
            
        } else { //if invalid id, then empty the panel and exit
            frame.resetSingleTicketPanel();
            return false;
        }
    }

    public boolean getTicketsPerPage(int ticketsFromPage, int ticketsPerPage) { //uses pagination to get tickets per page
     //if somehow in future the pageNumber or number of tickets go out of range
        if (ticketsFromPage < 1) {
            frame.dialogBoxMessageDisplay("ERROR: invalid page number.");
            return false;
        }
        if (ticketsPerPage < 1 || ticketsPerPage >100) {
            frame.dialogBoxMessageDisplay("ERROR: invalid per page quantity. Cannot be negative or 0 or more than 100.");
            return false;
        }
        
        
        pageRequest.URL = pageRequest.URL + ".json?page=" + ticketsFromPage + "&per_page=" + ticketsPerPage;
        String jsonString = pageRequest.makeRequest();
        //Request successful if responseCode between 199 and 400
        if (HTTPRequest.responseCode > 199 && HTTPRequest.responseCode < 400) {
            TicketList ticketListArray = jsonHandler.parseTicketList(jsonString); //parsing tickets
            if (ticketListArray == null) {
                frame.dialogBoxMessageDisplay("Empty List Array returned.");
                return false;
            }
            //Copy all variables to local variables
            this.ticketListArray = ticketListArray.ticketListArray;
            for (int i = 0; i < this.ticketListArray.length; i++) {
                fillTable(this.ticketListArray[i]);
            }
            frame.updateAddressBar(pageRequest.URL); //updates address bar on the frame everytime page is changed.
            return true;
        } else {
            showErrorMessage(HTTPRequest.responseCode);
            return false;
        }
    }

    private void fillTable(Ticket ticket) { //fills the table on multi ticket panel
        frame.model.addRow(new Object[]{ticket.id,   //model here stands for table's design
            ticket.subject, ticket.createdAt, ticket.type, ticket.status});
    }

    private void fillSingleTicketUI(Ticket ticket) {
        frame.fillSingleTicketUI(ticket);
        
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isPrevPage() {
        return prevPage;
    }

    public void setPrevPage(boolean prevPage) {
        this.prevPage = prevPage;
    }

    private void showErrorMessage(int responseCode) { //this method helps provide user a meaningful error response message
        switch (responseCode) {
            case 400:
                frame.dialogBoxMessageDisplay("Error: Bad Request. Response code: " + responseCode);
                break;
            case 401:
                frame.dialogBoxMessageDisplay("Error: connot authenticate you. Response code: " + responseCode);
                break;
            case 403:
                frame.dialogBoxMessageDisplay("Error: FORBIDDEN. Response code: " + responseCode);
                break;
            case 404:
                frame.dialogBoxMessageDisplay("Error: Resource not found. Response code: " + responseCode);
                break;
            case 500:
                frame.dialogBoxMessageDisplay("Error: Internal Server Error. Response code: " + responseCode);
                break;
            case 502:
                frame.dialogBoxMessageDisplay("Error: Bad gateway. Response code: " + responseCode);
                break;
            case 503:
                frame.dialogBoxMessageDisplay("Error: Service Unavailable. Response code: " + responseCode);
                break;
            case 504:
                frame.dialogBoxMessageDisplay("Error: Gateway Timeout. Response code: " + responseCode);
                break;
            default:
                frame.dialogBoxMessageDisplay("Error: unknown. Check Connection. Response code: " + responseCode);
                break;
        }

    }
}
