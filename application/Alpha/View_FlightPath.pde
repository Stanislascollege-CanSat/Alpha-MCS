// View_FlightPath.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_FlightPath extends ViewController {

	public PVector systemPos;
  public float progress;
  public PImage rocketImg;
  public float rocketWidth;

  public boolean rocketLanded;
  public float rocketLandingProgress;
  public float landingSpeed;

  public boolean babyCansDeployed;

  public CanSatModel motherCanModel;
  public CanSatModel betaCanModel;
  public CanSatModel rhoCanModel;

  // progress steps
  public float progress_deployRocket;
  public float progress_landRocket;
  public float progress_deployCanSat;
  public float progress_200;
  public float progress_700;
  public float progress_680;

	// text
	public String lowestBorder;
	public String deployBorder1;
	public String deployBorder2;


  public View_FlightPath(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

		this.systemPos = new PVector(0, 0);
    this.progress = 0;

    this.rocketImg = loadImage("rocket.png");
    this.rocketWidth = 60;

    this.rocketLanded = false;
    this.rocketLandingProgress = 0;
    this.landingSpeed = 0.5;

    this.babyCansDeployed = false;

    this.motherCanModel = new CanSatModel(0, 0, 0, 0.3);
    this.betaCanModel = new CanSatModel(-50, 0, 0, 0.1);
    this.rhoCanModel = new CanSatModel(50, 0, 0, 0.1);

    this.progress_deployRocket = 50;
    this.progress_landRocket = 75;
    this.progress_deployCanSat = 50;
    this.progress_200 = 5;
    this.progress_700 = 20;
    this.progress_680 = 84;

		this.lowestBorder = "2m";
		this.deployBorder1 = "5m";
		this.deployBorder2 = "4.5m";
  }

	private float calcSysX(float prog){
    return this.dim.x/2 - (this.dim.x/4 + this.dim.x/10)*cos(prog/100*PI);
  }

  private float calcSysY(float prog){
    return this.dim.y - ((1.8*this.dim.y)/2 + 0)*sin(prog/100*PI);
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
    text("Flight Path", this.dim.x/2, this.dim.y - 280);

		// fill(0);
    // textAlign(LEFT);
    // text(this.progress, 10, 20);

    stroke(56, 132, 255);
    strokeWeight(3);
    noFill();
    ellipse(this.dim.x/2, this.dim.y*1.1, this.dim.x/2, this.dim.y*1.8);
		stroke(0);
    strokeWeight(1);
    line(100, this.dim.y*0.4, this.dim.x*0.4, this.dim.y*0.4);
    line(this.dim.x - 100, this.dim.y*0.5, this.dim.x*0.6, this.dim.y*0.5);
    line(100, this.dim.y*0.8, this.dim.x - 100, this.dim.y*0.8);

		fill(0);
		textFont(fonts.get("SF").get("Regular"));
		textAlign(LEFT);
		text(this.deployBorder1, 100, this.dim.y*0.4 - 2);
		text(this.lowestBorder, 100, this.dim.y*0.8 - 2);
		textAlign(RIGHT);
		text(this.deployBorder2, this.dim.x - 100, this.dim.y*0.5 - 2);

    //this.progress = map(mouseX, 0, width, 0, 100);
    if(mousePressed){
     if(this.progress >= 100){
       this.progress = 100;
     }else{
       this.progress += 0.2;
     }
    }else{
     this.progress = 0;
    }

    // if(mousePressed){
    //   this.babyCansDeployed = true;
    // }else{
    //   this.babyCansDeployed = false;
    // }

    if(this.progress < this.progress_deployRocket && this.rocketLanded){
      this.progress = this.progress_deployRocket;
    }

    this.systemPos.x = this.calcSysX(this.progress);
    this.systemPos.y = this.calcSysY(this.progress);


    if(this.progress < this.progress_deployRocket){
      if(!this.rocketLanded){
        translate(this.systemPos.x, this.systemPos.y - this.rocketWidth);
        rotateZ(sin(this.progress/100*PI));
        imageMode(CENTER);
        image(this.rocketImg, 0, 0, this.rocketWidth, this.rocketWidth*2);
        rotateZ(-sin(this.progress/100*PI));
        translate(-this.systemPos.x, -this.systemPos.y + this.rocketWidth);
      }
    }else if(this.progress <= 100){
      if(!this.rocketLanded){
        this.rocketLanded = true;
        this.rocketLandingProgress = this.progress;
      }
      translate(this.systemPos.x, this.systemPos.y);
      this.motherCanModel.show();
      if(this.babyCansDeployed){
        this.betaCanModel.show();
        this.rhoCanModel.show();
      }
      translate(-this.systemPos.x, -this.systemPos.y);
    }

    if(this.rocketLanded){
      translate(this.calcSysX(this.progress_deployRocket) + map(this.rocketLandingProgress, this.progress_deployRocket, this.progress_landRocket, 0, 100), this.calcSysY(this.progress_deployRocket) + map(this.rocketLandingProgress, this.progress_deployRocket, this.progress_landRocket, 0, -this.calcSysY(this.progress_deployRocket)+this.dim.y) - this.rocketWidth);
      rotateZ(sin(this.rocketLandingProgress/this.progress_landRocket*PI));
      imageMode(CENTER);
      image(this.rocketImg, 0, 0, this.rocketWidth, this.rocketWidth*2);
      rotateZ(-sin(this.rocketLandingProgress/this.progress_landRocket*PI));
      translate(-(this.calcSysX(this.progress_deployRocket) + map(this.rocketLandingProgress, this.progress_deployRocket, this.progress_landRocket, 0, 100)), -(this.calcSysY(this.progress_deployRocket) + map(this.rocketLandingProgress, this.progress_deployRocket, this.progress_landRocket, 0, -this.calcSysY(this.progress_deployRocket)+this.dim.y) - this.rocketWidth));

      if(this.rocketLandingProgress < this.progress_landRocket){
        this.rocketLandingProgress += this.landingSpeed;
        if(this.rocketLandingProgress >= (this.progress_deployRocket + this.progress_landRocket)/2){
          this.landingSpeed -= this.landingSpeed/30;
        }
      }else{
        this.rocketLandingProgress = this.progress_landRocket;
      }
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
