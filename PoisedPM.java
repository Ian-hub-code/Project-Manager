package Level3_Task8;


import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class PoisedPM {
    public static void main(String[] args) throws IOException, SQLException {

        try {
            System.out.println("Poised Project Manager V2.0");
            System.out.println("With user input validation!");
            System.out.println("----------------------");
            int selection;
            do {
                System.out.println("\n1 - Create project\n2 - Create person\n3 - Edit person details\n" +
                        "4 - View Projects\n5 - Edit project\n6 - Finalize\n7 - View Incomplete\n8 - Quit");
                Scanner input = new Scanner(System.in);
                selection = input.nextInt();

                if (selection == 1) {
                    menu_option1();
                } else if (selection == 2) {
                    menu_option2();
                } else if (selection == 3) {
                    menu_option3(input);
                } else if (selection == 4) {
                    menu_option4();
                } else if (selection == 5) {
                    menu_option5(input);
                } else if (selection == 6) {
                    menu_option6(input);
                } else if (selection == 7) {
                    menu_option7();
                }
            } while (selection != 8); // run PoisedPM-menu while exit option not chosen
            menu_option8();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void menu_option8() {
        System.out.println("Closed");
        System.exit(0);
    } // exit PoisedPM

    public static void menu_option7() {
        System.out.println("--> View incomplete");
        System.out.println("-----------------------------------------");
        Project p1 = new Project();
        p1.viewIncomplete();
    } // View incomplete

    public static void menu_option6(Scanner input) throws IOException {
        System.out.println("--> Finalize project");
        System.out.println("Projects listed below");
        System.out.println("-----------------------------------------");
        Project p1 = new Project();
        p1.displayProjects();
        System.out.println("-----------------------------------------");
        int finProject; // variable which will be used to get the project which I would like to finalize
        System.out.println("\nEnter number of project which you would like to finalize:");
        finProject = input.nextInt(); // asking user to choose which project to finalize
        Project p2 = new Project();
        p2.finalizeProject(finProject);


    } // finalize project

    public static void menu_option5(Scanner input) {
        System.out.println("--> Editing project");
        System.out.println("Projects listed below");
        System.out.println("-----------------------------------------");
        Project p1 = new Project();
        p1.displayProjects();
        System.out.println("-----------------------------------------");


        int editProject; // variable which will be used to get the project which I would like to edit

        int newProjectNum;
        String newProjectName;
        String newProjectType;
        String newProjectAddress;
        int newProjectErfNum;
        double newProjectCost;
        double newProjectPaid;
        String newProjectDeadline;

        System.out.println("\nEnter number of project which you would like to edit:");
        editProject = input.nextInt(); // asking user to choose which project to edit

        newProjectNum = p1.getProjectID();
        newProjectName = p1.getProjectName();
        newProjectType = p1.getProjectType();
        newProjectAddress = p1.getProjectAddress();
        newProjectErfNum = p1.getProjectErf();
        newProjectCost = p1.getProjectFee();
        newProjectPaid = p1.getProjectFeePaid(newProjectCost);
        newProjectDeadline = p1.getProjectDeadline();

        Project p2 = new Project();
        p2.EditEntireProject(editProject,newProjectNum, newProjectName,newProjectType,newProjectAddress,newProjectErfNum,newProjectCost,newProjectPaid,newProjectDeadline);
        System.out.println("-----------------------------------------");
        p1.displayProjects();
        System.out.println("-----------------------------------------");

    } // edit entire project

    public static void menu_option4() throws SQLException {
        // Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost (this PC)
        // Use username "otheruser", password "swordfish".
        try {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                "otheruser",
                "swordfish"
        );
        Statement statement = connection.createStatement();
        ResultSet project_results;
        int rowsAffected;
        // Set up finished, do some stuff:
        //executeQuery: runs a SELECT statement and returns the results.
        project_results = statement.executeQuery("SELECT projectID, projectName,projectType,projectAddress," +
                "projectErf,projectFee,projectFeePaid,projectDeadline FROM Projects");
            System.out.println("--- Project List ----");
        while (project_results.next()) {
            System.out.println(project_results.getInt("projectID") + ", " +
                    project_results.getString("projectName") + ", " +
                    project_results.getString("projectType") + ", " +
                    project_results.getString("projectAddress") + ", " +
                    project_results.getInt("projectErf") + ", " +
                    project_results.getDouble("projectFee") + ", " +
                    project_results.getDouble("projectFeePaid") + ", " +
                    project_results.getDate("projectDeadline"));
        }
            System.out.println(" -------------------- ");
        // Close up our connections
        project_results.close();
        statement.close();
        connection.close();
        } catch (SQLException e) {
            // We only want to catch a SQLException - anything else is off-limits for now.
            e.printStackTrace();
        }
    } // view existing projects

    public static void menu_option3(Scanner input) {
        System.out.println("--> Editing person details");
        System.out.println("People listed below");
        System.out.println("-----------------------------------------");
        People p1 = new People();
        p1.displayPeople();
        System.out.println("-----------------------------------------");
        String editPerson; // variable which will be used to get the project which I would like to edit
        System.out.println("\nEnter ID of person to edit:");
        editPerson = input.next(); // asking user to choose which project to edit due date from

        int newPID;
        int newID;
        String newType;
        String newName;
        String newNumber;
        String newEmail;
        String newAddress;
        newPID = p1.getPersonID();
        newID = p1.getProject();
        newType = p1.getType();
        newName = p1.getName();
        newNumber = p1.getNumber();
        newEmail = p1.getEmail();
        newAddress = p1.getAddress();

        People p2 = new People();
        p2.EditPersonDetails(editPerson,newPID,newID,newType, newName, newNumber, newEmail, newAddress);
        System.out.println("-----------------------------------------");
        p1.displayPeople();
        System.out.println("-----------------------------------------");
    } // edit person details

    public static void menu_option2() {
        System.out.println("--> Creating a person");
        // getting the details entered by the user for the new project
        People m = new People();
        int personID;
        int project;
        String personType;
        String personName;
        String personNumber;
        String personEmail;
        String personAddress;

        personID = m.getPersonID();
        project = m.getProject();
        personType = m.getType();
        personName = m.getName();
        personNumber = m.getNumber();
        personEmail = m.getEmail();
        personAddress = m.getAddress();

        // storing the new person object in n, calling my write method and writing to file
        People n = new People(personID,project,personType,personName,personNumber,personEmail,personAddress);
        System.out.println("Person Information");
        System.out.println("------------------");
        System.out.println(n.toString());
        n.WriteToPeople();
    } // create a person

    private static void menu_option1() throws SQLException {
        System.out.println("--> Creating a project");
        // getting the details entered by the user for the new project
        Project p = new Project();
        int projectID = p.getProjectID();
        String pName = p.getProjectName();
        String pType = p.getProjectType();
        String projectAddress = p.getProjectAddress();
        int projectErf = p.getProjectErf();
        double projectFee = p.getProjectFee();
        double projectFeePaid = p.getProjectFeePaid(projectFee);
        String projectDeadline = p.getProjectDeadline();
        // storing the new project object in n
        Project n = new Project(projectID,pName,pType,projectAddress,projectErf,projectFee,projectFeePaid,projectDeadline);

        System.out.println("Project Information");
        System.out.println("------------------");
        System.out.println(n.toString());
        System.out.println("PoisedPM is adding the project...");
        n.WriteToProject();
        System.out.println("------------------");
        p.displayProjects();
        System.out.println("------------------");
    } // create new project


}

