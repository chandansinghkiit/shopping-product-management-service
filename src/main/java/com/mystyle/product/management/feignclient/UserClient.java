package com.mystyle.product.management.feignclient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("shopping-user-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/service/names", consumes = "application/json")
    List<String> getUsers(@RequestBody List<Long> userIdList);
}