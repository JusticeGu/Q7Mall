package com.q7w.DTO;

import com.q7w.Entity.Goods;
import com.q7w.Entity.Goods_images;
import com.q7w.common.util.OutputConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/4/26 20:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spu implements OutputConverter<Spu, Goods> {
    private int id;
    private String name;
    private float original;

    private Goods_images imgurl;
}
