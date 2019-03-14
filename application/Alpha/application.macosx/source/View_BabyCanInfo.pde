// View_BabyCanInfo.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_BabyCanInfo extends ViewController {


  public View_BabyCanInfo(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //
    
    fill(0);
    textFont(fonts.get("SF").get("Thin 20"));
    textAlign(CENTER);
    text("BabyCan information", this.dim.x/2, 50);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
