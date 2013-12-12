package simulation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.network.DefaultNode;
import uchicago.src.sim.space.Object2DTorus;
import uchicago.src.sim.engine.SimpleModel;
import uchicago.src.sim.util.Random;

import static spark.Spark.*;
import spark.*;

import data.*;
import ecommerce.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Model extends SimpleModel {
    
    public static Model thisModel;

    private ArrayList<Agent> agentList;
    private ArrayList<Product> products;
    private Schedule schedule;
    private DisplaySurface dsurf;
    private Object2DTorus space;
    private DefaultNode node1;
    private DefaultNode node2;
    private SimGraphics graphic;
    private OpenSequenceGraph plot;
    private int numberOfAgents, spaceSize;

    public Model() {
    	this.numberOfAgents = 15; 
        this.spaceSize = 100;
        modelManipulator.init();
    }

    @Override
    public String getName() {
        return "Ecommerce Trust Simulation";
    }

    @Override
    public String[] getInitParam() {
        return new String[]{"numberOfAgents", "spaceSize"};
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }

    public int getNumberOfAgents() {
        return numberOfAgents;
    }

    public void setNumberOfAgents(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }

    public int getSpaceSize() {
        return spaceSize;
    }

    public void setSpaceSize(int spaceSize) {
        this.spaceSize = spaceSize;
    }

    @Override
    public void setup() {
        //super.setup();
        autoStep = true;
        shuffle = true;
        schedule = new Schedule();
        if (dsurf != null) {
            dsurf.dispose();
        }
        dsurf = new DisplaySurface(this, "Ecommerce Trust Simulation");
        registerDisplaySurface("Ecommerce Trust Simulation", dsurf);
        modelManipulator.addButton("Agente ", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(agentList.get(0).getGlobalTrust());
            }
        });
    }

    @Override
    public void begin() {
        buildModel();
        webServer();
        buildDisplay();
        buildSchedule();
    }

    public void buildModel() {
        agentList = new ArrayList<>();
        space = new Object2DTorus(spaceSize, spaceSize);
        node1 = new DefaultNode();
        node2 = new DefaultNode();
        graphic = new SimGraphics();
        Parser p = new Parser("data.json");
        products = p.getProducts();
        ArrayList<User> users = p.getUsers();
        // Positions variables
        int baseX = 8;
        int baseY = 25;
        int deltaX = 20;
        int deltaY = 10;
        int posX = 0;
        int posY = 0;
        int labelX = 2;
        int labelY = 8;
        Random rand = new Random();
        Agent agent;
        int counter = 0;

        // Create agents
        for (int i = 0; i<numberOfAgents; i++) {
        	User user = users.get(i%users.size());
        	
			// Update positions
			posX = baseX + (counter % 5) * deltaX;
			posY = baseY + (counter / 5) * deltaY;
			 
			Color color =  new Color(Random.uniform.nextIntFromTo(2,255), Random.uniform.nextIntFromTo(2,255), Random.uniform.nextIntFromTo(30,255));
			 
			// Add agent
			agent = new Agent(posX, posY, color, space, user.getName(), user.getCountry(), user.getBehaviour(), user.getCategories());
			space.putObjectAt(posX, posY, agent);
			agentList.add(agent);
			
			counter++;             
        }
       
    }

    private void buildDisplay() {
        // Background
        Object2DDisplay background = new Object2DDisplay(space);
        ArrayList<Background> bgs = new ArrayList<Background>();
        bgs.add(new Background());
        background.setObjectList(bgs);
        dsurf.addDisplayableProbeable(background, "Background");

        // Agents
        Object2DDisplay display = new Object2DDisplay(space);
        display.setObjectList(agentList);
        dsurf.addDisplayableProbeable(display, "Agents Space");

        dsurf.display();

    }

    @Override
    public void buildSchedule() {
        schedule.scheduleActionAtInterval(1, new SimulateFeedback());
        schedule.scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);
    }

    private Agent selectRandomAgent(Set<Agent> removeAgents) {
        Set set = new HashSet(agentList);
        set.removeAll(removeAgents);
        ArrayList<Agent> agentList = new ArrayList(set);
        int randomIndex = (int) (Math.random() * (agentList.size()));
        Agent randomAgent = agentList.get(randomIndex);
        return randomAgent;
    }

    private Product selectProduct(Agent buyer, Agent seller) {
        Set sellerCategories = new HashSet(seller.getCategories());
        ArrayList<Product> selectedProducts = new ArrayList();
        for (Product product : products) {
            if (sellerCategories.contains(product.getCategory())) {
                selectedProducts.add(product);
            }
        }
        int randomIndex = (int) (Math.random() * (selectedProducts.size()));
        Product randomProduct = selectedProducts.get(randomIndex);
        return randomProduct;

    }
    
    private Agent getAgentByName(String name){
         for(Agent agent: agentList){
             System.out.println(agent.getName() + " == " + name);
                if(agent.getName().equals(name))
                    return agent;
                
            }
         return null;
    }

    int generateScore(Agent buyer, Agent seller) {
        String behaviour = seller.getBehaviour();
        int score = 0;
        int max = 0;
        int min = 0;
        if (behaviour.equals("bad")) {
            min = 0;
            max = 3;

        } else if (behaviour.equals("normal")) {
            min = 3;
            max = 4;
        } else if (behaviour.equals("good")) {
            min = 4;
            max = 5;
        }
        score = min + (int) (Math.random() * ((max - min) + 1));
        return score;
    }
    

    class SimulateFeedback extends BasicAction {

        public void execute() {
            double ticks = getTickCount();
            double feedbacksNumber = 1 + (int) (Math.random() * ((4 - 1) + 1));
            for (int i = 0; i < feedbacksNumber; i++) {
                // Give feedback
                System.out.println("New Feedback -->");

                // get a seller
                Agent seller = selectRandomAgent(new HashSet<Agent>());
                System.out.print("Seller : " + seller.getName());

                // get a buyer
                HashSet<Agent> sellerSet = new HashSet<Agent>();
                sellerSet.add(seller);
                Agent buyer = selectRandomAgent(sellerSet);
                System.out.print(" Buyer: " + buyer.getName());

                // select a product from the seller categories
                Product product = selectProduct(buyer, seller);
                System.out.print(" Product: " + product.getName());

                // find a score randomly
                int score = generateScore(buyer, seller);
                System.out.println(" Score: " + score);

                // give feedbackƒ
                seller.addFeedback(product.getName(), product.getCategory(), score, (int) ticks, buyer);
                System.out.println("Feedbacks: " + seller.getFeedbacks().size());
            }
        }
    }

    public static void main(String[] args) {        
        SimInit init = new SimInit();
        thisModel = new Model();
        init.loadModel(thisModel, null, false);
    }
    
    public void webServer(){
      get(new Route("/getAgents") {
         @Override
         public Object handle(Request request, Response response) {
            response.header("Content-type", "text/json");
            Gson gson = new Gson();
            JsonArray agentListJson = new JsonArray();
            
            for(Agent agent: agentList){
                String name = agent.getName();
                String country = agent.getCountry();
                String behaviour = agent.getBehaviour();
                JsonObject agentJson = new JsonObject();
                agentJson.addProperty("name", name);
                agentJson.addProperty("country", country);
                agentJson.addProperty("behaviour", behaviour);
                agentListJson.add(agentJson);
  
            }
            return gson.toJson(agentListJson);
         }
      });
      
       get(new Route("/getAgentFeedbacks/:name") {
         @Override
         public Object handle(Request request, Response response) {
            //response.header("Content-type", "text/json");
            String agentName = request.params(":name");
            Agent agent = getAgentByName(agentName);
            
            if(agent == null) {
                return "{'result':'notfound'}";
            }
            Gson gson = new Gson();
            JsonArray feedbackListJson = new JsonArray();
            for(Feedback feedback: agent.getFeedbacks()){
                JsonObject feedbackJson = new JsonObject();
                String category = feedback.getProduct().getCategory();
                String product = feedback.getProduct().getName();
                int score = feedback.getScore();
                int ticks = feedback.getTimeTick();
                String buyer = feedback.getBuyer().getName();
                feedbackJson.addProperty("category", category);
                feedbackJson.addProperty("product", product);
                feedbackJson.addProperty("score", score);
                feedbackJson.addProperty("ticks", ticks);
                feedbackListJson.add(feedbackJson);
            }
            
            return gson.toJson(feedbackListJson);
                    
         }
      });
    }

}
