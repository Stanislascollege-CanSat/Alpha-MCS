public void setup() {
	size(1024, 1024, P2D);
	smooth(8);
}

public void draw() {
	
	background(255, 0, 0);
	
	// ICON BACKGROUND
  noStroke();
	for(float i = width/1.1; i >= 0; i -= 0.005) {
		fill(map(sqrt(i), 0, sqrt(width/1.1), 200, 255));
		ellipse(width/2, height/2, i, i);
	}
	
	
	
	//noStroke();
	//fill(255);
	//ellipse(width/2, height/2, width/1.1, height/1.1);

  	if(width/20 < 2){
  	  strokeWeight(2);
    
  //    stroke(180);
  //    translate(-width/10, 0);
  //    line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
      
  //    translate(width/10, 0);
  //    stroke(56, 132, 255, 100);
  ////    line(width/3, height/2, 2*width/3, height/2);
  //    line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
  //    //line(2*width/3 + width/8, height/2 + height/4.444444, width/2, height/5);
  //    translate(width/10, 0);
      
      stroke(56, 132, 255);
      line(width/3, height/2, 2*width/3, height/2);
      line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
      line(2*width/3 + width/8, height/2 + height/4.444444, width/2, height/5);
    }else{
      strokeWeight(width/20);
  	
    	stroke(180);
    	translate(-width/10, 0);
    	line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
    	
    	translate(width/10, 0);
    	stroke(56, 132, 255, 100);
  //  	line(width/3, height/2, 2*width/3, height/2);
    	line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
    	//line(2*width/3 + width/8, height/2 + height/4.444444, width/2, height/5);
    	translate(width/10, 0);
    	
    	stroke(56, 132, 255);
    	line(width/3, height/2, 2*width/3, height/2);
    	line(width/3 - width/8, height/2 + height/4.444444, width/2, height/5);
    	line(2*width/3 + width/8, height/2 + height/4.444444, width/2, height/5);
    }
  
    save(str(width) + "x" + str(height) + ".png");
    noLoop();
}
