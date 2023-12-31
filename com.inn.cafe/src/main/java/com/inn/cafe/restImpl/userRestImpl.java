package com.inn.cafe.restImpl;


import com.inn.cafe.POJO.User;
import com.inn.cafe.constents.CafeConstents;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.rest.UserRest;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class userRestImpl implements UserRest {

@Autowired
   UserDao userDao;
    @Override
    public ResponseEntity<String> singUp(Map<String, String> requestMap) {
log.info("Inside SignUp{}",requestMap);
try{

if(validateSignUpMap(requestMap)){
User user= userDao.findByEmailId(requestMap.get("email"));
if(Objects.isNull(user)){
    userDao.save(getUserFromMap(requestMap));
    return CafeUtils.getResponseEntity("Data Saved Success", HttpStatus.OK);
}else{
    return CafeUtils.getResponseEntity("Email Already Exist",HttpStatus.BAD_REQUEST);
}
}else{
    return CafeUtils.getResponseEntity(CafeConstents.InvalidData,HttpStatus.BAD_REQUEST);
}
}
catch (Exception ex){
    ex.printStackTrace();
}
return CafeUtils.getResponseEntity(CafeConstents.SometingWentWrong,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateSignUpMap(Map<String,String>requestMap )       {
       if( requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")) {

                return true;
        }
return false;

    }

    private  User getUserFromMap(Map<String,String> requestMap){
    User user= new User();
    user.setName(requestMap.get("name"));
    user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));


        user.setStatus("false");
        user.setRole("user");
        return user;


    }
}
