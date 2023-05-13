package cn.edu.bupt;

import org.junit.jupiter.api.Test;

import java.util.List;

class DBTest {

    @org.junit.jupiter.api.Test
    void addStudent() {
    }

    @org.junit.jupiter.api.Test
    void deleteStudent() {
    }

    @org.junit.jupiter.api.Test
    void updateStudent() {
    }

    @org.junit.jupiter.api.Test
    void getStudent() {
    }

    @Test
    void testGetStudent() {
        Student s = DB.getStudent("2020213362");
        if (s != null) {
            List<Skill> skills = s.getSkills();
            // 将projs转为ArrayList<String[]>
            for (Skill skill : skills) {
                String[] s_honor = new String[2];
                s_honor[0] = skill.getSkill_name();
                s_honor[1] = skill.getSkill_level();
            }
        }
    }
}