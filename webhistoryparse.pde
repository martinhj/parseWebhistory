import java.util.Collections;

Parse p = new Parse(1);
DrawSites ds;
PVector bpos;
PVector bposcor;

color fillcolor = color(234, 69, 62, 160);
PFont font;

int rectSize = 15;
int counter = 0;

boolean box = false;
String boxText = "";
String linkText = "";
String boxSiteText = "";
PVector boxPos;

void setup() {
  //size(3200, 2000);
  size(800, 500);
  background(79, 72, 72);
  smooth(8);
  noStroke();
  fill(fillcolor);
  //fill(234, 69, 62);

  font = createFont("Futura", 11);
  textFont(font);
  ds = new DrawSites();
}

void draw() {
  background(79, 72, 72);
  //ellipse(width/2, height/2, 150, 150);

  bpos = new PVector(width/4, height/4);
  bposcor = bpos.get();
  bposcor.add(new PVector(25, 25));
  ds.draw();

}
void mouseMoved() {
  for (DrawSite d : ds.drawsites) {
    for (DrawPage dp : d.drawPages) {
      dp.mouseDraw();
    }
  }

}

void mousePressed() {
  if (box) {
    println("opening: " + linkText);
    link(linkText);
  }
}
  void setBox(String text, String ltext, PVector pos) {
    box = true;
    boxText = text;
    linkText = ltext;
    boxPos = pos;
  }


  void setBox(String text) {
    boxSiteText = text;
  }
