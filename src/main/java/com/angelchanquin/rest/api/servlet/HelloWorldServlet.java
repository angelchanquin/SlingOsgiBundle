package com.angelchanquin.rest.api.servlet;

import com.angelchanquin.rest.api.service.HelloWorldService;
import org.apache.commons.lang.CharEncoding;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * --------------------------------------------------------------------------------------
 * HelloWorldServlet
 * --------------------------------------------------------------------------------------
 * Sample servlet.
 * -­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-­‐-‐
 * Change History
 * --------------------------------------------------------------------------------------
 * Version | Date       | Developer       | Changes
 * 1.0     | 09/05/2016 | Angel Chanquin  | Initial Creation
 * --------------------------------------------------------------------------------------
 */

@SlingServlet(
        label = "Hello world sample servlet",
        paths = {"/sample/helloworld"},
        methods = {"GET" },
        extensions = { "json" }
)
public class HelloWorldServlet extends SlingAllMethodsServlet{

    @Reference
    private HelloWorldService helloWorldService;

    @Override
    protected void doGet(
            final SlingHttpServletRequest request,
            final SlingHttpServletResponse response) throws ServletException,
            IOException {
        try {
            final JSONObject helloWorldJson = new JSONObject();
            helloWorldJson.put("say", helloWorldService.getGreeting());
            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.getWriter().write(helloWorldJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
