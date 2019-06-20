// View_UniversalText.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_Overview extends ViewController {

	public ButtonElement DSYButton;



  public View_Overview(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.DSYButton = new ButtonElement(this.appController, this, this.dim.x/2 - 125, this.dim.y/2, 250, "Send Data Synchronization Point"){
		public void clickEvent(){
			SerialController.send("[DSY]");
		}
	};

	this.elements.add(this.DSYButton);
  }

  public void viewResizeTriggered(){
    this.DSYButton.resize(this.dim.x/2 - 125, this.dim.y/2, 250);
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
