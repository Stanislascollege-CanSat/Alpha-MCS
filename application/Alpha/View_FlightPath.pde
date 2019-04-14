// View_FlightPath.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_FlightPath extends ViewController {

	public PVector systemPos;
  public float progress;
  public float progressSpeed;
  public float progressGoal;

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
	public float lowestBorder;
	public float deployBorder1;
	public float deployBorder2;

  // flight simulation
  public boolean missionSucces;
  public float maxHeight;

  public ArrayList<Float> altitudeDerivList;

  // animation
  public float motherCanRotation;
  public float betaCanRotation;
  public float rhoCanRotation;
  public float canBounce;


  public int tempTime;


  public View_FlightPath(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

		this.systemPos = new PVector(0, 0);
    this.progress = 0;
    this.progressSpeed = 0;
    this.progressGoal = this.progress;

    this.rocketImg = loadImage("rocket.png");
    this.rocketWidth = 60;

    this.rocketLanded = false;
    this.rocketLandingProgress = 0;
    this.landingSpeed = 0.5;

    this.babyCansDeployed = false;

    this.motherCanModel = new CanSatModel(0, 0, 0, 0.3);
    this.betaCanModel = new CanSatModel(0, 0, 0, 0.1);
    this.rhoCanModel = new CanSatModel(0, 0, 0, 0.1);

    this.progress_deployRocket = 45;
    this.progress_landRocket = 70;
    this.progress_deployCanSat = 50;
    this.progress_200 = 5;
    this.progress_700 = 20;
    this.progress_680 = 84;

		this.lowestBorder = 2;
		this.deployBorder1 = 5;
		this.deployBorder2 = this.deployBorder1*0.9;

    this.missionSucces = false;
    this.maxHeight = this.deployBorder1/this.calcSysHeight(20)*this.calcSysHeight(50);

    this.altitudeDerivList = new ArrayList<Float>();

    this.motherCanRotation = 0;
    this.betaCanRotation = 0;
    this.rhoCanRotation = 0;
    this.canBounce = 0;

    this.tempTime = 0;
  }

	private float calcSysX(float prog){
    return this.dim.x/2 - (this.dim.x/4 + this.dim.x/10)*cos(prog/100*PI);
  }

  private float calcSysHeight(float prog){
    return ((1.8*this.dim.y)/2 + 0)*sin(prog/100*PI);
  }

  private float calcSysY(float prog){
    return this.dim.y - this.calcSysHeight(prog);
  }

  private float calcProgress(float height){
    if(height >= this.maxHeight){
      return 50;
    }else{
      float sh = map(height, 0, this.maxHeight, 0, this.calcSysHeight(50));
      float prog = (100*asin((2*sh)/(1.8*this.dim.y)))/PI;
      if(this.missionSucces){
        return 100 - prog;
      }else{
        return prog;
      }
    }
  }

  public void show(){
    this.tempTime += 1;

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
  //   textAlign(LEFT);
  //   text(this.progress, 10, 20);
  //   text(map(this.calcSysHeight(this.progress), 0, this.calcSysHeight(50), 0, this.maxHeight), 10, 70);

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
		text(str(this.deployBorder1) + "m", 100, this.dim.y*0.4 - 2);
		text(str(this.lowestBorder) + "m", 100, this.dim.y*0.8 - 2);
		textAlign(RIGHT);
		text(str(this.deployBorder2) + "m", this.dim.x - 100, this.dim.y*0.5 - 2);


    if(mousePressed && this.userInteractionEnabled && this.tempTime % 60 == 0){
      //this.progressGoal = this.calcProgress(map(height - mouseY, 0, this.calcSysHeight(50), 0, this.maxHeight));
      DataSetDeposit.mu_altitude.addDataPoint(new DataPoint(float(this.tempTime)/100, map(height - mouseY, 0, this.calcSysHeight(50), 0, this.maxHeight)));
    }
    if(keyPressed){
      if(key == 'd'){
        this.babyCansDeployed = true;
      }
    }

    if(DataSetDeposit.mu_altitude.size() > 0){
      this.progressGoal = this.calcProgress((float)DataSetDeposit.mu_altitude.getDataAt(DataSetDeposit.mu_altitude.size()-1).getY());
    }

    this.progressSpeed = (this.progressGoal - this.progress)/30;


    if(this.progressSpeed > 0){
      if(this.progress < this.progressGoal){
        this.progress += this.progressSpeed;
      }else{
        this.progress = this.progressGoal;
      }
    }else if(this.progressSpeed < 0){
      if(this.progress > this.progressGoal){
        this.progress += this.progressSpeed;
      }else{
        this.progress = this.progressGoal;
      }
    }



    if(this.progress < this.progress_deployRocket && this.rocketLanded){
      this.progress = this.progress_deployRocket;
    }

    this.systemPos.x = this.calcSysX(this.progress);
    this.systemPos.y = this.calcSysY(this.progress);

    if(DataSetDeposit.mu_altitude.size() >= 3 && !this.missionSucces){
      DataPoint[] points = new DataPoint[3];
      int j = 0;
      for(int i = DataSetDeposit.mu_altitude.size()-1; i >= DataSetDeposit.mu_altitude.size() - 3; --i){
        points[j] = DataSetDeposit.mu_altitude.getDataAt(i);
        ++j;
      }
      float avgDeriv = 0;
      for(int i = 0; i < points.length - 1; ++i){
        avgDeriv += (points[i+1].getY() - points[i].getY())/(points[i+1].getX() - points[i].getX());
      }
      avgDeriv /= points.length - 1;
      this.missionSucces = this.progress > 20;
      if(avgDeriv >= 0){
        this.missionSucces = false;
      }
    }


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
      rotateZ(map(sin(this.motherCanRotation), -1, 1, -PI/25, PI/25));
      this.motherCanModel.show();
      rotateZ(-map(sin(this.motherCanRotation), -1, 1, -PI/25, PI/25));
      this.motherCanRotation += 0.03;
      if(this.babyCansDeployed){
        translate(-75, map(sin(this.canBounce), -1, 1, -10, 10));
        rotateZ(map(sin(this.betaCanRotation), -1, 1, -PI/20, PI/20));
        this.betaCanModel.show();
        rotateZ(-map(sin(this.betaCanRotation), -1, 1, -PI/20, PI/20));
        translate(150, -map(sin(this.canBounce), -1, 1, -10, 10) + map(cos(this.canBounce), -1, 1, -10, 10));
        rotateZ(map(sin(this.rhoCanRotation), -1, 1, -PI/20, PI/20));
        this.rhoCanModel.show();
        rotateZ(-map(sin(this.rhoCanRotation), -1, 1, -PI/20, PI/20));
        translate(-75, -map(cos(this.canBounce), -1, 1, -10, 10));
        this.betaCanRotation += 0.04;
        this.rhoCanRotation += 0.05;
        this.canBounce += 0.02;
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
