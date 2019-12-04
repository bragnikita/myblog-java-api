package jp.bragnikita.myblogapi.controllers;

import jp.bragnikita.myblogapi.models.ModelObjectRefs;
import jp.bragnikita.myblogapi.models.Post;
import jp.bragnikita.myblogapi.repos.PostsRepo;
import jp.bragnikita.myblogapi.services.PostsOperations;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PostsOperations postsOperations;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void notExistsPostIdShouldReturn404() throws Exception {
        this.mockMvc.perform(get("/posts/1")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void existsPostIdShouldReturnPost() throws Exception {
        var post = new Post(1L);
        Mockito.when(postsOperations.get(Mockito.eq(1L))).thenReturn(post);
        this.mockMvc.perform(get("/posts/" + Long.toString(1))).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1));
        Mockito.verify(postsOperations, Mockito.times(1)).get(Mockito.eq(1L));
    }

    @Test
    public void existsPostIdShouldReturnFulfilledPost() throws Exception {
        var post = new Post(1L);
        post.setTitle("Post 1");
        post.setTextContent("Text content");
        post.setCover(new ModelObjectRefs.ImageRef());
        post.getCover().id = 2L;
        post.setStatus(Post.PostStatus.PUBLISHED);
        post.setVisibility(Post.PostVisibility.PUBLIC);
        Mockito.when(postsOperations.get(Mockito.anyLong())).thenReturn(post);

        this.mockMvc.perform(get("/posts/" + Long.toString(1)))
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.textContent").value(post.getTextContent()))
                .andExpect(jsonPath("$.visibility").value(post.getVisibility().name()))
                .andExpect(jsonPath("$.status").value(post.getStatus().name()))
                //.andExpect(jsonPath("$.cover.url").value(Matchers.startsWith("http://")))
                .andExpect(jsonPath("$.cover.id").value(2));
    }

}
