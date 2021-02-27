package com.czg.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("illness")
public class DataBean implements Serializable {

    // ASSIGN_ID(雪花算法)如果不设置type值，默认则使用 IdType.ASSIGN_ID 策略(自 3.3.0 起)。
    // 该策略会使用雪花算法自动生成主键D，主键类型为Long 或String(分别对应MySQL的表字段为BIGINT和VARCHAR)
    // @TableId(type = IdType.ASSIGN_ID)

    // ASSIGN_UUID(不含中划线的UUID)
    // 如果使用IdType.ASSIGN_UUID策略，则会自动生成不含中划线的UUID作为主键。主键类型为String，对应MySQL的表字段为VARCHAR(32)

    // AUTO(数据库ID自增)对于像MySQL这样的支持主键自动递增的数据库，我们可以使用IdType.AUTO策略。

    //其他详情百度查询

    @TableId()
    private Long id;
    private String area;
    private int nowConfirm;
    private int confirm;
    private int heal;
    private int dead;
}
