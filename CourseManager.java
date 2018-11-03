import java.util.*;

public class CourseManager{
    private ArrayList<Course> courseCatalog= new ArrayList<Course>();
    
    public Course getCourse(int index){
        return courseCatalog.get(index);
    }

    //rmb to implement return int
    public Course addCourse(){
        String courseName = "";
        String courseCode = "";
        String courseCoordinator = "";
        int AU = 0;
        char confirm = 'N';
        Scanner sc = new Scanner(System.in);
        Course created = null;
        
        while(confirm != 'Y'){
            System.out.println("Enter course name: (Computer Vision/Object-Orientated Design & Programming)");
            courseName = sc.nextLine();
            System.out.println("Enter course code: (CE2005/CE4001/CZ1023)");
            courseCode = sc.nextLine();
            System.out.println("Enter course AU: (2/3)");
            AU = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter name of course coordinator: ");
            courseCoordinator = sc.nextLine();
            System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator);
            System.out.println("Are you sure you want to add in this course? (Y/N)");
            confirm = sc.nextLine().charAt(0);
        }
        created = new Course(courseName, courseCode, AU, courseCoordinator);
        courseCatalog.add(created);
        System.out.println(courseCode + " " + courseName + " AU: " + AU + " by " + courseCoordinator +" is added.");
        //print out all courses after added in
        printCourseCatalog();
        return created;
    }

    //return 0 if added successfully, -1 if full, -2 if student is inside -3 if group does not exist
    public int regStudentToCourse(Student student, int i, String group, String type){
        return courseCatalog.get(i).registerStudent(student, group, type);
    }

    public int removeCourse(){
        int i = 0;
        Scanner sc = new Scanner(System.in);
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove the course");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        //return -1 if failed, return index of the removed array element if success
        if (index >= 0){
            courseCatalog.remove(i); //will return true or false depends on whether the session is created
            return index;
        }

        else{
            return index;
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }

    //return -1 if not exist , courseindex in arraylist otherwise
    public int verifyCourse(String courseCode){
        int i;
        int exist = -1;

        for (i = 0; i < courseCatalog.size(); i++){
            if (courseCatalog.get(i).getCourseCode().equals(courseCode)){
                exist = i;
            }
        }
        return exist;
    }

    public void printCourseCatalog(){
        int i;
        System.out.println("Course in current Catalog: ");
        for (i = 0; i < courseCatalog.size(); i++){
            System.out.println(courseCatalog.get(i).getCourseCode() + " " + courseCatalog.get(i).getCourseName() + " AU: " + courseCatalog.get(i).getAU() + " by " + courseCatalog.get(i).getCourseCoordinator());
        }
    }
    
    public Course getCourse(String courseCode){
        for(int i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode().equals(courseCode)) return courseCatalog.get(i);
        }
        return null;
    }
    public void addSession(){
        //int i;
        Scanner sc = new Scanner(System.in);
        String courseCode;
        boolean success = false;
        int index = -1;

        printCourseCatalog(); //print out all the course
        System.out.println("Please enter the course code that you want to create/add new session.");
        courseCode = sc.nextLine();

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode() == courseCode){
                success = courseCatalog.get(i).addSession(); //will return true or false depends on whether the session is created
                break;
            }
        }*/
        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).addSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("New session for " + courseCode + " is created successfully!");
        }else{
            System.out.println("Session is not added! Session does not exist");
        }
    }


    //find a way to delete seperate session ?
    public void removeSession(){
        int i;
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        int index = -1;
        String courseCode;

        printCourseCatalog();
        System.out.println("Please enter the course code that you want to remove session.");
        courseCode = sc.nextLine();

        index = verifyCourse(courseCode);

        if (index >= 0){
            success = courseCatalog.get(index).removeSession(); //will return true or false depends on whether the session is created
        }

        //session created successfully
        if(success){
            System.out.println("Session for " + courseCode + " is deleted successfully!");
        }
        
        else{
            System.out.println("Session is not added! Session does not exist");
        }

        /*for(i = 0; i < courseCatalog.size(); i++){
            if(courseCatalog.get(i).getCourseCode()== courseCode){
                success = courseCatalog.get(i).removeSession();
                break;
            }
        }*/
    }
  
  public void getSessions(int i){
        courseCatalog.get(i).printSessions();
  }
  
    public void checkVacancy(){
        Scanner sc = new Scanner(System.in);
        Course tempCourse;
        Session session;
        String courseCode;
        int index;
        String groupSession;
        String typeSession;

        //Interact with user to get course code
        System.out.println("Enter the course code you need check vacancy for: ");
        printCourseCatalog();
        courseCode = sc.nextLine();

        //verify if the course exist and access the session if it does
        index = verifyCourse(courseCode);
        if (index!=-1){
            tempCourse = getCourse(index);
            //get session information from the course
            System.out.println("Select the session group to check vacancy: ");
            tempCourse.printIndexList();
            groupSession = sc.nextLine();
            System.out.println("Vacancy for Lab or Tut? ");
            typeSession = sc.nextLine();
            session = tempCourse.getSession(groupSession, typeSession);
            System.out.println("Vacancy of "+ session.getType() + " " + session.getGroup() +": "+session.getNumberRegistered()+"/"+session.getMaxCapacity());
            
        }
        else{
            System.out.println("Course does not exist");
        } 
    }

    public void printSessionStudent(){
        int index; //index for course in array list.
        Scanner sc = new Scanner(System.in);
        String courseCode;
        Course obtainedCourse;
        String sessionGroup;
        String sessionType;
        Session group;
        
        printCourseCatalog();
        System.out.println("Please enter the course code that you would like to print out the student list");
        courseCode = sc.next();

        index = verifyCourse(courseCode); //
        if(index >= 0){
            obtainedCourse = courseCatalog.get(index); //getting the course from arraylist
            obtainedCourse.printIndexList();
            System.out.println("Enter the session name: ");
            sessionGroup = sc.next();
            System.out.println("Enter the session type: ");
            sessionType = sc.next();
            group = obtainedCourse.getSession(sessionGroup, sessionType); //return null if not found
            if(group != null){
                System.out.println(courseCode + ": " + sessionGroup + " " + sessionType);
                System.out.println("==========================================");
                group.printSessionStudent(); //if exist go ahead and print list
            }else{
                System.out.println("Session doesn't exist!"); 
            }
        }else{
            System.out.println("Course doesn't exist!");
        }
    }

    public void setResults(Assessment component, Student student, double marks){
        component.storeAssessmentResult(student, marks);
    }

    public ArrayList<Assessment> getAssessment(Course course){
        return course.getAssessment();
    }

}