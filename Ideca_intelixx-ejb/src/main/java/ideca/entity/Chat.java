package ideca.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Chat implements Serializable {
	private Date date;
	private String Text;
	private ChatPk Chatpk;
	private Trader trader_source;
	private Trader trader_destination;

	@EmbeddedId
	public ChatPk getChatpk() {
		return Chatpk;
	}

	public void setChatpk(ChatPk chatpk) {
		Chatpk = chatpk;
	}

	@ManyToOne
	@JoinColumn(name = "id_trader_source", referencedColumnName = "id", insertable = false, updatable = false)
	public Trader getTrader_source() {
		return trader_source;
	}

	public void setTrader_source(Trader trader_source) {
		this.trader_source = trader_source;
	}

	@ManyToOne
	@JoinColumn(name = "id_trader_destination", referencedColumnName = "id", insertable = false, updatable = false)
	public Trader getTrader_destination() {
		return trader_destination;
	}

	public void setTrader_destination(Trader trader_destination) {
		this.trader_destination = trader_destination;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

}
