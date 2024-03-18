## COMP3005 Assignment 3 Question 1
#### Emily Tang 101192604

Implement a PostgreSQL database using the following schema:

* Table name: `students`
* Fields:
  * `student_id`: Integer, Primary Key, Auto-increment
  * `first_name`: Text, Not Null
  * `last_name`: Text, Not Null
  * `email`: Text, Not Null, Unique
  * `enrollment_date`: Date

### Demo
Insert video here!!!

### Setup and Usage
1. Launch pgAdmin4.
2. Create a database called `assignment3`.
3. Right-click on `assignment3` and select `Query Tool`.
4. Run the following query (can download `assignment3.sql` and run it instead):
    ```
   CREATE TABLE students (
       student_id         SERIAL,
       first_name         VARCHAR(255) NOT NULL,
       last_name          VARCHAR(255) NOT NULL,
       email              VARCHAR(255) UNIQUE NOT NULL,
       enrollment_date    DATE,
       PRIMARY KEY (student_id)
   );
   
   INSERT INTO students (first_name, last_name, email, enrollment_date)
   VALUES
   ('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
   ('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
   ('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');
   ```
5. Open the folder `a3q1` in IntelliJ.
6. Update the `URL`, `USER`, and `PASSWORD` variables on lines 13, 18, and 23 of `Main.java` as necessary.
7. Run the main method in `Main.java`.

### Methods
- `getAllStudents()` - Retrieves and displays all records from the `students` table.
- `addStudent(firstName, lastName, email, enrollmentDate)` - Inserts a new student record into the students table.
- `updateStudentEmail(studentID, newEmail)` - Updates the email for a student with the specified student id.
- `deleteStudent(studentID)` - Deletes the record of the student with the specified student id.