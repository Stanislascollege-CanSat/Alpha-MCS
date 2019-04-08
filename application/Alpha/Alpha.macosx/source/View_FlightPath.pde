// View_FlightPath.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_FlightPath extends ViewController {
	
	public PImage rocket_image;
	public PVector rocket_pos;
	public float rocket_time;
	public float rocket_angle;


  public View_FlightPath(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.rocket_image = loadImage("rocket.png");
    this.rocket_pos = new PVector(this.dim.x/2 - 425, this.dim.y - 50);
    this.rocket_time = 0;
    this.rocket_angle = 0;
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    fill(0);

    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("Flight Path", this.dim.x/2, this.dim.y - 200);
    
    stroke(56, 132, 255);
    strokeWeight(5);
    noFill();
    
    ellipse(this.dim.x/2, this.dim.y + 100, 800, 1200);
    
    
    
//    stroke(0);
//    strokeWeight(1);
//    noFill();
//    
//    beginShape();
//    for(float i = 0; i <= this.rocket_time; i += 0.0001) {
//    	vertex(this.dim.x/2 - 450*cos(i), this.dim.y - 550*sin(i));
//    }
//    endShape();
    
    this.rocket_pos.set(this.dim.x/2 - 450*cos(this.rocket_time), this.dim.y - 50 - 550*sin(this.rocket_time));
    this.rocket_angle = this.rocket_time;
    translate(this.rocket_pos.x, this.rocket_pos.y);
    rotateZ(this.rocket_angle);
    imageMode(CENTER);
    image(this.rocket_image, 0, 0, 50, 100);
    rotateZ(-this.rocket_angle);
    translate(-this.rocket_pos.x, -this.rocket_pos.y);
    
    this.rocket_time += 0.005;

    if(this.rocket_time > PI) {
    	this.rocket_time = PI;
    }else if(this.rocket_time < 0) {
    	this.rocket_time = 0;
    }


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
