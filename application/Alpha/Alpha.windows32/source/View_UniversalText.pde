// View_UniversalText.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class View_UniversalText extends ViewController {
    public String title;
    public String subtitle;


  public View_UniversalText(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.title = "";
    this.subtitle = "";
  }

  public void setMessage(String t, String s){
    this.title = t;
    this.subtitle = s;
  }

  public void show(){

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    textFont(fonts.get("SF").get("Heavy 20"));
    textAlign(CENTER);
    fill(0);
    text(this.title, this.dim.x/2, 250);
    stroke(0);
    strokeWeight(2);
    line(this.dim.x/2 - textWidth(this.title)/2, 270, this.dim.x/2 + textWidth(this.title)/2, 270);
    textFont(fonts.get("Lora").get("Regular"));
    text(this.subtitle, this.dim.x/2, 300);


    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);

  }

}
