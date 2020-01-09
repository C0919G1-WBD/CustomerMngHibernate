package controller;

import model.Customer;
import model.NewCusForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;
import service.ICustomerService;

import java.util.*;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public ModelAndView viewListCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView viewSaveForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/add");
        modelAndView.addObject("cusForm",new NewCusForm());
        return modelAndView;
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("cusForm") NewCusForm newCus) {
        Customer customer = new Customer(newCus.getFirstName(),newCus.getLastName());
        customerService.save(customer);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public ModelAndView viewEditForm(@PathVariable("id")int id) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("newForm",new NewCusForm());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("newForm") NewCusForm newCus, @PathVariable("id") int id) {
        Customer customer = new Customer(newCus.getFirstName(),newCus.getLastName());
        customerService.update(id, customer);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        customerService.delete(id);
        return "redirect:/list";
    }
}
