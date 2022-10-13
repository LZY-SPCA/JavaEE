package whu.edu.assignment4;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import whu.edu.assignment4.controller.GoodsController;
import whu.edu.assignment4.entity.GoodsItem;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mvc;



    @Test
    void testAdd() throws Exception {
        GoodsItem good = new GoodsItem();
        good.setName("iPhone");
        good.setId(12);
        good.setPrice(9999.9);
        good.setNumber(10);
        good.setProvider("Apple");
        String RequestParam = JSON.toJSONString(good);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/goods").contentType(MediaType.APPLICATION_JSON).content(RequestParam)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
    }

    @Test
    void testFind() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/goods/12")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        mvc.perform(MockMvcRequestBuilders.get("/goods/11")).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
        mvc.perform(MockMvcRequestBuilders.get("/goods/abc")).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
    }

    @Test
    void testFindWithoutID() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/goods").param("name","iPhone").param("provider","Apple")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        mvc.perform(MockMvcRequestBuilders.get("/goods").param("name","iPhone").param("provider","华为")).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
    }

    @Test
    void testModify() throws Exception {
        GoodsItem good = new GoodsItem();
        good.setName("Mate50");
        good.setId(12);
        good.setPrice(5999.9);
        good.setNumber(100);
        good.setProvider("HUAWEI");
        String RequestParam = JSON.toJSONString(good);

        mvc.perform(MockMvcRequestBuilders.put("/goods/12").contentType(MediaType.APPLICATION_JSON).content(RequestParam)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        mvc.perform(MockMvcRequestBuilders.put("/goods/13").contentType(MediaType.APPLICATION_JSON).content(RequestParam)).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();

    }

    @Test
    void testDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/goods/123")).andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
        //mvc.perform(MockMvcRequestBuilders.delete("/goods/12")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

}
