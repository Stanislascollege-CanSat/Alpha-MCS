// View_UniversalText.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_Overview extends ViewController {

	public ButtonElement DSYButton;
	public ButtonElement DemoDeployButton;



  public View_Overview(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.DSYButton = new ButtonElement(this.appController, this, this.dim.x/2 - 125, this.dim.y/2, 250, "Send Data Synchronization Point"){
			public void clickEvent(){
				SerialController.send("[DSY]");
			}
		};

		this.DemoDeployButton = new ButtonElement(this.appController, this, this.dim.x/2 - 75, this.dim.y/2 + 100, 150, "Deploy BabyCans"){
			public void clickEvent(){
				for(int i = 0; i < 25; i++){
					SerialController.send("[DEP]");
				}
				this.appController.notifyBabyCansDeployed();
			}
		};

		this.elements.add(this.DSYButton);
    this.elements.add(this.DemoDeployButton);
  }

  public void viewResizeTriggered(){
    this.DSYButton.resize(this.dim.x/2 - 125, this.dim.y/2, 250);
		this.DemoDeployButton.resize(this.dim.x/2 - 75, this.dim.y/2 + 100, 150);
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
