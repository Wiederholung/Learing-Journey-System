package cn.edu.bupt;

import javax.swing.*;

public class ExportInfo {
    Student student;


    public void export(String studentID) {
        student = DB.getStudent(studentID);
        if (student != null) {
            String path = "src/main/resources/" + student.getName() + ".json";
            DB.writeToJson(DB.exportStudent(student), path);
            JOptionPane.showMessageDialog(null, "Data export successfully!");
        }
    }


}
