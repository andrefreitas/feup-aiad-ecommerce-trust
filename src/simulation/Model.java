package simulation;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.gui.SimGraphics;
import java.awt.*;
import uchicago.src.sim.space.Object2DTorus;
import uchicago.src.sim.util.SimUtilities;
import uchicago.src.sim.network.DefaultDrawableEdge;
import uchicago.src.sim.network.DefaultNode;
import java.awt.Color;
import java.util.ArrayList;

public class Model extends SimModelImpl {
	private ArrayList<Agent> agentList;
	private Schedule schedule;
	private DisplaySurface dsurf;
	private Object2DTorus space;
	private DefaultDrawableEdge network;
	private DefaultNode node1;
	private DefaultNode node2;
	private SimGraphics graphic;
	private int numberOfAgents, spaceSize;
	
	public Model() {
		this.numberOfAgents = 100;
		this.spaceSize = 100;
	}

	public String getName() {
		return "Ecommerce Trust Simulation";
	}

	public String[] getInitParam() {
		return new String[] { "numberOfAgents", "spaceSize", "movingMode"};
	}

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

	public void setup() {
		schedule = new Schedule();
		if (dsurf != null) dsurf.dispose();
		dsurf = new DisplaySurface(this, "Ecommerce Trust Simulation");
		registerDisplaySurface("Ecommerce Trust Simulation", dsurf);
	}

	public void begin() {
		buildModel();
		buildDisplay();
		buildSchedule();
	}

	public void buildModel() {
		agentList = new ArrayList<Agent>();
		space = new Object2DTorus(spaceSize, spaceSize);
		node1 = new DefaultNode();
		node2 = new DefaultNode();
		network = new DefaultDrawableEdge(node1, node2);
		graphic = new SimGraphics();
		
		Agent agent = new Agent(0,0,Color.red,space);
		space.putObjectAt(0, 0, agent);
		agentList.add(agent);
	}

	private void buildDisplay() {
		// space and display surface
		Object2DDisplay display = new Object2DDisplay(space);
		//graphic.drawCircle(Color.RED);
		//network.draw(graphic, 20, 40, 30, 50);
		graphic.setDisplaySurface(dsurf);
		
		display.setObjectList(agentList);
		dsurf.addDisplayableProbeable(display, "Agents Space");
		dsurf.display();
	}

	private void buildSchedule() {
		schedule.scheduleActionBeginning(0, new MainAction());
		schedule.scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);

	}


	class MainAction extends BasicAction {

		public void execute(){
			// shuffle agents
			SimUtilities.shuffle(agentList);
		}

	}


	public static void main(String[] args) {
		SimInit init = new SimInit();
		init.loadModel(new Model(), null, false);
	}

}
