package EduLink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.AutoNumMapper;

@Service
public class AutoNumService {
	@Autowired
	AutoNumMapper autoNumMapper;
	public String execute(String sep, String columnName, Integer len
			,String tableName ) {
		System.out.println(sep+"/"+columnName+"/"+len+"/"+tableName);
		String autoNum = autoNumMapper.AutoNumSelect(sep, columnName, len,tableName);
		return autoNum;
	}
}