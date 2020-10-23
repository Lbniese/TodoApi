package lbniese.controller;

import lbniese.model.Task;
import lbniese.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {

    TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/task")
    public Iterable<Task> index(){
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Optional<Task>> readOne(@PathVariable Long id){
        Optional<Task> res = taskRepository.findById(id);
        if(res.isPresent()){
            return ResponseEntity.status(200).body(res);
        } else {
            return ResponseEntity.status(404).body(res);
        }
    }

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/task")
    public ResponseEntity<String> create(@ModelAttribute Task t){
        Task task = taskRepository.save(t);
        return ResponseEntity.status(201).header("Location", "/task/" + task.getId()).body("{'Msg': 'task created'}");
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/task")
    public ResponseEntity<String> update(@ModelAttribute Task t){
        taskRepository.save(t);
        return ResponseEntity.status(204).body("{'msg':'Hello'}");
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        taskRepository.deleteById(id);
        return ResponseEntity.status(200).body("{'msg':'Deleted'}");
    }




}
