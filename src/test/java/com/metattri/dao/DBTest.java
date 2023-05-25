package com.metattri.dao;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the DB class.
 */
class DBTest {

    Student student;

    /**
     * Tear down method executed after all test cases.
     */
    @AfterAll
    static void tearDown() {
        Student s = DB.getStudent("2020213361");
        if (s != null) {
            s.setName("Hu Hansan-new");
        }
        JsonArray sArray = DB.updateStudent(s);
        if (sArray != null) {
            DB.writeToJson(sArray);
        }
        s = DB.getStudent("2020213361");
        if (s != null) {
            s.setName("Hu Hansan");
        }
        sArray = DB.updateStudent(s);
        if (sArray != null) {
            DB.writeToJson(sArray);
        }
    }

    /**
     * Set up method executed before each test case.
     */
    @BeforeEach
    void setUp() {
        student = DB.getStudent("2020213362");
    }

    /**
     * Test case for exporting a student.
     */
    @Test
    void exportStudent() {
        JsonArray s = DB.exportStudent(student);
        if (s != null) {
            assertEquals("2020213362", s.get(0).getAsJsonObject().get("s_id").getAsString());
        }
    }

    /**
     * Test case for deleting a project.
     */
    @Test
    void deleteProject() {
        assertNotNull(DB.deleteProject("Simulated business negotiation competition", "2023/6/2"));
        assertFalse(student.getProjs().contains(new Student.Project("123", "123", "22123123")));
    }

    /**
     * Test case for deleting a honor.
     */
    @Test
    void deleteHonor() {
        assertNotNull(DB.deleteHonor("University-level National Scholarship in 2023", "2023/6/2"));
        assertFalse(student.getHonors().contains(new Student.Honor("123", "123")));
    }

    /**
     * Test case for updating a student.
     */
    @Test
    void updateStudent() {
        student.setName("Yichang Zhang2");
        JsonArray s = DB.updateStudent(student);
        assertNotNull(s);
        assertEquals("Yichang Zhang2", s.get(0).getAsJsonObject().get("name").getAsString());
    }

    /**
     * Test case for deleting a skill.
     */
    @Test
    void deleteSkill() {
        assertNotNull(DB.deleteSkill("C++", "A"));
        assertFalse(student.getSkills().contains(new Student.Skill("123", "123")));
    }

}