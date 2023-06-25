package kuit.server.service.authService;

import kuit.server.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = userDao.findUserIdByEmail(username);
        if (authUser == null) throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username);
        return new UserDetailsImpl(authUser);
    }
}
