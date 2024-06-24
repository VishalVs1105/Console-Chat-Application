package chatApplicatoin;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private Admin admin;
    private List<User> members;
    private List<String> messages;

    public Group(String name, Admin admin) {
        this.name = name;
        this.admin = admin;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.members.add(admin);
    }

    public String getName() {
        return name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}

