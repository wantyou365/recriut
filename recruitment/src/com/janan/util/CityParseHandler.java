package com.janan.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.janan.data.data.City;

public class CityParseHandler  extends DefaultHandler{
	private List<City> Cities;
	private City _City;
	private StringBuilder builder;
	public List<City> getCities() {
		return Cities;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		Cities = new ArrayList<City>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		_City = new City();
		for (int i = 0; i < attributes.getLength(); i++) {
			if (attributes.getLocalName(i).equals("ID")) {
				_City.setmId(attributes.getValue(i));
			}
			if (attributes.getLocalName(i).equals("PID")) {
				_City.setmPid(attributes.getValue(i));
			}
			
			if (attributes.getLocalName(i).equals("status")) {
				if("hasmore".equals(attributes.getValue(i))){
					_City.setHasMore(true);
				}
				_City.setmPid(attributes.getValue(i));
			}
		}
		builder.setLength(0); 
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length); // 将读取的字符数组追加到builder中
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (localName.equals("City")) {
			_City.setmName(builder.toString());
		}
		if(!Cities.contains(_City)){
			Cities.add(_City);
		}
		
	}
}
