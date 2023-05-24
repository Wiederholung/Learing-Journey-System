package cn.edu.bupt.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBTest {

    Student student;

    @BeforeEach
    void setUp() {
        student = DB.getStudent("2020213362");
    }

    @Test
    void exportStudent() {
        JsonArray s = DB.exportStudent(student);
        if (s != null) {
            assertEquals("2020213362", s.get(0).getAsJsonObject().get("s_id").getAsString());
        }
    }

    @Test
    void deleteProject() {
        assertNotNull(DB.deleteProject("123", "123"));
        assertFalse(student.getProjs().contains(new Student.Project("123", "123", "22123123")));
    }

    @Test
    void deleteHonor() {
        assertNotNull(DB.deleteHonor("123", "123"));
        assertFalse(student.getHonors().contains(new Student.Honor("123", "123")));
    }

    @Test
    void updateStudent() {
        student.setName("Yichang Zhang2");
        JsonArray s = DB.updateStudent(student);
        assertNotNull(s);
        assertEquals("Yichang Zhang2", s.get(0).getAsJsonObject().get("name").getAsString());
    }

    @Test
    void deleteSkill() {
        assertNotNull(DB.deleteSkill("123", "123"));
        assertFalse(student.getSkills().contains(new Student.Skill("123", "123")));
    }

    @Test
    void writeToJson() {
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(gson.toJsonTree(student).getAsJsonObject());
        DB.writeToJson(jsonArray);
    }

    @Test
    void testWriteToJson() {
    }
}