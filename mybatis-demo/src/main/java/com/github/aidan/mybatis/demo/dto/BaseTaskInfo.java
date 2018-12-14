package com.github.aidan.mybatis.demo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class BaseTaskInfo implements Serializable{


    protected Integer threshold;

    protected Boolean ispopup;

    protected Quality quality;

    protected Boolean alarmed;

  //  Cannot get mapping results whether it is private or protected
/*    private Integer threshold;

    private Boolean ispopup;

    private Quality quality;

    private Boolean alarmed;*/

}
