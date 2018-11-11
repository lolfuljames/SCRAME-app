import java.util.*;
import java.io.*;

/**
 * An entity class to store relevant information of a student
 */
public class Student implements Serializable{
    private String studentName;
    private String matricNumber;
    private String school;
    private int acadYear;
    private char gender;
    private ArrayList<Course> coursesRegistered = new ArrayList<Course>(); //arraylist of course codes registered
    private int totalAU = 0;

    /**
     * Constructor for Student object, instantiating the following attributes
     * @param studentName String the name of the Student 
     * @param matricNumber String the matriculation number to identify the Student
     * @param gender char the Student's gender, M for male and F for female
     * @param school char the abbrevation name of Student's home-based school/faculty. Example SCSE for School of Computer Science and Engineering.
     * @param acadYear int the school year that the student is currently studying.
     */
    public Student(String studentName,String matricNumber, char gender, String school, int acadYear){
        this.studentName = studentName;
        this.matricNumber = matricNumber;
        this.gender = gender;
        this.school = school;
        this.acadYear = acadYear;
    }

    /**
     * A method to register calling Student under Course object being parsed in.
     * @param course Course object which Student is going to be registered under.
     * @see #coursesRegistered.
     */
    public void registerCourse(Course course){
        for(int i = 0; i < coursesRegistered.size(); i++){
            if(coursesRegistered.get(i) == course) return; //if course is already registered, ignore
        }
        coursesRegistered.add(course);
    }
    /**
     * A method to de-register the calling Student under the Course object being parsed in.
     * @param course Course object which Student is currently registerd and need to be removed/de-registered from.
     * @return boolean true if removal is successful and false if otherwise.
     * @see #coursesRegistered for more information about boolean value being returned.
     */
    public boolean deregisterCourse(Course course){
        return coursesRegistered.remove(course);
    }

    /**
     * A method to return the list of Course objects that the calling Student is currently registered under.
     * @return ArrayList<Course> an ArrayList of Course objects the Student is currently registered under.
     * @see #coursesRegistered for more usage of the returned array.
     */
    public ArrayList<Course> getCourseRegistered(){
        return this.coursesRegistered;
    }
    /**
     * A method to return the Name of the Student object calling this method.
     * @return String the name of the Student.
     */
    public String getName(){
        return this.studentName;
    }
    /**
     * A method to return the matriculation number of the Student object calling this method.
     * @return String the matriculation number of the Student.
     */
    public String getMatricNumber(){
        return this.matricNumber;
    }
    /**
     * A method to return the abbrevation of the school that the Student is based in.
     * @return String the abbrevation of school (for instance SCSE for School of Computer Science and Engineering).
     */
    public String getSchool(){
        return this.school;
    }
    /**
     * A method to return the academic year the calling Student is currently studying in.
     * @return int the school year the Student is studying in.
     */
    public int getAcadYear(){
        return this.acadYear;
    }

    /**
     * A method to return the gender of the calling Student object.
     * @return char describing the gender of the Student. 'M' for Male or 'F' for Female, assuming biological gender only
     */
    public char getGender(){
        return this.gender;
    }

    /**
     * An overrided method to help in printing out the Student object. Original toString() return the address of the object instead. 
     * @return String a String object containing the various information of the Student (in printing format for a neater program).
     */
    public String toString(){
        String nameFormat = "|| %1$-20s ";
        String matricFormat = "| %2$-25s ";
        String schoolFormat = "| %3$4s/";
        String yearFormat = "%4$-1s |";
        String genderFormat = " %5$-1s ||";
        String format = nameFormat.concat(matricFormat).concat(schoolFormat).concat(yearFormat).concat(genderFormat);
        return String.format(format, this.studentName, "Matric Number: " +  this.matricNumber, this.school, this.acadYear,"Gender : " +  this.gender);
    }
}
