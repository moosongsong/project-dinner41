package kr.co.dinner41.dao;

import java.util.List;

import kr.co.dinner41.vo.Log2MessageVO;

public interface Log2MessageDao {
	void insert(Log2MessageVO log2message);
	void delete(int logId);
	void update(int logId,String message);
	List<Log2MessageVO> selectAll();
}
