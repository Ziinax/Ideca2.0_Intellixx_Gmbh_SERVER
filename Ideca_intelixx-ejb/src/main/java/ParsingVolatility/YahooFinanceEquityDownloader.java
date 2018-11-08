package ParsingVolatility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.bind.*;

import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ideca.entity.EquitySymbol;

@Stateless
public class YahooFinanceEquityDownloader {

	public static ArrayList<Equity> lequity = new ArrayList<Equity>();
	static List<EquitySymbol> inputSymbolList = new ArrayList();

	public static void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
		byte[] buf = new byte[bufferSize];
		int n = input.read(buf);
		while (n >= 0) {
			output.write(buf, 0, n);
			n = input.read(buf);
		}
		output.flush();
	}

	public double MarketParser(String symbol) throws Exception {

		String request = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20"
				+ "yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22" + symbol
				+ "%22%20and%20startDate%20%3D%20%222015-09-11%22%20and%20endDate%20%3D%20%222017-02-21%22&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String result = performRequest(request);

		BufferedWriter bw = new BufferedWriter(new FileWriter("..\\docs\\query_result.xml", false));
		bw.write(result);
		bw.close();

		File file = new File("..\\docs\\query_result.xml");

		System.out.println(result);

		String request2 = "https://github.com/bdanalytics/Trading-Finance/raw/master/Yahoo%20Ticker%20Symbols%20-%20Jan%202016.xlsx";
		String result2 = performRequest(request2);
		JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Query query = (Query) unmarshaller.unmarshal(file);
		System.out.println(query.toString());

		try {
			//readExel("C:\\Yahoo-Ticker-Symbols-Jan 2016.xlsx");
		//	writeToXLSFormat();
			BufferedWriter bw2 = new BufferedWriter(new FileWriter("..//docs//Yahoo.xls", false));
			bw2.write(result2);
			bw2.close();

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EquityHandler Equityhandler = new EquityHandler();
			saxParser.parse(file, Equityhandler);
			lequity = (ArrayList<Equity>) Equityhandler.getLequity();

			/*
			 * URL url = new URL("http://blah.com/download.zip");
			 * HttpURLConnection connection = (HttpURLConnection)
			 * url.openConnection(); connection.setRequestMethod("GET");
			 * InputStream in = connection.getInputStream(); FileOutputStream
			 * out = new FileOutputStream("download.zip"); copy(in, out, 1024);
			 * out.close();
			 */
			return Equity.calculVolatility(lequity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0.0;

	}

	private static String performRequest(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		StringBuilder sb = new StringBuilder();

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			char[] buf = new char[1000000];

			int r = 0;
			do {
				if ((r = br.read(buf)) > 0)
					sb.append(new String(buf, 0, r));
			} while (r > 0);
		} finally {
			http.disconnect();
		}

		return sb.toString();
	}

	
}