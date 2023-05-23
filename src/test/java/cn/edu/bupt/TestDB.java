package cn.edu.bupt;

import cn.edu.bupt.dao.DB;
import cn.edu.bupt.dao.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestDB {
    Student student = DB.getStudent("2020213362");

    @Test
    void testGetStudent() {
        System.out.println(student.toString());
    }

    @Test
    void deleteProject() {
        System.out.println(student.getProjs().toString());
        List<Student.Project> projs = student.getProjs();
        projs.add(new Student.Project("123", "123", "22123123"));
        student.setProjs(projs);
        DB.writeToJson(DB.updateStudent(student));
        System.out.println(student.getProjs().toString());
        DB.writeToJson(DB.deleteProject("123", "123"));
        student = DB.getStudent("2020213362");
        System.out.println(student.getProjs().toString());
        //Check whether project "123" is being added and deleted
    }

    @Test
    void deleteHonor() {
        System.out.println(student.getHonors().toString());
        List<Student.Honor> honors = student.getHonors();
        honors.add(new Student.Honor("123", "123"));
        student.setHonors(honors);
        DB.writeToJson(DB.updateStudent(student));
        System.out.println(student.getHonors().toString());
        DB.writeToJson(DB.deleteHonor("123", "123"));
        student = DB.getStudent("2020213362");
        System.out.println(student.getHonors().toString());
        //Check whether honor "123" is being added and deleted
    }

    @Test
    void updateStudent() {
        student.setName("Yichang Zhang");
        DB.writeToJson(DB.updateStudent(student));
        Student testStudent = DB.getStudent("2020213362");
        System.out.println(testStudent.getName());
        //repeal
        student.setName("Anna Shelbacova");
        DB.writeToJson(DB.updateStudent(student));
    }


    @Test
    void deleteSkill() {
        System.out.println(student.getSkills().toString());
        List<Student.Skill> skill = student.getSkills();
        skill.add(new Student.Skill("123", "123"));
        student.setSkills(skill);
        DB.writeToJson(DB.updateStudent(student));
        System.out.println(student.getSkills().toString());
        DB.writeToJson(DB.deleteSkill("123", "123"));
        student = DB.getStudent("2020213362");
        System.out.println(student.getSkills().toString());
        //Check whether honor "123" is being added and deleted
    }


}