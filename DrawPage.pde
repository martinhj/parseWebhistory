class DrawPage {
  DrawSite drawsite;
  PVector position;
  float length;
  Page page;
  int sizeAdd = 0;
  public DrawPage(DrawSite drawsite, Page page, PVector position, float length) {
    this.position = position.get();
    this.page = page;
    this.length = length;
    this.drawsite = drawsite;
    /*
    System.out.print(position + " : " + length);
    System.out.println("\t\t\t" + page.visitCount);
    */
    draw();
  }
  void draw() {
    rect(position.x - sizeAdd, position.y - sizeAdd, length + sizeAdd * 2, rectSize + sizeAdd * 2);
    if (sizeAdd > 0) {

      bpos.y = map(position.y, 0, 800, 0, 600);
      rect(position.x - sizeAdd, position.y - sizeAdd, length + sizeAdd * 2, rectSize + sizeAdd * 2);
      stroke(234, 69, 62, 90);
      strokeWeight(4);


      noFill();
      bezier(position.x, position.y, position.x + 10, position.y + 10, bpos.x - 500, bpos.y, bpos.x, bpos.y + 150);
      line(bpos.x, bpos.y + 100, bpos.x, bpos.y + 200);
      stroke(0);
      fill(0);

      StringBuilder sb = new StringBuilder(page.title);

      for (int i = 1; i < sb.length(); i++) {
        if (i % 78 == 0) sb.insert(i, "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
      }
      String link = "";
      String boxtext = "Title: \t\t\t\t\t\t\t\t\t " + sb.toString() + "\n";
      boxtext += "Page visits:\t" + page.visitCount + "\n";
      sb = new StringBuilder(page.url);
      link = sb.toString();
      for (int i = 1; i < sb.length(); i++) {
        if (i % 78 == 0) sb.insert(i, "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
      }
      if (sb.length() > 78*2) {
        sb = new StringBuilder(sb.substring(0, 78*2));
        sb.append("...");
      }
      boxtext += "URL: \t\t\t\t\t\t\t\t\t\t " + sb.toString();
      setBox(boxtext, link, bpos);
      //text(page.visitCount, bpos.x + 15, bpos.y + 45 + 100);
      //text(page.url, bpos.x + 15, bpos.y + 60 + 100);
      fill(234, 69, 62, 90);
      drawsite.drawSiteMarker();
      noStroke();
    }
  }

  void mouseDraw() {
    //System.out.print("moving: ");
    if (mouseX >= position.x && mouseX <= position.x + length + 1 &&
        mouseY >= position.y/* - rectSize / 2*/ && mouseY <= position.y + rectSize /* / 2*/) {

      sizeAdd = 9;
      System.out.println(position.x + " : " + position.y);
      System.out.println(page.site + ": " + page.title);
      System.out.println(page.lastVisitDate);
      System.out.println(page.url);
    }
    else {
      sizeAdd = 0;
    }
  }
}
