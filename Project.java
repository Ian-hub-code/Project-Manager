package Level3_Task8;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;

public class Project {

    // Attributes for a project
    int projectID;
    String projectType;
    String projectAddress;
    int projectErf;
    String projectDeadline;
    String projectName;
    double projectFee;
    double projectFeePaid;

    // all args constructor
    public Project(int projectID, String projectName, String projectType, String projectAddress, int projectErf, double projectFee, double projectFeePaid, String projectDeadline) {
        this.projectID = projectID;
        this.projectType = projectType;
        this.projectAddress = projectAddress;
        this.projectErf = projectErf;
        this.projectDeadline = projectDeadline;
        this.projectName = projectName;
        this.projectFee = projectFee;
        this.projectFeePaid = projectFeePaid;
    }


    //no args constructor
    public Project() {
    }

    public int getProjectID() {

        Scanner input = new Scanner(System.in);
        boolean success1 = false;

        while (!success1) {
            try {
                System.out.println("Enter project number: ");
                projectID = input.nextInt();
                while (projectID < 1) {
                    System.out.println("You must enter a value above 1 ");
                    projectID = input.nextInt();
                } //close try
                System.out.println("Accepted");
                System.out.println("Project ID: " + projectID);
                success1 = true; //escape the while
            } catch (Exception ex) {
                System.out.println("You must enter a integer value for project number!");
                input = new Scanner(System.in);
            } //close catch
        }

        return projectID;
    }

    public String getProjectType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter project type: ");
        projectType = input.next();

        while (!projectType.matches("[a-zA-Z ,]+")) {
            System.out.println("No numbers or special characters!\nPlease enter type again: ");
            projectType = input.next();
        }
        System.out.println("Type: " + projectType);

        return projectType;
    }

    public String getProjectAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter project address: ");
        projectAddress = input.nextLine();

        return projectAddress;
    }

    public int getProjectErf() {
        Scanner input = new Scanner(System.in);
        boolean success2 = false;

        while (!success2) {
            try {
                System.out.println("Enter erf number: ");
                projectErf = input.nextInt();
                while (projectErf < 1) {
                    System.out.println("Erf number cannot be below 1! Try again: ");
                    projectErf = input.nextInt();
                } //close try
                System.out.println("Accepted");
                System.out.println("Erf number: " + projectErf);
                success2 = true; //escape the while
            } catch (Exception ex) {
                System.out.println("You must enter a integer value for project erf number!");
                input = new Scanner(System.in);
            } //close catch
        }
        return projectErf;
    }

    public String getProjectDeadline() {
        Scanner input = new Scanner(System.in);
        boolean success12 = false;
        int year;
        int month;
        int day;
        int current_year = Calendar.getInstance().get(Calendar.YEAR);

        while (!success12) {
            try {
                System.out.println("Enter deadline");
                System.out.println("Enter year: ");
                year = input.nextInt();
                System.out.println("Enter month: ");
                month = input.nextInt();
                System.out.println("Enter day: ");
                day = input.nextInt();

                while (year < current_year || month < 1 || month > 12 || day < 1 || day > 31) {
                    System.out.println("Invalid date! Try again: ");
                    System.out.println("Enter year: ");
                    year = input.nextInt();
                    System.out.println("Enter month: ");
                    month = input.nextInt();
                    System.out.println("Enter day: ");
                    day = input.nextInt();
                } //close try
                System.out.println("Accepted");
                System.out.println("Deadline: " + year + "-" + month + "-" + day);
                projectDeadline = year + "-" + month + "-" + day;
                success12 = true; //escape the while
            } catch (Exception ex) {
                System.out.println("You must enter integer values for year,month and day!");
                input = new Scanner(System.in);
            } //close catch
        }
        return projectDeadline;
    }

    public String getProjectName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter project name: ");
        projectName = input.nextLine();

        while (!projectName.matches("[a-zA-Z ,]+")) {
            System.out.println("No numbers or special characters!\nPlease enter project name again: ");
            projectName = input.nextLine();
        }
        System.out.println("Name: " + projectName);

        return projectName;
    }

    public double getProjectFee() {
        Scanner input = new Scanner(System.in);
        boolean success10 = false;

        while (!success10) {
            try {
                System.out.println("Enter project fee: ");
                projectFee = input.nextDouble();
                while (projectFee < 1) {
                    System.out.println("Project fee cannot be zero! Try again: ");
                    projectFee = input.nextDouble();
                } //close try
                System.out.println("Accepted");
                System.out.println("Project Fee: R" + projectFee);
                success10 = true; //escape the while
            } catch (Exception ex) {
                System.out.println("You must enter a integer value for project fee!");
                input = new Scanner(System.in);
            } //close catch
        }
        return projectFee;
    }

    public double getProjectFeePaid(double projectFee) {
        Scanner input = new Scanner(System.in);
        boolean success10 = false;

        while (!success10) {
            try {
                System.out.println("Enter project fee paid to date: ");
                projectFeePaid = input.nextDouble();

                while (projectFeePaid > projectFee) {
                    System.out.println("Project fee paid cannot be more than project total fee! Try again: ");
                    projectFeePaid = input.nextDouble();
                } //close try
                System.out.println("Accepted");
                System.out.println("Project Fee paid: R" + projectFeePaid);
                success10 = true; //escape the while
            } catch (Exception ex) {
                System.out.println("You must enter a integer value for project fee paid!");
                input = new Scanner(System.in);
            } //close catch
        }
        return projectFeePaid;
    }

    public String toString() {
        String output = "Project ID: " + projectID;
        output += "\nProject Name: " + projectName;
        output += "\nProject Type: " + projectType;
        output += "\nProject Address: " + projectAddress;
        output += "\nProject Erf Number: " + projectErf;
        output += "\nProject Fee: " + projectFee;
        output += "\nFees paid to date: " + projectFeePaid;
        output += "\nProject deadline: " + projectDeadline;

        return output;
    }

    public void WriteToProject() {
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "swordfish"
            );
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();

            int rowsAffected;

            // Add a new project to database:
            rowsAffected = statement.executeUpdate(
                    "INSERT INTO Projects VALUES ('" + projectID + "', '" + projectName + "', '" + projectType + "', '" +
                            projectAddress + "', '" + projectErf + "', '" + projectFee + "', '" + projectFeePaid +
                            "', '" + projectDeadline + "')"

            );
            System.out.println("\nQuery complete, " + rowsAffected + " project added to database successfully.");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void displayProjects() {
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
    }

    public void EditEntireProject(int editProject, int newProjectNum, String newProjectName, String newProjectType, String newProjectAddress,
                                  int newProjectErfNum, Double newProjectCost, Double newProjectPaid, String newProjectDeadline) {
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "swordfish"
            );
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();

            int rowsAffected;

            // Edit a project in database:
            rowsAffected = statement.executeUpdate(
                    "UPDATE Projects set projectID = '" + newProjectNum + "'," +
                            "projectName = '" + newProjectName + "',projectType = '" + newProjectType + "', projectAddress ='" +
                            newProjectAddress + "',projectErf = '" + newProjectErfNum + "',projectFee = '" + newProjectCost +
                            "',projectFeePaid = '" + newProjectPaid + "' ," +
                            "projectDeadline = '" + newProjectDeadline + "'WHERE projectID = '" + editProject + "'"

            );
            System.out.println("\nQuery complete, " + rowsAffected +" rows edited.");
            printAllFromTableProjects(statement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void finalizeProject(int finProject) {

        Date now = new Date();
        String finalized_year = new SimpleDateFormat("YYYY", Locale.ENGLISH).format(now);
        String finalized_month = new SimpleDateFormat("MM", Locale.ENGLISH).format(now);
        String finalized_day = new SimpleDateFormat("dd", Locale.ENGLISH).format(now);

        String dateFinalized = finalized_year + "-" + finalized_month + "-" + finalized_day;
        LocalDate date = LocalDate.parse(dateFinalized);
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost (this PC)
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "otheruser",
                    "swordfish"
            );
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();
            int rowsAffected;

            try {
                rowsAffected = statement.executeUpdate(
                        "INSERT INTO completedProjects (projectID,projectName,projectType,projectAddress,projectErf," +
                                "projectFee,projectFeePaid,projectDeadline) " +
                                "SELECT * FROM Projects WHERE projectID = '" + finProject + "' AND projectFeePaid = projectFee"

                );
                rowsAffected = statement.executeUpdate(
                        "UPDATE completedProjects SET projectFinalized = '" + date + "' WHERE projectID = '" + finProject + "'"

                );
                rowsAffected = statement.executeUpdate(
                        "DELETE FROM Projects WHERE projectID = '" + finProject + "' AND projectFeePaid = projectFee"

                );

            } catch (SQLException throwable) {
                System.out.println("Finalize unsuccessful!");
                System.out.println("Check if project ID matches existing project ID");
                System.out.println("If client has paid in full please edit project and update fee paid to date");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        public void viewIncomplete (){
            Date now = new Date();
            String finalized_year = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(now);
            String finalized_month = new SimpleDateFormat("MM", Locale.ENGLISH).format(now);
            String finalized_day = new SimpleDateFormat("dd", Locale.ENGLISH).format(now);

            String dateFinalized = finalized_year + "-" + finalized_month + "-" + finalized_day;
            LocalDate today = LocalDate.parse(dateFinalized);

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

                // Set up finished, do some stuff:
                //executeQuery: runs a SELECT statement and returns the results.
                project_results = statement.executeQuery("SELECT projectID, projectName,projectType,projectAddress," +
                        "projectErf,projectFee,projectFeePaid,projectDeadline FROM Projects WHERE projectDeadline < '"+today+"'");
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
        }

        public static void printAllFromTableProjects (Statement statement) throws SQLException {

            ResultSet results = statement.executeQuery("SELECT projectID, projectName,projectType,projectAddress," +
                    "projectErf,projectFee,projectFeePaid,projectDeadline FROM Projects");
            while (results.next()) {
                System.out.println(
                        results.getInt("projectID") + ", "
                                + results.getString("projectName") + ", "
                                + results.getString("projectType") + ", "
                                + results.getString("projectAddress") + ", "
                                + results.getInt("projectErf") + ", "
                                + results.getDouble("projectFee") + ", "
                                + results.getDouble("projectFeePaid") + ", "
                                + results.getDate("projectDeadline")
                );
            }
        }
}






