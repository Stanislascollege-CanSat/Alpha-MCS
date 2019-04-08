// Charts.pde
// Processing 3.4
// Rens Dur (Project Bèta)

import java.math.BigDecimal;

public class ChartRange {
  private float min;
  private float max;

  public ChartRange(float min, float max){
    this.min = min;
    this.max = max;
  }

  public float getMin(){
    return this.min;
  }

  public float getMax(){
    return this.max;
  }

  public ChartRange copy(){
    return new ChartRange(this.min, this.max);
  }

  public void set(float min, float max){
    this.min = min;
    this.max = max;
  }

  public void setMin(float min){
    this.min = min;
  }

  public void setMax(float max){
    this.max = max;
  }

}



public interface Chart_Interface {

}

public class Chart implements Chart_Interface {
  public ChartRange xRange;
  public ChartRange yRange;
  public PVector pos;
  public PVector dim;
  public ArrayList<DataSet> dataSets;
  public color[] colorList;
  public String title;
  public String Xquantity;
  public String Yquantity;
  public String Xunit;
  public String Yunit;
  public boolean autoScroll;

  public Chart(float x, float y, float w, float h, ChartRange xR, ChartRange yR, String title, String Xq, String Xu, String Yq, String Yu){
    this.xRange = xR;
    this.yRange = yR;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.dataSets = new ArrayList<DataSet>();
    this.colorList = new color[7];
    this.colorList[0] = color(255,   0,   0);
    this.colorList[1] = color(  0, 255,   0);
    this.colorList[2] = color(  0,   0, 255);
    this.colorList[3] = color(255,   0, 233);
    this.colorList[4] = color(255, 174,   0);
    this.colorList[5] = color(203,   0, 255);
    this.colorList[6] = color(  0,   0,   0);
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );
//    this.colorList[] = color(, , );

    this.title = title;
    this.Xquantity = Xq;
    this.Xunit = Xu;
    this.Yquantity = Yq;
    this.Yunit = Yu;

    this.autoScroll = false;
  }

  public void addDataSet(DataSet a){
    this.dataSets.add(a);
  }

  public void clear(){
    this.dataSets.clear();
  }
  
  public void resize(float x, float y, float w, float h) {
	 this.pos.set(x, y);
	 this.dim.set(w, h);
  }
  
  public void setRange(ChartRange xR, ChartRange yR) {
	 this.xRange = xR;
	 this.yRange = yR;
  }

  public void setXRange(ChartRange xR){
    this.xRange = xR;
  }

  public void setYRange(ChartRange yR){
    this.yRange = yR;
  }

  public void addScroll(float count){
    count = -count/100*(this.yRange.max - this.yRange.min);
    this.yRange.min += count;
    this.yRange.max += count;
  }



  public void show(){
    //
    // GRID
    //

    float dx = this.xRange.max - this.xRange.min;
    int n = floor(log10(dx));
    float amntOfUnitsPerLine = pow(10, n-1);
    while(((amntOfUnitsPerLine / dx) * this.dim.x) < 20){
      amntOfUnitsPerLine *= 2;
    }

    stroke(200);
    fill(0);
    strokeWeight(1);
    textFont(fonts.get("SF").get("Regular 6"));
    textAlign(LEFT);
    for(float currentX = 0; currentX <= this.xRange.max; currentX += amntOfUnitsPerLine){
      if(currentX >= this.xRange.min){
        line(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
        translate(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2 + 10);
        rotateZ(PI/3);
        text(str(currentX), 0, 7);
        rotateZ(-PI/3);
        translate(-map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), -this.pos.y - this.dim.y/2 - 10);
      }
    }
    for(float currentX = 0; currentX >= this.xRange.min; currentX -= amntOfUnitsPerLine){
      if(currentX <= this.xRange.max){
        line(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
        translate(map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2 + 10);
        rotateZ(PI/3);
        text(str(currentX), 0, 7);
        rotateZ(-PI/3);
        translate(-map(currentX, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), -this.pos.y - this.dim.y/2 - 10);
      }
    }

    float dy = this.yRange.max - this.yRange.min;
    n = floor(log10(dy));
    amntOfUnitsPerLine = pow(10, n-1);
    while(((amntOfUnitsPerLine / dy) * this.dim.y) < 20){
      amntOfUnitsPerLine *= 2;
    }

    stroke(200);
    fill(0);
    strokeWeight(1);
    textFont(fonts.get("SF").get("Regular 6"));
    textAlign(RIGHT);
    for(float currentY = 0; currentY <= this.yRange.max; currentY += amntOfUnitsPerLine){
      if(currentY >= this.yRange.min){
        line(this.pos.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
        text(str(currentY), this.pos.x - 10, map(currentY, this.yRange.min, yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2) + 4);
      }
    }
    for(float currentY = 0; currentY >= this.yRange.min; currentY -= amntOfUnitsPerLine){
      if(currentY <= this.yRange.max){
        line(this.pos.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(currentY, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
        text(str(currentY), this.pos.x - 10, map(currentY, this.yRange.min, yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2) + 4);
      }
    }

    //
    // AXES
    //
    stroke(0);
    strokeWeight(2);
    if(this.xRange.min <= 0 && this.xRange.max >= 0){
      //Draw y-axis

      line(map(0, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y - this.dim.y/2, map(0, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x), this.pos.y + this.dim.y/2);
    }

    if(this.yRange.min <= 0 && this.yRange.max >= 0){
      // Draw x-axis

      line(this.pos.x, map(0, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2), this.pos.x + this.dim.x, map(0, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2));
    }

    //
    // PLOTTING POINTS
    //

    strokeWeight(2);
    noFill();
    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);

    int colorScheme = 0;
    int dataSetNumber = 0;

    if(colorScheme < this.colorList.length){
      stroke(this.colorList[colorScheme]);
    }

    for(DataSet l : this.dataSets){

    	if(!(l == null)) {
	      beginShape();
	      float Pres = l.size()/this.dim.x;
	      int increment = 1;
	      while(Pres > 8){
	        increment *= 2;
	        Pres = (l.size()/increment)/this.dim.x;
	      }
	      for(int i = 0; i < l.size(); i += increment){
	        if(i < l.size()){
	          if(l.getDataAt(i).getXFloat() >= this.xRange.min && l.getDataAt(i).getXFloat() <= this.xRange.max && l.getDataAt(i).getYFloat() >= this.yRange.min && l.getDataAt(i).getYFloat() <= this.yRange.max){
	            vertex(
	              map(l.getDataAt(i).getXFloat(), this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x),
	              map(l.getDataAt(i).getYFloat(), this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2)
	            );
	          }
	          if(l.getDataAt(i).getYFloat() < this.yRange.min || l.getDataAt(i).getYFloat() > this.yRange.max){
	            endShape();
	            beginShape();
	          }
	        }
	      }
	      endShape();
        // if(l.size() > 0){
        //   if(l.getDataAt(l.size()-1).getXFloat() >= this.xRange.max){
        //     this.xRange.max = l.getDataAt(l.size()-1).getXFloat() + (this.xRange.max - this.xRange.min)/20;
        //   }
        // }
	      
	      fill(this.colorList[colorScheme]);
	      text(l.getQuantity(), this.pos.x + 5, this.pos.y - this.dim.y/2 + 15 + 15*dataSetNumber);
	      noFill();
	      
	
	
	      colorScheme++;
	      dataSetNumber++;
	      if(colorScheme >= this.colorList.length){
	        colorScheme = 0;
	      }
	      stroke(this.colorList[colorScheme]);
    	}
    }
    
    
    

    //
    // OUTER BORDER
    //

    stroke(0);
    strokeWeight(1);
    noFill();

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    //
    // TITLE AND NAMINGS
    //

    fill(0);
    textFont(fonts.get("SF").get("Bold"));
    textAlign(CENTER);
    text(this.title, this.pos.x + this.dim.x/2, this.pos.y - this.dim.y/2 - 10);

    textFont(fonts.get("SF").get("Regular"));
    textAlign(LEFT);
    text(this.Xquantity + " (" + this.Xunit + ")", this.pos.x + this.dim.x + 10, this.pos.y + this.dim.y/2);
    textAlign(CENTER);
    text(this.Yquantity + " (" + this.Yunit + ")", this.pos.x, this.pos.y - this.dim.y/2 - 10);
  }
}
