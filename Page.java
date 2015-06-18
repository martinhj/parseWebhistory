import java.util.Date;
import java.util.ArrayList;
//import java.net.URI;
//import java.net.URISyntaxException;

class Page implements Comparable<Page> {
  String url, title, site;
  Date lastVisitDate;
  int visitCount;



  /**
   * @param URL the url for the site
   * @param title the pages title
   * @param lastVisitDate the last time the page was visited
   * @param visitCount how many times the page is visited
   */
  public Page(String url, String title, Date lastVisitDate, int visitCount) {
    this.url = url;
    this.title = title;
    this.lastVisitDate = lastVisitDate;
    this.visitCount = visitCount;
    findSite();
  }


  void findSite() {
    try {
      site = getDomainName(url);
    } catch (Exception e) {
      System.err.println(e);
    }
  }



  public static String getDomainName(String url) {
    //URI uri = new URI(url);
    String domain = url;
    return(getSecondLevelName(url));
  }



  public static String getSecondLevelName(String url) {
    char[] urlchars = url.toCharArray();
    int dotcount = 0;
    int firstchar = 0;
    int lastchar = 0;
    int slashcount = 0;
    boolean slash = false;

    for (int i = 0; i < urlchars.length; i++) {

      if (urlchars[i] == '/' && slashcount == 1) {
        firstchar = i + 1;
      }

      if (urlchars[i] == '/' && slashcount++ >= 2) {
        lastchar = i;

        break;
      }
    }
    for (int i = lastchar; i >= firstchar; i--) {
      if (urlchars[i] == '.' && ++dotcount >= 2 ) {
        firstchar = i + 1;
        //return url.substring(i + 1, lastchar);
      }
    }

    if (dotcount > 0) {
      //System.out.println(firstchar + " : " + lastchar);
      //System.out.println(url.substring(firstchar, lastchar));
      return url.substring(firstchar, lastchar);
    } else {
      //System.out.println(url);
      //System.out.println("null");
      return null;
    }
  }



  @Override
  public String toString() {
    String s; 
    s = "URL: " + url+'\n';
    s += "title: " + title + '\n';
    s += "date: " + lastVisitDate.toString() + '\n';
    s += "visit#: " + visitCount + '\n';
    //System.out.print('\n' + s + '\n');
    return s;
  }



  @Override
  public int compareTo(Page page) {
      return this.lastVisitDate.compareTo(page.lastVisitDate);
  }
}
