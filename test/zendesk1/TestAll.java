/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 * @author karanbhatia
 */
public class TestAll {

	 public static void main(String[] args) {
		 Result result = JUnitCore.runClasses(TestSuite.class);

                 result.getFailures().forEach((failure) -> {
                     System.out.println("\n" + failure.toString());
             });
	      if (result.wasSuccessful())
	    	  System.out.println("\n Awesome! everything seems to be working fine. All tests passed.");
	      else
	    	  System.out.println("\n Oh no! There's something wrong. Errors occured. Some of the tests have failed.");
	   }
}
