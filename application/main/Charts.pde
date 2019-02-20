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
  public ArrayList<ArrayList<PVector>> dataSets;
  public color[] colorList;
  public String title;
  public String Xquantity;
  public String Yquantity;
  public String Xunit;
  public String Yunit;

  public Chart(float x, float y, float w, float h, ChartRange xR, ChartRange yR, String title, String Xq, String Xu, String Yq, String Yu){
    this.xRange = xR;
    this.yRange = yR;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.dataSets = new ArrayList<ArrayList<PVector>>();
    this.colorList = new color[5];
    this.colorList[0] = color(56, 132, 255);
    this.colorList[1] = color(244, 66, 66);
    this.colorList[2] = color(113, 244, 65);
    this.colorList[3] = color(244, 184, 65);
    this.colorList[4] = color(145, 65, 244);

    this.title = title;
    this.Xquantity = Xq;
    this.Xunit = Xu;
    this.Yquantity = Yq;
    this.Yunit = Yu;

    ArrayList<PVector> test = new ArrayList<PVector>();
    for(float angle = 0; angle <= 1000; angle += 0.001){
      test.add(new PVector(angle, log10(angle)));
    }
    this.dataSets.add(test);
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

    int colorScheme = 0;

    if(colorScheme < this.colorList.length){
      stroke(this.colorList[colorScheme]);
    }

    for(ArrayList<PVector> l : this.dataSets){


      beginShape();
      float Pres = l.size()/this.dim.x;
      int increment = 1;
      while(Pres > 8){
        increment *= 2;
        Pres = (l.size()/increment)/this.dim.x;
      }
      for(int i = 0; i < l.size(); i += increment){
        if(i < l.size()){
          if(l.get(i).x >= this.xRange.min && l.get(i).x <= this.xRange.max && l.get(i).y >= this.yRange.min && l.get(i).y <= this.yRange.max){
            vertex(
              map(l.get(i).x, this.xRange.min, this.xRange.max, this.pos.x, this.pos.x + this.dim.x),
              map(l.get(i).y, this.yRange.min, this.yRange.max, this.pos.y + this.dim.y/2, this.pos.y - this.dim.y/2)
            );
          }
          if(l.get(i).y < this.yRange.min || l.get(i).y > this.yRange.max){
            endShape();
            beginShape();
          }
        }
      }
      endShape();


      colorScheme++;
      if(colorScheme >= this.colorList.length){
        colorScheme = 0;
      }
      stroke(this.colorList[colorScheme]);
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
