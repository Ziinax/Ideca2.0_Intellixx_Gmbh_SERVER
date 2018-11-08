package ParsingVolatility;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nagornuy on 03.12.2015.
 */

@XmlRootElement(name = "query")
public class Query {

	private Res results;

	public Query(Res results) {
		this.results = results;
	}

	@XmlElement(name = "results")
	public void setResults(Res results) {
		this.results = results;
	}

	public Res getResults() {

		return results;
	}

	public Query() {

	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Query{");
		sb.append("results=").append(results);
		sb.append('}');
		return sb.toString();
	}
}