package ca.airspeed.wsbooks.exception

class TsheetsException extends RuntimeException {

	public TsheetsException() {
		super()
	}

	public TsheetsException(String msg) {
		super(msg)
	}
	
	public TsheetsException(String msg, Throwable thr) {
		super(msg, thr)
	}
	
	public TsheetsException(Throwable thr) {
		super(thr)
	}
}
