class DrawSite {
  Site site;
  ArrayList<DrawPage> drawPages = new ArrayList<DrawPage>();
  PVector position = new PVector(0,0);

  public DrawSite(Site site, PVector position) {
    this.position = position.get();
    this.site = site;
    for (Page page : site.pages) {
      // regne ut lengde her, slik at det er mulig å vite hvor position 
      // for neste drawPage skal være.

      float positionOffset;
      positionOffset = map(page.visitCount, 0, 1004*2, 0, 1440*4);
      //positionOffset = page.visitCount;
      //position.add(new PVector(positionOffset/2, 0));
      drawPages.add(new DrawPage(this, page, position, positionOffset - 1));
      position.add(new PVector(positionOffset, 0));
    }
    draw();
  }

  void drawSiteMarker() {


    strokeWeight(2);
    line(0 + rectSize, position.y + rectSize - 5, 0 + rectSize, position.y + rectSize);
    line(0 + rectSize, position.y + rectSize, map(site.count, 0, 1004*2, 0, 1440*4) + rectSize, position.y + rectSize);
    line(map(site.count, 0, 1004*2, 0, 1440*4) + rectSize, position.y + rectSize - 5, map(site.count, 0, 1004*2, 0, 1440*4) + rectSize, position.y + rectSize);

    line(map(site.count, 0, 1004*2, 0, 1440*4)/2 + rectSize, position.y + rectSize + 5, map(site.count, 0, 1004*2, 0, 1440*4)/2 + rectSize, position.y + rectSize);
    PVector ba = new PVector(map(site.count, 0, 1004*2, 0, 1440*4)/2 + rectSize, position.y + rectSize + 7);
    noFill();
    bezier(ba.x, ba.y, ba.x + 100, ba.y + 300, bpos.x - 250, bpos.y + 50, bpos.x, bpos.y + 50);
    line(bpos.x, bpos.y, bpos.x, bpos.y + 100);
    stroke(0);
    fill(0);
    if (site.siteName != null) {
      //String boxtext = "Title: \t\t\t\t\t\t\t\t\t " + sb.toString() + "\n";
      setBox("Site: \t\t\t\t\t\t\t\t\t\t " + site.siteName + "\nSite visits:\t\t " + site.count);
    }
    

    fill(fillcolor);
  }
  void draw() {
    //position.add(new PVector(0, rectSize+30));
    //position.x = 20;
    for (DrawPage dp : drawPages) {
      fill(fillcolor);
      dp.draw();
    }
  }
}