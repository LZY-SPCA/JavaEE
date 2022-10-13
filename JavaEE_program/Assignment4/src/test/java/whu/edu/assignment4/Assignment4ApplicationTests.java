package whu.edu.assignment4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import whu.edu.assignment4.controller.GoodsController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Assignment4ApplicationTests {

    @Autowired
    private GoodsController controller;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }



}
