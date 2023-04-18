package cn.edu.bupt;

import java.util.List;

public class Student {
    private String s_id;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String gender;
    private String affiliation;
    private String class_id;
    private String major;
    private String enroll_date;
    private String grad_date;
    private List<Course> courses;
    private List<Project> projs;

    public String getSid() {
        return s_id;
    }

    public void setSid(String s_id) {
        this.s_id = s_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getClassId() {
        return class_id;
    }

    public void setClassId(String class_id) {
        this.class_id = class_id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEnrollDate() {
        return enroll_date;
    }

    public void setEnrollDate(String enroll_date) {
        this.enroll_date = enroll_date;
    }

    public String getGradDate() {
        return grad_date;
    }

    public void setGradDate(String grad_date) {
        this.grad_date = grad_date;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Project> getProjs() {
        return projs;
    }

    public void setProjs(List<Project> projs) {
        this.projs = projs;
    }
}

class Course {
    private String course_id;
    private String credits;
    private int grade;

    // 省略构造方法、Getter 和 Setter 方法
}

class Project {
    private String proj_name;
    private String proj_time;
    private String describe;

    public Project(String proj_name, String proj_time, String describe) {
        this.proj_name = proj_name;
        this.proj_time = proj_time;
        this.describe = describe;
    }
    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getProj_time() {
        return proj_time;
    }

    public void setProj_time(String proj_time) {
        this.proj_time = proj_time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
