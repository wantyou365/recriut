package com.janan.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.janan.data.data.Province;

public class ProvinceParseHandler extends DefaultHandler{

	private List<Province> Provinces;
	private Province _Province;
	private StringBuilder builder;
	public List<Province> getProvinces() {
		return Provinces;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		Provinces = new ArrayList<Province>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		_Province = new Province();
		for (int i = 0; i < attributes.getLength(); i++) {
			if (attributes.getLocalName(i).equals("ID")) {
				_Province.setmId(attributes.getValue(i));
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
		if (localName.equals("Province")) {
			_Province.setmName(builder.toString());
		}
		if(!Provinces.contains(_Province)){
			Provinces.add(_Province);
		}
		
	}
}
