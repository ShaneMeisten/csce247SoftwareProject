import java.util.ArrayList;
import java.util.UUID;

public class ProjectCatalog {
    private ProjectCatalog projectCatalog;
    public static ArrayList<Project> projects;

    private ProjectCatalog() {

    }

    public projectList getInstance(){
        if (projectCatalog == null) {
            projectCatalog = new ProjectCatalog();
        }
        return projectCatalog;
    }

    public static  addProject(String ProjectName, String ProjectType){
         projects.add(new Project(ProjectName, ProjectType));
        
    }

    public static removeProject(UUID ProjectUUID){
        projects.remove(ProjectUUID);
        

    }

    public static Project getProject(UUID ProjectUUID){
        for (Project project : projects) {
            if (project.getUUID().equals(ProjectUUID)) {
                return project;
            }
        }
        return null; 
    }
    }





}
