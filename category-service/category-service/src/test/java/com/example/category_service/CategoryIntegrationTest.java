package com.example.category_service;

import com.example.category_service.model.CategoryModel;
import com.example.category_service.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void TestCreateCategory() throws Exception{
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("perrine");

        mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(categoryModel)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("perrine"));

    }

    @Test
    public void TestGetAllCategory() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TestGetCategoryById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/category/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void TestUpdateCategory() throws Exception{

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("perrine bella");

        mockMvc.perform(MockMvcRequestBuilders.put("/category/update/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryModel)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath(("$.name")).value("perrine bella"));

    }

    @Test
    public void TestDeleteCategory() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/category/delete/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}
