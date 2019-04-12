class CanSatModel{
  PVector pos;
  PVector basepos;
  PVector rotation;
  float base_diameter;
  float base_height;
  Cylinder base;
  float parachute_width;
  int parachute_amntofsegs;
  float parachute_centerdepth;
  float parachute_outerdepth;
  float parachute_stringlength;
  float sizeFactor;
  Parachute parachute;
  
  CanSatModel(float x, float y, float z, float factor){
    pos = new PVector(x,y,z);
    rotation = new PVector(0,0,0);
    base_diameter = 80*factor;
    base_height = 150*factor;
    basepos = new PVector(0,base_height/2,0);
    base = new Cylinder(0,0,0,base_diameter,base_height,color(255,0,0),color(255,0,0));
    parachute_width = 300*factor;
    parachute_amntofsegs = 9;
    parachute_centerdepth = 120*factor;
    parachute_outerdepth = 60*factor;
    parachute_stringlength = 200*factor;
    parachute = new Parachute(0,0,0,parachute_width,/*height*/0,parachute_centerdepth,parachute_outerdepth,parachute_stringlength,parachute_amntofsegs,color(0),color(244, 161, 66));
    sizeFactor = factor;
}
  
  void setRotation(float x, float y, float z){
    rotation.set(x,y,z);
  }
  
  void show(){
    pushMatrix();

    translate(pos.x,pos.y,pos.z);
    //stroke(0);
    //strokeWeight(1);
    //noFill();
    //box(450*sizeFactor);
    //fill(0);
    //textSize(12);
    //textAlign(CENTER);
    //text("CanSat 3D-model",0,-200*sizeFactor-20,200*sizeFactor);
    rotateX(rotation.x);
    rotateY(rotation.y);
    rotateZ(rotation.z);
    translate(basepos.x,basepos.y,basepos.z);
    base.show();
    translate(-basepos.x,-basepos.y,-basepos.z);
    parachute.show();
    
    //stroke(0,255,0);
    //strokeWeight(3);
    //line(0,base_height/2,0,0,base_height/2,100);
    //rotateY(-PI/10);
    //stroke(0,0,255);
    //line(0,base_height/2,0,0,base_height/2,100);
    
    popMatrix();
  }
}
