package ideca.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChatPk implements Serializable {
	private Integer id_trader_source;
	private Integer id_trader_destination;

	public Integer getId_trader_source() {
		return id_trader_source;
	}

	public void setId_trader_source(Integer id_trader_source) {
		this.id_trader_source = id_trader_source;
	}

	public Integer getId_trader_destination() {
		return id_trader_destination;
	}

	public void setId_trader_destination(Integer id_trader_destination) {
		this.id_trader_destination = id_trader_destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_trader_destination == null) ? 0 : id_trader_destination.hashCode());
		result = prime * result + ((id_trader_source == null) ? 0 : id_trader_source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatPk other = (ChatPk) obj;
		if (id_trader_destination == null) {
			if (other.id_trader_destination != null)
				return false;
		} else if (!id_trader_destination.equals(other.id_trader_destination))
			return false;
		if (id_trader_source == null) {
			if (other.id_trader_source != null)
				return false;
		} else if (!id_trader_source.equals(other.id_trader_source))
			return false;
		return true;
	}

}
