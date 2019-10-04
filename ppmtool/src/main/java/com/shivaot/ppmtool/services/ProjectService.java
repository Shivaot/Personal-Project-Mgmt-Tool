package com.shivaot.ppmtool.services;

import com.shivaot.ppmtool.domain.Project;
import com.shivaot.ppmtool.exceptions.ProjectIdException;
import com.shivaot.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception ex){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already " +
                    "exists.");
        }
    }


    public Project findProjectByIdentifier(String projectId){
        Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project ID '" + projectId+ "' does not " + "exists.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null){
            throw new ProjectIdException("Cannot delete project with ID '" + projectId + "'. This project does not " +
                    "exist");
        }

        projectRepository.delete(project);
    }
}
