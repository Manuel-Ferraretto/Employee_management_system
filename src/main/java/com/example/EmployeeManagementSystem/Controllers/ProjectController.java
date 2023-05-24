package com.example.EmployeeManagementSystem.Controllers;

import com.example.EmployeeManagementSystem.Entities.Employee;
import com.example.EmployeeManagementSystem.Entities.Project;
import com.example.EmployeeManagementSystem.Service.EmployeeService;
import com.example.EmployeeManagementSystem.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequestMapping("/project")
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }


    @GetMapping("/inProgressProjects")
    public String getInProgressProjects(Model model){
        model.addAttribute("inProgressProjects", projectService.findInProgressProjects());
        return "listOfProjects";
    }

    @GetMapping("/finishedProjects")
    public String getFinishedProjects(Model model){
        model.addAttribute("finishedProjects", projectService.findFinishedProjects());
        return "listOfProjects";
    }

    @GetMapping("/addEmployeesToProject/{idProject}")
    public String addEmployeesToProject(@PathVariable(value = "idProject") Long ID, Model model){
        model.addAttribute("employees", employeeService.findAvailableEmployees());
        Optional<Project> projectOptional = projectService.findById(ID);
        if (projectOptional.isPresent()){
            Project project = projectOptional.get();
            model.addAttribute("project", project);
        }
        return "availableEmployees";
    }

    // PathVariable para path variables de la url. Se usa cuando es el caso de {idEmployee} por ejemplo.
    // RequestParam para extraer variables de la url cuando usas los input en html. Ejemplo https://example.com/search?name=nameEmployee

    @GetMapping("/addToProject/{idEmployee}/{idProject}")
    public String addToProject(@PathVariable(value = "idEmployee") Long idEmployee,
                               @PathVariable(value = "idProject") Long idProject,
                               RedirectAttributes redirectAttributes){
        Optional<Project> projectOptional = projectService.findById(idProject);
        Optional<Employee> employeeOptional = employeeService.getById(idEmployee);
        if (projectOptional.isPresent() && employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            Project project = projectOptional.get();
            try{
                projectService.addCurrentEmployeeToProject(project, employee);
                System.out.println("ERROR");
            }catch (Exception ex){
                System.out.println("redirect:/project/addEmployeesToProject/".concat(String.valueOf(idProject)));
                ex.printStackTrace();
            }
            redirectAttributes.addFlashAttribute("message", "Employee added to project successfully!");
        }
        return "redirect:/project/addEmployeesToProject/".concat(String.valueOf(idProject));
    }
}
