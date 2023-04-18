package cn.edu.bupt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DB {

    private static final String FILE_PATH = "src/main/resources/db.json"; // JSON文件路径
    private static final Gson gson = new Gson(); // GSON对象

    // 新增学生信息
//    public static void addStudent(Student student) {
//        try {
//            // 读取JSON文件中的数据
//            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
//            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();
//
//            // 将学生对象转换为JSON对象
//            JsonObject jsonStudent = gson.toJsonTree(student).getAsJsonObject();
//
//            // 将JSON对象添加到JSON数组中
//            jsonArray.add(jsonStudent);
//
//            // 将JSON数组写回文件
//            FileWriter fw = new FileWriter(FILE_PATH);
//            fw.write(gson.toJson(jsonArray));
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // 删除学生信息
    public static void deleteStudent(int sId) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            // 遍历JSON数组，查找需要删除的学生信息
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                int id = jsonStudent.get("s_id").getAsInt();
                if (id == sId) {
                    // 删除该学生信息
                    jsonArray.remove(i);
                    break;
                }
            }

            // 将JSON数组写回文件
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(gson.toJson(jsonArray));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 修改学生信息
//    public static void updateStudent(Student student) {
//        try {
//            // 读取JSON文件中的数据
//            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
//            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();
//
//            // 遍历JSON数组，查找需要修改的学生信息
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
//                int id = jsonStudent.get("s_id").getAsInt();
//                if (id == student.getsId()) {
//                    // 将学生对象转换为JSON对象并替换原有JSON对象
//                    jsonArray.set(i, gson.toJsonTree(student).getAsJsonObject());
//                    break;
//                }
//            }
//
//            // 将JSON数组写回文件
//            FileWriter fw = new FileWriter(FILE_PATH);
//            fw.write(gson.toJson(jsonArray));
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // 查询学生信息
    // public static List<Student> getStudents()

    public static void main(String[] args) {
        // 新增学生信息
        deleteStudent(2020213362);

    }
}