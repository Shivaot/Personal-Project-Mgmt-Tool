package com.shivaot.ppmtool.repositories;

import com.shivaot.ppmtool.domain.Project;
import com.shivaot.ppmtool.exceptions.ProjectIdException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
}
