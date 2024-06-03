package com.rubejuca.proyectointegrador.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RolesController.class)
public class RolesControllerTest {

  @Autowired
  private MockMvc mvc;


  @Test
  public void testGetAllRoles() throws Exception {


    mvc.perform(MockMvcRequestBuilders.get("/api/roles"))
        .andExpect(status().isOk());
  }




}
