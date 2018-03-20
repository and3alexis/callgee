package com.intergalacticcallcenter.dto.abc;

public class IntergalacticCallCenterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6315628813750203908L;

	public IntergalacticCallCenterException() {
		// TODO Auto-generated constructor stub
	}

	public IntergalacticCallCenterException(String message) {
		super(message);
	}

	public IntergalacticCallCenterException(Throwable cause) {
		super(cause);
	}

	public IntergalacticCallCenterException(String message, Throwable cause) {
		super(message, cause);
	}

	public IntergalacticCallCenterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
