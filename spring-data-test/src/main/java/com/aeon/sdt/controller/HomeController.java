package com.aeon.sdt.controller;

import com.aeon.sdt.entity.Customer;
import com.aeon.sdt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by roshane on 3/12/2017.
 */
@Controller
public class HomeController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/")
    @ResponseBody
    public String redirectHome() {
        return "hello world";
    }

    @RequestMapping("/home")
    public String getHomePage(Model model) {

        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        System.out.println(">>> customers " + customers);
        return "index";
    }
}
