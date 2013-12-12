package com.janan.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.janan.data.data.City;
import com.janan.data.data.District;

public class DistrictParseHandler extends DefaultHandler{
	private List<District> Districts;
	private District _District;
	private StringBuilder builder;
	public List<District> getDistricts() {
		return Districts;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		Districts = new ArrayList<District>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		_District = new District();
		for (int i = 0; i < attributes.getLength(); i++) {
			if (attributes.getLocalName(i).equals("ID")) {
				_District.setmID(attributes.getValue(i));
			}
			if (attributes.getLocalName(i).equals("CID")) {
				_District.setmCID(attributes.getValue(i));
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
		if (localName.equals("District")) {
			_District.setmName(builder.toString());
		}
		if(!Districts.contains(_District)){
			Districts.add(_District);
		}
		
	}
}
