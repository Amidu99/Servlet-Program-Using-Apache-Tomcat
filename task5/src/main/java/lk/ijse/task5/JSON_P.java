package lk.ijse.task5;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="jsonProcess", urlPatterns = "/jsonp")
public class JSON_P extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        // using BufferedReader
        BufferedReader b_reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        b_reader.lines().forEach(line-> sb.append(line+"\n"));
        System.out.println(sb);

        // using JsonReader
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        // object read
        System.out.println("Name: "+jsonObject.getString("name"));
        System.out.println("City: "+jsonObject.getString("city"));
        System.out.println("Email: "+jsonObject.getString("email"));

        // array read
        JsonArray jsonValues = reader.readArray();
        for (int i=0; i<jsonValues.size(); i++){
            JsonObject jsonObject1 = jsonValues.getJsonObject(i);
            System.out.println("Name: "+jsonObject1.getString("name"));
            System.out.println("City: "+jsonObject1.getString("city"));
            System.out.println("Email: "+jsonObject1.getString("email"));
        }
    }
}