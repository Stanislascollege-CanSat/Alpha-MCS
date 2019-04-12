class Cylinder{
  PVector pos;
  float Diameter;
  float Radius;
  float Height;
  color primaryColor;
  color secondaryColor;
  
  Cylinder(float x, float y, float z, float d, float h, color p, color s){
    pos = new PVector(x,y,z);
    Diameter = d;
    Radius = Diameter / 2;
    Height = h;
    primaryColor = p;
    secondaryColor = s;
  }
  
  void show(){
    pushMatrix();
    
    translate(pos.x,pos.y,pos.z);
    //rotateX(map(mouseY,0,height,0,-2*PI));
    //rotateY(map(mouseX,0,width,0,2*PI));
    noStroke();
    fill(primaryColor);
    
    beginShape(TRIANGLE_STRIP);
    
    for(float angle = 0; angle <= 2*PI; angle += PI/int(Diameter*10)){
      vertex(Radius * cos(angle),Height/2,Radius * sin(angle));
      vertex(Radius * cos(angle),-Height/2,Radius * sin(angle));
    }
    
    endShape();
    
    translate(0,Height/2,0);
    rotateX(0.5*PI);
    stroke(0);
    strokeWeight(1);
    fill(secondaryColor);
    ellipse(0,0,Diameter,Diameter);
    rotateX(-0.5*PI);
    translate(0,-Height,0);
    rotateX(0.5*PI);
    ellipse(0,0,Diameter,Diameter);
    
    popMatrix();
  }
}