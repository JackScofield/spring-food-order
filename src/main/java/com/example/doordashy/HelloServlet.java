package com.example.doordashy;

import com.example.doordashy.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer= new Customer();
        customer.setEmail("apuaimanlian@gmail.com");
        customer.setPassword("123456");
        customer.setFirstName("Zeyuan");
        customer.setLastName("Pu");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = mapper.readValue(IOUtils.toString(req.getReader()), Customer.class);
        System.out.println(customer.getEmail());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        resp.getWriter().print(jsonResponse);
    }


    public void destroy() {
    }
}