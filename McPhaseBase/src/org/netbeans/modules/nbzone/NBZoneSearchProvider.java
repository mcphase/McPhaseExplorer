/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/quickSearch.java to edit this template
 */
package org.netbeans.modules.nbzone;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import org.openide.awt.HtmlBrowser.URLDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class NBZoneSearchProvider implements SearchProvider {

    @Override
    public void evaluate(SearchRequest request, SearchResponse response) {
        try {

            //The URL that we are providing a search for:
            String mcphaseDir = System.getenv("MCPHASE_DIR");
             String HomeDir = System.getenv("HOME");
             String ds = System.getProperty("file.separator");
             String relDir = ds + "doc" + ds + "manual" + ds + "index.html";
             File file;
            if (mcphaseDir != null) { file = new File(mcphaseDir+relDir); }
           else { file = new File(HomeDir+ds+"mato"+ds+"mcphas"+ds+"doc"+ds+"manual"+ds+"index.html"); }
            FileObject man = FileUtil.toFileObject(file);
            URL url = man.toURL();
            //URL url = new URL("http://netbeans.dzone.com");
            //Stuff needed by Tidy:
            Tidy tidy = new Tidy();
            tidy.setXHTML(true);
            tidy.setTidyMark(false);
            tidy.setShowWarnings(false);
            tidy.setQuiet(true);
          //  tidy.setPrintBodyOnly(true);
            //Get the org.w3c.dom.Document from Tidy,
            //or use a different parser of your choice:
            Document doc = tidy.parseDOM(url.openStream(), null);

            //Get all "a" elements:
            NodeList list = doc.getElementsByTagName("a");

            //Get the number of elements:
            int length = list.getLength();
            //Loop through all the "a" elements:
            for (int i = 0; i < length; i++) {

                String href = null;
                if (null != list.item(i).getAttributes().getNamedItem("href")) {
                    //Get the "href" attribute from the current "a" element:
                    href = list.item(i).getAttributes().getNamedItem("href").getNodeValue();
                }

                //Get the "title" attribute from the current "a" element:
               // if (null != list.item(i).getAttributes().getNamedItem("title")) {
                 //  String title = list.item(i).getAttributes().getNamedItem("title").getNodeValue();
 if (null != list.item(i).getChildNodes()) {NodeList tt =list.item(i).getChildNodes();
    if(tt.getLength()>0){String title = tt.item(0).getNodeValue();

                    //If the title matches the requested text:
                    if (title.toLowerCase().indexOf(request.getText().toLowerCase()) != -1) {
if(null !=list.item(i).getAttributes().getNamedItem("title"))
{       System.out.print(list.item(i).getAttributes().getNamedItem("title").getNodeValue());
System.out.print(" OK\n");
System.out.flush();
}
if(null !=list.item(i).getAttributes().getNamedItem("*"))
{System.out.print(list.item(i).getAttributes().getNamedItem("*").getNodeValue());
System.out.print(" body \n");
System.out.flush();
}
                        //Add the runnable and the title to the response
                        //and return if nothing is added:
                        if (!response.addResult(new OpenFoundArticle(href), title)) {
                            return;
                        }

                    }
    }
                }

            }

        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    private static class OpenFoundArticle implements Runnable {

        private String article;

        public OpenFoundArticle(String article) {
            this.article = article;
        }

        @Override
        public void run() {
            //try {//The URL that we are providing a search for:
            String mcphaseDir = System.getenv("MCPHASE_DIR");
             String HomeDir = System.getenv("HOME");
             String ds = System.getProperty("file.separator");
             String relDir = ds + "doc" + ds + "manual" + ds + article; //"index.html";
             File file;
            if (mcphaseDir != null) { file = new File(mcphaseDir+relDir); }
           else { file = new File(HomeDir+ds+"mato"+ds+"mcphas"+ds+"doc"+ds+"manual"+ds+ article); }
            FileObject man = FileUtil.toFileObject(file);
            URL url = man.toURL();
           // System.out.print(article+" OK\n");
           //  System.out.flush();
               // URL url = new  URL("http://netbeans.dzone.com" + article);
                StatusDisplayer.getDefault().setStatusText(url.toString());
                URLDisplayer.getDefault().showURL(url);
          //  } catch (MalformedURLException ex) {
             //   Logger.getLogger(NBZoneSearchProvider.class.getName()).log(Level.SEVERE, null, ex);
           // }
        }

    }

}