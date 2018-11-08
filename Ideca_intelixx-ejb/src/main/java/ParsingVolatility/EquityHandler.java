package ParsingVolatility;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Asynchronous;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EquityHandler extends DefaultHandler {

	boolean bDate = false;
	boolean bOpen = false;
	boolean bClose = false;
	boolean bMarks = false;

	String rollNo;

	Equity e = new Equity();
	public List<Equity> lequity = new ArrayList<Equity>();
	public String ch = "";

	public static List<Equity> cloneList(List<Equity> dogList) {
		List<Equity> clonedList = new ArrayList<Equity>(dogList.size());
		for (Equity dog : dogList) {
			clonedList.add(new Equity(dog.getSymbol(), dog.getOpen(), dog.getClose(), dog.getDate()));
		}

		return clonedList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("quote")) {
			rollNo = attributes.getValue("Symbol");
			// System.out.println("Symbol : " + rollNo);
		} else if (qName.equalsIgnoreCase("Date")) {
			bDate = true;
		} else if (qName.equalsIgnoreCase("Open")) {
			bOpen = true;
		} else if (qName.equalsIgnoreCase("Close")) {
			bClose = true;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("quote")) {
			Equity equ = new Equity();
			equ.setSymbol("AAPL");
			equ.setDate(e.getDate());
			equ.setClose(e.getClose());
			equ.setOpen(e.getOpen());
			lequity.add(equ);

		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bDate) {
			e.setDate(new String(ch, start, length));
			// System.out.println("Date: " + new String(ch, start, length));
			bDate = false;
		} else if (bOpen) {
			e.setOpen(new Double(new String(ch, start, length)));
			// System.out.println("Open: " + new String(ch, start, length));
			bOpen = false;
		} else if (bClose) {
			e.setClose(new Double(new String(ch, start, length)));
			// System.out.println("Close: " + new String(ch, start, length));
			bClose = false;
		}

	}

	public List<Equity> getLequity() {

		return lequity;
	}

}