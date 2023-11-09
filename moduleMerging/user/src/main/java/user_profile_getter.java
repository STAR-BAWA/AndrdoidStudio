public class user_profile_getter {
    private String name;
    private String email;
    private String photoUrl;
    private String phone;

    public user_profile_getter() {
        // Default constructor required for calls to DataSnapshot.getValue(UserProfile.class)
    }

    public user_profile_getter(String name, String email, String photoUrl, String phone) {
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
//  public String getPhotoUrl() {
    //    return photoUrl;
    //}

}




