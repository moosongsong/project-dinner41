package kr.co.dinner41.vo;

public class Log2ManagerVO {
	private LogVO log;
	private UserVO user;
	
	public Log2ManagerVO() {}

	public Log2ManagerVO(LogVO log, UserVO user) {
		this.log = log;
		this.user = user;
	}

	public LogVO getLog() {
		return log;
	}

	public void setLog(LogVO log) {
		this.log = log;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}
	
	
}
