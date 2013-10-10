package simulation;
import uchicago.src.sim.engine.BasicAction;
import uchicago.src.sim.engine.Schedule;
import uchicago.src.sim.engine.SimModelImpl;
import uchicago.src.sim.engine.SimInit;
import uchicago.src.sim.gui.DisplaySurface;
import uchicago.src.sim.gui.Object2DDisplay;
import uchicago.src.sim.space.Object2DTorus;
import uchicago.src.sim.util.SimUtilities;
import uchicago.src.sim.analysis.OpenSequenceGraph;
import uchicago.src.sim.analysis.Sequence;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Model extends SimModelImpl {
	private ArrayList<Agent> agentList;
	private Schedule schedule;
	private DisplaySurface dsurf;
	private Object2DTorus space;
	private OpenSequenceGraph plot;

	private int numberOfAgents, spaceSize;


	private Hashtable<Color, Integer> agentColors;
	
	public Model() {
		this.numberOfAgents = 100;
		this.spaceSize = 100;
		
	}

	public String getName() {
		return "Color Picking Model";
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
		dsurf = new DisplaySurface(this, "Color Picking Display");
		registerDisplaySurface("Color Picking Display", dsurf);

	
	}

	public void begin() {
		buildModel();
		buildDisplay();
		buildSchedule();
	}

	public void buildModel() {
		agentList = new ArrayList<Agent>();
		space = new Object2DTorus(spaceSize, spaceSize);
		Color color = new Color(255,0,0);
		Agent agent = new Agent(0,0,color,space);
		space.putObjectAt(0, 0, agent);
		agentList.add(agent);
		
	}

	private void buildDisplay() {
		// space and display surface
		Object2DDisplay display = new Object2DDisplay(space);
		display.setObjectList(agentList);
		dsurf.addDisplayableProbeable(display, "Agents Space");
		dsurf.display();

		// graph
		if (plot != null) plot.dispose();
		plot = new OpenSequenceGraph("Colors and Agents", this);
		plot.setAxisTitles("time", "n");
		// plot number of different existing colors
		plot.addSequence("Number of colors", new Sequence() {
			public double getSValue() {
				return agentColors.size();
			}
		});
		// plot number of agents with the most abundant color
		plot.addSequence("Top color", new Sequence() {
			public double getSValue() {
				int n = 0;
				Enumeration<Integer> agentsPerColor = agentColors.elements();
				while(agentsPerColor.hasMoreElements()) {
					int c = agentsPerColor.nextElement();
					if(c>n) n=c;
				}
				return n;
			}
		});
		plot.display();
	}

	private void buildSchedule() {
		schedule.scheduleActionBeginning(0, new MainAction());
		schedule.scheduleActionAtInterval(1, dsurf, "updateDisplay", Schedule.LAST);
		schedule.scheduleActionAtInterval(1, plot, "step", Schedule.LAST);
	}


	class MainAction extends BasicAction {

		public void execute() {
			// prepare agent colors hashtable
			agentColors = new Hashtable<Color,Integer>();

			// shuffle agents
			SimUtilities.shuffle(agentList);

			
		}

	}


	public static void main(String[] args) {
		SimInit init = new SimInit();
		init.loadModel(new Model(), null, false);
	}

}
