package com.gdgvitvellore.gdgvitvellore.Entity.model;


public class ProjectModel {

    public String projectName,projectDescription,projectContributer,projectIcon;

    public ProjectModel()
    {

    }

    public ProjectModel(String projectContributer, String projectDescription, String projectIcon, String projectName) {
        this.projectContributer = projectContributer;
        this.projectDescription = projectDescription;
        this.projectIcon = projectIcon;
        this.projectName = projectName;
    }

    public String getProjectContributer() {
        return projectContributer;
    }

    public String getProjectIcon() {
        return projectIcon;
    }

    public void setProjectContributer(String projectContributer) {

        this.projectContributer = projectContributer;
    }

    public void setProjectIcon(String projectIcon) {
        this.projectIcon = projectIcon;
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
