// main.pde
// Processing 3.4
// Rens Dur (Project Bèta)

import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import processing.opengl.PGL;
import processing.opengl.PJOGL;

public PImage appIcon;
public PImage MOUSEPOINTER_arrow;
public PImage MOUSEPOINTER_text;
public String CURRENT_MOUSEPOINTER;
public boolean SET_MOUSEPOINTER_TEXT;

import static javax.swing.JOptionPane.*;

public boolean completedStartup;

// Frame components
public float w, h;
public int minWidth, minHeight;


// Fonts
public Map<String, HashMap<String, PFont>> fonts;

// Conditions
public boolean ALT_PRESSED;
public boolean CTRL_PRESSED;

// Application components
public AppController appController;
//StartupView startupView;

 //PImage mouse_pointer_img;


public Calendar CALENDAR;
public String[] DAYS;

public void settings(){
  size(1000, 700, P3D);
  //fullScreen(P3D);
  //pixelDensity(displayDensity());
  //if(displayDensity() < 2) {
	 // smooth(8);
  //}
  PJOGL.setIcon("1024x1024.png");
}

public void setup(){
  surface.setTitle("Alpha");
  surface.setResizable(true);
//  appIcon = loadImage("icon1000.png");
//  surface.setIcon(appIcon);
  background(200);
  
  MOUSEPOINTER_arrow = loadImage("arrow.png");
  MOUSEPOINTER_arrow.resize(25, 25);
  
  MOUSEPOINTER_text = loadImage("text.png");
  MOUSEPOINTER_text.resize(12, 20);
  
  CURRENT_MOUSEPOINTER = "";
  
  SET_MOUSEPOINTER_TEXT = false;

  // Frame components
  w = width;
  h = height;
  minWidth = 800;
  minHeight = 700;

  // Cursor settings
   //mouse_pointer_img = loadImage("MousePointer.png");
   //cursor(mouse_pointer_img, 2, 13);

  // Load fonts
  fonts = new HashMap<String, HashMap<String, PFont>>();
  fonts.put("Lora", new HashMap<String, PFont>());
  fonts.get("Lora").put("Regular",      createFont("data/fonts/Lora/Regular.ttf",      15));
  fonts.get("Lora").put("Bold",         createFont("data/fonts/Lora/Bold.ttf",         15));
  fonts.get("Lora").put("Italic",       createFont("data/fonts/Lora/Italic.ttf",       15));

  fonts.put("OverpassMono", new HashMap<String, PFont>());
  fonts.get("OverpassMono").put("Regular",      createFont("data/fonts/OverpassMono/Regular.ttf",      15));
  fonts.get("OverpassMono").put("Bold",         createFont("data/fonts/OverpassMono/Bold.ttf",         15));
  fonts.get("OverpassMono").put("Light",       createFont("data/fonts/OverpassMono/Light.ttf",         15));

  fonts.put("PlexMono", new HashMap<String, PFont>());
  fonts.get("PlexMono").put("Regular",      createFont("data/fonts/PlexMono/Regular.ttf",      15));
  fonts.get("PlexMono").put("Bold",         createFont("data/fonts/PlexMono/Bold.ttf",         15));
  fonts.get("PlexMono").put("Italic",       createFont("data/fonts/PlexMono/Italic.ttf",       15));
  fonts.get("PlexMono").put("Light",       createFont("data/fonts/PlexMono/Light.ttf",         15));

  fonts.put("SF", new HashMap<String, PFont>());
  fonts.get("SF").put("Black",      createFont("data/fonts/SF/Black.otf",      8));
  fonts.get("SF").put("Bold",         createFont("data/fonts/SF/Bold.otf",     8));
  fonts.get("SF").put("Heavy",       createFont("data/fonts/SF/Heavy.otf",     8));
  fonts.get("SF").put("Light",       createFont("data/fonts/SF/Light.otf",     8));
  fonts.get("SF").put("Medium",       createFont("data/fonts/SF/Medium.otf",   8));
  fonts.get("SF").put("Regular",       createFont("data/fonts/SF/Regular.otf", 8));
  fonts.get("SF").put("Thin",       createFont("data/fonts/SF/Thin.otf",       8));

  fonts.get("SF").put("Black 6",      createFont("data/fonts/SF/Black.otf",      6));
  fonts.get("SF").put("Bold 6",         createFont("data/fonts/SF/Bold.otf",     6));
  fonts.get("SF").put("Heavy 6",       createFont("data/fonts/SF/Heavy.otf",     6));
  fonts.get("SF").put("Light 6",       createFont("data/fonts/SF/Light.otf",     6));
  fonts.get("SF").put("Medium 6",       createFont("data/fonts/SF/Medium.otf",   6));
  fonts.get("SF").put("Regular 6",       createFont("data/fonts/SF/Regular.otf", 6));
  fonts.get("SF").put("Thin 6",       createFont("data/fonts/SF/Thin.otf",       6));

  fonts.get("SF").put("Black 15",      createFont("data/fonts/SF/Black.otf",      15));
  fonts.get("SF").put("Bold 15",         createFont("data/fonts/SF/Bold.otf",     15));
  fonts.get("SF").put("Heavy 15",       createFont("data/fonts/SF/Heavy.otf",     15));
  fonts.get("SF").put("Light 15",       createFont("data/fonts/SF/Light.otf",     15));
  fonts.get("SF").put("Medium 15",       createFont("data/fonts/SF/Medium.otf",   15));
  fonts.get("SF").put("Regular 15",       createFont("data/fonts/SF/Regular.otf", 15));
  fonts.get("SF").put("Thin 15",       createFont("data/fonts/SF/Thin.otf",       15));

  fonts.get("SF").put("Black 20",      createFont("data/fonts/SF/Black.otf",      20));
  fonts.get("SF").put("Bold 20",         createFont("data/fonts/SF/Bold.otf",     20));
  fonts.get("SF").put("Heavy 20",       createFont("data/fonts/SF/Heavy.otf",     20));
  fonts.get("SF").put("Light 20",       createFont("data/fonts/SF/Light.otf",     20));
  fonts.get("SF").put("Medium 20",       createFont("data/fonts/SF/Medium.otf",   20));
  fonts.get("SF").put("Regular 20",       createFont("data/fonts/SF/Regular.otf", 20));
  fonts.get("SF").put("Thin 20",       createFont("data/fonts/SF/Thin.otf",       20));
  
  CALENDAR = Calendar.getInstance();
  CALENDAR.setTime(new Date());
  
  DAYS = new String[7];
  DAYS[0] = "Monday";
  DAYS[1] = "Tuesday";
  DAYS[2] = "Wednesday";
  DAYS[3] = "Thursday";
  DAYS[4] = "Friday";
  DAYS[5] = "Saturday";
  DAYS[6] = "Sunday";


  // Application components
  appController = new AppController(this);

  //println(MAX_INT);

}

public final void setMousePointerToARROW(){
  if(!(CURRENT_MOUSEPOINTER.equals("ARROW"))){
    cursor(MOUSEPOINTER_arrow, 1, 1);
    CURRENT_MOUSEPOINTER = "ARROW";
  }
}

public final void setMousePointerToTEXT(){
  if(!(CURRENT_MOUSEPOINTER.equals("TEXT"))){
    // cursor(MOUSEPOINTER_text, 6, 10);
    CURRENT_MOUSEPOINTER = "TEXT";
    cursor(TEXT);
  }
}

public void draw(){
  if(!completedStartup){
    //showMessageDialog(null, "This application is still being developed. Some functions might not work.", "Work in progress", WARNING_MESSAGE);
    setMousePointerToARROW();
    completedStartup = true;
  }else{

  if(w != width || h != height){
    if(width < minWidth){
      surface.setSize(minWidth, height);
    }
    if(height < minHeight){
      surface.setSize(width, minHeight);
    }

    // window resized
    appController.resize();
  }

  background(255);

  appController.show();

  w = width;
  h = height;

  }
}

public void mousePressed(){
  appController.mousePressed();
}

public void mouseReleased(){
  appController.mouseReleased();
}

public void mouseWheel(MouseEvent event){
  appController.mouseWheel(float(event.getCount()));
}

public void keyPressed(){
  if(keyCode == ALT){
    ALT_PRESSED = true;
  }else if(keyCode == CONTROL){
    CTRL_PRESSED = true;
  }
  appController.keyPressed(key, keyCode);
}

public void keyTyped(){
  appController.keyTyped(key);

  if(key == 's' && ALT_PRESSED && CTRL_PRESSED){
    save("screen-shot.png");
  }
}

public void keyReleased(){
  ALT_PRESSED = false;
  CTRL_PRESSED = false;
  appController.keyReleased();
}
