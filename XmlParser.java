/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Justin
 */
public class XmlParser
{
    private static final Logger log = Logger.getLogger(XmlParser.class.getName());
    ConnectionData connectionData = new ConnectionData();
    Document document;
    
    /**
     *
     * @param docName name of the document storing the XML properties
     */
    public XmlParser(String docName)
    {        
        log.log(Level.SEVERE, "Parsing XML file");
        
        // Parsing the XML document
        parseXmlFile(docName);
    }
    
    /**
     *
     * @param name name of the XML document storing the properties of the DB
     */
    public void parseXmlFile(String name)
    {
        DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        
        try
        {
            log.log(Level.SEVERE, "Removing data from XML document");
            
            DocumentBuilder dB = dBF.newDocumentBuilder();
        
            document = dB.parse(ClassLoader.getSystemResourceAsStream(name));
        
            NodeList list = document.getDocumentElement().getChildNodes();
        
            // Adding different types of data to the list
            for(int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                
                // Finding what type of data the node is
                if(node instanceof Element)
                {
                    String type = node.getAttributes().getNamedItem("type").getNodeValue();
                    String url = "";
                    String login = "";
                    String ipaddress = "";
                    String database = "";
                    String port = "";
                    String password = "";
                    
                    NodeList childNodes = node.getChildNodes();
                    
                    for(int j = 0; j < childNodes.getLength(); j++)
                    {
                        Node child = childNodes.item(j);
                        
                        // Checking more data
                        if(child instanceof Element)
                        {
                            String text = child.getLastChild().getTextContent().trim();
                            
                            // Cases for different types of possible data
                            switch(child.getNodeName())
                            {
                                case "url":
                                    url = text;
                                    break;
                                
                                case "ipaddress":
                                    ipaddress = text;
                                    break;
                                
                                case "port":
                                    port = text;
                                    break;
                                
                                case "database":
                                    database = text;
                                
                                case "login":
                                    login = text;
                                    
                                case "password":
                                    password = text;
                            }
                        }
                    }
                    
                    // Setting different types of connection data
                    connectionData.setType(type);
                    connectionData.setUrl(url);
                    connectionData.setDatabase(database);
                    connectionData.setIpaddress(ipaddress);
                    connectionData.setLogin(login);
                    connectionData.setPassword(password);
                    connectionData.setPort(port);
                }
            }
        }
        
        catch(ParserConfigurationException pce) 
        {
            pce.printStackTrace();
        }
          catch(SAXException se) 
        {
            se.printStackTrace();
        }
        catch(IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @return data regarding the connection to the database
     */
    public ConnectionData getConnectionData()
    {
        // Return connection data
        log.log(Level.SEVERE, "Getting connection data from XML class");
        
        return connectionData;
    }  
}
