import java.util.ArrayList;
import java.util.Collections;

class Site implements Comparable<Site> {
  String siteName;
	int count = 0;
  ArrayList<Page> pages;
  Site(String siteName) {
    pages = new ArrayList<Page>();
    this.siteName = siteName;
  }

	void visitCount() {
		for (Page p : pages) {
			count += p.visitCount;
			//System.out.println(p.visitCount);
		}
		//System.out.println("total visit count for this site: " + count);
	}
	
  void add(Page p) {
    pages.add(p);
    count += p.visitCount;
    Collections.sort(pages);
  }

  @Override
  public int compareTo(Site t) {
    if (this.count == t.count) {
      return 0;
    }
    return this.count > t.count ? 1 : -1;
  }

}
