package com.app.starbucks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import starbucks.*;
@RestController
@RequestMapping("/")
public class StarbucksController {
	starbucks.ManagePayments mp = new starbucks.ManagePayments();
	List<starbucks.Payment >paymentsList = new ArrayList<>();
	
	@RequestMapping("/")
    public String home(){
        return "Hello World!";
    }


@PostMapping("/MakePayment")
public void makePayment()
{
	mp.makePayment("116","128",5.00,20.00);

}

}
