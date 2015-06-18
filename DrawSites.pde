class DrawSites {
  PVector metaPosition = new PVector(0,0);
  ArrayList<Site> sites = new ArrayList<Site>(p.sites.values());
  ArrayList<DrawSite> drawsites = new ArrayList<DrawSite>();
  int rectHeight = rectSize;
  Site site;


  DrawSites() {
    Collections.sort(sites);
    translate(width/2, height/2);
    //rotate(-2 * PI/16);
    for (int i = sites.size() - 1 ; i >= sites.size() - (109); i--) {
    //for (int i = sites.size() - 28 ; i >= sites.size() - 29; i--) {
      //rotate(0);/
      metaPosition.add(new PVector(rectSize, rectHeight + 3));
      site = sites.get(i);
      metaPosition.x = rectSize;
      //metaPosition.add(new PVector(0, rectHeight));
      drawsites.add(new DrawSite(site, metaPosition));
      System.out.println(site.siteName + " : " + site.count);
    }
    draw();
  }
  void draw() {
    //rectMode(CENTER);
    //translate(width/4, height/2);
    //rotate(0);
    box = false;
    for (DrawSite ds : drawsites) {
      ds.draw();
      //translate(0, rectSize);
      //rotate(PI/16);
    }
    if (box) {
      fill(255, 255, 255, 200);
      noStroke();
      rect(bpos.x + 3, bpos.y, 500, 200);
      fill(0);
      text(boxSiteText, bpos.x + 15, bpos.y + 30);
      text(boxText, bpos.x + 15, bpos.y + 115);
    }
  }

}
