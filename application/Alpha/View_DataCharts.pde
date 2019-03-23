//import javafx.scene.chart.Chart;

// View_DataCharts.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_DataCharts extends ViewController {
	public VerticalScrollElement scrollBar;
	public ArrayList<Chart> charts;

  public ButtonElement selectMuDataButton;
  public ButtonElement selectBetaDataButton;
  public ButtonElement selectRhoDataButton;
  public float selectButtonWidth;

  public Chart chart_acceleration;
  public Chart chart_velocity;


  public View_DataCharts(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);
    
    this.scrollBar = new VerticalScrollElement(this.appController, this, this.dim.x - 10, this.dim.y/2, this.dim.y, 0, this.dim.y);
    
    this.charts = new ArrayList<Chart>();

    this.selectButtonWidth = 200;
    this.selectBetaDataButton = new ButtonElement(this.appController, this, this.dim.x/2 - this.selectButtonWidth * 1.5 - 5, 20, this.selectButtonWidth, "Bèta data");
    this.selectMuDataButton = new ButtonElement(this.appController, this, this.dim.x/2 - this.selectButtonWidth/2, 20, this.selectButtonWidth, "Mu data");
    this.selectRhoDataButton = new ButtonElement(this.appController, this, this.dim.x/2 + this.selectButtonWidth/2 + 5, 20, this.selectButtonWidth, "Rho data");
    
    this.chart_acceleration = new Chart(0, 0, 0, 500, new ChartRange(0, 100), new ChartRange(-15, 15), "Acceleration", "Time", "s", "Acceleration", "m/s/s");
    this.chart_velocity = new Chart(0, 0, 0, 500, new ChartRange(0, 100), new ChartRange(-50, 50), "Velocity", "Time", "s", "Velocity", "m/s");

    this.charts.add(chart_acceleration);
    this.charts.add(chart_velocity);
    
    this.viewResizeTriggered();
    
    this.elements.add(this.scrollBar);
    this.elements.add(this.selectMuDataButton);
    this.elements.add(this.selectBetaDataButton);
    this.elements.add(this.selectRhoDataButton);
  }
  
  public void viewResizeTriggered(){
	  this.scrollBar.resize(this.dim.x - 10, this.dim.y/2, this.dim.y);
	  
	  
	  float y = 100;
	  for(Chart c : this.charts) {
		  c.resize(100, y + c.dim.y/2, this.dim.x - 210, c.dim.y);
		  y += c.dim.y + 100;
	  }
	  y -= 50;
	  if(y > this.dim.y) {
		  this.scrollBar.setExtremes(0, y);
		  this.scrollBar.setCurrentPosition(0, this.dim.y);
	  }else {
		  this.scrollBar.setExtremes(0, this.dim.y);
		  this.scrollBar.setCurrentPosition(0, this.dim.y);
	  }
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    //this.scrollBar.show();
    
    translate(0, -this.scrollBar.getMinimumValue(), -1);

    for(Chart c : this.charts) {
    	c.show();
    }
    
    translate(0, this.scrollBar.getMinimumValue(), 1);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }
  
  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
  }

}
