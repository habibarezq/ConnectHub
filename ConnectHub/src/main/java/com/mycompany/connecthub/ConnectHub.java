
package com.mycompany.connecthub;

import Backend.*;
import Backend.FileManagers.*;
import Backend.GroupManagement.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectHub {

    public static void main(String[] args) {
        // Initialize the GroupsFileManager
        GroupsFileManager groupsFileManager = GroupsFileManager.getInstance();

        // Read groups from the file
        groupsFileManager.readFromFile();

        // Retrieve the list of groups
        ArrayList<Group> groups = groupsFileManager.getGroups();

        // Print the groups
        if (groups.isEmpty()) {
            System.out.println("No groups found.");
        } else {
            System.out.println("Groups loaded from file:");
            for (Group group : groups) {
                System.out.println("---------------------------------");
                System.out.println("Group ID: " + group.getGroupId());
                System.out.println("Creator ID: " + group.getCreatorId());
                System.out.println("Name: " + group.getName());
                System.out.println("Description: " + group.getDescription());
                System.out.println("Photo Path: " + group.getPhotoPath());
            }
        }
        //GroupServiceManager.createGroup("My Family Group", "Here we are a family", "C:\\Users\\habib\\Documents\\NetBeansProjects\\ConnectHub\\ConnectHub\\pics\\download (2).jpeg", "69890c7f-4ed0-4eda-a986-1583e21087bd");
         
         // Initialize the GroupMembershipFileManager
        GroupMembershipFileManager membershipFileManager = GroupMembershipFileManager.getInstance();

        // Read data from the file
        membershipFileManager.readFromFile();

        // Get and print group memberships
        System.out.println("Group Memberships:");
        for (String groupId : membershipFileManager.getGroupsMembership().keySet()) {
            JSONObject groupData = membershipFileManager.getUserData(groupId);

            System.out.println("---------------------------------");
            System.out.println("Group ID: " + groupData.getString("groupId"));
            System.out.println("Creator ID: " + groupData.getString("creatorId"));

            JSONArray members = groupData.getJSONArray("members");
            System.out.println("Members: " + members);

            JSONArray admins = groupData.getJSONArray("admins");
            System.out.println("Admins: " + admins);
        }

        Group g=GroupsFileManager.getInstance().getGroupById("group123");
        GroupUser u;
        
        // Example: Add a new member to a group and save changes
//        JSONObject groupData = membershipFileManager.getUserData("group123");
//        if (groupData != null) {
//            groupData.getJSONArray("members").put("user127");
//            membershipFileManager.updateUserData("group123", groupData);
//            membershipFileManager.saveToFile();
//            System.out.println("Added new member to group123 and saved changes.");
//        }
    }
        
    }

