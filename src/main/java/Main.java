import java.sql.*;

/**
 * A class to connect to the assignment3 database in PostgreSQL.
 *
 * @author Emily Tang 101192604
 */
public class Main {

    /**
     * A String representing the URL to connect to the assignment3 database in PostgreSQL.
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/assignment3";

    /**
     * A String representing the username to connect to the assignment3 database in PostgreSQL.
     */
    private static final String USER = "postgres";

    /**
     * A String representing the password to connect to the assignment3 database in PostgreSQL.
     */
    private static final String PASSWORD = "postgres";

    /**
     * A Connection representing the connection to the assignment3 database in PostgreSQL.
     */
    private Connection connection;

    /**
     * Constructs an instance of Main. Establishes connection to PostgreSQL.
     */
    public Main() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception ignored) {}
    }

    /**
     * Retrieves and prints all records from the students table.
     */
    public void getAllStudents() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("student_id") + " \t");
                System.out.print(resultSet.getString("first_name") + " \t");
                System.out.print(resultSet.getString("last_name") + " \t");
                System.out.print(resultSet.getString("email") + " \t");
                System.out.print(resultSet.getString("enrollment_date") + " \t\n");
            }
        } catch (Exception ignored) {}
    }

    /**
     * Inserts a new student record into the students table.
     *
     * @param firstName A String representing the first name of the student record to add.
     * @param lastName A String representing the last name of the student record to add.
     * @param email A String representing the email of the student record to add.
     * @param enrollmentDate A String representing the enrollment date of the student record to add.
     */
    public void addStudent(String firstName, String lastName, String email, String enrollmentDate) {
        try {
            String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, Date.valueOf(enrollmentDate));
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {}
    }

    /**
     * Updates the email for a student with the specified student id.
     *
     * @param studentID An integer representing the student id of the student to update.
     * @param newEmail A String representing the new email of the student.
     */
    public void updateStudentEmail(int studentID, String newEmail) {
        try {
            String sql = "UPDATE students SET email = ? WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, studentID);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {}
    }

    /**
     * Deletes the record of the student with the specified student id.
     *
     * @param studentID An integer representing the student id of the student to delete.
     */
    public void deleteStudent(int studentID) {
        try {
            String sql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentID);
            preparedStatement.executeUpdate();
        } catch (Exception ignored) {}
    }

    /**
     * For testing purposes.
     */
    public static void main(String[] args) {
        Main main = new Main(); // an object to perform specific CRUD operations on the students table

        /*
        getAllStudents() retrieves and prints all records from the students table.

        1 	John 	Doe 	john.doe@example.com 	2023-09-01
        2 	Jane 	Smith 	jane.smith@example.com 	2023-09-01
        3 	Jim 	Beam 	jim.beam@example.com 	2023-09-02
         */
        System.out.println("Testing getAllStudents()...");
        main.getAllStudents();
        System.out.println();

        /*
        addStudent() inserts a new student record into the students table.

        1 	John 	Doe 	john.doe@example.com 	2023-09-01
        2 	Jane 	Smith 	jane.smith@example.com 	2023-09-01
        3 	Jim 	Beam 	jim.beam@example.com 	2023-09-02
        4   Emily   Tang    emily.tang@example.com  2023-09-04
         */
        System.out.println("Testing addStudent()...");
        main.addStudent("Emily", "Tang", "emily.tang@example.com", "2023-09-04");
        main.getAllStudents();
        System.out.println();

        /*
        updateStudentEmail() updates the email for a student with the specified studentID.

        1 	John 	Doe 	johnny.doe@example.com 	2023-09-01
        2 	Jane 	Smith 	jane.smith@example.com 	2023-09-01
        3 	Jim 	Beam 	jim.beam@example.com 	2023-09-02
        4   Emily   Tang    emily.tang@example.com  2023-09-04
         */
        System.out.println("Testing updateStudentEmail()...");
        main.updateStudentEmail(1, "johnny.doe@example.com");
        main.getAllStudents();
        System.out.println();

        /*
        deleteStudent() deletes the record of the student with the specified student id.

        1 	John 	Doe 	johnny.doe@example.com 	2023-09-01
        3 	Jim 	Beam 	jim.beam@example.com 	2023-09-02
        4   Emily   Tang    emily.tang@example.com  2023-09-04
         */
        System.out.println("Testing deleteStudent()...");
        main.deleteStudent(2);
        main.getAllStudents();
    }
}