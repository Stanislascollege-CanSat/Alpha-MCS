// AppController.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public interface AppController_Interface {
  public void show();

  public void addView(ViewController v);
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
  private ElementTestView elementTestView;
  private ConsoleView testConsoleView;

  public AppController(PApplet environment){
    this.mainJavaEnvironment = environment;

    this.viewControllers = new ArrayList<ViewController>();

    this.serialController = new SerialController(this, this.mainJavaEnvironment);

    this.startupView = new StartupView(this, 0, 0, width, height);

    this.setupView = new SetupView(this, 0, 0, width, height);
    this.setupView.visible = false;

    this.elementTestView = new ElementTestView(this, 400, 0, width-400, height);
    this.elementTestView.visible = false;

    this.testConsoleView = new ConsoleView(this, 0, 0, 400, height);
    this.testConsoleView.visible = false;

    this.viewControllers.add(this.startupView);
    this.viewControllers.add(this.setupView);
    this.viewControllers.add(this.elementTestView);
    this.viewControllers.add(this.testConsoleView);
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
    this.elementTestView.resize(400, 0, width-400, height);
    this.testConsoleView.resize(0, 0, 400, height);
  }

  public void addView(ViewController v){
    this.viewControllers.add(v);
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

  public void runMissionSetup(String serialPort, int serialBaud, String missionIdentifier, boolean createConsoleLogFile, boolean createCSVDataFile){

  }

  public void openElementTestView(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = false;
    }
    for(ViewController v : this.viewControllers){
      v.visible = false;
    }
    this.testConsoleView.visible = true;
    this.elementTestView.visible = true;
  }

  public void closeElementTestView(){
    for(ViewController v : this.viewControllers){
      v.userInteractionEnabled = false;
    }
    for(ViewController v : this.viewControllers){
      v.visible = false;
    }
    this.startupView.visible = true;
  }

  public void logMessage(String msg){
    this.testConsoleView.addMessage(msg);
  }
}
