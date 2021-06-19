package cinesquare.cinesquare.test;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface testMap {

    testDao getTest(testDao param);
    
}
