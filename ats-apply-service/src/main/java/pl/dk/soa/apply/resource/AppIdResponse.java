package pl.dk.soa.apply.resource;

public class AppIdResponse {
    private String applicationId;
    private String status;
    private String priority;

    public AppIdResponse(String applicationId, String status, String priority) {
        this.applicationId = applicationId;
        this.status = status;
        this.priority = priority;
    }

    public AppIdResponse() {
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
