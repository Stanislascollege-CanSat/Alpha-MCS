// ConsoleElements.pde
// Processing 3.4
// Rens Dur (Project Bèta)

// 'Elements.pde' must be included in your project, since all ConsoleElements are an extention of class Elements

public class ConsoleMessageListElement extends Element {
  public ArrayList<ConsoleMessageElement> messageElements;

  public ConsoleMessageListElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);

    this.messageElements = new ArrayList<ConsoleMessageElement>();
  }

  public void appendMessage(int hours, int mins, int secs, String msg){
    //this.messageElements.add(new ConsoleMessageElement(this.appController, ))
  }
}


//-----------------------------------------------------------------------------------------------------------------//

public class ConsoleMessageElement extends Element {
  public float originalYPos;
  public String[] time;
  public String preMsg;
  public String buffer;
  public String message;
  public String title;
  public int lineCounter;
  public color backgroundColor;
  public boolean isInsideScrollContainer;
  public float scrollContainerMin;
  public float scrollContainerMax;
  public boolean isLayerShift;

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg){
    super(a, v, x, y, w, 30);

    this.isInsideScrollContainer = false;

    this.originalYPos = this.pos.y;

    this.time = new String[3];
    this.time[0] = str(hours);
    this.time[1] = str(mins);
    this.time[2] = str(secs);
    while(this.time[0].length() < 2){
      this.time[0] = "0" + this.time[0];
    }
    while(this.time[1].length() < 2){
      this.time[1] = "0" + this.time[1];
    }
    while(this.time[2].length() < 2){
      this.time[2] = "0" + this.time[2];
    }


    this.preMsg = msg;
    this.buffer = "";
    this.message = "";
    this.title = "";

    textFont(fonts.get("SF").get("Regular"));
    while(this.preMsg.length() > 0){
      if(!(textWidth(this.buffer + this.preMsg.charAt(0)) > 2*this.dim.x/3 - 20)){
        this.buffer += this.preMsg.charAt(0);
        this.preMsg = this.preMsg.substring(1, this.preMsg.length());
      }else{
        this.message += this.buffer;
        this.buffer = "";
        if(this.preMsg.length() > 0){
          this.message += "\n";
        }
      }
    }
    if(this.buffer.length() > 0){
      this.message += this.buffer;
    }

    this.lineCounter = 1;
    for(int i = 0; i < this.message.length(); ++i){
      if(this.message.charAt(i) == '\n'){
        this.lineCounter++;
      }
    }
    this.calcAndSetHeight(this.lineCounter);

    this.backgroundColor = color(56, 132, 255);

  }

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg, color c){
    this(a, v, x, y, w, hours, mins, secs, msg);
    this.backgroundColor = c;
  }

  public ConsoleMessageElement(AppController a, ViewController v, float x, float y, float w, int hours, int mins, int secs, String msg, String c){
    this(a, v, x, y, w, hours, mins, secs, msg);
    if(c == "blue"){
      this.backgroundColor = color(56, 132, 255);
    }else if(c == "yellow"){
      this.backgroundColor = color(255, 233, 0);
    }else if(c == "red"){
      this.backgroundColor = color(255, 0, 0);
    }else if(c == "orange"){
      this.backgroundColor = color(244, 185, 66);
    }
  }

  public void setScrollContainer(float scrlMin, float scrlMax){
    this.scrollContainerMin = scrlMin;
    this.scrollContainerMax = scrlMax;
    this.isInsideScrollContainer = true;
  }

  public void removeScrollContainer(){
    this.isInsideScrollContainer = false;
  }



  public void calcAndSetHeight(int lines){
    this.dim.y = lines * 20.5 + 14;
  }

  public void setTitle(String t){
    if(this.title == "" && t.length() > 0){
      this.lineCounter++;
      this.title = t;
    }
    this.calcAndSetHeight(this.lineCounter);
  }

  public void resize(float x, float y){
    this.pos.set(x, y);
  }

  public void applyScrollAmount(float amnt){
    this.pos.y = this.originalYPos + amnt;
  }

  public void removeTitle(){
    this.title = "";
    this.lineCounter--;
    this.calcAndSetHeight(this.lineCounter);
  }

  public void show(){
    if(!this.isInsideScrollContainer || (this.pos.y - this.dim.y/2 < this.scrollContainerMax && this.pos.y + this.dim.y/2 > this.scrollContainerMin)){
      if(this.pos.y + this.dim.y/2 > this.scrollContainerMax || this.pos.y - this.dim.y/2 < this.scrollContainerMin){
        this.isLayerShift = true;
        translate(0, 0, -1);
      }
      if(this.pos.y + this.dim.y/2 >= this.viewController.pos.y && this.pos.y - this.dim.y/2 <= this.viewController.pos.y + this.viewController.dim.y){
        stroke(0);
        strokeWeight(1);
        fill(0);

        textAlign(LEFT);
        textFont(fonts.get("SF").get("Regular"));
        text(this.time[0] + ":" + this.time[1] + ":" + this.time[2], this.pos.x + 10, this.pos.y + 6);


        stroke(this.backgroundColor);
        strokeWeight(2);
        fill(this.backgroundColor, 50);

        rectMode(CORNER);

        rect(this.pos.x + this.dim.x/3, this.pos.y - this.dim.y/2, 2*this.dim.x/3, this.dim.y);
        stroke(0);
        strokeWeight(1);
        fill(0);
        text((this.title.length() > 0 ? "\n" : "") + this.message, this.pos.x + this.dim.x/3 + 10, this.pos.y - this.dim.y/2 + 22);
        if(this.title.length() > 0){
          textFont(fonts.get("SF").get("Bold"));
          text(this.title, this.pos.x + this.dim.x/3 + 10, this.pos.y - this.dim.y/2 + 22);
        }

        if(this.selected){
          this.drawSelectionOutline();
        }
      }
      if(this.isLayerShift){
        this.isLayerShift = false;
        translate(0, 0, 1);
      }
    }
  }
}
