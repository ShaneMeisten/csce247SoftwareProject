public class TerminalUI {
    Facade facade = new Facade();
    public TerminalUI() {
        loadData();
    }

    /*
     * Loads the data for scenario
     * Users:
     *  Atticus Madden
     *  Jeff Goldblum
     *  Atticus Finch
     * Projects:
     *  Electric Missiles
     *  Soap Free Washers
     *  Air Computers
     * 
     */

    public void loadData() {
        User AtticusM = new User("Atticus Madden", "Atticus Madden", "Madden123", "111-111-1111", "AtticusM@gmail.com");
        User Jeff = new User("Jeff Goldblum", "Jeff", "Goldblum123", "111-111-1112", "Jeff@gmail.com");
        User AtticusF = new User("Atticus Finch", "Atticus Finch", "Finch123", "111-111-1113", "AtticusF@gmail.com");
        facade.createUser(AtticusM);
        facade.createUser(Jeff);
        facade.createUser(AtticusF);

        facade.createProject("Electric Missiles", "SCRUM");
        facade.createProject("Soap Free Washers", "SCRUM");
        facade.createProject("Air Computers", "SCRUM");

        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(1).getUUID());
        facade.InviteUserToProject(AtticusM.getUUID(), facade.getProject(2).getUUID());
        facade.InviteUserToProject(Jeff.getUUID(), facade.getProject(0).getUUID());
        facade.InviteUserToProject(AtticusF.getUUID(), facade.getProject(0).getUUID());
        facade.login("Atticus Madden", "Madden123");
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.AcceptInvite(0);
        facade.login("Jeff", "Goldblum123");
        facade.AcceptInvite(0);
        facade.login("Atticus Finch", "Finch123");
        facade.AcceptInvite(0);


    }
}
