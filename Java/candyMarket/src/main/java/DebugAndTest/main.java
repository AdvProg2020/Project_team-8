package DebugAndTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.web.HTMLEditorSkin;

public class main {
    public static void main(String[] args) throws JsonProcessingException {
        Product product = new Product(null, "pizza");
        User user = new User("arash", product);
        product.setUser(user);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        User user1 = mapper.readValue(json,User.class);
        String productJson = mapper.writeValueAsString(product);
        System.out.println(productJson);
    }
}
