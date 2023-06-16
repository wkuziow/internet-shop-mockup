package pl.kuziow.internetshopmockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kuziow.internetshopmockup.service.CartService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testReceiveCart_ValidCartRequest_ReturnsOk() throws Exception {
        var cartRequest = Fixture.createValidCartRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cart request processed successfully"));

        verify(cartService, times(1)).processCartRequest(cartRequest);
    }
}
