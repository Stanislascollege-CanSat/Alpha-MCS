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

  public SmartSelectionElement serialPortSelect;
  public SmartSelectionElement serialBaudSelect;

  public UtilityButtonElement dataOutputFolderButton;
  public TextElement dataOutputFolderName;

  public NewLineInputElement missionIdentifierInput;

  public BooleanElement consoleLogFileTickBox;
  public BooleanElement csvDataFileTickBox;

  public SmartSliderElement testSlider;

  // Data
  String absoluteMissionPath;




  public SetupView(AppController a, float x, float y, float w, float h){
    super(a, x, y, w, h);



    this.backButton = new TextButtonElement(this.appController, this, this.dim.x/2 - 70, this.dim.y - 40, 60, "Back"){
      public void clickEvent(){
        this.appController.displayStartupScheme();
      }
    };

    this.continueButton = new ButtonElement(this.appController, this, this.dim.x/2 + 10, this.dim.y - 40, 100, "Continue"){
      public void clickEvent(){
        try{
          this.appController.runMissionSetup();
        }catch(IOException e){
          println("Something went wrong when trying to write to file.");
        }catch(Exception e){
          println("Something unidentifiable went wrong.");
        }
      }
    };

    // Main elements
    this.serialConnectionLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 200, 200, "Serial connection:", RIGHT);
    this.missionFolderLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 280, 200, "Mission folder:", RIGHT);
    this.missionIdentifierLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 320, 200, "Mission identifier:", RIGHT);

    this.serialPortSelect = new SmartSelectionElement(this.appController, this, this.dim.x/2 + 10, 200, 300);
    this.serialPortSelect.setPlaceholder("Serial port");
    this.serialPortSelect.setStrict(true);
    this.serialBaudSelect = new SmartSelectionElement(this.appController, this, this.dim.x/2 + 10, 240, 150);
    this.serialBaudSelect.setPlaceholder("Baud rate");
    this.serialBaudSelect.setStrict(true);

    this.dataOutputFolderButton = new UtilityButtonElement(this.appController, this, this.dim.x/2 + 10, 280){
      public void clickEvent(){
        SetupView_ask_folder_MissionData();
      }
    };
    this.dataOutputFolderName = new TextElement(this.appController, this, this.dim.x/2 + 30, 280, 500, "", LEFT);

    this.missionIdentifierInput = new NewLineInputElement(this.appController, this, this.dim.x/2 + 10, 320, 200);

    this.consoleLogFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 400, 200, "Create console-log file", RIGHT);
    this.consoleLogFileTickBox = new BooleanElement(this.appController, this, this.dim.x/2 + 10, 400);


    this.csvDataFileTBLabel = new TextElement(this.appController, this, this.dim.x/2 - 210, 440, 200, "Create CSV data-output file", RIGHT);
    this.csvDataFileTickBox = new BooleanElement(this.appController, this, this.dim.x/2 + 10, 440);

    this.testSlider = new SmartSliderElement(this.appController, this, 100, 100, ElementOrient.VERTICAL, 200, 0, 100);

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

    //this.elements.add(this.testSlider);

    // Setting default values
    for(String s : SerialController.getAvailablePorts()){
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


    // Setting default data

    this.absoluteMissionPath = "";

  }

  public String getSelectedSerialPort(){
    return this.serialPortSelect.getValue();
  }

  public int getSelectedSerialBaud(){
    return int(this.serialBaudSelect.getValue());
  }

  public String getSelectedMissionPath(){
    return this.absoluteMissionPath;
  }

  public String getSelectedMissionIdentifier(){
    return this.missionIdentifierInput.getValue();
  }

  public boolean getSelectedDoConsoleLogFile(){
    return this.consoleLogFileTickBox.getValue();
  }

  public boolean getSelectedDoCSVDataFile(){
    return this.csvDataFileTickBox.getValue();
  }



  public void viewResizeTriggered(){
    this.backButton.resize(this.dim.x/2 - 70, this.dim.y - 40, 60);
    this.continueButton.resize(this.dim.x/2 + 10, this.dim.y - 40, 100);

    this.serialConnectionLabel.resize(this.dim.x/2 - 210, 200, 200);
    this.missionFolderLabel.resize(this.dim.x/2 - 210, 280, 200);
    this.missionIdentifierLabel.resize(this.dim.x/2 - 210, 320, 200);

    this.serialPortSelect.resize(this.dim.x/2 + 10, 200, 300);
    this.serialBaudSelect.resize(this.dim.x/2 + 10, 240, 150);

    this.dataOutputFolderButton.resize(this.dim.x/2 + 10, 280);
    this.dataOutputFolderName.resize(this.dim.x/2 + 30, 280, 500);

    this.missionIdentifierInput.resize(this.dim.x/2 + 10, 320, 200);

    this.consoleLogFileTickBox.resize(this.dim.x/2 + 10, 400);
    this.consoleLogFileTBLabel.resize(this.dim.x/2 - 210, 400, 200);

    this.csvDataFileTickBox.resize(this.dim.x/2 + 10, 440);
    this.csvDataFileTBLabel.resize(this.dim.x/2 - 210, 440, 200);
  }

  public void show(){
    // view UPDATE part
    if(this.serialPortSelect.getValue() == "" ||
      this.serialBaudSelect.getValue() == "" ||
      this.missionIdentifierInput.getValue().length() < 1 ||
      this.absoluteMissionPath == ""){

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

  // --------------- APPCONTROLLER DATA FILL ----------------- //
  public void Response_selected_folder_MissionData(File selected){
    this.absoluteMissionPath = selected.getAbsolutePath();
    this.dataOutputFolderName.setText(selected.getName());
  }
}
