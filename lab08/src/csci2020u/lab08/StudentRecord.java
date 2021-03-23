package csci2020u.lab08;

public class StudentRecord {
    String studentID;
    float midterm;
    float assignments;
    float exam;
    float finalMark;
    String letterGrade;

    public StudentRecord(String sID, float a, float m, float e) {
        this.studentID = sID;
        this.assignments = a;
        this.midterm = m;
        this.exam = e;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public float getAssignments() {
        return this.assignments;
    }

    public float getExam() {
        return this.exam;
    }

    public float getMidterm() {
        return this.midterm;
    }

    public float getFinalMark() {
        this.finalMark = (20 * this.assignments + 30 * this.midterm + 50 * this.exam) / 100;
        return this.finalMark;
    }

    public String setGrade() {
        int mark = ((int) getFinalMark());
        if (mark >= 80 && mark <= 100){
            return "A";
        }
        else if (mark >= 70 && mark <= 79){
            return "B";
        }
        else if (mark >= 60 && mark <= 69){
            return "C";
        }
        else if (mark >= 50 && mark <= 59){
            return "D";
        }
        else if (mark >= 0 && mark <= 49){
            return "F";
        }
        else {
            return "Letter Grade could not be found.";
        }
    }
    public String getLetterGrade(){
        return this.letterGrade=setGrade();
    }
}
