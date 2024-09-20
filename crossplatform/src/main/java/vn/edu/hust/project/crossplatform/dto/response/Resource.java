package vn.edu.hust.project.crossplatform.dto.response;

import org.springframework.http.HttpStatus;

public class Resource {
    private Object data;
    private Object meta;
    public Resource(Object data) {
        this.meta = new MetaResource((long) HttpStatus.OK.value(), "Success");
        this.data = data;
    }

    public Resource(Long code, String message){
        this.meta = new MetaResource(code, message);
        this.data = null;
    }

}
