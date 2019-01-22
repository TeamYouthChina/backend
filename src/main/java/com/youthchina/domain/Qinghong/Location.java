package com.youthchina.domain.Qinghong;

public interface Location {
    interface ChineseLocation{
        public String toString(Integer location_num);

    }
    interface AmericanLocation{
        public String toString(Integer location_num);

    }
}
