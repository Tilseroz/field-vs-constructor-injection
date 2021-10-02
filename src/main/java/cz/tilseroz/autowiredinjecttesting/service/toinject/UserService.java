package cz.tilseroz.autowiredinjecttesting.service.toinject;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Long retrieveUserByUsername(String username) {
        return 1L;
    }
}
