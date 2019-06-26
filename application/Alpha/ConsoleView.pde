// ConsoleView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

import java.util.*;

public class ConsoleView extends ViewController {
  public VerticalScrollElement scrollBar;
  public ArrayList<ConsoleMessageElement> messages;

  public ArrayList<String[]> messagesToLog;
  public ArrayList<Integer> messagesToRemove;

  public TickBoxElement autoScrollTickBox;
  public TextElement autoScrollLabel;

  public ButtonElement clearMessagesButton;

  public NewLineInputElement commandInput;

  public float calculatedMessageHeight;
  public float messageViewHeight;
  public float messageWidth;

  public ConsoleView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);

    this.messageWidth = (this.dim.x > 600) ? 600 : this.dim.x;

    this.messageViewHeight = this.dim.y - 80;

    this.scrollBar = new VerticalScrollElement(this.appController, this, this.dim.x - 10, this.messageViewHeight/2, this.messageViewHeight, 0, this.messageViewHeight);


    this.messages = new ArrayList<ConsoleMessageElement>();

    this.messagesToLog = new ArrayList<String[]>();
    this.messagesToRemove = new ArrayList<Integer>();

    this.autoScrollTickBox = new TickBoxElement(this.appController, this, 15, this.dim.y - 20);
    this.autoScrollTickBox.setValue(true);

    this.autoScrollLabel = new TextElement(this.appController, this, 30, this.dim.y - 20, 200, "Autoscroll", LEFT);

    this.clearMessagesButton = new ButtonElement(this.appController, this, this.dim.x - 65, this.dim.y - 20, 50, "Clear"){
      public void clickEvent(){
        this.appController.clearConsoleMessages();
      }
    };

    this.commandInput = new NewLineInputElement(this.appController, this, 10, this.dim.y - 55, this.dim.x - 20){
      public void enterEvent(){
        String[] parse = this.appController.parseCommand(this.getValue());
        if(parse.length > 0){
          this.appController.runCommand(parse[0], Arrays.copyOfRange(parse, 1, parse.length));
        }
        this.reset();
      }
    };
    this.commandInput.setTextFont(fonts.get("SF").get("Regular"));
    this.commandInput.setPlaceholder("Enter command");

    this.calculatedMessageHeight = 0;

    this.elements.add(this.scrollBar);
    this.elements.add(this.autoScrollTickBox);
    this.elements.add(this.autoScrollLabel);
    this.elements.add(this.clearMessagesButton);
    this.elements.add(this.commandInput);

    // this.addMessage("First message", "error");
    // for(int i = 0; i < 100; ++i){
    //   this.addMessage("This is a message", "message");
    // }
    // this.addMessage("Last message", "error");


  }


  private void arrangeMessages(){
    this.calculatedMessageHeight = 0;
    for(ConsoleMessageElement e : this.messages){
      e.resize((this.dim.x - 10)/2 - (this.messageWidth - 20)/2, this.calculatedMessageHeight + 5 + e.dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + e.dim.y;
    }
    this.calculatedMessageHeight += 5;
    this.scrollBar.setExtremes(0, this.messageViewHeight);
    if(this.calculatedMessageHeight > this.messageViewHeight){
      // Now messages start to fall outside of the visible field
      this.scrollBar.setExtremes(0, this.calculatedMessageHeight);
      if(this.autoScrollTickBox.getValue()){
        this.scrollBar.setCurrentPosition(this.calculatedMessageHeight - this.messageViewHeight, this.calculatedMessageHeight);
      }else{
    	 this.scrollBar.setCurrentPosition(this.scrollBar.getMinimumValue(), this.scrollBar.getMinimumValue() + this.messageViewHeight);
    	 if(this.scrollBar.getMaximumValue() > this.calculatedMessageHeight) {
    		 this.scrollBar.setCurrentPosition(this.calculatedMessageHeight - this.messageViewHeight, this.calculatedMessageHeight);
    	 }
      }
    }else {
        this.scrollBar.setCurrentPosition(0, this.messageViewHeight);
    }

  }

  private void addMessage(String msg, String type){
	switch(type) {
	case "warning":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("WARNING");
	  break;
	case "unknown_command":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("UNKNOWN COMMAND");
	  break;
	case "syntax_error":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "yellow"));
	  this.messages.get(this.messages.size() - 1).setTitle("SYNTAX ERROR");
	  break;
	case "error":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "red"));
	  this.messages.get(this.messages.size() - 1).setTitle("ERROR");
	  break;
	case "serial":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "orange"));
	  this.messages.get(this.messages.size() - 1).setTitle("SERIAL");
	  break;
	case "setup":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, color(66, 241, 244)));
	  this.messages.get(this.messages.size() - 1).setTitle("SETUP MESSAGE");
	  break;
	case "response":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "blue"));
	  this.messages.get(this.messages.size() - 1).setTitle("RESPONSE");
	  break;
	case "help":
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg, "blue"));
	  this.messages.get(this.messages.size() - 1).setTitle("HELP");
	  break;
	default:
	  this.messages.add(new ConsoleMessageElement(this.appController, this, (this.dim.x - 10)/2 - (this.messageWidth - 20)/2, 0, this.messageWidth - 20, hour(), minute(), second(), msg));
	  this.messages.get(this.messages.size() - 1).setTitle("MESSAGE");
	  break;
	}
	this.messages.get(this.messages.size() - 1).setScrollContainer(0, this.messageViewHeight);
	this.arrangeMessages();
  
  }

  private void removeMessage(int id){
    if(id >= 0 && id < this.messages.size()){
      this.messages.remove(id);
    }
  }

  public void deleteMessage(int id){
    this.messagesToRemove.add(id);
  }

  public void clearMessages(){
    for(ConsoleMessageElement e : this.messages){
      e.deselect();
    }
    this.messages.clear();
    this.arrangeMessages();
  }

  public void logMessage(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "message";
    }
  }

  public void logSerial(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "serial";
    }
  }

  public void logWarning(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "warning";
    }
  }

  public void logError(String msg){
    if(msg.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = "error";
    }
  }

  public void logSetup(String msg){
    this.logSpecial(msg, "setup");
  }

  public void logResponse(String msg) {
	  this.logSpecial(msg,  "response");
  }

  public void logHelp(String msg) {
	  this.logSpecial(msg, "help");
  }

  public void logSpecial(String msg, String id){
    if(msg.length() > 0 && id.length() > 0){
      this.messagesToLog.add(new String[2]);
      this.messagesToLog.get(this.messagesToLog.size() - 1)[0] = msg;
      this.messagesToLog.get(this.messagesToLog.size() - 1)[1] = id;
    }
  }





  public void mouseScrolled(float count){
    this.scrollBar.addScroll(count);
    if(abs(count) > 18) {
    	this.autoScrollTickBox.setValue(false);
    }
    if(this.scrollBar.isAtBottom()){
      this.autoScrollTickBox.setValue(true);
    }
  }

  public void viewResizeTriggered(){
    //this.messageWidth = (this.dim.x > 400) ? 400 : this.dim.x;
	  this.messageViewHeight = this.dim.y - 80;
	  this.scrollBar.resize(this.dim.x - 10, this.messageViewHeight/2, this.messageViewHeight);
    this.commandInput.resize(10, this.dim.y - 55, this.dim.x - 20);
    this.autoScrollTickBox.resize(15, this.dim.y - 20);
    this.autoScrollLabel.resize(30, this.dim.y - 20, 200);
    this.clearMessagesButton.resize(this.dim.x - 65, this.dim.y - 20, 50);
    for(ConsoleMessageElement e : this.messages){
      e.setScrollContainer(0, this.messageViewHeight);
    };
    //this.scrollBar.setCurrentPosition(0, this.messageViewHeight);
    //this.autoScrollTickBox.setValue(true);
    this.arrangeMessages();
  }

  public void show(){

    while(this.messagesToLog.size() > 0){
      this.addMessage(this.messagesToLog.get(0)[0], this.messagesToLog.get(0)[1]);
      this.messagesToLog.remove(0);
    }

    while(this.messagesToRemove.size() > 0){
      this.removeMessage(this.messagesToRemove.get(0));
      this.messagesToRemove.remove(0);
      this.arrangeMessages();
    }

    this.calculatedMessageHeight = 0;
    for(int i = 0; i < this.messages.size(); ++i){
      this.messages.get(i).resize((this.dim.x - 10)/2 - (this.messageWidth - 20)/2, this.calculatedMessageHeight + 5 + this.messages.get(i).dim.y/2 - this.scrollBar.getMinimumValue());
      this.calculatedMessageHeight += 5 + this.messages.get(i).dim.y;

      this.messages.get(i).setId(i);
    }


    stroke(0);
    strokeWeight(1);
    noFill();

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    stroke(0);
    strokeWeight(1);
    line((this.dim.x - 10)/2 - (this.messageWidth - 20)/6 - 15, 0, (this.dim.x - 10)/2 - (this.messageWidth - 20)/6 - 15, this.messageViewHeight);
    line(0, this.messageViewHeight, this.dim.x, this.messageViewHeight);
    noStroke();
    fill(255);
    rect(0, this.messageViewHeight, this.dim.x, this.dim.y - this.messageViewHeight);

    for(Element e : this.elements){
      e.show();
    }

    for(ConsoleMessageElement e : this.messages){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }

  public void mousePressed(){
    this.elementFilter.clear();
    for(int i = 0; i < this.elements.size(); ++i){
      if(this.elements.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    int biggest = -1;
    int index = -1;
    for(int i : this.elementFilter){
      if(this.elements.get(i).layer > biggest){
        biggest = this.elements.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.elements.get(index).mousePressed();
    }
    for(int i = 0; i < this.elements.size(); ++i){
      if(i != index){
        this.elements.get(i).deselect();
      }
    }

    // message elements

    this.elementFilter.clear();
    for(int i = 0; i < this.messages.size(); ++i){
      if(this.messages.get(i).mousePressIsWithinBorder()){
        this.elementFilter.add(i);
      }
    }
    biggest = -1;
    index = -1;
    for(int i : this.elementFilter){
      if(this.messages.get(i).layer > biggest){
        biggest = this.messages.get(i).layer;
        index = i;
      }
    }
    if(index > -1){
      this.messages.get(index).mousePressed();
    }
    for(int i = 0; i < this.messages.size(); ++i){
      if(i != index){
        this.messages.get(i).deselect();
      }
    }
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }

    for(ConsoleMessageElement e : this.messages){
      e.mouseReleased();
    }
  }

  public void mouseWheel(float count){
    if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y && mouseY <= this.pos.y + this.dim.y){
      this.mouseScrolled(count);
    }
    for(Element e : this.elements){
      if(e.mousePressIsWithinBorder()){
        e.mouseWheel(count);
      }
    }
  }

  public void keyPressed(char k, int c){
    for(Element e : this.elements){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(Element e : this.elements){
      if(e.selected){
        e.keyTyped(k);
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(Element e : this.elements){
      if(e.selected){
        e.keyReleased();
      }
    }
    for(ConsoleMessageElement e : this.messages){
      if(e.selected){
        e.keyReleased();
      }
    }
  }
}
