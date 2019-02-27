package com.youthchina.zhongyang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.dto.RichTextDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by zhongyangwu on 2/26/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RichTextDTOTest {


    @Test
    public void readRichTextDTO() {
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\": {\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\": \"dtj4a\",\n" +
                "        \"text\": \"dsfgdfgdfg\",\n" +
                "        \"type\": \"unstyled\",\n" +
                "        \"depth\": 0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\": {}\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\": {\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\": \"sdfgdfgdfg\",\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        RichTextDTO richTextDTO = null;
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTO.class);
        } catch (IOException e) {
            Assert.fail();
        }
        String output = null;
        try {
            output = new ObjectMapper().writeValueAsString(richTextDTO);
        } catch (JsonProcessingException e) {
            Assert.fail();
        }


    }
}
