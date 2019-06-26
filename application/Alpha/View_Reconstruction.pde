// View_UniversalText.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public float sqr(float a){
  return a*a;
}

public class View_Reconstruction extends ViewController {

	public CanSatModel motherCanModel;
	public CanSatModel betaCanModel;
	public CanSatModel rhoCanModel;



  public View_Reconstruction(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

		this.motherCanModel = new CanSatModel(0, 0, 0, 0.4);
		this.betaCanModel = new CanSatModel(0, 0, 0, 0.15);
		this.rhoCanModel = new CanSatModel(0, 0, 0, 0.15);
  }

  public void viewResizeTriggered(){

  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

		pushMatrix();

    translate(this.dim.x/2, this.dim.y/2);
    //rotateX(-PI/6);

    // drawing air
    noStroke();
    fill(66, 134, 244, 100);
    rectMode(CENTER);
    translate(0, 0, -1000);
    rect(0, 0, this.dim.x*5, this.dim.y*100);
    translate(0, 0, 1000);

		// drawing Ground
		noStroke();
		fill(0, 147, 19, 180);
		rectMode(CENTER);
    translate(0, (float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY()*25 + 150.0*0.4);
		rotateX(-PI/2);
		rect(0, 0, this.dim.x*2, this.dim.y*2);
    fill(0, 100);
    translate(0, 0, -5);
    ellipse(0, 0, 100, 100);
    translate(0, 0, 5);
		rotateX(PI/2);
    translate(0, -(float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY()*25 - 150.0*0.4);


						// drawing MotherCan
						double xGaussData = DataSetDeposit.mu_compassX.getLatestDataPoint().getY() * 0.48828125;
						double yGaussData = DataSetDeposit.mu_compassY.getLatestDataPoint().getY() * 0.48828125;
						double zGaussData = DataSetDeposit.mu_compassZ.getLatestDataPoint().getY() * 0.48828125;
						rotateY(-atan(((float)yGaussData)/((float)xGaussData)));
						this.motherCanModel.show();
						rotateY(atan(((float)yGaussData)/((float)xGaussData)));

						translate(((float)DataSetDeposit.mu_GPSLat.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_GPSLat.getLatestDataPoint().getY()) * 10000, ((float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_altitude.getLatestDataPoint().getY())*25, ((float)DataSetDeposit.mu_GPSLon.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_GPSLon.getLatestDataPoint().getY())*10000);

						xGaussData = DataSetDeposit.beta_compassX.getLatestDataPoint().getY() * 0.48828125;
						yGaussData = DataSetDeposit.beta_compassY.getLatestDataPoint().getY() * 0.48828125;
						zGaussData = DataSetDeposit.beta_compassZ.getLatestDataPoint().getY() * 0.48828125;
						rotateY(-atan(((float)yGaussData)/((float)xGaussData)));
						this.betaCanModel.show();
						rotateY(atan(((float)yGaussData)/((float)xGaussData)));

				    translate(-((float)DataSetDeposit.mu_GPSLat.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_GPSLat.getLatestDataPoint().getY()) * 10000, -((float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_altitude.getLatestDataPoint().getY())*25, -((float)DataSetDeposit.mu_GPSLon.getLatestDataPoint().getY() - (float)DataSetDeposit.beta_GPSLon.getLatestDataPoint().getY())*10000);

				    translate(((float)DataSetDeposit.mu_GPSLat.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_GPSLat.getLatestDataPoint().getY()) * 10000, ((float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_altitude.getLatestDataPoint().getY())*25, ((float)DataSetDeposit.mu_GPSLon.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_GPSLon.getLatestDataPoint().getY())*10000);


						// rotateY(atan(((float)DataSetDeposit.beta_compassY.getLatestDataPoint().getY())/((float)DataSetDeposit.beta_compassX.getLatestDataPoint().getY())));
						// rotateX(atan(((float)DataSetDeposit.beta_compassZ.getLatestDataPoint().getY())/(sqrt(sqr((float)DataSetDeposit.beta_compassY.getLatestDataPoint().getY()) + sqr((float)DataSetDeposit.beta_compassX.getLatestDataPoint().getY())))));
						// this.betaCanModel.show();
						// rotateY(-atan(((float)DataSetDeposit.beta_compassY.getLatestDataPoint().getY())/((float)DataSetDeposit.beta_compassX.getLatestDataPoint().getY())));
						// rotateX(-atan(((float)DataSetDeposit.beta_compassZ.getLatestDataPoint().getY())/(sqrt(sqr((float)DataSetDeposit.beta_compassY.getLatestDataPoint().getY()) + sqr((float)DataSetDeposit.beta_compassX.getLatestDataPoint().getY())))));

						// PVector comp = new PVector((float)DataSetDeposit.beta_compassY.getLatestDataPoint().getY(), (float)DataSetDeposit.beta_compassZ.getLatestDataPoint().getY(), (float)DataSetDeposit.beta_compassX.getLatestDataPoint().getY());
						// rotate(comp.heading());
						// this.betaCanModel.show();
						// rotate(-comp.heading());

						xGaussData = DataSetDeposit.rho_compassX.getLatestDataPoint().getY() * 0.48828125;
						yGaussData = DataSetDeposit.rho_compassY.getLatestDataPoint().getY() * 0.48828125;
						zGaussData = DataSetDeposit.rho_compassZ.getLatestDataPoint().getY() * 0.48828125;
						rotateY(-atan(((float)yGaussData)/((float)xGaussData)));
						this.rhoCanModel.show();
						rotateY(atan(((float)yGaussData)/((float)xGaussData)));


				    translate(-((float)DataSetDeposit.mu_GPSLat.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_GPSLat.getLatestDataPoint().getY()) * 10000, -((float)DataSetDeposit.mu_altitude.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_altitude.getLatestDataPoint().getY())*25, -((float)DataSetDeposit.mu_GPSLon.getLatestDataPoint().getY() - (float)DataSetDeposit.rho_GPSLon.getLatestDataPoint().getY())*10000);


    popMatrix();


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
