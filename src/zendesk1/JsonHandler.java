/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;


import org.json.*;

/**
 *
 * @author karan
 */
public class JsonHandler {
    public static final int MAX = 25; //for now set at 25 
    UserInterface frame = Zendesk1.frame;;

   

    public Ticket parseSingleTicket(String jsonString) {
        try {
            JSONObject jsonParser = new JSONObject(jsonString);
            //get ticket structure from the json object
            JSONObject ticketStructure = (JSONObject) jsonParser.get("ticket");
            //if there is no ticket in the json format returned.
            if (ticketStructure.isEmpty()) {
                return null;
            }
            return parseTicketStructure(ticketStructure);
        } catch (JSONException e) { //if JSON fails...then display error message
            frame.dialogBoxMessageDisplay("Error: there is no ticket with this id.");
            return null;
        }
    }

    public TicketList parseTicketList(String jsonString) {
        TicketList ticketList=frame.ticketList;
        JSONObject jsonParser;
        try {
            jsonParser = new JSONObject(jsonString);
            JSONArray ticketListArray = jsonParser.getJSONArray("tickets");
            if (ticketListArray.isEmpty()) {
                frame.dialogBoxMessageDisplay("Sorry, there are currently no records in the list.");
                return null;
            }
            if (ticketListArray.length() < MAX) { //when the values in the list are less than 25 then set list length to the number of items
                ticketList.ticketListArray = new Ticket[ticketListArray.length()];
            } else {
                ticketList.ticketListArray = new Ticket[MAX];
            }
            for (int i = 0; i < ticketListArray.length() && i < MAX; i++) {
                //Parse single ticket within the array
                JSONObject ticketStructure = (JSONObject) ticketListArray.get(i);
                Ticket ticket = parseTicketStructure(ticketStructure);
                if (ticket == null) {
                    frame.dialogBoxMessageDisplay("Error: no records found");
                    return null;
                }
                ticketList.ticketListArray[i] = ticket;
            }
            if (!jsonParser.get("next_page").equals(null)) {
                ticketList.setNextPage(true);
            } else {
                ticketList.setNextPage(false);
            }
            if (!jsonParser.get("previous_page").equals(null)) {
                ticketList.setPrevPage(true);
            } else {
                ticketList.setPrevPage(false);
            }
            return ticketList;
        } catch (JSONException e1) {

            frame.dialogBoxMessageDisplay("Error: No JSON Returned. Check your Connection and try again");
            return null;
        }
    }

    private Ticket parseTicketStructure(JSONObject ticketStructure) {
        JSONArray jsonArray; //for storing some of the arrays in the ticket structure
        Ticket ticket = new Ticket();

        if (ticketStructure == null) {
            return null;
        }
        //filling ticket data--------------------------------------------------------------------
        ticket.url = ticketStructure.get("url").toString();
        ticket.id = Long.parseLong(ticketStructure.get("id").toString()); //will be definitely there in each ticket so no null-check
        //parsing string values-------------------------------------------------------------------
        ticket.externalId = ticketStructure.get("external_id").toString();
        ticket.createdAt = ticketStructure.get("created_at").toString();
        ticket.updatedAt = ticketStructure.get("updated_at").toString();
        ticket.type = ticketStructure.get("type").toString();
        ticket.subject = ticketStructure.get("subject").toString();
        ticket.rawSubject = ticketStructure.get("raw_subject").toString();
        ticket.description = ticketStructure.get("description").toString();
        ticket.priority = ticketStructure.get("priority").toString();
        ticket.status = ticketStructure.get("status").toString();
        ticket.recipient = ticketStructure.get("recipient").toString();
        ticket.dueAt = ticketStructure.get("due_at").toString();

        ticket.requesterId = Long.parseLong(ticketStructure.get("requester_id").toString()); //mandatory value for every ticket

        //parsing boolean values---------------------------------------------------------------------	
        ticket.hasIncidents = Boolean.parseBoolean(ticketStructure.get("has_incidents").toString());
        ticket.isPublic = Boolean.parseBoolean(ticketStructure.get("is_public").toString());
        ticket.hasIncidents = Boolean.parseBoolean(ticketStructure.get("has_incidents").toString());
        ticket.isPublic = Boolean.parseBoolean(ticketStructure.get("is_public").toString());
        ticket.allowChannelback = Boolean.parseBoolean(ticketStructure.get("allow_channelback").toString());

        ticket.satisfactionRating = (Object) ticketStructure.get("satisfaction_rating");

        //parsing long variables if they're not null---------------------------------------------------
        if (ticketStructure.get("submitter_id") != null) {
            ticket.submitterId = (long) ticketStructure.getLong("submitter_id");
        }
        if (ticketStructure.get("assignee_id") != null) {
            ticket.assigneeId = (long) ticketStructure.get("assignee_id");
        }
        if (!ticketStructure.get("organization_id").equals(null)) {
            ticket.organizationId = (long) ticketStructure.get("organization_id");
        }
        if (ticketStructure.get("group_id") != null) {
            ticket.groupId = (long) ticketStructure.get("group_id");
        }
        if (!ticketStructure.get("forum_topic_id").equals(null)) {
            ticket.forumTopicId = (long) ticketStructure.get("forum_topic_id");
        }
        if (!ticketStructure.get("problem_id").equals(null)) {
            ticket.problemId = (long) ticketStructure.get("problem_id");
        }
        if (ticketStructure.get("brand_id") != null) {
            ticket.brandId = (long) ticketStructure.getLong("brand_id");
        }

        //filling data into the via part of ticket-------------------------------------------------------
        JSONObject viaStructure = (JSONObject) ticketStructure.get("via");
        if (viaStructure != null) {
            ticket.via.channel = viaStructure.getString("channel");
            JSONObject sourceStructure = (JSONObject) viaStructure.get("source");
            //doing same with the source part of via
            if (sourceStructure != null) {
                if (!sourceStructure.get("from").equals(null)) {
                    ticket.via.src.from = sourceStructure.getJSONObject("from");
                }
                if (!sourceStructure.get("to").equals(null)) {
                    ticket.via.src.to = sourceStructure.getJSONObject("to");
                }
                if (!sourceStructure.get("rel").equals(null)) {
                    ticket.via.src.rel = sourceStructure.getJSONObject("rel");
                }
            }
        }

        jsonArray = (JSONArray) ticketStructure.getJSONArray("collaborator_ids"); //collaborator_id is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.collaboratorsIds = new long[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.collaboratorsIds[i] = jsonArray.getLong(i);
            }
        } else {
            ticket.collaboratorsIds = new long[1];
            ticket.collaboratorsIds[0] = 0;
        }

        jsonArray = (JSONArray) ticketStructure.getJSONArray("tags");
        if (!jsonArray.isEmpty()) {
            ticket.tags = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.tags[i] = jsonArray.getString(i);
            }
        } else {
            ticket.tags = new String[1];
            ticket.tags[0] = "";
        }
 

        jsonArray = (JSONArray) ticketStructure.getJSONArray("custom_fields"); //custom_fields is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.customField = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.customField[i] = jsonArray.getString(i);
            }
        } else {
            ticket.customField = new String[1];
            ticket.customField[0] = "";
        }

        jsonArray = (JSONArray) ticketStructure.getJSONArray("sharing_agreement_ids"); //sharing_agreement_ids is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.sharingAgreementIds = new long[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.sharingAgreementIds[i] = jsonArray.getLong(i);
            }
        } else {
            ticket.sharingAgreementIds = new long[1];
            ticket.sharingAgreementIds[0] = 0;
        }

        //might change - still in beta version
        jsonArray = (JSONArray) ticketStructure.getJSONArray("followup_ids"); //followup_id is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.followupIds = new long[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.followupIds[i] = jsonArray.getLong(i);

            }
        } else {
            ticket.followupIds = new long[1];
            ticket.followupIds[0] = 0;
        }

        //might change - still in beta version
        jsonArray = (JSONArray) ticketStructure.getJSONArray("follower_ids"); //follower_id is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.followerIds = new long[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.followerIds[i] = jsonArray.getLong(i);
            }
        } else {
            ticket.followerIds = new long[1];
            ticket.followerIds[0] = 0;
        }

        //might change - still in beta version
        jsonArray = (JSONArray) ticketStructure.getJSONArray("email_cc_ids"); //follower_id is an array hence handling it with array manipulation
        if (!jsonArray.isEmpty()) {
            ticket.emailCCIds = new long[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                ticket.emailCCIds[i] = (long) jsonArray.get(i);
            }
        } else {
            ticket.emailCCIds = new long[1];
            ticket.emailCCIds[0] = 0;
        }
        return ticket;
    }

}
