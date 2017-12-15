package bean;

import bean.Person;

public class Student extends Person {
    private long enrolledSince;

    public long getEnrolledSince() {
        return enrolledSince;
    }

    public void setEnrolledSince(long enrolledSince) {
        this.enrolledSince = enrolledSince;
    }
}
