/**
 * This class encapsulates some reading
 * , modifying and writing methods involving Student.json files.
 */
package cn.edu.bupt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.List;

public class DB {

    private static final String FILE_PATH = "src/main/resources/db.json";
    private static final Gson gson = new Gson(); // GSON Object

    public static void addStudent(Student student) {
        try {
            // read the JSON Files
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();
            // 将学生对象转换为JSON对象
            JsonObject jsonStudent = gson.toJsonTree(student).getAsJsonObject();

            // 将JSON对象添加到JSON数组中
            jsonArray.add(jsonStudent);

            // 将JSON数组写回文件
           writeToJson(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the student's project
     * @param name project's name
     * @param time project's time
     * @return An array of student's object
     */
    public static JsonArray deleteProject(String name, String time) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            // 遍历JSON数组，查找需要删除的项目信息
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonProject = jsonArray.get(i).getAsJsonObject();
                if (jsonProject.has("projs")) {
                    JsonArray projs = jsonProject.getAsJsonArray("projs");
                    for (int j = 0; j < projs.size(); j++) {
                        JsonObject proj = projs.get(j).getAsJsonObject();
                        if (proj.has("proj_name") && proj.has("proj_time")) {

                            String projectName = proj.get("proj_name").getAsString();
                            String projectTime = proj.get("proj_time").getAsString();
                            if (projectName.equals(name) && projectTime.equals(time)) {
                                // remove the project from the array
                                projs.remove(j);
                                break;
                            }
                        }
                    }
                }
            }
          return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Delete student's Honor
     */
    public static JsonArray deleteHonor(String honor_name, String honor_time) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                if (jsonStudent.has("honors")) {
                    JsonArray honors = jsonStudent.getAsJsonArray("honors");
                    for (int j = 0; j < honors.size(); j++) {
                        JsonObject jsonHonor = honors.get(j).getAsJsonObject();
                        if (jsonHonor.has("honor_name") && jsonHonor.has("honor_time")) {
                            String honorName = jsonHonor.get("honor_name").getAsString();
                            String honorTime = jsonHonor.get("honor_time").getAsString();
                            if (honorName.equals(honor_name) && honorTime.equals(honor_time)) {
                                honors.remove(j);
                                break;
                            }
                        }
                    }
                }
            }

            // 将JSON数组写回文件
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    /**
     * Transfer a student object into JsonArray, in order to write them into JSON file.
     * @param student student object that are modified
     * @return JsonArray Object
     */
    public static JsonArray updateStudent(Student student) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            // 遍历JSON数组，查找需要修改的学生信息
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                String id = jsonStudent.get("s_id").getAsString();
                if (id.equals(student.getSid())) {
                    // 将学生对象转换为JSON对象并替换原有JSON对象
                    jsonArray.set(i, gson.toJsonTree(student).getAsJsonObject());
                    break;
                }
            }
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the Student Object by the ID of the Students
     * @param sId Student's ID
     * @return Student Object
     */
    public static Student getStudent(String sId) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            // 遍历JSON数组，查找需要查询的学生信息
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                String id = jsonStudent.get("s_id").getAsString();
                if (id.equals(sId)) {
                    // 将JSON对象转换为学生对象
                    return gson.fromJson(jsonStudent, Student.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete the Skill
     * @return JsonArray that the skill is deleted
     */
    public static JsonArray deleteSkill(String skill_name, String skill_level) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = new JsonParser().parse(br).getAsJsonArray();

            // 遍历JSON数组，查找需要删除的skills信息
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                if (jsonStudent.has("skills")) {
                    JsonArray skills = jsonStudent.getAsJsonArray("skills");
                    for (int j = 0; j < skills.size(); j++) {
                        JsonObject jsonSkill = skills.get(j).getAsJsonObject();
                        if (jsonSkill.has("skill_name") && jsonSkill.has("skill_level")) {
                            String skillName = jsonSkill.get("skill_name").getAsString();
                            String skillLevel = jsonSkill.get("skill_level").getAsString();
                            if (skillName.equals(skill_name) && skillLevel.equals(skill_level)) {
                                skills.remove(j);
                                break;
                            }
                        }
                    }
                }
            }
       return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Write the Student Object into JSON file
     * @param jsonArray
     */
    public static void writeToJson(JsonArray jsonArray){
        try {
                // 将JSON数组写回文件
                FileWriter fw = new FileWriter(FILE_PATH);
                fw.write(gson.toJson(jsonArray));
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }






}