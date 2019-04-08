public class SpaceSegment {
  public PVector begin;
  public PVector pointer;
  public PVector temp;
  public float length;
  
  public SpaceSegment(PVector a, PVector b){
    this.begin = a;
    this.pointer = b;
    this.temp = new PVector(0, 0);
    this.length = this.pointer.mag();
  }
  
  public void show(){
    line(this.begin.x, this.begin.y, this.begin.x + this.pointer.x, this.begin.y + this.pointer.y);
  }
  
  public void follow(float x, float y){
    this.temp.set(x - this.begin.x, y - this.begin.y);
    this.pointer.rotate(this.temp.heading() - this.pointer.heading());
    this.begin.x = x - this.pointer.x;
    this.begin.y = y - this.pointer.y;
  }
}

public class SpaceImageSegment extends SpaceSegment {
  Parachute p;
  
  public SpaceImageSegment(PVector a){
    super(a, new PVector(2, 2));
    this.p = new Parachute(0, 0, 0, 100, 100, 50, 30, 60, 5, color(0), color(244, 161, 66));
  }
  
  public void show(){
    translate(this.begin.x, this.begin.y, 0);
    rotate(this.pointer.heading());
    
    stroke(0);
    strokeWeight(1);
    fill(255, 0, 0, 150);
    rectMode(CENTER);
    //rect(-15, 0, 30, 15);
    
    //textAlign(RIGHT);
    //textFont(fonts.get("SF").get("Heavy"));
    //text("Project Bèta", 0, 4);
    
    rotate(-PI/2);
    
    this.p.show();
    
    rotate(PI/2);
    
    rotate(-this.pointer.heading());
    translate(-this.begin.x, -this.begin.y);
  }
}

public class SpaceAnimation {
  public ArrayList<SpaceSegment> points;
  
  public SpaceAnimation(){
    this.points = new ArrayList<SpaceSegment>();
    this.points.add(new SpaceSegment(new PVector(0,0), new PVector(3,3)));
    for(int i = 0; i < 50; ++i){
      this.points.add(new SpaceSegment(new PVector(-i * 10, -i * 10), new PVector(3, 3)));
    }
    this.points.add(new SpaceImageSegment(new PVector(0, 0)));
  }
  
  public void show(){
    if(this.points.size() > 0){
      this.points.get(0).follow(mouseX, mouseY);
    }
    for(int i = 1; i < this.points.size(); ++i){
      this.points.get(i).follow(this.points.get(i-1).begin.x, this.points.get(i-1).begin.y);
    }
    
    stroke(0, 50);
    strokeWeight(2);
    for(SpaceSegment s : this.points){
      s.show();
    }
  }
}
