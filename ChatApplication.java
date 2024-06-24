package chatApplicatoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatApplication {
    private List<User> users;
    private List<Group> groups;

    public ChatApplication() {
        users = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public void registerUser(String username, boolean isAdmin) {
        if (isAdmin) {
            users.add(new Admin(username));
        } else {
            users.add(new User(username, false));
        }
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Group getGroupByName(String groupName) {
        for (Group group : groups) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine();

            if (command.startsWith("register ")) {
                String[] parts = command.split(" ");
                String username = parts[1];
                boolean isAdmin = parts[2].equals("admin");
                registerUser(username, isAdmin);
                System.out.println("User " + username + " registered.");
            } else if (command.startsWith("create_group ")) {
                String[] parts = command.split(" ");
                String adminUsername = parts[1];
                String groupName = parts[2];
                Admin admin = (Admin) getUserByUsername(adminUsername);
                if (admin != null) {
                    Group group = admin.createGroup(groupName);
                    groups.add(group);
                    System.out.println("Group " + groupName + " created by admin " + adminUsername);
                } else {
                    System.out.println("Admin " + adminUsername + " not found.");
                }
            } else if (command.startsWith("add_member ")) {
                String[] parts = command.split(" ");
                String adminUsername = parts[1];
                String groupName = parts[2];
                String memberUsername = parts[3];
                Admin admin = (Admin) getUserByUsername(adminUsername);
                Group group = getGroupByName(groupName);
                User member = getUserByUsername(memberUsername);
                if (admin != null && group != null && member != null) {
                    admin.addMember(group, member);
                    System.out.println("User " + memberUsername + " added to group " + groupName);
                } else {
                    System.out.println("Invalid admin, group, or member.");
                }
            } else if (command.startsWith("remove_member ")) {
                String[] parts = command.split(" ");
                String adminUsername = parts[1];
                String groupName = parts[2];
                String memberUsername = parts[3];
                Admin admin = (Admin) getUserByUsername(adminUsername);
                Group group = getGroupByName(groupName);
                User member = getUserByUsername(memberUsername);
                if (admin != null && group != null && member != null) {
                    admin.removeMember(group, member);
                    System.out.println("User " + memberUsername + " removed from group " + groupName);
                } else {
                    System.out.println("Invalid admin, group, or member.");
                }
            } else if (command.startsWith("send_message ")) {
                String[] parts = command.split(" ", 4);
                String username = parts[1];
                String groupName = parts[2];
                String message = parts[3];
                User user = getUserByUsername(username);
                Group group = getGroupByName(groupName);
                if (user != null && group != null) {
                    group.addMessage(username + ": " + message);
                    System.out.println("Message sent to group " + groupName);
                } else {
                    System.out.println("Invalid user or group.");
                }
            } else if (command.startsWith("view_messages ")) {
                String[] parts = command.split(" ");
                String groupName = parts[1];
                Group group = getGroupByName(groupName);
                if (group != null) {
                    System.out.println("Messages in group " + groupName + ":");
                    for (String message : group.getMessages()) {
                        System.out.println(message);
                    }
                } else {
                    System.out.println("Group not found.");
                }
            } else if (command.startsWith("view_members ")) {
                String[] parts = command.split(" ");
                String groupName = parts[1];
                Group group = getGroupByName(groupName);
                if (group != null) {
                    System.out.println("Members in group " + groupName + ":");
                    for (User member : group.getMembers()) {
                        System.out.println(member.getUsername() + (member.isAdmin() ? " (admin)" : ""));
                    }
                } else {
                    System.out.println("Group not found.");
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ChatApplication app = new ChatApplication();
        app.start();
    }
}

