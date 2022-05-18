package com.sg.prime.api.resource;

import com.sg.prime.api.service.PrimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PrimeResource.class)
public class PrimeResourceTest {

    private MockMvc mockMvc;

    @Autowired
    private PrimeResource primeResource;

    @MockBean
    private PrimeService primeService;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(primeResource).build();
    }

    @Test
    void should_return_status_ok_when_get_list_of_last_searches() throws Exception {
        mockMvc.perform(
                get("/primes?limit=10")
        ).andExpect(status().isOk());

    }


}
