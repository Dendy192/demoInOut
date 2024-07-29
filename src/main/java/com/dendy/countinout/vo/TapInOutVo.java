package com.dendy.countinout.vo;

import java.util.List;

public class TapInOutVo {
    private List<TapInOutDetailVo> data;

    public List<TapInOutDetailVo> getData() {
        return data;
    }

    public void setData(List<TapInOutDetailVo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "dashboard{" +
                "data=" + data.toString() +
                '}';
    }
}
