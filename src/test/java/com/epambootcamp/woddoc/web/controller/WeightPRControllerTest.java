package com.epambootcamp.woddoc.web.controller;


import com.epambootcamp.woddoc.model.WeightPR;
import com.epambootcamp.woddoc.service.WeightPRService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class WeightPRControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private WeightPRController controller;

    @Mock
    private WeightPRService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void weightprs_ShouldRenderAllRecordView() throws Exception {
        // Arrange the mock behavior
        List<WeightPR> weightPRs = Arrays.asList(
                new WeightPR(1L,"deadlift", "kg", 160),
                new WeightPR(2L, "clean", "kg", 90)
        );
        when(service.findAll()).thenReturn(weightPRs);

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(get("/weightprs"))
                .andExpect(view().name("weightpr/index"))
                .andExpect(model().attribute("weightPRs",weightPRs));
        verify(service).findAll();
    }

    @Test
    public void weightprid_ShouldRenderDetailView() throws Exception {

        WeightPR weightPR = null;

        mockMvc.perform(get("/weightprs/{weightPRId}",1))
                .andExpect(model().attribute("weightPR",weightPR))
                .andExpect(view().name("weightpr/details"));
    }

    @Test
    public void edit_ShouldRenderFormView() throws Exception {
        // Arrange the mock behavior
        WeightPR weightPR = new WeightPR(1L,"Deadlift", "kg", 160);
        when(service.findById(1L)).thenReturn(weightPR);

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(get("/weightprs/{weightPRId}/edit",1L))
                .andExpect(view().name("weightpr/form"))
                .andExpect(model().attribute("DEADLIFT", nullValue()))
                .andExpect(model().attribute("action", String.format("/weightprs/%s",1L)))
                .andExpect(model().attribute("heading","Edit Weightlifting Personal Record"))
                .andExpect(model().attribute("weightPR",weightPR));
        verify(service).findById(1L);
    }

    @Test
    public void add_ShouldRedirectToRecords() throws Exception {
        // Arrange the mock behavior
        doAnswer(invocation -> {
            WeightPR w = (WeightPR) invocation.getArguments()[0];
            w.setId(1L);
            return null;
        }).when(service).save(any(WeightPR.class));

        // Act (perform the MVC request) and Assert results
        mockMvc.perform(
                post("/weightprs")
                        .param("exerciseName","Deadlift")
                        .param("weightUnit","kg")
                        .param("record", "160")
        ).andExpect(redirectedUrl("/weightprs"));
        verify(service).save(any(WeightPR.class));
    }

}



