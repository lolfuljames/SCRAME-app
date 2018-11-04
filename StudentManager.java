import java.util.*;

public class StudentManager{
    private ArrayList<Student> studentCatalog= new ArrayList<Student>();

    public boolean addStudent(){
        String studentName = "";
        String matricNumber = "";
        String school = "";
        int acadYear = 0;
        char gender = 'N';
        char confirm = 'N';
        Scanner sc = new Scanner(System.in);

        
        while(confirm != 'Y'){
            System.out.println("Enter student's name: ");
            studentName = sc.nextLine();
            System.out.println("Enter student's matric No.: ");
            matricNumber = sc.nextLine();
            System.out.println("Enter student's school (SCSE): ");
            school = sc.nextLine();
            System.out.println("Enter student's year of study: ");
            acadYear = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter student's gender: ");
            gender = sc.nextLine().charAt(0);
            if(studentExists(matricNumber) != -1){
                System.out.println("Student with matric number " + matricNumber + " already exists!");
            }
            else{
                System.out.println("Student: "+ studentName +", Matric Number: " + matricNumber + ", "+ school + " Year " + acadYear + " , "+gender);
                System.out.println("Are you sure you want to add in this student? (Y/N)");
                confirm = sc.next().charAt(0);
            }
        }
        Student student = new Student(studentName, matricNumber, gender, school, acadYear);
        studentCatalog.add(student);
        System.out.println(student + " is added!");
        //print out all student (after added)
        printAllStudent();
        return true;
    }

    public void printAllStudent(){
        int i;
        System.out.println("All students in record: ");
        for (i = 0; i < studentCatalog.size(); i++){
            System.out.println((i+1) + ". " + studentCatalog.get(i));
        }
    }
    
//return -1 if student is not inside, studentindex otherwise
    public int studentExists(String matricNumber){
        for (int i = 0; i< studentCatalog.size(); i++){
            if(matricNumber.equals(studentCatalog.get(i).getMatricNumber())){
                return i;
            } 
        }
        return -1;
    }

    public void updateCourseTaken(Course course, int i){
        studentCatalog.get(i).registerCourse(course);
    }

    public Student getStudent(String matricNumber){
        for(int i = 0; i < studentCatalog.size(); i++){
            if(studentCatalog.get(i).getMatricNumber().equals(matricNumber)) return studentCatalog.get(i);
        }
        return null;
    }

    public void printTranscript(Student student){
        ArrayList<Course> courseRegistered = student.getCourseRegistered();
        ArrayList<Assessment> results = null;
        double totalResults = 0;
        String grade = "F";
        for(int i = 0; i < courseRegistered.size(); i++){
            totalResults = 0;
            results = courseRegistered.get(i).getAssessment();
            for(int j = 0; j < results.size(); j++){
                totalResults += results.get(j).retrieveAssessmentResult(student) * results.get(j).getWeightage() / 100;
            }
            System.out.println(courseRegistered.get(i).getCourseCode() + " " + courseRegistered.get(i).getCourseName() + " - Final Grade :" + marksToGrade(totalResults));
        }
    }

    public String marksToGrade(double marks){
        if(marks > 90) return "A+";
        if(marks > 80) return "A";
        if(marks > 75) return "A-";
        if(marks > 70) return "B+";
        if(marks > 60) return "B";
        if(marks > 50) return "B-";
        if(marks > 45) return "C+";
        if(marks > 40) return "C";
        if(marks > 30) return "D";
        return "F";
    }
}