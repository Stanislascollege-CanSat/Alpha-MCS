// SetupView.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public class SetupView extends ViewController {
  public TextButtonElement backButton;
  public ButtonElement continueButton;

  // Main elements
  public TextElement serialConnectionLabel;
  public TextElement missionFolderLabel;
  public TextElement missionIdentifierLabel;
  public TextElement consoleLogFileTBLabel;
  public TextElement csvDataFileTBLabel;

  public SelectionElement serialPortSelect;
  public SelectionElement serialBaudSelect;

  public UtilityButtonElement dataOutputFolderButton;
  public TextElement dataOutputFolderName;

  public LineInputElement missionIdentifierInput;

  public TickBoxElement consoleLogFileTickBox;
  public TickBoxElement csvDataFileTickBox;




  public SetupView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);



    this.backButton = new TextButtonElement(this.appController, this, this.dim.x/2 - 70, this.dim.y - 40, 60, "Back"){
      public void clickEvent(){
        this.appController.displayStartupScheme();
      }
    };

    this.continueButton = new ButtonElement(this.appController, this, this.dim.x/2 + 10, this.dim.y - 40, 100, "Continue"){
      public void clickEvent(){

      }
    };

    // Main elements
    this.serialConnectionLabel = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 - 210, this.pos.y + 200, 200, "Serial connection:", RIGHT);
    this.missionFolderLabel = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 - 210, this.pos.y + 280, 200, "Mission folder:", RIGHT);
    this.missionIdentifierLabel = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 - 210, this.pos.y + 320, 200, "Mission identifier:", RIGHT);

    this.serialPortSelect = new SelectionElement(this.appController, this, this.pos.x + this.dim.x/2 + 10, this.pos.y + 200, 100);
    this.serialBaudSelect = new SelectionElement(this.appController, this, this.pos.x + this.dim.x/2 + 10, this.pos.y + 240, 100);

    this.dataOutputFolderButton = new UtilityButtonElement(this.appController, this, this.pos.x + this.dim.x/2 + 10, this.pos.y + 280);
    this.dataOutputFolderName = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 + 30, this.pos.y + 280, 500, "", LEFT);

    this.missionIdentifierInput = new LineInputElement(this.appController, this, this.pos.x + this.dim.x/2 + 10, this.pos.y + 320, 200);

    this.consoleLogFileTickBox = new TickBoxElement(this.appController, this, this.pos.x + this.dim.x/2 - 100, this.pos.y + 400);
    this.consoleLogFileTBLabel = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 - 80, this.pos.y + 400, 300, "Create console-log file", LEFT);

    this.csvDataFileTickBox = new TickBoxElement(this.appController, this, this.pos.x + this.dim.x/2 - 100, this.pos.y + 440);
    this.csvDataFileTBLabel = new TextElement(this.appController, this, this.pos.x + this.dim.x/2 - 80, this.pos.y + 440, 300, "Create CSV data-output file", LEFT);

    this.elements.add(this.backButton);
    this.elements.add(this.continueButton);

    this.elements.add(this.serialConnectionLabel);
    this.elements.add(this.missionFolderLabel);
    this.elements.add(this.missionIdentifierLabel);

    this.elements.add(this.serialPortSelect);
    this.elements.add(this.serialBaudSelect);

    this.elements.add(this.dataOutputFolderButton);
    this.elements.add(this.dataOutputFolderName);

    this.elements.add(this.missionIdentifierInput);

    this.elements.add(this.consoleLogFileTickBox);
    this.elements.add(this.consoleLogFileTBLabel);
    this.elements.add(this.csvDataFileTickBox);
    this.elements.add(this.csvDataFileTBLabel);

    // Setting default values
    for(String s : this.appController.serialController.getPortsList()){
      this.serialPortSelect.addOption(s);
    }

    this.serialBaudSelect.addOption("300");
    this.serialBaudSelect.addOption("1200");
    this.serialBaudSelect.addOption("2400");
    this.serialBaudSelect.addOption("4800");
    this.serialBaudSelect.addOption("9600");
    this.serialBaudSelect.addOption("19200");
    this.serialBaudSelect.addOption("38400");
    this.serialBaudSelect.addOption("57600");
    this.serialBaudSelect.addOption("74880");
    this.serialBaudSelect.addOption("115200");
    this.serialBaudSelect.addOption("230400");
    this.serialBaudSelect.addOption("250000");
    this.serialBaudSelect.addOption("500000");
    this.serialBaudSelect.addOption("1000000");
    this.serialBaudSelect.addOption("2000000");

    this.missionIdentifierInput.setValue("PB_Flight01");

    this.consoleLogFileTickBox.setValue(true);
  }

  public void viewResizeTriggered(){
  }

  public void show(){
    // view UPDATE part
    if(this.serialPortSelect.getValue() == "" ||
      this.serialBaudSelect.getValue() == "" ||
      this.missionIdentifierInput.getValue().length() < 1){

      this.continueButton.disabled = true;
    }else{
      this.continueButton.disabled = false;
    }


    stroke(255);
    strokeWeight(1);
    fill(255);

    rectMode(CORNER);

    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    translate(this.pos.x, this.pos.y);

    //
    // Begin Content
    //

    fill(0);

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Thin 20"));
    text("Session setup", this.dim.x/2, 50);

    for(Element e : this.elements){
      e.show();
    }

    //
    // End Content
    //


    translate(-this.pos.x, -this.pos.y);
  }
}
