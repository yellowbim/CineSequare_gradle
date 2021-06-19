package cinesquare.cinesquare.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class testSvc {

    @Autowired
    private testMap testMap;

    public testDao getTest(testDao param){
        return testMap.getTest(param);
    }

}
