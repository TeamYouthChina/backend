package com.youthchina.zhongyang;

/**
 * Created by zhongyangwu on 2/26/19.
 */
/*
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

        RichTextResponseDTO richTextDTO = null;
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextResponseDTO.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertEquals("sdfgdfgdfg", richTextDTO.getPreviewText());
        String output = null;
        try {
            output = new ObjectMapper().writeValueAsString(richTextDTO);
            System.out.println(output);
        } catch (JsonProcessingException e) {
            Assert.fail();
        }


    }
}*/
