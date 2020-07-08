package Level3_Task8;

import java.sql.*;
import java.util.Scanner;

public class People {

    // Attributes
    private static int personID;
    private static int project;
    private static String type;
    private static String name;
    private static String number;
    private static String email;
    private static String address;

    // Methods
    // Constructor
    public People(int personID,int project, String type, String name, String number, String email, String address) {
        People.personID = personID;
        People.project = project;
        People.type = type;
        People.name = name;
        People.number = number;
        People.email = email;
        People.address = address;
    }
    //no args constructor
    public People() {
    }
    // getting the project number associated with person
    // added validation so that a project number must be entered

    public int getPersonID() {
        Scanner input = new Scanner(System.in);
        boolean success15 = false;

        while (!success15) {
            try {
                System.out.println("Enter ID number: ");
                personID = input.nextInt();
                String personIDTest = String.valueOf(personID);
                String regex = "[0-9]+";

                while (!personIDTest.matches(regex)) {
                    personID = input.nextInt();
                } //close try
                System.out.println("Accepted");
                System.out.println("Person ID number: " + personID);
                success15 = true; //escape the while
            } catch(Exception e){
                System.out.println("ID number can only contain digits! Try again: ");
                input = new Scanner(System.in);
            } //close catch
        }
        return Integer.parseInt(String.valueOf(personID));
    }

    public int getProject() {
        Scanner input = new Scanner(System.in);
        boolean success7 = false;

        while (!success7) {
            try {
                System.out.println("Enter project number associated with person");
                project = input.nextInt();
                String projectTest = String.valueOf(project);
                String regex = "[0-9]+";

                while (!projectTest.matches(regex)) {
                    project = input.nextInt();
                } //close try
                System.out.println("Accepted");
                System.out.println("Project number: " + project);
                success7 = true; //escape the while
            } catch(Exception e){
                System.out.println("Project number can only contain digits! Try again: ");
                input = new Scanner(System.in);
            } //close catch
        }
        return Integer.parseInt(String.valueOf(project));
    }

    // get type of person
    public String getType() {
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("Select type of person to add\n1- Architect\n2- Contractor\n3- Customer\n ");
        selection = input.nextInt();

            if (selection == 1) {
                type = "Architect";
            } else if (selection == 2) {
                type = "Contractor";
            } else if (selection == 3) {
                type = "Customer";
            }
        return type;
    }

    // get name of person
    // added a while loop that checks if the name entered matches the range specified
    // no numbers or special characters is accepted
    public String getName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name: ");
        name = input.nextLine();

        while(!name.matches("[a-zA-Z ,]+")){
            System.out.println("No numbers or special characters!\nPlease enter name again: ");
            name = input.nextLine();
        }
        System.out.println("Name: " + name);
        return name;
    }

    // get contact number of person
    public String getNumber() {
        Scanner input = new Scanner(System.in);
        boolean success5 = false;

        while (!success5) {
            try {
                System.out.println("Enter contact number of person: \n");
                number = input.next();
                String regex = "[0-9]+";

                    while (number.length() != 10 || !number.matches(regex)) {
                        System.out.println("Number should be 10 digits and not contain any letters! Try again: ");
                        number = input.next();
                    } //close try
                    System.out.println("Accepted");
                    System.out.println("Contact number: " + number);
                success5 = true; //escape the while
                } catch(Exception e){
                    System.out.println("Error!");
                    input = new Scanner(System.in);
                } //close catch
            }
        return number;
        }

    // get email of person
    // added validation to check email
    public String getEmail() {
        Scanner input = new Scanner(System.in);

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean success6 = false;

        while (!success6) {
            try {
                System.out.println("Enter email:");
                email = input.next();

                while (!email.matches(regex)) {
                    System.out.println("Email should contain prefix@suffix.com");
                    email = input.next();
                } //close try
                System.out.println("Accepted");
                System.out.println("Email: " + email);
                success6 = true; //escape the while

            } catch(Exception e){
                System.out.println("Error!");
                input = new Scanner(System.in);
            } //close catch
        }
        return email;
    }

    // get address of person
    public String getAddress() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter address of person: \n");
        address = input.nextLine();

        return address;
    }

    public String toString() {
        String output = "Project: " + project;
        output += "\nType: " + type;
        output += "\nName: " + name;
        output += "\nNumber: " + number;
        output += "\nEmail: " + email;
        output += "\nAddress: " + address;

        return output;
    }
    // method to write new person to text file
    public void WriteToPeople() {
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

            // Add a new person to database:
            rowsAffected = statement.executeUpdate(
                    "INSERT INTO People VALUES ('"+personID+"','"+project+"', '"+type+"', '"+name+"', '"+
                            number+"', '"+email+"', '"+address+"')"

            );
            System.out.println("\nQuery complete, " + rowsAffected + " person added to database successfully.");
            printAllFromTablePeople(statement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    // to display information about people listed in people text file
    public void displayPeople() {
        // Connect to the PoisePMS database, via the jdbc:mysql: channel on localhost (this PC)
        // Use username "otheruser", password "swordfish".
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser",
                    "swordfish"
            );
            Statement statement = connection.createStatement();
            ResultSet people_results;
            // Set up finished, do some stuff:
            //executeQuery: runs a SELECT statement and returns the results.
            people_results = statement.executeQuery("SELECT personID, projectID,type,name," +
                    "number,email,address FROM People");
            System.out.println("--- People List ----");
            while (people_results.next()) {
                System.out.println(people_results.getInt("personID") + ", " +
                        people_results.getInt("projectID") + ", " +
                        people_results.getString("type") + ", " +
                        people_results.getString("name") + ", " +
                        people_results.getString("number") + ", " +
                        people_results.getString("email") + ", " +
                        people_results.getString("address"));
            }
            System.out.println(" -------------------- ");
            // Close up our connections
            people_results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // We only want to catch a SQLException - anything else is off-limits for now.
            e.printStackTrace();
        }
    }

    public void EditPersonDetails(String editPerson,int newPID, int newID, String newType, String newName,
                                  String newNumber,String newEmail,String newAddress) {
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
                    "UPDATE People set personID = '"+newPID+"'," +
                            "projectID = '"+newID+"',type = '"+newType+"', name ='"+
                            newName+"',number = '"+newNumber+"',email = '"+newEmail+"',address = '"+newAddress+"' WHERE " +
                            "personID = '"+editPerson+"'"

            );
            System.out.println("\nQuery complete, " + rowsAffected + " person details updated successfully in database.");
            printAllFromTablePeople(statement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printAllFromTablePeople(Statement statement) throws SQLException{

        ResultSet results = statement.executeQuery("SELECT personID,projectID,type,name,number," +
                "email,address FROM People");
        while (results.next()) {
            System.out.println(results.getInt("personID") + ", "
                            + results.getString("projectID") + ", "
                            + results.getString("type") + ", "
                            + results.getString("name") + ", "
                            + results.getString("number")+ ", "
                            + results.getString("email")+ ", "
                            + results.getString("address")

            );
        }
    }
}
