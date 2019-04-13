package com.youthchina.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * Created by zhongyangwu on 4/13/19.
 */
public abstract class CustomMockMvcMatchers {
    /**
     * @param expect  expect json string
     * @param ignores jsonpath array, those part of json will not match
     * @return ResultMatcher
     * @see JsonPath
     */
    public static ResultMatcher partialContent(String expect, String... ignores) {
        return new PartialJsonResultMatchers(expect, ignores);
    }
}

/**
 * Match Json content from http response, but ignore some part of json
 */
class PartialJsonResultMatchers implements ResultMatcher {
    private final JsonExpectationsHelper jsonHelper;
    private String[] ignores;
    private String expect;

    public PartialJsonResultMatchers(String expect, String[] ignores) {
        this.expect = expect;
        this.ignores = ignores;
        this.jsonHelper = new JsonExpectationsHelper();
    }

    @Override
    public void match(MvcResult result) throws Exception {
        String actual = result.getResponse().getContentAsString();
        DocumentContext jsonActual = JsonPath.parse(actual);
        if (this.expect == null || this.expect.length() == 0) {
            throw new AssertionError("The expect json string is null or empty");
        }
        DocumentContext jsonExpect = JsonPath.parse(this.expect);
        for (String ignore : ignores) {
            jsonExpect = jsonExpect.set(ignore, jsonActual.read(ignore)); //replace the jsonExpect according to jsonContent
        }
        this.jsonHelper.assertJsonEqual(jsonExpect.jsonString(), actual, false);
    }
}