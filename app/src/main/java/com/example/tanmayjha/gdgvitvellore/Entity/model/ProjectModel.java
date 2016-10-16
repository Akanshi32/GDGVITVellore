package com.example.tanmayjha.gdgvitvellore.Entity.model;


public class ProjectModel {

    public String projectName,projectDescription;

    public ProjectModel()
    {

    }

    public ProjectModel(String projectDescription, String projectName) {
        this.projectDescription = projectDescription;
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectDescription() {

        return projectDescription;
    }

    public String getProjectName() {

        return projectName;
    }

    public void setProjectName(String projectName) {

        this.projectName = projectName;
    }
}
