import processing.serial.*;

public interface SerialController_Interface {

}

public class SerialController implements SerialController_Interface {
  public AppController appController;
  private PApplet mainJavaEnvironment;
  private Serial com;
  private boolean serialSetupDone;
  private int[] possibleBaudRates;

  public SerialController(AppController a, PApplet environment){
    this.appController = a;
    this.mainJavaEnvironment = environment;
  }

  public String[] getPortsList(){
    return Serial.list();
  }

  public void openConnection(String port, int baudrate){
    if(this.serialSetupDone){
      this.stopConnection();
    }
    this.com = null;
    this.com = new Serial(this.mainJavaEnvironment, port, baudrate);
    this.serialSetupDone = true;
  }

  public void stopConnection(){
    if(this.serialSetupDone){
      this.com.stop();
      this.serialSetupDone = false;
    }
  }

  public void write(String msg){
    if(this.serialSetupDone && this.com.available() == 1){
      this.com.write(msg);
    }
  }

  public boolean opened(){
    return this.serialSetupDone;
  }
}
