package xyz.prmusic.utils.result;

public enum ResultMessage {
    SUCCESS(200,"处理请求成功"),
    ERROR(404,"请求失败"),
    NO_CONTENT(204,"请求已经受理"),
    RESET_CONTENT(205,"请求已经受理，请刷新页面信息"),
    MOVEN_PERMANENTLY(301,"请求的资源貌似已经永久移动到了一个新的位置"),
    MOVE_TEMPORARILY(302,"将重定向到新的连接"),
    USE_PROX(305,"不合法的请求"),
    BAD_REQUEST(400,"请求错误"),
    UNAUTHORIZED(401,"当前的操作需要验证身份信息或身份认证不通过"),
    FORBIDDEN(403,"没有权限访问"),
    REQUEST_TIMEOUT(408,"请求超时"),
    CONFLICT(409,"请求出现异常,无法完成当前请求"),
    REQUEST_ENTITY_TOO_LARGE(413,"服务器拒绝当前的请求，不合法的操作"),
    UNSUPPORTED_MEDIA_TYPE(415,"不支持的类型"),
    LOCKED(423,"资源被锁定"),
    FAILED_DEPENDENCY(424,"因为之前的请求存在问题，当前的请求出现错误"),
    SERVICE_UNAVALIABLE(503,"拒绝访问");
    private int statusCode;
    private String message;
    ResultMessage(int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
