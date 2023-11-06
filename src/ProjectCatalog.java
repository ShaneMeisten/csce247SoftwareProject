import java.util.ArrayList;
import java.util.UUID;

public class ProjectCatalog {
    private static ProjectCatalog projectCatalog;
    public static ArrayList<Project> projects = new ArrayList<Project>();

    private ProjectCatalog() {

    }

    public static ProjectCatalog getInstance(){
        if (projectCatalog == null) {
            projectCatalog = new ProjectCatalog();
        }
        return projectCatalog;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addProject(String ProjectName){
        UUID id = UUID.randomUUID();
        Layout layout = Layout.SCRUM; 
        ArrayList<Task> completedTasks = new ArrayList<>();
        ArrayList<Task> ongoingTasks = new ArrayList<>();
        ArrayList<Column> columnList = new ArrayList<>();
        History history = new History(); 
        String ProjectType = "";
        projects.add(new Project(id, ProjectName, ProjectType, layout, completedTasks, ongoingTasks, columnList, history));
    }

    public static Project removeProject(UUID ProjectUUID){
        Project remove = projectCatalog.getProject(ProjectUUID);
        projects.remove(remove);
        return remove;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public Project getProject(UUID ProjectUUID){
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
