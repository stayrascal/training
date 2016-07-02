package com.thoughtworks;

import com.thoughtworks.util.RequestContextUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AbstractTest {

    public static final String USERNAME = "unit_test";

    protected void setUp(){
        RequestContextUtil.setUsername(AbstractTest.USERNAME);
    }
}
