package kr.co.dinner41.vo;

public class Log2MessageVO {
	private LogVO log;
	private String content;
	private String message;
	
	public Log2MessageVO() {}

	public Log2MessageVO(LogVO log, String content, String message) {
		this.log = log;
		this.content = content;
		this.message = message;
	}

	public LogVO getLog() {
		return log;
	}

	public void setLog(LogVO log) {
		this.log = log;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
