package pl.dk.soa.apply.resourcev3;

public class AppIdResponseV3 {
    private String applicationId;
    private String priority;

    public AppIdResponseV3(String applicationId, String priority) {
        this.applicationId = applicationId;
        this.priority = priority;
    }

    public AppIdResponseV3() {
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
