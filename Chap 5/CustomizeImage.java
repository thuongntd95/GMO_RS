package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Servlet that displays referer-specific image.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class CustomizeImage extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String referer = request.getHeader("Referer");
    if (referer == null) {
      referer = "<I>none</I>";
    }
    String title = "Referring page: " + referer;
    String imageName;
    if (contains(referer, "JRun")) {
      imageName = "jrun-powered.gif";
    } else if (contains(referer, "Resin")) {
      imageName = "resin-powered.gif";
    } else {
      imageName = "tomcat-powered.gif";
    }
    String imagePath = "../request-headers/images/" + imageName;
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<CENTER><H2>" + title + "</H2>\n" +
                "<IMG SRC=\"" + imagePath + "\">\n" +
                "</CENTER></BODY></HTML>");
  }

  private boolean contains(String mainString,
                           String subString) {
    return(mainString.indexOf(subString) != -1);
  }
}
