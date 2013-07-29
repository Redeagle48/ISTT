package com.app.Controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class ProcessXML {

	final String INITHOUR = "hour_init";
	final String ENDHOUR = "hour_end";
	final String DEPART = "depart";
	final String ARRIVE = "arrive";

	public ProcessXML() {
	};

	public void executeTrips(Context ctx){

		// Get the actual date
		Date cDate = new Date();
		String fDate = new SimpleDateFormat("MM-dd").format(cDate);
		String[] dateArray = fDate.split("-");

		Context context = ctx;
		try {

			//Get xml file from project asset folder
			AssetManager assetManager = context.getAssets();
			InputStream is = assetManager.open(com.app.domain.Values.XML_TRIPS);


			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("trip");

			if(com.app.domain.Values.debug){
				System.out.println("Root element " + doc.getDocumentElement().getNodeName());
				System.out.println("Information of times");
				System.out.println("Tamanho do nó: " + nodeLst.getLength());
			}

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					if(com.app.domain.Values.debug){
						Log.i("XML_trips","Season: " + fstNode.getAttributes().item(0).getTextContent());
					}

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("hour_init");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					
					if(com.app.domain.Values.debug){
						Log.i("XML_trips","Hour Init : "  + ((Node) fstNm.item(0)).getNodeValue());
					}

					NodeList scdNmElmntLst = fstElmnt.getElementsByTagName("hour_end");
					Element scdNmElmnt = (Element) scdNmElmntLst.item(0);
					NodeList scdNm = scdNmElmnt.getChildNodes();
					
					if(com.app.domain.Values.debug){
						Log.i("XML_trips","Hour End : " + ((Node) scdNm.item(0)).getNodeValue());
					}

					NodeList thrNmElmntLst = fstElmnt.getElementsByTagName("depart");
					Element thrNmElmnt = (Element) thrNmElmntLst.item(0);
					NodeList thrNm = thrNmElmnt.getChildNodes();
					
					if(com.app.domain.Values.debug){
						Log.i("XML_trips","Depart : " + ((Node) thrNm.item(0)).getNodeValue());
					}

					NodeList fourNmElmntLst = fstElmnt.getElementsByTagName("arrive");
					Element fourNmElmnt = (Element) fourNmElmntLst.item(0);
					NodeList fourNm = fourNmElmnt.getChildNodes();
					
					if(com.app.domain.Values.debug){
						Log.i("XML_trips", "Arrive : " + ((Node) fourNm.item(0)).getNodeValue());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}