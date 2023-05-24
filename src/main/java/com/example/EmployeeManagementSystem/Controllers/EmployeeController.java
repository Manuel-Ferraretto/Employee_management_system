package com.example.EmployeeManagementSystem.Controllers;

import com.example.EmployeeManagementSystem.Entities.Employee;
import com.example.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/allEmployees")
    public String listEmployees(Model model, @ModelAttribute("successMessage") String successMessage,
                                             @ModelAttribute("deleteMessage") String deleteMessage,
                                             @ModelAttribute("editMessage") String editMessage){
        model.addAttribute("employees", employeeService.findAll());
        if (!successMessage.isEmpty()){
            System.out.println("Success message: " + successMessage);
            model.addAttribute("successMessage", successMessage);
        }
        if (!deleteMessage.isEmpty()){
            System.out.println("Delete message: " + deleteMessage);
            model.addAttribute("deleteMessage", deleteMessage);
        }
        if (!editMessage.isEmpty()){
            System.out.println("Edit message: " + editMessage);
            model.addAttribute("editMessage", editMessage);
        }
        System.out.println(employeeService.findAll());
        return "listOfEmployees";
    }

    @GetMapping("/getEmployee/{idEmployee}")
    @ResponseBody
    public Employee getEmployee(@PathVariable Long idEmployee){
        Employee employee = employeeService.getById(idEmployee).orElse(null);
        return employee;
    }

    @PostMapping("/saveChanges/{idEmployee}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable(value = "idEmployee") Long ID, @RequestBody Employee employee,
                                                              RedirectAttributes redirectAttributes){
        Map<String, Object> response = new HashMap<>();
        if (ID != null){
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("editMessage", "Employee updated successfully!");
        }
        else{
            redirectAttributes.addFlashAttribute("editMessage", "There was an error!");
        }
        response.put("redirectUrl", "/employee/allEmployees");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addNewEmployee")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addNewEmployee(@RequestBody Employee newEmployee, BindingResult bindingResult,
                                                              RedirectAttributes redirectAttributes){
        Map<String, Object> response = new HashMap<>();
        if (!bindingResult.hasErrors()) {
            employeeService.addNewEmployee(newEmployee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee inserted successfully!");
        }
        response.put("redirectUrl", "/employee/allEmployees");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/deleteEmployee/{idEmployee}")
    public String deleteEmployee(@PathVariable(value = "idEmployee") Long ID, RedirectAttributes redirectAttributes){
        Optional<Employee> employee = employeeService.getById(ID);
        if (employee.isPresent()){
            employeeService.deleteEmployeeById(ID);
            redirectAttributes.addFlashAttribute("deleteMessage", "Employee deleted successfully!");
        }
        return "redirect:/employee/allEmployees";
    }
}
