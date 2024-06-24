package chatApplicatoin;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    public Admin(String username) {
        super(username, true);
    }

    public Group createGroup(String groupName) {
        return new Group(groupName, this);
    }

    public void addMember(Group group, User user) {
        group.addMember(user);
    }

    public void removeMember(Group group, User user) {
        group.removeMember(user);
    }
}
