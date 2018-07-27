package cloud.common;

import lombok.Data;

@Data
public class Result {


    public Result(String status) {
        this.status = status;
    }

    public Result(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public Result(String status, String description, Object obj) {
        this.status = status;
        this.description = description;
        this.detail = obj;
    }

    public Result(String status, String description, Object obj, Object obj2) {
        this.status = status;
        this.description = description;
        this.detail = obj;
        this.more = obj2;
    }

    String status;

    String description;

    Object detail;

    Object more;

}
