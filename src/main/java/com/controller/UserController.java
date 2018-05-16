package com.controller;

import com.model.response.OperationResponse;
import com.model.user.User;
import com.model.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dima on 22.01.18.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
    public UserResponse getUserInformation(@RequestParam(value = "name",required = false) String userIdParam, HttpServletRequest request){
        String loggedUserid = userService.getLoggedUserId();

        User user;
        boolean provideUserDetails = false;

        //todo разобраться с provideUserDetails почему оно везде станосится true
        if (!StringUtils.isEmpty(userIdParam)){
            provideUserDetails = true;
            user = userService.getLoggedUser();
        } else if (loggedUserid.equals(userIdParam)) {
            provideUserDetails = true;
            user = userService.getLoggedUser();
        } else {
            provideUserDetails = true;
            user = userService.getUserInfoUserId(userIdParam);
        }

        UserResponse userResponse = new UserResponse();

        if(provideUserDetails){
            userResponse.setOperationStatus(OperationResponse.ResponseStatusEnum.SUCCESS);
        } else {
            userResponse.setOperationStatus(OperationResponse.ResponseStatusEnum.NO_ACCESS);
            userResponse.setOperationMessage("NO ACCESS");
        }

        userResponse.setUser(user);
        return userResponse;
    }
}
