class Parachute{
  PVector pos;
  float Width;
  float Height;
  float CenterDepth;
  float Depth;
  float depthStep;
  float segWidth;
  float StringLength;
  int nSegments;
  
  color primaryColor;
  color secondaryColor;
  
  float alpha;
  float beta;
  
  PVector frontLeft;
  PVector frontRight;
  PVector backRight;
  PVector backLeft;
  
  Parachute(float x, float y, float z, float w, float h, float cd, float od, float sl, int nseg, color p, color s){
    pos = new PVector(x,y,z);
    Width = w;
    Height = h;
    Depth = cd;
    CenterDepth = cd;
    StringLength = sl;
    if(nseg > 0){
      nSegments = nseg;
    }else{
      nSegments = 1;
    }
    segWidth = Width/nSegments;
    depthStep = (cd-od)/((nSegments-1)/2);
    
    alpha = sin((0.5*segWidth)/StringLength)*2;
    beta = sin((0.5*Depth)/StringLength)*2;
    
    primaryColor = p;
    secondaryColor = s;
    
    frontLeft = new PVector(0,0,0);
    frontRight = new PVector(0,0,0);
    backRight = new PVector(0,0,0);
    backLeft = new PVector(0,0,0);
  }
  
  void show(){
    pushMatrix();
    translate(pos.x,pos.y,pos.z);
    //rotateX(map(mouseY,0,height,0,-2*PI));
    //rotateY(map(mouseX,0,width,0,2*PI));
    stroke(0);
    strokeWeight(1);
    fill(primaryColor);
    
    Depth = CenterDepth;
    beta = sin((0.5*Depth)/StringLength)*2;
    
    // 1
    line(0,0,0,-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    line(0,0,0,StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    line(0,0,0,StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    line(0,0,0,-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    beginShape();
    vertex(-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    vertex(StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    vertex(StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    vertex(-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    endShape(CLOSE);
    
    frontLeft.set(-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    frontRight.set(StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),StringLength*(sin(0.5*beta)));
    backRight.set(StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    backLeft.set(-StringLength*sin(0.5*alpha),-StringLength*cos(0.5*alpha),-StringLength*(sin(0.5*beta)));
    
    String colorSet = "primary";
    
    for(int i = 1; i <= (nSegments-1)/2; ++i){
      Depth -= depthStep;
      beta = sin((0.5*Depth)/StringLength)*2;
      
      if(colorSet == "primary"){
        fill(secondaryColor);
        colorSet = "secondary";
      }else if(colorSet == "secondary"){
        fill(primaryColor);
        colorSet = "primary";
      }
      
      line(0,0,0,-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      line(0,0,0,-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
      beginShape();
      vertex(-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      vertex(frontLeft.x,frontLeft.y,frontLeft.z);
      vertex(backLeft.x,backLeft.y,backLeft.z);
      vertex(-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
      endShape(CLOSE);
      
      frontLeft.set(-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      backLeft.set(-StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
      
      line(0,0,0,StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      line(0,0,0,StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
      beginShape();
      vertex(frontRight.x,frontRight.y,frontRight.z);
      vertex(StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      vertex(StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
      vertex(backRight.x,backRight.y,backRight.z);
      endShape(CLOSE);
      
      frontRight.set(StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),StringLength*(sin(0.5*beta)));
      backRight.set(StringLength*sin((i+0.5)*alpha),-StringLength*cos((i+0.5)*alpha),-StringLength*(sin(0.5*beta)));
    }
    
    
    
    
    
    //// 1
    //line(0,0,0,-30,-147,20);
    //line(0,0,0,30,-147,20);
    //line(0,0,0,30,-147,-20);
    //line(0,0,0,-30,-147,-20);
    //beginShape();
    //vertex(-30,-147,20);
    //vertex(30,-147,20);
    //vertex(30,-147,-20);
    //vertex(-30,-147,-20);
    //endShape(CLOSE);
    
    ////2
    //line(0,0,0,-84,-124,20);
    //line(0,0,0,-84,-124,-20);
    //beginShape();
    //vertex(-84,-124,20);
    //vertex(-30,-147,20);
    //vertex(-30,-147,-20);
    //vertex(-84,-124,-20);
    //endShape(CLOSE);
    
    ////2
    //line(0,0,0,84,-124,20);
    //line(0,0,0,84,-124,-20);
    //beginShape();
    //vertex(30,-147,20);
    //vertex(84,-124,20);
    //vertex(84,-124,-20);
    //vertex(30,-147,-20);
    //endShape(CLOSE);
    
    popMatrix();
  }
}
