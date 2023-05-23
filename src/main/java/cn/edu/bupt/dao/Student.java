/**
 * All class of student, all the getter and setter
 * can be found in this class
 */
package cn.edu.bupt.dao;

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
    private List<Honor> honors;
    private List<Skill> skills;

    @Override
    public String toString() {
        return "Student{" +
                "s_id='" + s_id + '\'' + "\n" +
                ", password='" + password + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", phone='" + phone + '\'' + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", gender='" + gender + '\'' + "\n" +
                ", affiliation='" + affiliation + '\'' + "\n" +
                ", class_id='" + class_id + '\'' + "\n" +
                ", major='" + major + '\'' + "\n" +
                ", enroll_date='" + enroll_date + '\'' + "\n" +
                ", grad_date='" + grad_date + '\'' + "\n" +
                ", Courses='" + courses.toString() + '\'' + "\n" +
                ", Honor='" + honors.toString() + '\'' + "\n" +
                ", Project='" + projs.toString() + '\'' + "\n" +
                ", Skills='" + skills.toString() + '\'' + "\n" +
                '}';
    }

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

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public static class Course {
        private String course_id;
        private String credits;
        private int grade;

        @Override
        public String toString() {
            return "Course{" +
                    "course_id='" + course_id + '\'' +
                    ", credits='" + credits + '\'' +
                    ", grade=" + grade +
                    '}';
        }

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getCredits() {
            return credits;
        }

        public void setCredits(String credits) {
            this.credits = credits;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        // 省略构造方法、Getter 和 Setter 方法
    }

    public static class Project {
        private String proj_name;
        private String proj_time;
        private String describe;

        @Override
        public String toString() {
            return "Project{" +
                    "proj_name='" + proj_name + '\'' +
                    ", proj_time='" + proj_time + '\'' +
                    ", describe='" + describe + '\'' +
                    '}';
        }

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

    public static class Honor {
        private String honor_time;
        private String honor_name;

        public Honor(String honor_time, String honor_name) {
            this.honor_time = honor_time;
            this.honor_name = honor_name;
        }

        @Override
        public String toString() {
            return "Honor{" +
                    "honor_time='" + honor_time + '\'' +
                    ", honor_name='" + honor_name + '\'' +
                    '}';
        }

        public String getHonor_time() {
            return honor_time;
        }

        public void setHonor_time(String honor_time) {
            this.honor_time = honor_time;
        }

        public String getHonor_name() {
            return honor_name;
        }

        public void setHonor_name(String honor_name) {
            this.honor_name = honor_name;
        }
    }

    public static class Skill {
        private String skill_name;
        private String skill_level;

        public Skill(String skill_name, String skill_level) {
            this.skill_name = skill_name;
            this.skill_level = skill_level;
        }

        @Override
        public String toString() {
            return "Skill{" +
                    "skill_name='" + skill_name + '\'' +
                    ", skill_level='" + skill_level + '\'' +
                    '}';
        }

        public String getSkill_name() {
            return skill_name;
        }

        public void setSkill_name(String skill_name) {
            this.skill_name = skill_name;
        }

        public String getSkill_level() {
            return skill_level;
        }

        public void setSkill_level(String skill_level) {
            this.skill_level = skill_level;
        }
    }
}




