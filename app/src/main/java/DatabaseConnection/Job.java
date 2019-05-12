package DatabaseConnection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("nature")
        @Expose
        private String nature;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("location")
        @Expose
        private String location;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getJobNature() {
            return nature;
        }

        public void getJobNature(String nature) {
            this.nature = nature;
        }

        public String getCompanyName() {
            return company;
        }

        public void getCompanyName(String company) {
            this.company = company;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
}
