/**

 This class encapsulates the data access operations for Student objects,
 including reading, modifying, and writing methods involving Student.json files.
 */
package com.metattri.dao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DB {private static final String FILE_PATH = "src/main/resources/db.json";
    private static final Gson gson = new Gson(); // GSON Object

    /**
     * Exports a Student object as a JsonArray.
     *
     * @param student The Student object to export.
     * @return A JsonArray containing the exported Student object.
     */
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
     * Deletes a project from the student's records.
     *
     * @param name The name of the project to delete.
     * @param time The time of the project to delete.
     * @return An array of student objects after deleting the project.
     */
    public static JsonArray deleteProject(String name, String time) {
        try {
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
     * Deletes a honor from the student's records.
     *
     * @param honor_name The name of the honor to delete.
     * @param honor_time The time of the honor to delete.
     * @return An array of student objects after deleting the honor.
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

            // Write the JSON array back to the file
            writeToJson(jsonArray);

            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Updates a student object and converts it into a JsonArray for writing to the JSON file.
     *
     * @param student The modified student object.
     * @return A JsonArray representing the updated student objects.
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
     * Retrieves a Student object by the student's ID.
     *
     * @param sId The ID of the student.
     * @return The Student object with the specified ID.
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
     * Retrieves the JsonArray of student objects.
     *
     * @return The JsonArray of student objects.
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
     * Deletes a skill from the student's records.
     *
     * @param skill_name  The name of the skill to delete.
     * @param skill_level The level of the skill to delete.
     * @return An array of student objects after deleting the skill.
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
     * Writes a JsonArray to the JSON file.
     *
     * @param jsonArray The JsonArray to write.
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
     * Writes a JsonArray to the JSON file at a specified path.
     *
     * @param jsonArray The JsonArray to write.
     * @param path      The path of the JSON file.
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

