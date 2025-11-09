package org.example.trackersystemlab05.Controller;

import org.example.trackersystemlab05.Api.ApiResponse;
import org.example.trackersystemlab05.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();
   final static AtomicLong idGenerator  =  new AtomicLong(1);


@GetMapping("/get")
   public  ArrayList<Project> getProject(){
       return projects;
   }

    @PostMapping("/add")
    public ApiResponse addProject(@RequestBody Project project){
        project.setID(idGenerator.getAndIncrement());
        projects.add(project);

        return  new ApiResponse("PROJECT IS ADDED");

    }

    @PutMapping("/update-project/{id}")
    public  ApiResponse updateProject(@PathVariable long id, @RequestBody Project project ){
        for(Project p : projects){
            if(p.getID()==id){
                p.setTitle(project.getTitle());
                p.setDescription(project.getDescription());
                p.setStatus(project.getStatus());
                p.setCompanyName(project.getCompanyName());
                return  new ApiResponse("project is updated Successfully");
            }
        }
        return new ApiResponse("project not found");
    }

    @DeleteMapping("/delete/{index}")
    public  ApiResponse deleteProject(@PathVariable int index){
        projects.remove(index);
        return  new ApiResponse("project is deleted Successfully");
    }

@PutMapping("/change-status/{index}")
    public  ApiResponse changeStatus(@PathVariable int index){
        Project project =projects.get(index);
            if(project.getStatus().equalsIgnoreCase("done")){
            project.setStatus("not done");
        }else {
                project.setStatus("done");
            }
            return new ApiResponse("project status hase been change");
    }

@GetMapping("/search-title/{title}")
public  ApiResponse searchProject(@PathVariable String title){
    for (Project p : projects ) {
        if (p.getTitle().equalsIgnoreCase(title)) {
            return new ApiResponse("project found: " + p.toString());
        }
    }
    return  new ApiResponse("project not found");
}


@GetMapping("/all-project-for-company/{companyname}")
public ApiResponse allProjectForOneCompany(@PathVariable String companyname){
    ArrayList <String > projectOneCompany = new ArrayList<>();
    for(Project p : projects){
        if (p.getCompanyName().equalsIgnoreCase(companyname)){
            projectOneCompany.add(p.getTitle());

        }
    }
    return new ApiResponse("Projects for: "+companyname +" is: "+projectOneCompany.toString());

}














}
