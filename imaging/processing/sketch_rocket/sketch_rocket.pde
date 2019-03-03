public void setup(){
  size(200, 400, P2D);
  background(255);
}

public void draw(){
  background(255);
  stroke(0);
  strokeWeight(1);
  fill(0);
  
  beginShape();
  vertex(width/4, height/2 - 10);
  vertex(width/10, height/3*2);
  vertex(width/20, height/18*17);
  vertex(width/6, height/7*6);
  vertex(width/3, height/2 - 10 + (height - height/2 + 10)/2);
  endShape();
  
  beginShape();
  vertex(width/4*3, height/2 - 10);
  vertex(width/10*9, height/3*2);
  vertex(width/20*19, height/18*17);
  vertex(width/6*5, height/7*6);
  vertex(width/3*2, height/2 - 10 + (height - height/2 + 10)/2);
  endShape();
  
  stroke(0);
  strokeWeight(1);
  fill(56, 132, 255);
  ellipse(width/2, height/2-10, width/2, height/4*3);
  
  fill(255);
  ellipse(width/2, height/4, width/5, width/5);
  
  //fill(0, 0, 0);
  //rectMode(CENTER);
  //rect(width/2, height/2 - 10 + height/8*3, 20, 10);
  
  
}
