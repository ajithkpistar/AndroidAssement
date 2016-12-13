package assessment.android.istar.com.androidassessment.istarindia.complexobject.report;


import org.simpleframework.xml.ElementList;

import java.util.ArrayList;


public class XMLSkillGraph {

    @ElementList(name="graph_points", required = false)
	ArrayList<XMLGraphPoints> graphPoints;

	public XMLSkillGraph() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<XMLGraphPoints> getGraphPoints() {
		return graphPoints;
	}

	
	public void setGraphPoints(ArrayList<XMLGraphPoints> graphPoints) {
		this.graphPoints = graphPoints;
	}
	
	
	
}
