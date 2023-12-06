package coding.test.find;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import coding.test.repository.QuaryRepository;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserService {

   private final QuaryRepository quary;
  

    public boolean userEmailCheck(String email, String userName) {
	String findEmail = quary.selectEmail(email);
	String findName = quary.selectNameByName(userName);
	System.out.println(findEmail);
	System.out.println(findName);

	if (findEmail != null && findName != null) {
	    return true;
	} else {
	    return false;
	}
    }
}