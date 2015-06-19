import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

class Parse {
  File f;
  ArrayList<Page> pages = new ArrayList<Page>();
  HashMap<String, Site> sites =
    new HashMap<String, Site>();
  public Parse(int fileNum) {
    if (fileNum == 0) {
      f = new File("HistoryTest.xml");
    } else {
      f = new File("/Users/martinhj/dev/processing/webhistoryparseb/History.xml");
    }
    parseFile();
    //printOut();

    sortPages();
    //printSites();
  }


  ArrayList<Page> getURL() {
    return pages;
  }

  void parseFile() {
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(f);
      doc.getDocumentElement().normalize();


      NodeList nList = doc.getElementsByTagName("*");

      for (int temp = 2; temp < nList.getLength() - 1; temp++) {

        Node nNode = nList.item(temp);
        Element eElement = (Element) nNode;



        if (nNode.getTextContent().compareTo("redirectURLs") == 0) {
          temp += traverseArrayCount(nList, temp);
          continue;
        }


        if ((nList.item(temp).getNodeName().compareTo("key") == 0) &&
            (nList.item(temp).getTextContent().compareTo("") == 0)) {

          Page pagetmp = buildPage(nList, temp);
          if (pagetmp.visitCount > 0) {
            pages.add(buildPage(nList, temp));
          }
        }

      }


    } catch (Exception e) {
      System.err.println(e);
    }
  }



  int traverseArrayCount(NodeList nList, int position) {
    int counter = 1;
    while (
      nList.item(position + counter).getNodeName().compareTo("array") == 0 ||
      nList.item(position + counter).getNodeName().compareTo("string") == 0) {
      counter++;
    }
    return counter - 1;
  }



  Page buildPage(NodeList nList, int position) {
    String title = "", url = "", datetmp = "0", visitCounttmp = "0";
    int visitCount;
    double datetmp2;
    long date;

    url = getURL(nList, position);
    for (int i = 2; i < 8; i+=2) {
      int n = 0;
      if(nList.item(position+i).getTextContent().compareTo("title") == 0) n = 1;
      if(nList.item(position+i).getTextContent().compareTo("visitCount") == 0 ) n = 2;
      if(nList.item(position+i).getTextContent().compareTo("lastVisitedDate") == 0) n = 3;
      switch (n) {
        case 1 :
          title = nList.item(position+i+1).getTextContent();
          break;
        case 2 :
          visitCounttmp = nList.item(position + i + 1).getTextContent();
          break;
        case 3 :
          datetmp = nList.item(position+i+1).getTextContent();
          break;
      }
    }

    visitCount = Integer.parseInt(visitCounttmp);
    datetmp2 = Double.parseDouble(datetmp);
    date = (long) datetmp2;
    date = (date + 978307200) * 1000;
    //System.out.println(date);
    Page tempPage = new Page(url, title, new Date(date), visitCount);
    //tempPage.toString();

    return tempPage;
  }



  String getURL(NodeList nList, int position) {
    if ((nList.item(position).getNodeName().compareTo("key") == 0) &&
        (nList.item(position).getTextContent().compareTo("") == 0)) {

      return nList.item(position + 1).getTextContent();
    }

    return null;
  }


  void printOut() {
    for (Page p : pages) {
      System.out.println(p.toString());
    }
    System.out.println("Number of pages: " + pages.size());
    printLatestDate();
  }

  void printLatestDate() {
    Date newest = pages.get(0).lastVisitDate;
    for (Page p : pages) {
      if (p.lastVisitDate.compareTo(newest) < 0) {
        newest = p.lastVisitDate;
      }
    }
    System.out.println("\n\n\n###########$$$$$$$$$$###########");
    System.out.println(newest.toString());
  }



  void printSites() {
    for (String k : sites.keySet()) {
      System.out.println(k);
      if (k != null) {
        Site s = sites.get(k);
        for (Page r : s.pages) {
          System.out.println("\t\t " + r.url);
        }
      }
    }
		for (Site s : sites.values()) {
			System.out.println(s.siteName);
			s.visitCount();
		}
		int mostVisits = 0;
		Site mostVisitedSite = null;
		Site nextMostVisitedSite = null;
		Site nextnextMostVisitedSite = null;
		Site nextnextnextMostVisitedSite = null;
		Site nextnextnextnextMostVisitedSite = null;
		for (Site s : sites.values()) {
			if(s.count > mostVisits && s.siteName != null) {
				mostVisits = s.count;
				nextnextnextnextMostVisitedSite = nextnextnextMostVisitedSite;
				nextnextnextMostVisitedSite = nextnextMostVisitedSite;
				nextnextMostVisitedSite = nextMostVisitedSite;
				nextMostVisitedSite = mostVisitedSite;
				mostVisitedSite = s;
			}
		}
		System.out.println("The most visited site is: " + mostVisitedSite.siteName);
		System.out.println("With " + mostVisitedSite.count);
		System.out.println("The next most visited site is: " + nextMostVisitedSite.siteName);
		System.out.println("With " + nextMostVisitedSite.count);
		System.out.println("The next most visited site is: " + nextnextMostVisitedSite.siteName);
		System.out.println("With " + nextnextMostVisitedSite.count);
		System.out.println("The next most visited site is: " + nextnextnextMostVisitedSite.siteName);
		System.out.println("With " + nextnextnextMostVisitedSite.count);
		System.out.println("The next most visited site is: " + nextnextnextnextMostVisitedSite.siteName);
		System.out.println("With " + nextnextnextnextMostVisitedSite.count);
  }




  void sortPages() {
    for (Page p : pages) {
      if (!sites.containsKey(p.site)) {
        Site s = new Site(p.site);
        sites.put(p.site, s);
        s.add(p);

      } else {
        sites.get(p.site).add(p);
      }
    }
  }
}
