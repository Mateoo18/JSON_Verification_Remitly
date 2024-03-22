package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Aws_Verify {

    private final String name;
    private final JSONObject document;
    private final JSONArray statements;

    boolean[] islegitResource ;

    public Aws_Verify(String name, JSONObject document, JSONArray statements) {
        this.name = name;
        this.document = document;
        this.statements = statements;
        this.islegitResource = new boolean[statements.length()];
    }

    public String getName() {
        return name;
    }

    public JSONObject getDocument() {
        return document;
    }

    public JSONArray getStatements() {
        return statements;
    }

    public static Aws_Verify readAws_Verify(String path) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(path)));

            JSONObject jsonObject = new JSONObject(jsonContent);

            String name = jsonObject.getString("PolicyName");

            JSONObject policyDocument = jsonObject.getJSONObject("PolicyDocument");

            JSONArray statements = policyDocument.getJSONArray("Statement");

            return new Aws_Verify(name, policyDocument, statements);

        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return null;
        }
    }

    public boolean[] verify() {

        for (int i = 0; i < this.statements.length(); i++) {
            JSONObject statement = this.statements.getJSONObject(i);

            String resource = statement.getString("Resource");
            if (!"*".equals(resource)) {
                islegitResource[i] = true;
            } else {
                islegitResource[i] = false;
            }
        }
        return islegitResource;
    }

}
