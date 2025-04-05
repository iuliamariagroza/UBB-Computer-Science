import domain.Student;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAddStudent {
    private StudentXMLRepo studentFileRepository;
    private StudentValidator studentValidator;
    private Service service;

    @BeforeAll
    static void createXML() {
        File xml = new File("studentiTest.xml");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(xml))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                    "<inbox>\n" +
                    "\n" +
                    "</inbox>");
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setup() {
        this.studentFileRepository = new StudentXMLRepo("studentiTest.xml");
        this.studentValidator = new StudentValidator();
        this.service = new Service(this.studentFileRepository, this.studentValidator, null, null, null, null);
    }

    @AfterAll
    static void removeXML() {
        new File("studentiTest.xml").delete();
    }

    //EC Test cases
    @Test
    void testAddStudentWithValidId() {
        Student validIdStudent = new Student("1", "Iulia", 933, "iulia@email.com");
        this.service.addStudent(validIdStudent);
        var students = service.getAllStudenti().iterator();
        assertEquals(validIdStudent, students.next());

        this.service.deleteStudent("1");
    }

    @Test
    void testAddStudentWithEmptyId() {
        Student emptyIdStudent = new Student("", "Diana", 933, "diana@email.com");
        assertThrows(ValidationException.class, () -> service.addStudent(emptyIdStudent));
    }

    @Test
    void testAddStudentWithNullId() {
        Student nullIdStudent = new Student(null, "Iulia", 933, "iulia@email.com");
        assertThrows(NullPointerException.class, () -> service.addStudent(nullIdStudent));
    }

    @Test
    void testAddStudentWithValidName() {
        Student validNameStudent = new Student("1", "Diana", 933, "diana@email.com");
        this.service.addStudent(validNameStudent);
        var students = service.getAllStudenti().iterator();
        assertEquals(validNameStudent, students.next());

        this.service.deleteStudent("1");
    }

    @Test
    void testAddStudentWithEmptyName() {
        Student emptyNameStudent = new Student("3", "", 933, "test@email.com");
        assertThrows(ValidationException.class, () -> service.addStudent(emptyNameStudent));
    }

    @Test
    void testAddStudentWithNullName() {
        Student nullNameStudent = new Student("3", null, 933, "test@email.com");
        assertThrows(ValidationException.class, () -> service.addStudent(nullNameStudent));
    }


    @Test
    void testAddStudentWithValidGroup() {
        Student validStudent = new Student("1", "Iulia", 933, "iulia@email.com");
        this.service.addStudent(validStudent);
        var students = service.getAllStudenti().iterator();
        assertEquals(validStudent, students.next());

        this.service.deleteStudent("1");
    }

    @Test
    void testAddStudentWithNegativeGroup() {
        Student invalidStudent = new Student("2", "Diana", -5, "diana@email.com");
        assertThrows(ValidationException.class, () -> service.addStudent(invalidStudent));
    }

    @Test
    void testAddStudentWithZeroGroup() {
        Student zeroGroupStudent = new Student("2", "Diana", 0, "diana@email.com");
        this.service.addStudent(zeroGroupStudent);
        var students = service.getAllStudenti().iterator();
        assertEquals(zeroGroupStudent, students.next());

        this.service.deleteStudent("2");
    }

    @Test
    void testAddStudentWithValidEmail() {
        Student validEmailStudent = new Student("3", "Iulia", 933, "valid@example.com");
        this.service.addStudent(validEmailStudent);
        var students = service.getAllStudenti().iterator();
        assertEquals(validEmailStudent, students.next());

        this.service.deleteStudent("3");
    }

    @Test
    void testAddStudentWithEmptyEmail() {
        Student emptyEmailStudent = new Student("10", "Diana", 933, "");
        assertThrows(ValidationException.class, () -> service.addStudent(emptyEmailStudent));
    }

    @Test
    void testAddStudentWithNullEmail() {
        Student nullEmailStudent = new Student("10", "Iulia", 933, null);
        assertThrows(ValidationException.class, () -> service.addStudent(nullEmailStudent));
    }

    //BVA Test cases
    @Test
    public void testAddStudentGroupLowerBVABound(){
        Student newStudent1 = new Student("1", "Diana", 0, "diana@email.com");
        this.service.addStudent(newStudent1);
        var students = this.service.getAllStudenti().iterator();
        assertEquals(students.next().getID(), newStudent1.getID());
        this.service.deleteStudent("1");
    }
}
