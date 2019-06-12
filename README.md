# Zendesk1
Hi and thanks for Downloading Ticket Officer - The Ticket Viewer.
This is a guide on how to set up and run the project on your local machine. This guide assumes that the testers are using a mac and the they've little to pro knowledge of general mac use. (Although, I know you guys are pros :)).

# About the Project
The project has been developed using JAVA 8 and Netbeans IDE.
* This project helps to view tickets from the Zendesk API using your subdomain account.
* The application has 3 tabs in total(located at top-center) -- Login, Multi-Ticket and Single Ticket.
* When you login, you request all tickets for your account, which are displayed in sets of 25 per page.
* You can double click on any ticket if you want to view more information about it.
* Alternatively, you can search for information about a single ticket in the single-ticket view tab.


# Pre-Requisites
After you have downloaded the project from github. Extract it anywhere you like.
The Extracted folder will be named as Zendesk1-master.
* Major Code is contained in "Zendesk1-master/src/zendesk1" folder.
* Testing Code is contained in "Zendesk1-master/test/zendesk1" foler.

The pre-requisites you will need for this project to run are:
1. Java Versiion 1.8.0_211. 
    Download it from: https://java.com/en/download/manual.jsp
    
2. Java Development Kit or JDK.
    Download it from: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html?printOnly=1
    
3. Download ant in your terminal.
    If it is not already installed, then your best bet is to install Homebrew (brew install ant) 
    Or
    Follow this link: https://www.mkyong.com/ant/how-to-apache-ant-on-mac-os-x/
    To confirm the installation Try "ant -v" in your terminal.
    
4. IMPORTANT Step for Mac Users => 
    Copy the 4 '.jar' files from "Zendesk1-master/dist/lib/" folder
    and paste them to
    "Library/Java/Extensions/" on your MacBook.
    
    To access the "Library" folder either press "Shift+Command+C" and select your drive>Library>Java>Extensions>"paste here"
    or
    Go into terminal:
    Enter=> cd
    Enter=> cd Library/Java?Extensions
    
5. Netbeans 8.2 (Optional)
    Download it from https://netbeans.org/downloads/8.2/
    
# Starting the Project.
After you have successfully installed all the above requirements, follow the following steps:
1. Open terminal and "cd" to the Folder you have extracted the files to.
2. Make sure you're in the root folder, i.e., Zendesk1-master.
3. Type "ant compile" and press enter.
4. Now type "ant run" and press enter. Wait a few seconds until an application pops up.
5. The application should have loaded by now. 
6. Login details have been provided in the email.
6. Once you login. You will be redirected to multi-ticket page.
7. Click any ticket to view more information about it or seach for any single ticket in the Single ticket tab.
8. Feel free to exploit. :)

Alternatively, you can open the project in NetBeans and just press Run. (But installing NetBeans and setting up the project is a Pain in the Bum for Mac).

# Testing
The tests are contained in the test folder.
To run the tests follow these steps.
1. Open terminal and "cd" to the Folder you have extracted the files to.
2. Make sure you're in the root folder, i.e., Zendesk1-master.
3. Type "ant test" and press enter.
4. tests will runn automatically. Look for "Passed" words in the output generated. If anything fails, it will be reported.
5. If you see pop-ups during testing - that means tests are going well.

# For Zendesk Team
I have really enjoyed myself taking up this coding challenge. At the beginning I tried doing it in Ruby, although I didn't know Ruby at all but just wanted to give it a shot. Spent 4 days at Ruby and then switched back to Java. I've tried hard to do the best I can. I just want to thank you all at Zendesk for holding up this challenge as I personally have learnt a lot of new things, just to get this done. 

I would love to have some feedback on my work from the amazing team at Zendesk even if I'm progressed to next stage or not. Hope to hear from you guys soon :).
