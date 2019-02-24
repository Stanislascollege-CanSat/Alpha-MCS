// AppController.pde
// Processing 3.4
// Rens Dur (Project Bèta)

import java.io.FileWriter;

public interface AppController_Interface {
  public void show();

  public void addView(ViewController v);
  public void exitApplication();
  public void resize();
  public void mousePressed();
  public void mouseReleased();
  public void keyPressed(char k, int c);
  public void keyTyped(char k);
  public void keyReleased();

  public void displaySetupScheme();
}

public class AppController implements AppController_Interface {
  public PApplet mainJavaEnvironment;

  private ArrayList<ViewController> viewControllers;

  private SerialController serialController;

  private StartupView startupView;
  private SetupView setupView;

  

  public AppController(PApplet environment){
    this.mainJavaEnvironment = environment;

    this.viewControllers = new ArrayList<ViewController>();

    this.serialController = new SerialController(this, this.mainJavaEnvironment);

    this.startupView = new StartupView(this, 0, 0, width, height);

    this.setupView = new SetupView(this, 0, 0, width, height);
    this.setupView.visible = false;

    this.viewControllers.add(this.startupView);
    this.viewControllers.add(this.setupView);
  }

  public void show(){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.show();
      }
    }
    strokeWeight(1);
    fill(0);
    textAlign(LEFT);
    textFont(fonts.get("SF").get("Regular"));
    text(int(frameRate), 10, 20);
  }

  public void resize(){
    this.startupView.resize(0, 0, width, height);
    this.setupView.resize(0, 0, width, height);
  }

  public void addView(ViewController v){
    this.viewControllers.add(v);
  }

  public void exitApplication(){
    exit();
  }

  public void mousePressed(){
    for(ViewController v : this.viewControllers){
      if(v.visible && v.userInteractionEnabled){
        v.mousePressed();
      }
    }
  }

  public void mouseReleased(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = true;
      if(v.visible){
        v.mouseReleased();
      }
    }
  }

  public void mouseWheel(float count){
    for(ViewController v : this.viewControllers){
      if(v.visible && v.userInteractionEnabled){
        v.mouseWheel(count);
      }
    }
  }

  public void keyPressed(char k, int c){
    if(k == '1' && CTRL_PRESSED){
      this.exitApplication();
    }
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyPressed(k, c);
      }
    }
  }

  public void keyTyped(char k){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyTyped(k);
      }
    }
  }

  public void keyReleased(){
    for(ViewController v : this.viewControllers){
      if(v.visible){
        v.keyReleased();
      }
    }
  }






  public void displaySetupScheme(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = false;
    }
    for(ViewController v : this.viewControllers){
      v.visible = false;
    }
    this.setupView.visible = true;
  }

  public void displayStartupScheme(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = false;
    }
    for(ViewController v : this.viewControllers){
      v.visible = false;
    }
    this.startupView.visible = true;
  }

  public void runMissionSetup() throws IOException{
    // println(this.setupView.getSelectedSerialPort());
    // println(this.setupView.getSelectedSerialBaud());
    // println(this.setupView.getSelectedMissionPath());
    // println(this.setupView.getSelectedMissionIdentifier());
    // println(this.setupView.getSelectedDoConsoleLogFile());
    // println(this.setupView.getSelectedDoCSVDataFile());



    //println(loadStrings(this.setupView.getSelectedMissionPath() + "/test.txt"));

  }



  // ------------------ FILE SELECTION METHODS --------------------- //
  public void SetupView_ask_folder_MissionData(){
    SetupView_ask_folder_MissionData();
  }

  public void SetupView_selected_folder_MissionData(File selected){
    this.setupView.Response_selected_folder_MissionData(selected);
  }
}
