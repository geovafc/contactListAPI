package com.backendapp;

import com.backendapp.model.Contact;
import com.backendapp.model.People;
import com.backendapp.service.PeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PeopleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PeopleService peopleService;

    @Test
    void successfullyCreateAPeople() throws Exception {
        People people = new People(1L,"Geovane",18,"male",null);
        Contact contact1 = new Contact(1L,"whatsapp","91998362314","",null);
        Contact contact2 = new Contact(2L,"phone","9132973939","",null);
        Contact contact3 = new Contact(3L,"email","","geo@email.com",null);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        people.setContactList(contacts);

        when(peopleService.save(any(People.class))).thenReturn(people);

        ObjectMapper objectMapper = new ObjectMapper();
        String peolpleToJson = objectMapper.writeValueAsString(people);

        ResultActions result = mockMvc.perform(post("/peoples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(peolpleToJson)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Geovane"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.genre").value("male"))

                .andExpect(jsonPath("$.contactList[0].tipo").value("whatsapp"))
                .andExpect(jsonPath("$.contactList[0].number").value("91998362314"))

                        .andExpect(jsonPath("$.contactList[1].tipo").value("phone"))
                .andExpect(jsonPath("$.contactList[1].number").value("9132973939"))

                                        .andExpect(jsonPath("$.contactList[2].tipo").value("email"))
                .andExpect(jsonPath("$.contactList[2].email").value("geo@email.com"));;
    }

    @Test
    void successfullyUpdateAPeople() throws Exception {
        People people = new People(1L,"Geovane",18,"male",null);
        peopleService.save(people);

        People peopleUpdate = new People(1L,"Geovane Freitas",18,"male",null);


        when(peopleService.update(any(Long.class),any(People.class))).thenReturn(java.util.Optional.of(peopleUpdate));

        ObjectMapper objectMapper = new ObjectMapper();
        String peolpleToJson = objectMapper.writeValueAsString(people);

        ResultActions result = mockMvc.perform(put("/peoples/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(peolpleToJson)
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Geovane Freitas"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.genre").value("male"));

    }

    @Test
    void getAllPeoples() throws Exception {
        List<People> peopleoList = new ArrayList<People>();
        peopleoList.add(new People(1L,"Geovane",18,"male",null));
        peopleoList.add(new People(2L,"Luana",15,"feminine",null));
        when(peopleService.findAll()).thenReturn(peopleoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/peoples")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }
}
