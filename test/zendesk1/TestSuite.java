/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	HTTPRequestTest.class,
        JsonHandlerTest.class,
        TicketListTest.class              
})

/**
 *
 * @author karanbhatia
 */
public class TestSuite{}
