package com.app.Controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	ArrayList<String[]> normal;
	ArrayList<String[]> exams;

	public ProcessXML() {
		normal = new ArrayList<String[]>();
		exams = new ArrayList<String[]>();
	};

	public ArrayList<String[]> getNormal() {
		return normal;
	}

	public ArrayList<String[]> getExams() {
		return exams;
	}

	public void executeXMLSources(Context ctx) {
		executeTrips(ctx);
		executeSeasons(ctx);
	}

	public void executeTrips(Context ctx){

		try {
			//Get xml file from project asset folder
			AssetManager assetManager = ctx.getAssets();
			InputStream is = assetManager.open(com.app.domain.Values.XML_TRIPS);


			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("trip");

			if(com.app.domain.Values.debugXML){
				System.out.println("Root element " + doc.getDocumentElement().getNodeName());
				System.out.println("Information of times");
				System.out.println("Tamanho do nó: " + nodeLst.getLength());
			}

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					if(com.app.domain.Values.debugXML){
						Log.i("XML_trips","Season: " + fstNode.getAttributes().item(0).getTextContent());
					}

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("hour_init");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_trips","Hour Init : "  + ((Node) fstNm.item(0)).getNodeValue());
					}

					NodeList scdNmElmntLst = fstElmnt.getElementsByTagName("hour_end");
					Element scdNmElmnt = (Element) scdNmElmntLst.item(0);
					NodeList scdNm = scdNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_trips","Hour End : " + ((Node) scdNm.item(0)).getNodeValue());
					}

					NodeList thrNmElmntLst = fstElmnt.getElementsByTagName("depart");
					Element thrNmElmnt = (Element) thrNmElmntLst.item(0);
					NodeList thrNm = thrNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_trips","Depart : " + ((Node) thrNm.item(0)).getNodeValue());
					}

					NodeList fourNmElmntLst = fstElmnt.getElementsByTagName("arrive");
					Element fourNmElmnt = (Element) fourNmElmntLst.item(0);
					NodeList fourNm = fourNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_trips", "Arrive : " + ((Node) fourNm.item(0)).getNodeValue());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeSeasons(Context ctx) {

		try {
			//Get xml file from project asset folder
			AssetManager assetManager = ctx.getAssets();
			InputStream is = assetManager.open(com.app.domain.Values.XML_SEASONS);


			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			doc.getDocumentElement().normalize();

			if(com.app.domain.Values.debugXML){
				Log.i("XML_seasons","Root element " + doc.getDocumentElement().getNodeName());
			}

			if(com.app.domain.Values.debugXML){
				Log.i("XML_seasons","Reading Normal slots");
			}
			readNodeSeasons(doc, "normal");

			if(com.app.domain.Values.debugXML){
				Log.i("XML_seasons","Reading Exams slots");
			}
			readNodeSeasons(doc, "exams");

			//Maybe try to catch all the specific exceptions  :)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Method to read seasons slots
	void readNodeSeasons(Document doc, String masterNode) {
		NodeList NodeLst = doc.getElementsByTagName(masterNode);

		for (int i = 0; i < NodeLst.getLength(); i++) {
			Node node = NodeLst.item(i);
			NodeList slotList = node.getChildNodes();

			for (int s = 0; s < slotList.getLength(); s++) {

				Node fstNode = slotList.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("date_init");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_seasons","Date Init : "  + ((Node) fstNm.item(0)).getNodeValue());
					}
					String date_init = ((Node)fstNm.item(0)).getNodeValue();

					NodeList scdNmElmntLst = fstElmnt.getElementsByTagName("date_end");
					Element scdNmElmnt = (Element) scdNmElmntLst.item(0);
					NodeList scdNm = scdNmElmnt.getChildNodes();

					if(com.app.domain.Values.debugXML){
						Log.i("XML_seasons","Date End : " + ((Node) scdNm.item(0)).getNodeValue());
					}
					String date_end = ((Node) scdNm.item(0)).getNodeValue();

					if(com.app.domain.Values.debugXML){
						Log.i("Check","==========BEGIN=============");

						Log.i("Check","Extracted date_end: " + date_end);

					}

					if(masterNode.equals("normal")) {
						normal.add(new String[]{date_init,date_end});
					} else if (masterNode.equals("exams")) {
						exams.add(new String[]{date_init,date_end});
					}

					if(com.app.domain.Values.debugXML){
						Log.i("Check","NORMAL:");
						for (String[] slot : normal) {
							Log.i("Check","date_begin: "+slot[0]);
							Log.i("Check","date_end: "+slot[1]);
						}

						Log.i("Check","EXAMS:");
						for (String[] slot : exams) {
							Log.i("Check","date_begin: "+slot[0]);
							Log.i("Check","date_end: "+slot[1]);
						}

						Log.i("Check","==========END=============");
					}
				}
			}
		}
	}
}
