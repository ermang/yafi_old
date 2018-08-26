package egcom.yafi.packy;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserResolver {

    public UserDetails getActiveUser() {
        UserDetails userDetail =  (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetail;
    }
}
