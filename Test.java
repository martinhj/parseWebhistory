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



class Test {
  public static void main(String[] args) {
    if (args.length == 0) new Parse(0);
    else new Parse(Integer.parseInt(args[0]));

  }



}


