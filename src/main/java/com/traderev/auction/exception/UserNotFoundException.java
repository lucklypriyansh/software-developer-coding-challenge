package com.traderev.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
