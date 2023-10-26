import java.util.ArrayList;
import java.util.UUID;

public class ProjectCatalog {
    private static ProjectCatalog projectCatalog;
    public static ArrayList<Project> projects;

    private ProjectCatalog() {

    }

    public static ProjectCatalog getInstance(){
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

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public static Project getProject(UUID ProjectUUID){
        for (Project project : projects) {
            if (project.getUUID().equals(ProjectUUID)) {
                return project;
            }
        }
        return null; 
    }
    

    public static ArrayList<Project> readUserProjectUUID(ArrayList<UUID> projectUUID) {
        ArrayList<Project> readProject = new ArrayList<Project>();
        for (Project current: projects) {
            for(UUID id: projectUUID){
                if(current.getUUID().compareTo(id) == 0) {
                    if(readProject.contains(current)) continue;
                    readProject.add(current);
                }
            }
        }
        return readProject;
    }





}
