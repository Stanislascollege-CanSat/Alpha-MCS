// Elements.pde
// Processing 3.4
// Rens Dur (Project Bèta)

public final float defaultNewElementHeight = 30;
public final float defaultNewElementAdditiveHeight = 20;
public final color elementColor_Blue = color(56, 132, 255);

public enum ElementOrient {
  HORIZONTAL,
  VERTICAL
}



public interface Element_Interface {
  public void select();
  public void deselect();
  public void resize(float x, float y, float w, float h);
  public void show();
  public void drawSelectionOutline();
  public boolean mousePressIsWithinBorder();
  public void mousePressed();
  public void mouseReleased();
  public void mouseWheel(float count);
  public void mouseScrolled(float count);
  public void keyPressed(char k, int c);
  public void keyTyped(char k);
  public void keyReleased();

  public void clickEvent();
}

public class Element implements Element_Interface {
  public AppController appController;
  public ViewController viewController;
  public PVector pos;
  public PVector dim;
  public boolean selected;
  public boolean mouseHeld;
  public boolean disabled;
  public int layer;

  public Element(AppController a, ViewController v, float x, float y, float w, float h){
    this.appController = a;
    this.viewController = v;
    this.pos = new PVector(x, y);
    this.dim = new PVector(w, h);
    this.selected = false;
    this.mouseHeld = false;
    this.disabled = false;
    this.layer = 0;
  }

  public void select(){
    this.selected = true;
  }

  public void deselect(){
    this.selected = false;
  }

  public void disable(){
    this.disabled = true;
    this.deselect();
  }

  public void enable(){
    this.disabled = false;
  }

  public void drawSelectionOutline(){
    stroke(0, 0, 255, 100);
    strokeWeight(1);
    noFill();
    rectMode(CORNER);
    rect(this.pos.x-2, this.pos.y - this.dim.y/2 - 2, this.dim.x+4, this.dim.y+4);
  }

  public void resize(float x, float y, float w, float h){
    this.pos.set(x, y);
    this.dim.set(w, h);
  }

  public boolean mousePressIsWithinBorder(){
    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2){
      // User clicked element
      return true;
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
      }
    }
  }

  public void mouseReleased(){
    this.mouseHeld = false;
  }

  public void mouseWheel(float count){}

  public void mouseScrolled(float count){}

  public void keyPressed(char k, int c){

  }

  public void keyTyped(char k){

  }

  public void keyReleased(){

  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    fill(255);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y, this.dim.x, this.dim.y);

    if(this.selected){
      this.drawSelectionOutline();
    }
  }

  public void clickEvent(){}
}

//-----------------------------------------------------------------------------------------------------------------//

public class ButtonElement extends Element {
  public float defaultHeight;
  public String text;
  public color strokeColor;
  public boolean suggested;

  public ButtonElement(AppController a, ViewController v, float x, float y, float w, String t){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.text = t;
    this.strokeColor = color(56, 132, 255);
    this.suggested = false;
  }

  public ButtonElement(AppController a, ViewController v, float x, float y, float w, float h, String t){
    super(a, v, x, y, w, h);
    this.defaultHeight = h;
    this.text = t;
    this.strokeColor = color(56, 132, 255);
    this.suggested = false;
  }

  public void setStrokeColor(color c){
    this.strokeColor = c;
  }

  public void setSuggested(boolean s){
    this.suggested = s;
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
  }

  public void resize(float x, float y, float w, float h){
    this.pos.set(x, y);
    this.dim.set(w, h);
  }

  public void show(){
    if(this.disabled){
      stroke(150);
      fill(255);
    }else{
      stroke(this.strokeColor);
      if(this.mouseHeld){
        fill(200);
      }else{
        if(this.suggested){
          fill(this.strokeColor, 50);
        }else{
          fill(255);
        }
      }
    }
    strokeWeight(2);

    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    if(this.disabled){
      fill(150);
      if(this.selected){
        this.selected = false;
      }
    }else{
      fill(0);
    }

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Regular"));
    text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class UtilityButtonElement extends ButtonElement {
  public UtilityButtonElement(AppController a, ViewController v, float x, float y){
    super(a, v, x, y, 20, 20, "...");
  }

  public void resize(float x, float y){
    this.pos.set(x, y);
    this.dim.set(this.defaultHeight, this.defaultHeight);
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class TextButtonElement extends ButtonElement {
  public TextButtonElement(AppController a, ViewController v, float x, float y, float w, String t){
    super(a, v, x, y, w, 20, t);
  }

  public void show(){
    stroke(56, 132, 255);
    fill(56, 132, 255);

    if(this.mouseHeld){
      stroke(0);
      fill(0);
    }

    textAlign(CENTER);
    textFont(fonts.get("SF").get("Regular"));
    text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);

    //if(this.selected){
    //  stroke(0, 100);
    //  strokeWeight(1);
    //  line(this.pos.x + this.dim.x/2 - textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8, this.pos.x + this.dim.x/2 + textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8);
    //}
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class TickBoxElement extends Element {
  public boolean ticked;

  public TickBoxElement(AppController a, ViewController v, float x, float y, float size){
    super(a, v, x, y, size, size);

    this.ticked = false;

  }

  public TickBoxElement(AppController a, ViewController v, float x, float y){
    this(a, v, x, y, 20);
  }

  public void setValue(boolean t){
    this.ticked = t;
  }

  public boolean getValue(){
    return this.ticked;
  }

  public void resize(float x, float y, float size){
    this.pos.set(x, y);
    this.dim.set(size, size);
  }

  public void resize(float x, float y){
    this.resize(x, y, this.dim.x);
  }

  public void show(){
    stroke(56, 132, 255);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.ticked){
      stroke(0);
      strokeWeight(2);
      //line(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8);
      //line(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + 1.2*this.dim.x, this.pos.y - this.dim.y/2);
      //(this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8, this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4, this.pos.x + 1.2*this.dim.x, this.pos.y - this.dim.y/2 );
      noFill();
      beginShape();
        vertex(this.pos.x + this.dim.x/6, this.pos.y - this.dim.y/8);
        vertex(this.pos.x + this.dim.x/2, this.pos.y + this.dim.y/4);
        vertex(this.pos.x + 1.2*this.dim.x, this.pos.y - this.dim.y/2);
      endShape();
  }

    if(this.selected){
      this.drawSelectionOutline();
    }
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.ticked = !this.ticked;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class BooleanElement extends TickBoxElement {
  public BooleanElement(AppController a, ViewController v, float x, float y){
    super(a, v, x, y, 0);
    this.dim.x = 35;
    this.dim.y = 20;
  }

  public void show(){
    noStroke();
    fill((this.mouseHeld ? 150 : 200));
    ellipse(this.pos.x + this.dim.y/2, this.pos.y, this.dim.y, this.dim.y);
    ellipse(this.pos.x + this.dim.x - this.dim.y/2, this.pos.y, this.dim.y, this.dim.y);
    rectMode(CORNER);
    rect(this.pos.x + this.dim.y/2, this.pos.y - this.dim.y/2, this.dim.x - this.dim.y, this.dim.y);
    fill(elementColor_Blue);
    ellipse(this.pos.x + (this.ticked ? (this.dim.x - this.dim.y/2) : (this.dim.y/2)), this.pos.y, this.dim.y, this.dim.y);
  }
}




//-----------------------------------------------------------------------------------------------------------------//


public class TextElement extends ButtonElement {
  public int alignment;

  public TextElement(AppController a, ViewController v, float x, float y, float w, String t, int align){
    super(a, v, x, y, w, 20, t);
    this.alignment = align;
  }

  public void setText(String t){
    this.text = t;
  }

  public void show(){
    stroke(0);
    fill(0);

    textFont(fonts.get("SF").get("Regular"));

    if(this.alignment == CENTER){
      textAlign(CENTER);
      text(this.text, this.pos.x + this.dim.x/2, this.pos.y + 6);
    }else if(this.alignment == LEFT){
      textAlign(LEFT);
      text(this.text, this.pos.x + 10, this.pos.y + 6);
    }else if(this.alignment == RIGHT){
      textAlign(RIGHT);
      text(this.text, this.pos.x + this.dim.x - 10, this.pos.y + 6);
    }

    //if(this.selected){
    //  stroke(0, 100);
    //  strokeWeight(1);
    //  line(this.pos.x + this.dim.x/2 - textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8, this.pos.x + this.dim.x/2 + textWidth(this.text)/2, this.pos.y + this.dim.y/2 + 8);
    //}
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class LineInputElement extends Element {
  public float defaultHeight;
  public String text;
  public String displayText;
  public boolean backspaceStillPressed;
  public int backspaceCount;
  public PFont stdFont;
  public boolean contentsHidden;

  public LineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.text = "";
    this.displayText = "";
    this.backspaceCount = 0;
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.contentsHidden = false;
  }

  public LineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
    this.defaultHeight = h;
    this.text = "";
    this.displayText = "";
    this.backspaceCount = 0;
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.contentsHidden = false;
  }

  public void hideContents(boolean a){
    this.contentsHidden = a;
    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    while(textWidth(this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
    }
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
  }

  public void reset(){
    this.text = "";
    this.displayText = "";
  }

  public void setValue(String s){
    for(int i = 0; i < s.length(); ++i){
      this.keyTyped(s.charAt(i));
    }
  }

  public String getValue(){
    return this.text;
  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    text(this.displayText + (this.selected ? "_" : ""), this.pos.x + 10, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }

    //update text input
    if(this.backspaceStillPressed){
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }
  }

  public void enterEvent(){}

  public void keyPressed(char k, int c){
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }
  }

  public void keyReleased(){
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
  }

  public void backspaceTriggered(){
    if(ALT_PRESSED){
      this.reset();
    }else if(this.text.length() > 0){
      this.text = this.text.substring(0, this.text.length() - 1);
      if(this.contentsHidden){
        this.displayText = "";
        for(int i = 0; i < this.text.length(); ++i){
          this.displayText += "*";
        }
      }else{
        this.displayText = this.text;
      }

      textFont(this.stdFont);
      while(textWidth(this.displayText) > this.dim.x - 20){
        this.displayText = this.displayText.substring(1, this.displayText.length());
      }
    }
  }

  public void keyTyped(char k){
    this.text += k;
    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    while(textWidth(this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//
public class NumberLineInputElement extends LineInputElement {
  public boolean sign;
  public boolean signed;
  public boolean displaySign;
  public boolean numbersHidden;

  public NumberLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
    this.sign = true;
    this.signed = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public NumberLineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
    this.sign = true;
    this.signed = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public void setSigned(boolean s){
    this.signed = s;
  }

  public void reset(){
    this.text = "";
    this.displayText = "";
    this.sign = true;
    this.displaySign = true;
    this.numbersHidden = false;
  }

  public float getNumber(){
    return (this.sign ? 1.0 : -1.0) * float(this.text);
  }

  public void show(){

    if(this.getNumber() == 0 || this.text.isEmpty()){
      this.displaySign = false;
    }else{
      this.displaySign = true;
    }

    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    text((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText + (this.selected ? "_" : ""), this.pos.x + 10, this.pos.y + 6);

    if(this.selected){
      this.drawSelectionOutline();
    }

    //update text input
    if(this.backspaceStillPressed){
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }
  }

  public void backspaceTriggered(){
    if(ALT_PRESSED){
      this.reset();
    }else if(this.text.length() > 0){
      this.text = this.text.substring(0, this.text.length() - 1);
      if(this.contentsHidden){
        this.displayText = "";
        for(int i = 0; i < this.text.length(); ++i){
          this.displayText += "*";
        }
      }else{
        this.displayText = this.text;
      }

      textFont(this.stdFont);
      this.numbersHidden = false;
      while(textWidth((this.numbersHidden ? "<" : "") + (this.displaySign ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
        this.displayText = this.displayText.substring(1, this.displayText.length());
        this.numbersHidden = true;
      }
    }
  }

  public void keyTyped(char k){

    if(k >= '0' && k <= '9'){
      this.text += k;
    }else if(k == '.'){
      if(!this.text.contains(".")){
        if(this.text.length() == 0){
          this.text += "0";
        }
        this.text += ".";
      }
    }else if(k == '-' && this.signed){
      this.sign = !this.sign;
    }

    if(this.text.length() > 1){
      while(this.text.charAt(0) == '0' && this.text.length() > 1){
        if(!(this.text.charAt(1) == '.')){
          this.text = this.text.substring(1, this.text.length());
        }else{
          break;
        }
      }
    }


    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    this.numbersHidden = false;
    while(textWidth((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
      this.numbersHidden = true;
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//
public class IntegerLineInputElement extends NumberLineInputElement {
  public IntegerLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
  }

  public IntegerLineInputElement(AppController a, ViewController v, float x, float y, float w, float h){
    super(a, v, x, y, w, h);
  }

  public int getInteger(){
    return int((this.sign ? 1.0 : -1.0) * float(this.text));
  }

  public void keyTyped(char k){

    if(k >= '0' && k <= '9'){
      this.text += k;
    }else if(k == '-' && this.signed){
      this.sign = !this.sign;
    }

    if(this.text.length() > 1){
      while(this.text.charAt(0) == '0' && this.text.length() > 1){
        if(!(this.text.charAt(1) == '.')){
          this.text = this.text.substring(1, this.text.length());
        }else{
          break;
        }
      }
    }


    if(this.contentsHidden){
      this.displayText = "";
      for(int i = 0; i < this.text.length(); ++i){
        this.displayText += "*";
      }
    }else{
      this.displayText = this.text;
    }

    textFont(this.stdFont);
    this.numbersHidden = false;
    while(textWidth((this.numbersHidden ? "<" : "") + ((this.displaySign && this.signed) ? (this.sign ? "+" : "-") : "") + this.displayText) > this.dim.x - 20){
      this.displayText = this.displayText.substring(1, this.displayText.length());
      this.numbersHidden = true;
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class NewLineInputElement extends Element {
  public int cursorPos;
  public boolean cursorBlinkOn;
  public float cursorBlinkCount;
  public int beginTextDisplay, endTextDisplay;
  public ArrayList<Character> text;
  public String displayText;
  public String placeHolder;
  public boolean backspaceStillPressed;
  public int backspaceCount;
  public boolean arrowStillPressed;
  public int arrowCount;
  public int arrowFrequencyCount;
  public boolean arrowDirection;
  public PFont stdFont;



  public NewLineInputElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, defaultNewElementHeight);
    this.cursorPos = 0;
    this.cursorBlinkOn = true;
    this.cursorBlinkCount = 0;
    this.beginTextDisplay = 0;
    this.endTextDisplay = 0;
    this.text = new ArrayList<Character>();
    this.displayText = "";
    this.placeHolder = "Type here";
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
    this.arrowStillPressed = false;
    this.arrowCount = 0;
    this.arrowFrequencyCount = 0;
    this.arrowDirection = false;
    this.stdFont = fonts.get("SF").get("Light");
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, defaultNewElementHeight);
    this.arrangeString();
  }

  public void setPlaceholder(String s){
    this.placeHolder = s;
  }

  public String getValue(){
    String output = "";
    for(char i : this.text){
      output += i;
    }
    return output;
  }

  public void setValue(String v){
    this.text.clear();
    for(int i = 0; i < v.length(); ++i){
      this.text.add(v.charAt(i));
    }
    this.cursorPos = this.text.size();
    this.arrangeString();
    this.contentEdited();
  }

  public void reset(){
    this.cursorPos = 0;
    this.text.clear();
    this.arrangeString();
    this.contentEdited();
  }

  public void setTextFont(PFont f){
    this.stdFont = f;
  }

  public void show(){

    if(this.mousePressIsWithinBorder()){
      SET_MOUSEPOINTER_TEXT = true;
    }

    // stroke(0);
    // strokeWeight(1);
    // line(this.pos.x, this.pos.y + this.dim.y/2, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2);

    // SHOWING CONTENTS
    textFont(this.stdFont);
    textAlign(LEFT);

    if(this.displayText.length() > 0){
      stroke(0);
      fill(0);
      text(this.displayText, this.pos.x + 2, this.pos.y + 6);
    }else{
      stroke(180);
      fill(180);
      text(this.placeHolder, this.pos.x + 2, this.pos.y + 6);
    }

    if(this.selected && this.cursorBlinkOn){
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y - this.dim.y/2.3, this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y + this.dim.y/2.3);
    }






    if(this.selected){
      this.cursorBlinkCount += 1/frameRate;
      if(this.cursorBlinkCount > 0.5){
        this.cursorBlinkOn = !this.cursorBlinkOn;
        this.cursorBlinkCount = 0;
      }
    }else{
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
    }

    //update text input
    if(this.backspaceStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }

    if(this.arrowStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.arrowCount++;
      this.arrowFrequencyCount++;
    }
    if(this.arrowCount >= frameRate/2){
      if(this.arrowFrequencyCount > frameRate/10){
        if(this.arrowDirection == false){
          // LEFT
          if(this.cursorPos > 0){
            this.cursorPos--;
            if(this.cursorPos < this.beginTextDisplay){
              // cursor out of field <--
              this.beginTextDisplay = this.cursorPos;
              this.arrangeString();
            }

          }
        }else{
          // RIGHT
          if(this.cursorPos < this.text.size()){
            this.cursorPos++;
            this.arrangeString();
          }
        }
        this.arrowFrequencyCount = 0;
      }
    }
  }

  public void enterEvent(){}

  public void arrangeString(){
    // constructing displayText
    this.displayText = "";
    for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
      this.displayText += this.text.get(i);
    }

    textFont(this.stdFont);
    this.endTextDisplay = this.text.size();
    while(textWidth(this.displayText) > this.dim.x - 4){
      if(this.endTextDisplay > this.cursorPos){
        this.displayText = this.displayText.substring(0, this.displayText.length() - 1);
        this.endTextDisplay--;
      }else{
        // cursor left visible field
        this.beginTextDisplay++;
        this.displayText = "";
        for(int i = this.beginTextDisplay; i < this.cursorPos; ++i){
          this.displayText += this.text.get(i);
        }
      }
    }
  }

  public void contentEdited(){}

  public void keyPressed(char k, int c){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }else if(c == LEFT){
      if(this.cursorPos > 0){
        this.cursorPos--;
        if(this.cursorPos < this.beginTextDisplay){
          // cursor out of field <--
          this.beginTextDisplay = this.cursorPos;
          this.arrangeString();
        }

      }
      this.arrowStillPressed = true;
      this.arrowDirection = false;
    }else if(c == RIGHT){
      if(this.cursorPos < this.text.size()){
        this.cursorPos++;
        this.arrangeString();
      }
      this.arrowStillPressed = true;
      this.arrowDirection = true;
    }
    this.contentEdited();
  }

  public void keyReleased(){
    this.backspaceStillPressed = false;
    this.backspaceCount = 0;
    this.arrowStillPressed = false;
    this.arrowCount = 0;
    this.arrowFrequencyCount = 0;
  }

  public void backspaceTriggered(){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    // handle backspace event
    if(this.cursorPos > 0){
      this.text.remove(this.cursorPos - 1);
      this.cursorPos--;
      if(this.cursorPos < this.beginTextDisplay){
        // cursor out of field <--
        this.beginTextDisplay = this.cursorPos;
      }
      this.arrangeString();
      textFont(this.stdFont);
      if(textWidth(this.displayText) < this.dim.x - 4){
        this.beginTextDisplay = 0;
        this.arrangeString();
      }
    }
    this.contentEdited();
  }

  public void keyTyped(char k){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(this.cursorPos == this.text.size()){
      // cursor is at end of string
      this.text.add(k);
      this.cursorPos++;
      this.displayText = "";
      for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
        this.displayText += this.text.get(i);
      }
      textFont(this.stdFont);
      while(textWidth(this.displayText) > this.dim.x - 4){
        // cursor went out of visible field.
        this.beginTextDisplay++;
        this.displayText = "";
        for(int i = this.beginTextDisplay; i < this.text.size(); ++i){
          this.displayText += this.text.get(i);
        }
      }
    }else{
      // cursor is not at end of string
      this.text.add(this.cursorPos, k);
      this.cursorPos++;

      this.arrangeString();
    }
    this.contentEdited();
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();

        //this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2

        textFont(this.stdFont);
        int closest = 0;
        float smallestDistance = width;
        for(int i = 0; i <= this.displayText.length(); ++i){
          if(abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2)) < smallestDistance){
            closest = i;
            smallestDistance = abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2));
          }
        }
        this.cursorPos = this.beginTextDisplay + closest;
      }
    }
  }



  


}

//-----------------------------------------------------------------------------------------------------------------//

public class SmartSelectionElement extends NewLineInputElement {
  public ArrayList<String> options;
  public ArrayList<String> optionFilter;
  public String filterBuffer;
  public PFont boldFont;
  public float dimY_options;
  public boolean superStrict;

  public SmartSelectionElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w);
    this.options = new ArrayList<String>();
    // for(int i = 1400; i < 2400; ++i){
    //   this.options.add("/dev/cu.usbmodem" + str(i));
    // }
    this.optionFilter = new ArrayList<String>();
    this.filterBuffer = "";
    this.boldFont = fonts.get("SF").get("Bold");
    this.dimY_options = this.dim.y;
    this.superStrict = false;
  }

  public void setStrict(boolean s){
    this.superStrict = s;
  }

  public void addOption(String s){
    this.options.add(s);
  }

  public void select(){
    this.selected = true;
    this.layer = 1;
    this.contentEdited();
  }

  public void deselect(){
    this.selected = false;
    this.layer = 0;
    this.dimY_options = this.dim.y;
  }

  public void show(){

    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2){
      // user hovers element
      SET_MOUSEPOINTER_TEXT = true;
    }


    // stroke(0);
    // strokeWeight(1);
    // line(this.pos.x, this.pos.y + this.dim.y/2, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2);

    if(this.selected){
      translate(0, 0, 1);
      stroke(0);
      strokeWeight(1);
      fill(255);
      rectMode(CORNER);
      rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);
    }

    // SHOWING CONTENTS
    // if(this.optionFilter.size() > 0 || this.displayText.length() == 0){
    //   textFont(this.stdFont);
    // }else{
    //   textFont(this.boldFont);
    // }
    textFont(this.stdFont);
    textAlign(LEFT);

    if(this.selected && this.displayText.length() > 0 && this.optionFilter.size() > 0){
      fill(180);
      text(this.optionFilter.get(0), this.pos.x + 2, this.pos.y + 6);
    }

    if(this.displayText.length() > 0){
      stroke(0);
      fill(0);
      text(this.displayText, this.pos.x + 2, this.pos.y + 6);
    }else{
      stroke(180);
      fill(180);
      text(this.placeHolder, this.pos.x + 2, this.pos.y + 6);
    }

    if(this.selected && this.cursorBlinkOn){
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y - this.dim.y/2.3, this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2, this.pos.y + this.dim.y/2.3);
    }






    if(this.selected){
      this.cursorBlinkCount += 1/frameRate;
      if(this.cursorBlinkCount > 0.5){
        this.cursorBlinkOn = !this.cursorBlinkOn;
        this.cursorBlinkCount = 0;
      }
    }else{
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
    }

    //update text input
    if(this.backspaceStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.backspaceCount++;
    }

    if(this.backspaceCount >= frameRate/2){
      this.backspaceTriggered();
    }

    if(this.arrowStillPressed){
      this.cursorBlinkCount = 0;
      this.cursorBlinkOn = true;
      this.arrowCount++;
      this.arrowFrequencyCount++;
    }
    if(this.arrowCount >= frameRate/2){
      if(this.arrowFrequencyCount > frameRate/10){
        if(this.arrowDirection == false){
          // LEFT
          if(this.cursorPos > 0){
            this.cursorPos--;
            if(this.cursorPos < this.beginTextDisplay){
              // cursor out of field <--
              this.beginTextDisplay = this.cursorPos;
              this.arrangeString();
            }

          }
        }else{
          // RIGHT
          if(this.cursorPos < this.text.size()){
            this.cursorPos++;
            this.arrangeString();
          }
        }
        this.arrowFrequencyCount = 0;
      }
    }

    //
    // PART BELONGING TO SMART SELECTION ELEMENT
    //

    if(this.selected){
      float y = 0;
      stroke(0);
      strokeWeight(1);
      rectMode(CORNER);
      textFont(this.stdFont);
      textAlign(LEFT);
      for(String s : this.optionFilter){
        if(this.viewController.pos.y + this.pos.y + this.dim.y/2 + y <= height){
          noStroke();
          fill(255);
          rect(this.pos.x, this.pos.y + this.dim.y/2 + y, this.dim.x, defaultNewElementAdditiveHeight);

          stroke(0);
          line(this.pos.x, this.pos.y + this.dim.y/2 + y, this.pos.x, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight);
          line(this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight);

          fill(0);
          text(s, this.pos.x + 2, this.pos.y + this.dim.y/2 + y + defaultNewElementAdditiveHeight/2 + 6);
        }
        y += defaultNewElementAdditiveHeight;
      }
      line(this.pos.x, this.pos.y + this.dim.y/2 + y, this.pos.x + this.dim.x, this.pos.y + this.dim.y/2 + y);
      this.dimY_options = this.dim.y + y;
      translate(0, 0, -1);
    }else{
      this.dimY_options = this.dim.y;
    }





  }

  public void contentEdited(){
    //if(this.text.size() > 0){
      // user has made request for suggestion
      String userInput = "";
      for(char c : this.text){
        userInput += c;
      }

      this.optionFilter.clear();
      for(String s : this.options){
        if(s.length() >= userInput.length()){
          this.optionFilter.add(s);
        }
      }
      //printArray(this.optionFilter);
      if(userInput.length() > 0){
        for(int i = this.optionFilter.size() - 1; i >= 0; --i){
          if(!(userInput.equals(this.optionFilter.get(i).substring(0, userInput.length())))){
            this.optionFilter.remove(i);
          }
        }
      }

      if(this.superStrict){
        if(this.optionFilter.size() == 0){
          this.cursorPos = this.text.size();
          this.backspaceTriggered();
        }
      }

      //printArray(this.optionFilter);
    //}
  }

  public void keyPressed(char k, int c){
    this.cursorBlinkCount = 0;
    this.cursorBlinkOn = true;
    if(k == BACKSPACE){
      this.backspaceTriggered();
      this.backspaceStillPressed = true;
    }else if(k == ENTER || k == RETURN){
      this.enterEvent();
    }else if(c == LEFT){
      if(this.cursorPos > 0){
        this.cursorPos--;
        if(this.cursorPos < this.beginTextDisplay){
          // cursor out of field <--
          this.beginTextDisplay = this.cursorPos;
          this.arrangeString();
        }

      }
      this.arrowStillPressed = true;
      this.arrowDirection = false;
    }else if(c == RIGHT){
      if(this.cursorPos < this.text.size()){
        this.cursorPos++;
        this.arrangeString();
      }else if(this.cursorPos == this.text.size() && this.optionFilter.size() > 0 && this.text.size() > 0){
        this.text.clear();
        for(int j = 0; j < this.optionFilter.get(0).length(); ++j){
          this.text.add(this.optionFilter.get(0).charAt(j));
        }
        this.cursorPos = this.text.size();
        this.arrangeString();
        this.contentEdited();
      }
      this.arrowStillPressed = true;
      this.arrowDirection = true;
    }
    this.contentEdited();
  }

  public boolean mousePressIsWithinBorder(){
    if(mouseX >= this.viewController.pos.x + this.pos.x &&
      mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x &&
      mouseY >= this.viewController.pos.y + this.pos.y - this.dim.y/2 &&
      mouseY <= this.viewController.pos.y + this.pos.y - this.dim.y/2 + this.dimY_options){
      // User clicked element
      return true;
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled && ( mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2 )){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();

        //this.pos.x + textWidth(this.displayText.substring(0, this.cursorPos - this.beginTextDisplay)) + 2

        textFont(this.stdFont);
        int closest = 0;
        float smallestDistance = width;
        for(int i = 0; i <= this.displayText.length(); ++i){
          if(abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2)) < smallestDistance){
            closest = i;
            smallestDistance = abs(mouseX - (this.viewController.pos.x + this.pos.x + textWidth(this.displayText.substring(0, i)) + 2));
          }
        }
        this.cursorPos = this.beginTextDisplay + closest;
      }else if(!this.disabled){
        // option pressed
        int selected = 0;
        for(int i = 0; i < this.optionFilter.size(); ++i){
          if(mouseY >= this.viewController.pos.y + this.pos.y + this.dim.y/2 + defaultNewElementAdditiveHeight * i &&
             mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y/2 + defaultNewElementAdditiveHeight * (i+1)){
            selected = i;
            this.text.clear();
            for(int j = 0; j < this.optionFilter.get(i).length(); ++j){
              this.text.add(this.optionFilter.get(i).charAt(j));
            }
            this.cursorPos = this.text.size();
            this.arrangeString();
            this.contentEdited();
            this.deselect();
            break;
          }
        }
      }
    }
  }





}




//-----------------------------------------------------------------------------------------------------------------//
public class SelectionElement extends Element {
  public ArrayList<String> options;
  public int sel;
  public float defaultHeight;
  public boolean opened;
  public PFont stdFont;

  public SelectionElement(AppController a, ViewController v, float x, float y, String[] list){
    super(a, v, x, y, 100, 30);
    this.defaultHeight = 30;
    this.options = new ArrayList<String>();
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.sel = -1;
    textFont(this.stdFont);
    for(String s : list){
      this.options.add(s);
      if(textWidth(s) + 35 > this.dim.x - 20){
        this.dim.x = textWidth(s) + 35;
      }
    }
  }

  public SelectionElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
    this.defaultHeight = 30;
    this.options = new ArrayList<String>();
    this.stdFont = fonts.get("PlexMono").get("Regular");
    this.sel = -1;
  }

  public void select(){
    this.selected = true;
    this.layer = 1;
  }

  public void deselect(){
    this.selected = false;
    this.opened = false;
    this.layer = 0;
  }

  public String getValue(){
    if(this.sel >= 0 && this.sel < this.options.size()){
      return this.options.get(this.sel);
    }
    return "";
  }

  public void resize(float x, float y, float w){
    this.pos.set(x, y);
    this.dim.set(w, this.defaultHeight);
    textFont(this.stdFont);
    for(String s : this.options){
      if(textWidth(s) + 35 > this.dim.x - 20){
        this.dim.x = textWidth(s) + 35;
      }
    }
  }

  public void addOption(String s){
    textFont(this.stdFont);
    this.options.add(s);
    if((textWidth(s) + 35) > (this.dim.x - 20)){
      this.dim.x = textWidth(s) + 35;
    }
  }

  public void removeOption(String s){
    for(int i = 0; i < this.options.size(); ++i){
      if(this.options.get(i) == s){
        this.options.remove(i);
        break;
      }
    }
  }

  public boolean mousePressIsWithinBorder(){
    if(this.opened){
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.options.size() * this.dim.y + this.dim.y/2){
        // User clicked element or an option
        return true;
      }
      return false;
    }else{
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y/2){
        // User clicked element
        return true;
      }
      return false;
    }
  }

  public void mousePressed(){
    if(this.opened){
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.options.size() * this.dim.y + this.dim.y/2){
        // User clicked element or an option
        this.clickEvent();
        this.selectOptionClickEvent();
        this.mouseHeld = true;
        this.select();
      }else{
        this.deselect();
        this.opened = false;
      }
    }else{
      if(mouseX >= this.pos.x && mouseX <= this.pos.x + this.dim.x && mouseY >= this.pos.y - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y/2){
        // User clicked element
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
        this.opened = true;
      }
    }
  }

  private void selectOptionClickEvent(){
    for(int i = 0; i < this.options.size(); ++i){
      if(mouseY >= this.pos.y + this.dim.y * (i+1) - this.dim.y/2 && mouseY <= this.pos.y + this.dim.y * (i+1) + this.dim.y/2){
        // An option has been pressed!
        this.deselect();
        this.opened = false;
        this.sel = i;
        break;
      }
    }
  }

  public void show(){
    stroke(0);
    strokeWeight(2);
    if(this.mouseHeld){
      fill(200);
    }else{
      fill(255);
    }
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    strokeWeight(1);
    fill(0);

    textAlign(LEFT);
    textFont(this.stdFont);
    if(this.sel >= 0 && this.sel < this.options.size()){
      text(this.options.get(this.sel), this.pos.x + 10, this.pos.y + 6);
    }else{
      this.sel = -1;
    }

    if(this.opened){
      translate(0, 0, 2);

      this.sel = -1;

      stroke(0);
      strokeWeight(1);
      rectMode(CORNER);
      textAlign(LEFT);
      textFont(this.stdFont);
      for(int i = 0; i < this.options.size(); ++i){
        fill(255);
        rect(this.pos.x, this.pos.y + this.dim.y * (i+1) - this.dim.y/2, this.dim.x, this.dim.y);
        fill(0);
        text(this.options.get(i), this.pos.x + 10, this.pos.y + this.dim.y * (i+1) + 6);
      }

      translate(0, 0, -2);
    }else{
      stroke(0);
      strokeWeight(1);
      line(this.pos.x + this.dim.x - 10 - 5, this.pos.y + 3, this.pos.x + this.dim.x - 10 - 5 - 5, this.pos.y - 3);
      line(this.pos.x + this.dim.x - 10 - 5, this.pos.y + 3, this.pos.x + this.dim.x - 10, this.pos.y - 3);
    }
  }


}

//-----------------------------------------------------------------------------------------------------------------//

public class StepperCounterElement extends Element {

  public StepperCounterElement(AppController a, ViewController v, float x, float y, float w){
    super(a, v, x, y, w, 30);
  }

}

//-----------------------------------------------------------------------------------------------------------------//

public class DateSelectionElement extends Element {
  public float defaultHeight;
  public String parts;
  public ArrayList<Element> elements;
  public SelectionElement day;
  public SelectionElement month;
  public SelectionElement year;

  public DateSelectionElement(AppController a, ViewController v, float x, float y, String parts){
    super(a, v, x, y, 200, 30);
    this.defaultHeight = 30;
    this.parts = parts;
    this.elements = new ArrayList<Element>();
    this.day = new SelectionElement(this.appController, this.viewController, this.pos.x, this.pos.y, 50);
    for(int i = 1; i <= 31; ++i){
      this.day.addOption(str(i));
    }

    this.elements.add(this.day);
  }

  public void mousePressed(){
    for(Element e : this.elements){
      e.deselect();
      e.mousePressed();
    }
  }

  public void mouseReleased(){
    for(Element e : this.elements){
      e.mouseReleased();
    }
  }

  public void show(){
    for(Element e : this.elements){
      e.show();
    }
  }
}

//-----------------------------------------------------------------------------------------------------------------//

public class SpinWheelLoadingElement extends Element {
  public float counter;
  public float step;
  public int amountOfLines;
  public boolean shown;

  public SpinWheelLoadingElement(AppController a, ViewController v, float x, float y, float s){
    super(a, v, x, y, s, s);
    this.counter = 0;
    this.step = 0;
    this.amountOfLines = int(s);
    this.shown = true;
  }

  public SpinWheelLoadingElement(AppController a, ViewController v, float x, float y, float s, int n){
    super(a, v, x, y, s, s);
    this.counter = 0;
    this.step = 0;
    this.amountOfLines = n;
    this.shown = true;
  }

  public void resize(float x, float y, float s){
    this.pos.set(x, y);
    this.dim.set(s, s);
  }

  public void toggleHide(){
    this.shown = !this.shown;
  }

  public void hide(boolean f){
    this.shown = !f;
  }

  public void show(){
    if(this.shown){
      this.counter += 30/frameRate;
      if(this.counter >= 1){
        this.counter = 0;
        this.step += TWO_PI/float(this.amountOfLines);
        if(this.step >= TWO_PI){
          this.step = 0;
          this.counter += 30/frameRate;
        }
      }

      translate(this.pos.x, this.pos.y);

      rotate(this.step);

      strokeWeight(this.dim.x/5);

      for(int i = 0; i < this.amountOfLines; ++i){
        stroke(255 - map(float(i), 0, float(this.amountOfLines)-1, 0, 255));
        line(
          this.dim.x/3 * cos(i/float(this.amountOfLines) * TWO_PI),
          this.dim.x/3 * sin(i/float(this.amountOfLines) * TWO_PI),
          this.dim.x * cos(i/float(this.amountOfLines) * TWO_PI),
          this.dim.x * sin(i/float(this.amountOfLines) * TWO_PI)
        );
      }


      //stroke(200);
      //strokeWeight(2);
      //fill(200);
      //for(float i = 0; i <= 2*PI; i += (2/float(this.amountOfLines))*PI){
      //  line(
      //    this.dim.x/3*cos(i),
      //    this.dim.x/3*sin(i),
      //    this.dim.x*cos(i),
      //    this.dim.x*sin(i)
      //  );
      //}

      //stroke(0);
      //fill(0);
      //line(
      //  this.dim.x/3*cos(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x/3*sin(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x*cos(this.step * ((2/float(this.amountOfLines))*PI)),
      //  this.dim.x*sin(this.step * ((2/float(this.amountOfLines))*PI))
      //);

      //if(this.step > 0){
      //  stroke(100);
      //  fill(100);
      //  line(
      //    this.dim.x/3*cos((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x/3*sin((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*cos((this.step-1) * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*sin((this.step-1) * ((2/float(this.amountOfLines))*PI))
      //  );
      //}else{
      //  stroke(100);
      //  fill(100);
      //  line(
      //    this.dim.x/3*cos(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x/3*sin(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*cos(this.amountOfLines * ((2/float(this.amountOfLines))*PI)),
      //    this.dim.x*sin(this.amountOfLines * ((2/float(this.amountOfLines))*PI))
      //  );
      //}

      rotate(-this.step);

      translate(-this.pos.x, -this.pos.y);
    }
  }
}


//-----------------------------------------------------------------------------------------------------------------//

public class VerticalScrollElement extends Element {
  public float min; // Minimum rangeMin
  public float max; // Maximum rangeMax
  public float rangeMin; // Current rangeMin
  public float rangeMax; // Current rangeMax
  public boolean isDragged;
  public float dragHeight;


  public VerticalScrollElement(AppController a, ViewController v, float x, float y, float h, float min, float max){
    super(a, v, x, y, 10, h);

    if(max > min){
      this.min = min;
      this.max = max;
      this.rangeMin = this.min;
      this.rangeMax = this.max;
    }

    this.isDragged = false;
    this.dragHeight = this.rangeMax - this.rangeMin;
  }

  public void resize(float x, float y, float h){
	  this.pos.set(x, y);
	  this.dim.y = h;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.isDragged = true;
      this.dragHeight = this.rangeMax - this.rangeMin;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }

  public void mouseReleased(){
    this.isDragged = false;
    this.mouseHeld = false;
  }

  public void setExtremes(float min, float max){
    if(max > min){
      this.min = min;
      this.max = max;
    }
  }

  public void setCurrentPosition(float minVisible, float maxVisible){
    if(maxVisible > minVisible){
      this.rangeMin = minVisible;
      this.rangeMax = maxVisible;
    }
  }

  public float getMinimumValue(){
    return this.rangeMin;
  }
  
  public float getMaximumValue() {
    return this.rangeMax;
  }

  public boolean isAtTop(){
    return (this.rangeMin == this.min);
  }

  public boolean isAtBottom(){
    return (this.rangeMax == this.max);
  }

  public void addScroll(float count){
    this.dragHeight = this.rangeMax - this.rangeMin;
    this.rangeMin += count;
    this.rangeMax = this.rangeMin + this.dragHeight;
    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }
  }

  public void show(){
    noStroke();
    fill(230);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.mouseHeld){
      stroke(0);
      strokeWeight(1);
      fill(0);
    }else{
      noStroke();
      fill(200);
    }
    rect(this.pos.x, this.pos.y - this.dim.y/2 + (this.rangeMin/(this.max - this.min))*this.dim.y, this.dim.x, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.y);

    if(this.isDragged){
      this.rangeMin = this.min + ((mouseY - (this.viewController.pos.y + this.pos.y - this.dim.y/2))/this.dim.y)*(this.max - this.min) - this.dragHeight/2;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }

    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }

  }


}


//-----------------------------------------------------------------------------------------------------------------//

public class HorizontalScrollElement extends Element {
  public float min; // Minimum rangeMin
  public float max; // Maximum rangeMax
  public float rangeMin; // Current rangeMin
  public float rangeMax; // Current rangeMax
  public boolean isDragged;
  public float dragHeight;


  public HorizontalScrollElement(AppController a, ViewController v, float x, float y, float w, float min, float max){
    super(a, v, x, y, w, 10);

    if(max > min){
      this.min = min;
      this.max = max;
      this.rangeMin = this.min;
      this.rangeMax = this.max;
    }

    this.isDragged = false;
    this.dragHeight = this.rangeMax - this.rangeMin;
  }

  public void resize(float x, float y, float w){
	  this.pos.set(x, y);
	  this.dim.x = w;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      this.isDragged = true;
      this.dragHeight = this.rangeMax - this.rangeMin;
      this.clickEvent();
      this.mouseHeld = true;
      this.select();
    }
  }

  public void mouseReleased(){
    this.isDragged = false;
    this.mouseHeld = false;
  }

  public void setExtremes(float min, float max){
    if(max > min){
      this.min = min;
      this.max = max;
    }
  }

  public void setCurrentPosition(float minVisible, float maxVisible){
    if(maxVisible > minVisible){
      this.rangeMin = minVisible;
      this.rangeMax = maxVisible;
    }
  }

  public float getMinimumValue(){
    return this.rangeMin;
  }
  
  public float getMaximumValue() {
	  return this.rangeMax;
  }

  public void addScroll(float count){
    this.dragHeight = this.rangeMax - this.rangeMin;
    this.rangeMin += count;
    this.rangeMax = this.rangeMin + this.dragHeight;
    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }
  }

  public void show(){
    noStroke();
    fill(230);
    rectMode(CORNER);
    rect(this.pos.x, this.pos.y - this.dim.y/2, this.dim.x, this.dim.y);

    if(this.mouseHeld){
      stroke(0);
      strokeWeight(1);
      fill(0);
    }else{
      noStroke();
      fill(200);
    }
    //rect(this.pos.x, this.pos.y - this.dim.y/2 + (this.rangeMin/(this.max - this.min))*this.dim.y, this.dim.x, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.y);
    rect(this.pos.x + (this.rangeMin/(this.max - this.min))*this.dim.x, this.pos.y - this.dim.y/2, ((this.rangeMax - this.rangeMin)/(this.max - this.min))*this.dim.x, this.dim.y);

    if(this.isDragged){
      this.rangeMin = this.min + ((mouseX - (this.viewController.pos.x + this.pos.x))/this.dim.x)*(this.max - this.min) - this.dragHeight/2;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }

    if(this.rangeMin < this.min){
      this.rangeMin = this.min;
      this.rangeMax = this.rangeMin + this.dragHeight;
    }else if(this.rangeMax > this.max){
      this.rangeMax = this.max;
      this.rangeMin = this.rangeMax - this.dragHeight;
    }

  }


}


// ------------------------------------------------------------------------------- //


public class SliderElement extends Element {
	public boolean vertical;
	
	public Range range;
	public float currentPos;
	
	public SliderElement(AppController a, ViewController v, float x, float y, float size, String orient, Range r) {
		super(a, v, x, y, 0, 0);
		
		this.vertical = false;
		if(orient == "vertical") {
			this.vertical = true;
		}
		
		if(this.vertical) {
			this.resize(x, y, 10, size);
		}else {
			this.resize(x, y, size, 10);
		}
		
		// Determined the orientation of the slider
		
		this.range = r;
		
		this.currentPos = this.range.min;
	}
	
	
}



// -------------------------------------------------------------------------------------------------------------------- //

public class SmartSliderElement extends Element {
  public ElementOrient orientation;
  public Range range;
  public float lowerValue;
  public float upperValue;
  public float ballSize;
  public boolean grub;
  public boolean leftGrub;

  public SmartSliderElement(AppController a, ViewController v, float x, float y, ElementOrient o, float size, float min, float max){
    super(a, v, x, y, (o == ElementOrient.HORIZONTAL ? size : 10), (o == ElementOrient.HORIZONTAL ? 10 : size));
    this.orientation = o;
    this.range = new Range(min, max);
    this.lowerValue = this.range.min;
    this.upperValue = this.range.max;
    this.ballSize = 20;
  }

  public Range getValue(){
    return new Range(this.lowerValue, this.upperValue);
  }

  public void setValue(float min, float max){
    if((max > min) && (min >= this.range.min) && (max <= this.range.max)){
      this.lowerValue = min;
      this.upperValue = max;
    }
  }

  public void resize(float x, float y, float size){
    this.pos.set(x, y);
    if(this.orientation == ElementOrient.HORIZONTAL){
      this.dim.x = size;
    }else if(this.orientation == ElementOrient.VERTICAL){
      this.dim.y = size;
    }
  }

  public void show(){
    if(this.orientation == ElementOrient.HORIZONTAL){
      stroke(0);
      strokeWeight(2);
      line(this.pos.x, this.pos.y, this.pos.x + this.dim.x, this.pos.y);
      noStroke();
      fill(elementColor_Blue);
      translate(0, 0, 1);
      ellipse(this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y, this.ballSize, this.ballSize);
      ellipse(this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y, this.ballSize, this.ballSize);
      translate(0, 0, -1);

      if(this.grub){
        textFont(fonts.get("SF").get("Regular 6"));
        textAlign(CENTER);
        fill(0);
        if(this.leftGrub){
          this.lowerValue = map(mouseX - this.viewController.pos.x - this.pos.x, 0, this.dim.x, this.range.min, this.range.max);
          if(this.lowerValue < this.range.min){
            this.lowerValue = this.range.min;
          }
          if(this.lowerValue >= this.upperValue - (this.range.max - this.range.min)/1000){
            this.lowerValue = this.upperValue - (this.range.max - this.range.min)/1000;
          }
        }else{
          this.upperValue = map(mouseX - this.viewController.pos.x - this.pos.x, 0, this.dim.x, this.range.min, this.range.max);
          if(this.upperValue > this.range.max){
            this.upperValue = this.range.max;
          }
          if(this.upperValue <= this.lowerValue + (this.range.max - this.range.min)/1000){
            this.upperValue = this.lowerValue + (this.range.max - this.range.min)/1000;
          }
        }
        text(this.lowerValue, this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y + this.ballSize);
        text(this.upperValue, this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x), this.pos.y + this.ballSize);
      }


    }else if(this.orientation == ElementOrient.VERTICAL){
      stroke(0);
      strokeWeight(2);
      line(this.pos.x, this.pos.y, this.pos.x, this.pos.y + this.dim.y);
      noStroke();
      fill(elementColor_Blue);
      ellipse(this.pos.x, this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y), this.ballSize, this.ballSize);
      ellipse(this.pos.x, this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y), this.ballSize, this.ballSize);

      if(this.grub){
        textFont(fonts.get("SF").get("Regular 6"));
        textAlign(LEFT);
        fill(0);
        if(this.leftGrub){
          this.lowerValue = map(mouseY - this.viewController.pos.y - this.pos.y, 0, this.dim.y, this.range.min, this.range.max);
          if(this.lowerValue < this.range.min){
            this.lowerValue = this.range.min;
          }
          if(this.lowerValue >= this.upperValue - (this.range.max - this.range.min)/1000){
            this.lowerValue = this.upperValue - (this.range.max - this.range.min)/1000;
          }
        }else{
          this.upperValue = map(mouseY - this.viewController.pos.y - this.pos.y, 0, this.dim.y, this.range.min, this.range.max);
          if(this.upperValue > this.range.max){
            this.upperValue = this.range.max;
          }
          if(this.upperValue <= this.lowerValue + (this.range.max - this.range.min)/1000){
            this.upperValue = this.lowerValue + (this.range.max - this.range.min)/1000;
          }
        }
        text(this.lowerValue, this.pos.x + this.ballSize, this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y));
        text(this.upperValue, this.pos.x + this.ballSize, this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y));
      }
    }
  }

  public boolean mousePressIsWithinBorder(){
    if(this.orientation == ElementOrient.HORIZONTAL){
      if(mouseX >= this.viewController.pos.x + this.pos.x - this.ballSize/2 &&
        mouseX <= this.viewController.pos.x + this.pos.x + this.dim.x + this.ballSize/2 &&
        mouseY >= this.viewController.pos.y + this.pos.y - this.ballSize/2 &&
        mouseY <= this.viewController.pos.y + this.pos.y + this.ballSize/2){
        // User clicked element
        return true;
      }
    }else if(this.orientation == ElementOrient.VERTICAL){
      if(mouseX >= this.viewController.pos.x + this.pos.x - this.ballSize/2 &&
        mouseX <= this.viewController.pos.x + this.pos.x + this.ballSize/2 &&
        mouseY >= this.viewController.pos.y + this.pos.y - this.ballSize/2 &&
        mouseY <= this.viewController.pos.y + this.pos.y + this.dim.y + this.ballSize/2){
        // User clicked element
        return true;
      }
    }
    return false;
  }

  public void mousePressed(){
    if(this.mousePressIsWithinBorder()){
      // User clicked element
      if(!this.disabled){
        this.clickEvent();
        this.mouseHeld = true;
        this.select();
        if(this.orientation == ElementOrient.HORIZONTAL){
          if(mouseX >= this.viewController.pos.x + this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x) - this.ballSize/2 &&
              mouseX <= this.viewController.pos.x + this.pos.x + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.x) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = true;
          }else if(mouseX >= this.viewController.pos.x + this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x) - this.ballSize/2 &&
              mouseX <= this.viewController.pos.x + this.pos.x + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.x) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = false;
          }
        }else if(this.orientation == ElementOrient.VERTICAL){
          if(mouseY >= this.viewController.pos.y + this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y) - this.ballSize/2 &&
              mouseY <= this.viewController.pos.y + this.pos.y + map(this.lowerValue, this.range.min, this.range.max, 0, this.dim.y) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = true;
          }else if(mouseY >= this.viewController.pos.y + this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y) - this.ballSize/2 &&
              mouseY <= this.viewController.pos.y + this.pos.y + map(this.upperValue, this.range.min, this.range.max, 0, this.dim.y) + this.ballSize/2){
            this.grub = true;
            this.leftGrub = false;
          }
        }
      }
    }
  }

  public void mouseReleased(){
    this.mouseHeld = false;
    this.grub = false;
  }


}
