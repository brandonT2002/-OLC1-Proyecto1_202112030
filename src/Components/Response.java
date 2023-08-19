package Components;
public class Response {
    String execution;
    ErrorS error;
    public Response(String execution) {
        this.execution = execution;
    }
    public Response(ErrorS error) {
        this.error = error;
    }
    public String toString() {
        return (execution != null ? execution : error) + "";
    }
}