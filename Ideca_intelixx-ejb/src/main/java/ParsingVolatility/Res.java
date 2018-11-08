package ParsingVolatility;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagornuy on 03.12.2015.
 */

@XmlRootElement(name = "results")
public class Res {

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Res{");
		sb.append("rates=").append(rates);
		sb.append('}');
		return sb.toString();
	}

	@XmlElement(name = "rate")
	List<ValRate> rates = new ArrayList<ValRate>();

	public void add(ValRate valRate) {
		rates.add(valRate);
	}

}