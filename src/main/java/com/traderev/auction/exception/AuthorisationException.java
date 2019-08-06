package com.traderev.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthorisationException extends RuntimeException {

	public AuthorisationException(String msg) {
		super(msg);
	}
}
