package com.shivaot.ppmtool.web;

import com.shivaot.ppmtool.domain.Project;
import com.shivaot.ppmtool.exceptions.ProjectIdException;
import com.shivaot.ppmtool.services.ProjectService;
import com.shivaot.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ValidationErrorService validationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = validationErrorService.ValidationError(result);
        if (errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID '" + projectId + "' was deleted",HttpStatus.OK);
    }
}