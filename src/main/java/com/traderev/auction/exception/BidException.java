package com.traderev.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
public class BidException extends RuntimeException {

	public BidException(String string) {
		super(string);
	}

}
