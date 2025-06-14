    package com.skillbridge.entities;
     // change the casing->studentName.
    public class Student {
        // add variables and standard getters,setters.
        private int studentId;                 //PRIMARY KEY
        private String studentName;            //Student Name
        private String studentEmail;           //Student Email (NOT NULL & UNIQUE)
        private String studentCollege;         //Student College name

         public Student(){}

        public Student(int studentId,String studentName,String studentEmail,String studentCollege){
            this.studentId=studentId;
            this.studentName=studentName;
            this.studentEmail=studentEmail;
            this.studentCollege=studentCollege;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }

        public String getStudentCollege() {
            return studentCollege;
        }

        public void setStudentCollege(String studentCollege) {
            this.studentCollege = studentCollege;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "student_id=" + studentId +
                    ", student_name='" + studentName + '\'' +
                    ", student_email='" + studentEmail + '\'' +
                    ", student_college='" + studentCollege + '\'' +
                    '}';
        }
    }


