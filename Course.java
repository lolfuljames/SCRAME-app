import java.util.*;

public class Course{
    //Record of Session (Tut, Lab and Lecture) under a course
    private ArrayList<Session> indexList = new ArrayList<Session>();
    //Record of Assessment (Exam and Coursework) under a course
    //index 0 is equivalent to exam marks, the rest are coursemarks
    private ArrayList<Assessment> results = new ArrayList<Assessment>(); 

    //Other relevant information of a course
    private String courseName;
    private String courseCode;
    private int AU;
    private String courseCoordinator;

    
    //default constructor
    public Course(){}

    public Course(String courseName, String courseCode, int AU, String courseCoordinator){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.AU = AU;
        this.courseCoordinator = courseCoordinator;
    }

    //accessers and modifiers
    public String getCourseName(){
        return this.courseName;
    }

    public String getCourseCode(){
        return this.courseCode;
    }

    public int getAU(){
        return this.AU;
    }

    public String getCourseCoordinator(){
        return this.courseCoordinator;
    }

    //Add in new Session (Lab, Tut, LT)
    //need to check for whethere there's existing session already
    public boolean addSession(){
        boolean success = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter session type: (LEC/TUT/LAB)");
        String type = sc.nextLine();
        System.out.println("Enter session group : (SEP1/CE3/SEA2)");
        String group = sc.nextLine();
        System.out.println("Enter session timing: (Mon 15:00 - 17:00/ Fri 09:00 - 11:00)");
        String dayTime = sc.nextLine();
        System.out.println("Enter session location: (LT19a/TRx44/SWLAB3)");
        String location = sc.nextLine();
        System.out.println("Enter session tutor name: ");
        String tutorName = sc.nextLine();
        System.out.println("Enter session's max capacity: ");
        int maxCapacity = sc.nextInt();
        sc.nextLine(); //capture \n
        if(sessionExist(group, type) >= 0){
            System.out.println("This session is already in!");
        }else{
            success = indexList.add(new Session(type, group, dayTime, location, tutorName, maxCapacity, 0));
        }
        return success;
    }

    //Remove session
    public boolean removeSession(){
        boolean success;
        Scanner sc = new Scanner(System.in);
        String group;
        int index;
        String type;

        printIndexList();
        System.out.println("Which session do you want to remove?");
        group = sc.nextLine();
        System.out.println("Remove TUT or LAB?");
        type = sc.nextLine();
        index = sessionExist(group, type);
        if(index >= 0){
            success = indexList.remove(indexList.get(index)); //if removed then return true
            return success;
        }else{
            return false; //else false
        }
    }

    //print session catalogue
    public void printIndexList(){
        int i;

        System.out.println("Sessions for " + this.courseName + " " + this.courseCode);
        for(i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i));
        }
    }

    public Session getSession(String group, String type){
        int i;
        Session obtained = null;

        for(i = 0; i < indexList.size(); i++){
            if(indexList.get(i).getType().equals(type) && indexList.get(i).getGroup().equals(group)){
                obtained = indexList.get(i);
                break;
            }
        }
        return obtained;
    }
       

    //check if session exist according to group name first
    //may need to add more though
    public int sessionExist(String sessionGroup, String type){
        int i;
        int index = -1;

        for(i = 0; i < indexList.size(); i++){  //return index if found, return -1 if not found
            if(indexList.get(i).getGroup().equals(sessionGroup) && indexList.get(i).getType().equals(type)){
                index = i;
                break;
            }
        }
        return index;
    }

    //Set results for assessment;

    //Set Course Assessment Information
    public int setAssessment(Assessment assessment){
        results.add(assessment);
        return 0;
    }

    //return 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
    public int registerStudent(Student student, String group, String type){
        int result = -3;
        for(int i = 0; i < indexList.size();  i++){
            if(indexList.get(i).getGroup().equals(group) && indexList.get(i).getType().equals(type)){
                result = indexList.get(i).addStudent(student);
                //result is -1 if full, -2 if student is already inside, 0 if success
            }
        }
        return result;
    }

    public void printSessions(){
        for (int i = 0; i < indexList.size(); i++){
            System.out.println(indexList.get(i) + " Vacancy: " 
            + indexList.get(i).getVacancy() + "/" + indexList.get(i).getMaxCapacity());
        }
    }

    public ArrayList<Assessment> getAssessment(){
        return this.results;
    }

    public String toString(){
        return (this.courseCode + ": " + this.courseName + " by " + this.courseCoordinator + " | Total AU : " + this.AU);
    }
}