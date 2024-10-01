package vn.edu.hust.project.crossplatform.dto.response;

import org.springframework.http.HttpStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;

public class Resource {
    private Object data;
    private Object meta;

    public Resource(Object data) {
        this.meta = new MetaResource(ResponseCode.OK);
        this.data = data;
    }

    public Resource(Long code, String message){
        this.meta = new MetaResource(code, message);
        this.data = null;
    }

    public Resource(Long code, String message, Object data){
        this.meta = new MetaResource(code, message);
        this.data = data;
    }

    public Resource(ResponseCode responseCode, Object data){
        this.meta = new MetaResource(responseCode);
        this.data = data;
    }

}
