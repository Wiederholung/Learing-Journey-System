/**
 * This class encapsulates some reading
 * , modifying and writing methods involving Student.json files.
 */
package cn.edu.bupt.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DB {

    private static final String FILE_PATH = "src/main/resources/db.json";
    private static final Gson gson = new Gson(); // GSON Object

    public static JsonArray exportStudent(Student student) {
        try {
            JsonObject jsonStudent = gson.toJsonTree(student).getAsJsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(jsonStudent);
            return jsonArray;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete the student's project
     *
     * @param name project's name
     * @param time project's time
     * @return An array of student's object
     */
    public static JsonArray deleteProject(String name, String time) {
        try {
            // 读取JSON文件中的数据
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();

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
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();

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

    /**
     * Transfer a student object into JsonArray, in order to write them into JSON file.
     *
     * @param student student object that are modified
     * @return JsonArray Object
     */
    public static JsonArray updateStudent(Student student) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                String id = jsonStudent.get("s_id").getAsString();
                if (id.equals(student.getSid())) {
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
     *
     * @param sId Student's ID
     * @return Student Object
     */
    public static Student getStudent(String sId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();
                String id = jsonStudent.get("s_id").getAsString();
                if (id.equals(sId)) {
                    return gson.fromJson(jsonStudent, Student.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the JsonArray of the Students
     *
     * @return JsonArray of the Students
     */
    public static JsonArray getStudents() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            return JsonParser.parseReader(br).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete the Skill
     *
     * @return JsonArray that the skill is deleted
     */
    public static JsonArray deleteSkill(String skill_name, String skill_level) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            JsonArray jsonArray = JsonParser.parseReader(br).getAsJsonArray();
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
     *
     * @param jsonArray JsonArray Object
     */
    public static void writeToJson(JsonArray jsonArray) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(gson.toJson(jsonArray));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the Student Object into JSON file
     * by setting a specific path
     */
    public static void writeToJson(JsonArray jsonArray, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(gson.toJson(jsonArray));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}