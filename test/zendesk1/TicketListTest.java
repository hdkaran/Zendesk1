/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karanbhatia
 */
public class TicketListTest {
    /**
     * Test of getSingleTicketById method, of class TicketList.
     */
    //id=1 will be SUCCESS
    @Test
    public void testGetSingleTicketById() {
        System.out.println("getSingleTicketById");
        long id = 1;
        Zendesk1.frame=new UserInterface();
        TicketList instance = new TicketList("karanbhatia0161@gmail.com","theSoftCroissant");
        
        instance.getSingleTicketById(id);
        boolean expResult = false;
        boolean result = instance.getSingleTicketById(id);
        assertNotEquals(expResult,result);
        if (expResult != result) {
            System.out.println("-----------Get Single ticket with id=1 test passed------------");
        }
    }
    
    @Test
    public void testGetSingleTicketByIdZero() {
        System.out.println("getSingleTicketByIdZero test");
        long id = 0;
        Zendesk1.frame=new UserInterface();
        TicketList instance = new TicketList("karanbhatia0161@gmail.com","theSoftCroissant");
        boolean expResult = false;
        boolean result = instance.getSingleTicketById(id);
        assertEquals(expResult,result);
        if (expResult == result) {
            System.out.println("-----------Get Single ticket with id=0 test passed------------");
        }
    }
    
    @Test
    public void testGetSingleTicketByIdMoreThanMaximum() {
        System.out.println("getSingleTicketByIdMoreThanMaximum test");
        long id = 200;
        Zendesk1.frame=new UserInterface();
        TicketList instance = new TicketList("karanbhatia0161@gmail.com","theSoftCroissant");
        boolean expResult = false;
        boolean result = instance.getSingleTicketById(id);
        assertEquals(expResult,result);
        if (expResult == result) {
            System.out.println("-----------Get Single ticket with id=200 test passed------------");
        }
    }
    
    
    

    /**
     * Test of getTicketsPerPage method, of class TicketList.
     */
    @Test
    public void testGetTicketsPerPage() {
        System.out.println("getTicketsPerPage Page=0 PerPage=0");
        int ticketsFromPage = 0;
        int ticketsPerPage = 0;
        Zendesk1.frame=new UserInterface();
        TicketList instance = new TicketList("karanbhatia0161@gmail.com","theSoftCroissant");
        boolean expResult = false;
        boolean result = instance.getTicketsPerPage(ticketsFromPage, ticketsPerPage);
        assertEquals(expResult, result);
        if (expResult == result) {
            System.out.println("-----------Get tickets per page from page 0 and 0 tickets per page test passed------------");
        }
    }
    
    //tickets from page 1 & tickets per page =200. Expected result FAILURE.
     @Test
    public void testGetTicketsPerPageTest2() {
        System.out.println("getTicketsPerPage Page=1 PerPage=200");
        int ticketsFromPage = 1;
        int ticketsPerPage = 200;
        UserInterface ui=new UserInterface();
        TicketList instance = new TicketList("karanbhatia0161@gmail.com","theSoftCroissant");
        
        boolean expResult = false;
        boolean result = instance.getTicketsPerPage(ticketsFromPage, ticketsPerPage);
        assertEquals(expResult, result);
        if (expResult == result) {
            System.out.println("-----------Get tickets per page from page 1 and 200 tickets per page test passed------------");
        }
    }

 //NOT Testing getters and setters as well as showErrorMessageMethods as they're not too complex and cannot fail.
}
