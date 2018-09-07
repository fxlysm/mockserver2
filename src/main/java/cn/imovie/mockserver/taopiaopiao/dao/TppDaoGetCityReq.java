package cn.imovie.mockserver.taopiaopiao.dao;

import cn.imovie.mockserver.Alipay.co.FlagValidator;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
public class TppDaoGetCityReq extends TppDao {

//    private static final long serialVersionUID = -1L;




    @NotEmpty(message = "user_id cannot to null")
    private String user_id;


    @NotEmpty(message = "platform cannot to null")
    private String  platform;


    @Override
    public String toString() {
        return super.toString();
    }



    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



    public String getPlatform() {
        return platform;
    }

    public String getUser_id() {
        return user_id;
    }


}
