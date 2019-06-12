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
public class Ticket {
	
    
    // FOLLOWING description about the variables can be found on: https://developer.zendesk.com/rest_api/docs/support/tickets
        
    long id; //auto-increment - read only value
    String url; //api url - read only value
    String externalId; //id use to link to local records
    String createdAt; //time and date of the ticket when it was created - read only value
    String updatedAt; //time and date of update - read only value
    String type; //The type of this ticket. Possible values: "problem", "incident", "question" or "task
    String subject; //The value of the subject field for this ticket
    String rawSubject; //The dynamic content placeholder, if present, or the "subject" value, if not. 
    String description; //The first comment on the ticket - read only value
    String priority; //The urgency with which the ticket should be addressed: "urgent", "high", "normal", "low"
    String status; //The state of the ticket, "new", "open", "pending", "hold", "solved", "closed"
    String recipient; //The original recipient e-mail address of the ticket
    String dueAt; //If this is a ticket of type "task" it has a due date. Due date format uses ISO 8601 format.
    String[] tags; //The array of tags applied to this ticket
    String[] customField; //The custom fields of the ticket
    long requesterId; //The user who requested this ticket (MANDATORY)
    long submitterId; //The user who submitted the ticket. The submitter always becomes the author of the first comment on the ticket.
    long assigneeId; //	What agent is currently assigned to the ticket
    long organizationId; //The organization of the requester. You can only specify the ID of an organization associated with the requester.
    long groupId; //The group this ticket is assigned to
    long[] collaboratorsIds; //Who are currently CC'ed on the ticket
    long forumTopicId; //The topic this ticket originated from, if any
    long problemId; //The problem this incident is linked to, if any
    long[] sharingAgreementIds; //The ids of the sharing agreements used for this ticket
    long[] followupIds; //The ids of the followups created from this ticket - only applicable for closed tickets 
    long ticketFormId; //The id of the ticket form to render for this ticket - only applicable for enterprise accounts
    long brandId; //The id of the brand this ticket is associated with - only applicable for enterprise accounts
    long[] followerIds;
    long[] emailCCIds;
    boolean hasIncidents; //Is true of this ticket has been marked as a problem, false otherwise
    boolean allowChannelback; //Is false if channelback is disabled, true otherwise - only applicable for channels framework ticket 
    boolean isPublic; //Is true if any comments are public, false otherwise
    Object satisfactionRating; //The satisfaction rating of the ticket, if it exists, or the state of satisfaction, 'offered' or 'unoffered' 
    
    Via via = new Via();
    
    public class Via
    {
    	String channel;
    	Source  src = new Source();
    	public class Source
    	{
    		Object from;
    		Object to;
    		Object rel;
    	}
    }
}