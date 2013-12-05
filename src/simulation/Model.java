package simulation;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import uchicago.src.repastdemos.neuralfromfile.AgentSelectorDialog;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.gui.SimGraphics;
import uchicago.src.sim.network.DefaultDrawableEdge;
import uchicago.src.sim.network.DefaultNode;
import uchicago.src.sim.space.Object2DTorus;

import data.*;
import ecommerce.*;

public class Model extends SimModelImpl {

    private ArrayList<Agent> agentList;
    private ArrayList<Trust> trustList;
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
        numberOfAgents = 2;
        spaceSize = 100;
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
        buildDisplay();
        buildSchedule();
    }

    public void buildModel() {
        agentList = new ArrayList<>();
        trustList = new ArrayList<>();
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
        int deltaY = 20;
        int posX = 0;
        int posY = 0;
        int labelX = -1;
        int labelY = 8;
        Random rand = new Random();
        Agent agent;
        Trust trust;
        int counter = 0;
        
        // Create agents
        for(User user: users) {
            // Update positions
            posX = baseX + (counter % 5) * deltaX;
            posY = baseY + (counter / 5) * deltaY;
            
            // Add agent
            agent = new Agent(posX, posY, Color.red, space, user.getName(), user.getCountry());
            agentList.add(agent);
            
            // Set trust label
            trust = new Trust(posX + labelX, posY + labelY, 0);
            agent.setTrust(trust);
            trustList.add(trust);
            
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
        
        // Trusts
        Object2DDisplay display2 = new Object2DDisplay(space);
        display2.setObjectList(trustList);
        dsurf.addDisplayableProbeable(display2, "Trust Space");
        
        dsurf.setSize(200, 200);
        dsurf.display();

        // graph
        if (plot != null) {
            plot.dispose();
        }
        plot = new OpenSequenceGraph("Colors and Agents", this);
        plot.setAxisTitles("time", "n");
        // plot number of different existing colors
        plot.addSequence("Agent List size", new Sequence() {
            @Override
            public double getSValue() {
                return agentList.size();
            }
        });
        for (int i = 0; i < agentList.size(); i++) {
            final int j = i;
            // plot number of agents with the most abundant color
            plot.addSequence("Global Trust", new Sequence() {
                @Override
                public double getSValue() {
                	return agentList.get(j).getGlobalTrust();             
                }
            });
            plot.display();
        }
        
    }
    

    private void buildSchedule() {
        schedule.scheduleActionBeginning(0, new MainAction());
        schedule.scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);
        schedule.scheduleActionAtInterval(1, plot, "step", Schedule.LAST);

    }

    class MainAction extends BasicAction {
        public void execute() {

        }
    }

    public static void main(String[] args) {
        SimInit init = new SimInit();
        init.loadModel(new Model(), null, false);
    }

}
