    package com.skillbridge.entities;
     // change the casing->studentName.
    public class Student {
        // add variables and standard getters,setters.
        private int student_id;                 //PRIMARY KEY
        private String student_name;            //Student Name
        private String student_email;           //Student Email (NOT NULL & UNIQUE)
        private String student_college;         //Student College name

         public Student(){}

        public Student(String student_name,String student_email,String student_college){
            this.student_name=student_name;
            this.student_email=student_email;
            this.student_college=student_college;
        }

        public int getStudent_id() {
            return student_id;
        }

        public void setStudent_id(int student_id) {
            this.student_id = student_id;
        }

        public String getStudent_name() {
            return student_name;
        }

        public void setStudent_name(String student_name) {
            this.student_name = student_name;
        }

        public String getStudent_email() {
            return student_email;
        }

        public void setStudent_email(String student_email) {
            this.student_email = student_email;
        }

        public String getStudent_college() {
            return student_college;
        }

        public void setStudent_college(String student_college) {
            this.student_college = student_college;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "student_id=" + student_id +
                    ", student_name='" + student_name + '\'' +
                    ", student_email='" + student_email + '\'' +
                    ", student_college='" + student_college + '\'' +
                    '}';
        }
    }


