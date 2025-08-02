package com.sample.qwords;

import com.sample.qwords.controller.GameController;
import com.sample.qwords.model.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class GameControllerTest {

    private MockMvc mockMvc;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        gameController = new GameController();
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void testGameIndexEndpoint() throws Exception {
        mockMvc.perform(get("/game").param("user", "testuser"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attributeExists("word"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attributeExists("attempts"))
                .andExpect(model().attributeExists("result"))
                .andExpect(model().attributeExists("status"))
                .andExpect(model().attribute("message", "Make your first guess!"))
                .andExpect(model().attribute("attempts", 0))
                .andExpect(model().attribute("result", ""))
                .andExpect(model().attribute("status", GameStatus.INPROGRESS));
    }

    @Test
    public void testGameIndexWithUser() {
        Model model = new ConcurrentModel();
        String result = gameController.index("testuser", model);
        
        Assertions.assertEquals("game", result);
        Assertions.assertNotNull(model.getAttribute("word"));
        Assertions.assertEquals("Make your first guess!", model.getAttribute("message"));
        Assertions.assertEquals(0, model.getAttribute("attempts"));
        Assertions.assertEquals("", model.getAttribute("result"));
        Assertions.assertEquals(GameStatus.INPROGRESS, model.getAttribute("status"));
    }

    @Test
    public void testMakeGuessWithValidGuess() throws Exception {
        // First initialize the game
        mockMvc.perform(get("/game").param("user", "testuser"));
        
        // Then make a guess
        mockMvc.perform(post("/game")
                .param("guess", "bakery")
                .param("attempts", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attributeExists("result"))
                .andExpect(model().attributeExists("attempts"))
                .andExpect(model().attributeExists("guess"))
                .andExpect(model().attribute("guess", "bakery"))
                .andExpect(model().attribute("attempts", 1));
    }

    @Test
    public void testMakeGuessWithEmptyGuess() throws Exception {
        mockMvc.perform(post("/game")
                .param("guess", "")
                .param("attempts", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("message", "Please enter a valid guess"))
                .andExpect(model().attribute("attempts", 0))
                .andExpect(model().attribute("status", GameStatus.INPROGRESS));
    }

    @Test
    public void testMakeGuessWithNullGuess() throws Exception {
        mockMvc.perform(post("/game")
                .param("attempts", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("message", "Please enter a valid guess"))
                .andExpect(model().attribute("attempts", 0))
                .andExpect(model().attribute("status", GameStatus.INPROGRESS));
    }

    @Test
    public void testMakeGuessWithWhitespaceGuess() throws Exception {
        mockMvc.perform(post("/game")
                .param("guess", "   ")
                .param("attempts", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("message", "Please enter a valid guess"))
                .andExpect(model().attribute("attempts", 0))
                .andExpect(model().attribute("status", GameStatus.INPROGRESS));
    }

    @Test
    public void testMakeGuessMaxAttemptsReached() throws Exception {
        // First initialize the game
        mockMvc.perform(get("/game").param("user", "testuser"));
        
        // Make a guess with 4 attempts (will become 5 after increment)
        mockMvc.perform(post("/game")
                .param("guess", "bakery")
                .param("attempts", "4"))
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("attempts", 5))
                .andExpect(model().attribute("message", "Sorry, you've reached the maximum number of attempts."))
                .andExpect(model().attribute("status", GameStatus.FAILED));
    }

    @Test
    public void testGameControllerDirectMethodCalls() {
        GameController controller = new GameController();
        Model model = new ConcurrentModel();
        
        // Test index method
        String result = controller.index("testuser", model);
        Assertions.assertEquals("game", result);
        
        // Test makeGuess with valid input
        String guessResult = controller.makeGuess("bakery", 0, model);
        Assertions.assertEquals("game", guessResult);
        Assertions.assertEquals(1, model.getAttribute("attempts"));
        Assertions.assertEquals("bakery", model.getAttribute("guess"));
    }

    @Test
    public void testGameControllerWithNullGuess() {
        GameController controller = new GameController();
        Model model = new ConcurrentModel();
        
        // Initialize game first
        controller.index("testuser", model);
        
        // Test makeGuess with null input
        String result = controller.makeGuess(null, 0, model);
        Assertions.assertEquals("game", result);
        Assertions.assertEquals("Please enter a valid guess", model.getAttribute("message"));
        Assertions.assertEquals(GameStatus.INPROGRESS, model.getAttribute("status"));
    }
}