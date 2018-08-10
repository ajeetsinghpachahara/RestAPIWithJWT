package com.ajeet.jwtcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajeet.jwtmodel.Employee;
import com.ajeet.jwtmodel.JwtUser;
import com.ajeet.jwtutils.JwtGenerator;
import com.ajeet.response.Data;
import com.ajeet.response.Response;




@RestController
public class TokenController {

    @Autowired
    private JwtGenerator jwtGenerator;

   

    @RequestMapping(value = { "/getToken" }, method = RequestMethod.POST)
    public ResponseEntity<Response> generate(@RequestBody final JwtUser jwtUser) {

    	String token = jwtGenerator.generate(jwtUser);
    	Response response = new Response();
    	response.setStatus(1);
    	Data data = new Data();
    	data.setToken(token);
    	response.setData(data);
        return new ResponseEntity<Response>(response,HttpStatus.OK);

    }
    
    @RequestMapping(value= "/findById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee findByID(@PathVariable("id") final String id){
		return new Employee(id, "Doe, John");
	}
}
