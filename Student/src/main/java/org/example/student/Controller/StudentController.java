package org.example.student.Controller;


import org.example.student.Api.ApiResponse;
import org.example.student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    ArrayList <Student> students = new ArrayList<>();
    //generator for id 
    static final AtomicLong idGenerator = new AtomicLong(1);



    @GetMapping("/get")
    public  ArrayList<Student> getStudent (){
        return students;
    }


    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
        student.setID(idGenerator.getAndIncrement());
        students.add(student);
        return new ApiResponse("student are added");
    }
@PutMapping ("/update/{id}")
public  ApiResponse updateStudent (@RequestBody Student  student, @PathVariable long id ){
    for(Student s:students){
        if(s.getID() == id){
            s.setName(student.getName());
            s.setAge(student.getAge());
            s.setGender(student.getGender());
            s.setGPA(student.getGPA());
            return new ApiResponse("Successfully updated Student ");
        }
    }
return new ApiResponse("student not found");

}


@DeleteMapping("/delete/{id}")
public ApiResponse deleteStudent(@PathVariable long id){
        for (Student s : students){
            if (s.getID()==id){
                students.remove(s);
                return new ApiResponse("Student removed");
            }

        }
        return new ApiResponse("Student not found");
}
//printing all honors
@GetMapping("/all-honor")
public  ApiResponse classifyStudent(){
        StringBuilder firstClass= new StringBuilder("First Class Honor: ");
        StringBuilder secondeClass = new StringBuilder("Second Class Honor: ");
        StringBuilder thirdClass = new StringBuilder("Third Class Honor: ");

        for(Student student : students){
            if (student.getGPA() >= 3.5 && student.getGPA() <= 4) {
                firstClass.append(student.getName()).append(", ");
            } else if (student.getGPA() >= 3 && student.getGPA() < 3.5) {
                secondeClass.append(student.getName()).append(", ");
            } else {
                thirdClass.append(student.getName()).append(", ");
            }

        }



    return new ApiResponse(
            firstClass.toString() + "----------  " +
                    " " +
                    secondeClass.toString() + "---------" +
                    thirdClass.toString()
    );
}

@GetMapping("/get-student-above-average")
public ArrayList<Student> getStudentsAboveAvg(){
        double sum =0;
        ArrayList <Student> studentAboveAvg = new ArrayList<>();
        for (Student s: students){
            sum+=s.getGPA();
        }

        double avg = sum/students.size();


        for(Student s: students){
            if(s.getGPA()>avg){
                studentAboveAvg.add(s);
            }
        }

        return studentAboveAvg;

}


@GetMapping("classify-honor-by-id/{id}")
public ApiResponse classify(@PathVariable long id){
        for (Student student : students){
            if(student.getID()==id){
                if (student.getGPA() >= 3.5 && student.getGPA() <= 4){
                    return  new ApiResponse(student.getName()+" Is First Class Honor ");
                } else if (student.getGPA() >= 3 && student.getGPA() < 3.5) {
                    return  new ApiResponse(student.getName()+" Is Seconde Class Honor");
                }else{
                    return  new ApiResponse(student.getName()+ " Is Third Class Honor");
                }

            }
        }

        return  new ApiResponse("Student not found");

}



}
