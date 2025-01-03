package EduLink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.mapper.LoginMapper;

@Service
public class IdcheckService{
	@Autowired
	LoginMapper loginMapper;
	
    public boolean isIdAvailable(String userId) {
        String resultId = execute(userId);
        return resultId == null;
    }
    
	public String execute(String userId) {
		String resultId = loginMapper.selectIdCheck(userId);
		return resultId;
	}
}