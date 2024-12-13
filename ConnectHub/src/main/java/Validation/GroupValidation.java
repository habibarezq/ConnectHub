
package Validation;

import Backend.FileManagers.GroupsFileManager;
import Backend.GroupManagement.Group;

public class GroupValidation {
    public static boolean isNameTaken(String name)
    {
        for (Group existingGroup : GroupsFileManager.getInstance().getGroups()) {
        if (existingGroup.getName().equals(name)) {
           return true;
        }
    }
    return false;
}
}
