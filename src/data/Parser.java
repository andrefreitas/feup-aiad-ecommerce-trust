/*
 * This class is responsible to load data from the .json file
 */
package data;

import com.google.gson.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import ecommerce.*;

public class Parser {

    public ArrayList<User> users;
    public ArrayList<Product> products;
    public String fileName;
    public JsonElement parseTree;

    public Parser(String fileName) {
        this.fileName = fileName;
        parse();
    }

    public void parse() {
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String s = new String(data, "UTF-8");
            JsonParser gParser = new JsonParser();
            parseTree = gParser.parse(s);
            parseUsers();
            parseProducts();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    
    public void parseUsers(){
       JsonObject jsonObject = parseTree.getAsJsonObject();
       JsonArray users = jsonObject.get("users").getAsJsonArray();
       Iterator<JsonElement> usersIterator = users.iterator();
       String name;
       String country;
       String behaviour;
       this.users = new ArrayList<User>();
       
       while (usersIterator.hasNext()){
           JsonObject user = usersIterator.next().getAsJsonObject();
           name = user.get("name").getAsString().trim();
           country = user.get("country").getAsString().trim();
           behaviour = user.get("behaviour").getAsString().trim();
           User u = new User(name, country);
           u.setBehaviour(behaviour);
           // Parse categories
           String category;
           ArrayList<String> categoriesList = new ArrayList();
           JsonArray categories = user.get("categories").getAsJsonArray();
           Iterator<JsonElement> categoriesIterator = categories.iterator();
           while(categoriesIterator.hasNext()) {
               category = categoriesIterator.next().getAsString();
               categoriesList.add(category);
           }
           u.setCategories(categoriesList);
           this.users.add(u);
       }
    }
    
    public void parseProducts() {
        JsonObject jsonObject = parseTree.getAsJsonObject();
        JsonArray categories = jsonObject.get("categories").getAsJsonArray();
        Iterator<JsonElement> categoriesIterator = categories.iterator();
        this.products = new ArrayList<>();
        
        
        while(categoriesIterator.hasNext()) {
            JsonObject category = categoriesIterator.next().getAsJsonObject();
            String categoryName = category.get("name").getAsString().trim();
            JsonArray products = category.getAsJsonArray("products");
            Iterator<JsonElement> productsIterator = products.iterator();
            
            while(productsIterator.hasNext()){
                String productName = productsIterator.next().getAsString().trim(); 
                this.products.add(new Product(productName, categoryName));
            }
            
        }
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }   

    public static void main(String args[]) {
        Parser p = new Parser("data.json");
        System.out.println(p.getProducts());
        System.out.println(p.getUsers());
    }

}