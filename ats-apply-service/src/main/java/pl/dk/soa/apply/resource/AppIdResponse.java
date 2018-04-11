package pl.dk.soa.apply.resource;

public class AppIdResponse {
    private String applicationId;
    private String status;

    public AppIdResponse(String applicationId, String status) {
        this.applicationId = applicationId;
        this.status = status;
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
}
