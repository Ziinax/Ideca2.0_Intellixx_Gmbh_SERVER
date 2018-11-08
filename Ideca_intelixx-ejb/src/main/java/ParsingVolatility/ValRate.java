package ParsingVolatility;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by nagornuy on 03.12.2015.
 */
@XmlRootElement(name = "rate")
public class ValRate {

    String name;
    Float Rate;
    String date;
    String time;
    float ask;
    float bid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValRate{");
        sb.append("name='").append(name).append('\'');
        sb.append(", Rate=").append(Rate);
        sb.append(", date='").append(date).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", ask=").append(ask);
        sb.append(", bid=").append(bid);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public Float getRate() {
        return Rate;
    }

    @XmlElement(name = "Rate")
    public void setRate(Float rate) {
        Rate = rate;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "Date")
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    @XmlElement(name = "Time")
    public void setTime(String time) {
        this.time = time;
    }

    public float getAsk() {
        return ask;
    }

    @XmlElement(name = "Ask")
    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getBid() {
        return bid;
    }

    @XmlElement(name = "Bid")
    public void setBid(float bid) {
        this.bid = bid;
    }

    public ValRate() {
    }

    public ValRate(String name, Float rate, String date, String time, float ask, float bid) {
        this.name = name;
        Rate = rate;
        this.date = date;
        this.time = time;
        this.ask = ask;
        this.bid = bid;
    }
}