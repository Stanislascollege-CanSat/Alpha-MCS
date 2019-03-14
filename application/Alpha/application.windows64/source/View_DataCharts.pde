//import javafx.scene.chart.Chart;

// View_DataCharts.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_DataCharts extends ViewController {
	public VerticalScrollElement scrollBar;
	public ArrayList<Chart> charts;


  public View_DataCharts(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);
    
    this.scrollBar = new VerticalScrollElement(this.appController, this, this.dim.x - 10, this.dim.y/2, this.dim.y, 0, this.dim.y);
    
    this.charts = new ArrayList<Chart>();
    
    this.charts.add(new Chart(100, 200, this.dim.x - 210, 300, new ChartRange(0, 500), new ChartRange(-20, 20), "Acceleration", "Time", "s", "Acceleration", "m/s/s"));
    //DataSetDeposit.acceleration.addDataPoint(new DataPoint(0,0));
    this.charts.get(0).addDataSet(DataSetDeposit.mu_accX);
    this.charts.get(0).addDataSet(DataSetDeposit.mu_accY);
    this.charts.get(0).addDataSet(DataSetDeposit.mu_accZ);
    this.charts.add(new Chart(100, 200, this.dim.x - 210, 300, new ChartRange(0, 500), new ChartRange(-20, 20), "Gyroscope", "Time", "s", "Speed", "deg/s"));
    this.charts.get(1).addDataSet(DataSetDeposit.mu_gyroX);
    this.charts.get(1).addDataSet(DataSetDeposit.mu_gyroY);
    this.charts.get(1).addDataSet(DataSetDeposit.mu_gyroZ);
    this.charts.add(new Chart(100, 200, this.dim.x - 210, 300, new ChartRange(0, 500), new ChartRange(25865, 25885), "Height", "Time", "s", "Height", "m"));
    this.charts.get(2).addDataSet(DataSetDeposit.mu_altitude);
    
    this.viewResizeTriggered();
    
    this.elements.add(this.scrollBar);
  }
  
  public void viewResizeTriggered(){
	  this.scrollBar.resize(this.dim.x - 10, this.dim.y/2, this.dim.y);
	  
	  
	  float y = 200;
	  for(Chart c : this.charts) {
		  c.resize(100, y, this.dim.x - 210, c.dim.y);
		  y += c.dim.y + 100;
	  }
	  y -= 200;
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
    
    this.scrollBar.show();
    
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
