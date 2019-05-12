package com.example.nazmussakib.firebaseapp;







public class jobInfo {
    private String title;
    private String jobNature;
    private String companyName;
    private String experienceTime;
    private boolean isLinked;
    private int position;



    public jobInfo(String title, String jobNature, String companyName, String experienceTime) {
        this.title = title;
        this.jobNature = jobNature;
        this.companyName = companyName;
        this.experienceTime = experienceTime;

    }

    public jobInfo(String title, String jobNature, String companyName, String experienceTime,boolean isLinked)
    {
        this.title = title;
        this.jobNature = jobNature;
        this.companyName = companyName;
        this.experienceTime = experienceTime;
        this.isLinked = isLinked;
    }

    public int getPosition() {
        return position;
    }
    public void setLinked(boolean linked) {
        isLinked = linked;
    }
    public boolean getLinked()
    {
        return isLinked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExperienceTime() {
        return experienceTime;
    }

    public void setExperienceTime(String experienceTime) {
        this.experienceTime = experienceTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        return true;
    }



}
