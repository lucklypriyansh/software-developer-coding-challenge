package com.traderev.auction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Vehical")
public class Vehical {
	
	@Id
	String vehicalId;
	
	String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVehicalId() {
		return vehicalId;
	}

	public void setVehicalId(String vehicalId) {
		this.vehicalId = vehicalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((vehicalId == null) ? 0 : vehicalId.hashCode());
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
		Vehical other = (Vehical) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (vehicalId == null) {
			if (other.vehicalId != null)
				return false;
		} else if (!vehicalId.equals(other.vehicalId))
			return false;
		return true;
	}

	
}
