package educationexplorer.dradtech.com.educationexplorer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by siddhant on 7/25/17.
 */

public class ReturnResponse implements Serializable {

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("affilation")
    @Expose
    private String affilation;

    @SerializedName("college_name")
    @Expose
    private String college_name;

    @SerializedName("district")
    @Expose
    private String district;

    @SerializedName("faculty")
    @Expose
    private String faculty;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("url")
    @Expose
    private String url;

    public ReturnResponse(String address, String affilatiion, String college_name,
                          String district, String faculty, String logo, String url){
        this.address = address;
        this.affilation = affilatiion;
        this.college_name = college_name;
        this.district = district;
        this.faculty = faculty;
        this.logo = logo;
        this.url = url;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAffilation() {
        return affilation;
    }

    public void setAffilation(String affilation) {
        this.affilation = affilation;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ReturnResponse {" +
                "  Address:'"+ address + '\'' +
                ", Affilation: '" + affilation + '\'' +
                ", College Name:" + college_name +
                ", District:" + district +
                ", Faculty:"+ faculty +
                ", logo:" + logo +
                ", url:"+ url +
                '}';
    }
}
