package lime1st.example.clean;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import lime1st.example.clean.user.adapter.in.web.dto.request.CreateUserRequest;
import lime1st.example.clean.user.adapter.in.web.dto.request.UpdateUserRequest;
import lime1st.example.clean.user.application.dto.query.FindUserQuery;
import lime1st.example.clean.user.application.dto.query.ListUserQuery;
import lime1st.example.clean.user.application.port.out.DeleteUserPort;
import lime1st.example.clean.user.application.port.out.LoadUserPort;
import lime1st.example.clean.user.application.port.out.UpdateUserPort;
import lime1st.example.clean.user.application.service.ReadUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class CleanApplicationApiV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReadUserService readUserService;

    @MockitoBean
    private LoadUserPort loadUserPort;

    @MockitoBean
    private UpdateUserPort updateUserPort;

    @MockitoBean
    private DeleteUserPort deleteUserPort;

    @Test
    @DisplayName("User 생성 요청")
    public void testPostUsersSuccess() throws Exception {

        var request = new CreateUserRequest("name", "email@mail.com", "password");

        mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated()
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "USER 생성 성공",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("USER V1")
                                                .summary("USER 생성")
                                                .description(
                                                        """
                                                                ## USER 생성 엔드포인트 입니다.
                                                                
                                                                ---
                                                                
                                                                Location Header 가 리턴됩니다.
                                                                
                                                                """)
                                                .build()
                                )

                        )
                );
    }

    @Test
    @DisplayName("User 전체 목록 가져오기")
    public void testGetUsersSuccess() throws Exception {
        var query = new ListUserQuery(List.of(
                new FindUserQuery(
                        UUID.randomUUID(),
                        "name",
                        "email@mail.com",
                        "password",
                        null, null)));

        given(readUserService.readUsers()).willReturn(query);

        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/users")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "USER 목록 조회 성공",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("USER V1")
                                                .summary("USER 목록 조회")
                                                .description(
                                                        """
                                                                ## USER 목록 조회 엔드포인트 입니다.
                                                                
                                                                ---
                                                                
                                                                USER LIST 가 리턴됩니다.
                                                                
                                                                """)
                                                .build()
                                )

                        )
                );
    }

    @Test
    @DisplayName("User 가져오기")
    public void testGetUserSuccess() throws Exception {
        // given
        var userId = UUID.randomUUID();
        var query = FindUserQuery.builder()
                .id(userId)
                .name("name")
                .email("email@mail.com")
                .password("password")
                .build();

        given(readUserService.readUserById(userId))
                .willReturn(query);

        // when & then
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/api/users/" + userId)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk()
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "USER 조회 성공",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("USER V1")
                                                .summary("USER 조회")
                                                .description(
                                                        """
                                                                ## USER 조회 엔드포인트 입니다.
                                                                
                                                                ---
                                                                
                                                                USER 가 리턴됩니다.
                                                                
                                                                """)
                                                .build()
                                )

                        )
                );
    }

    @Test
    @DisplayName("User 수정하기")
    public void testUpdateUserSuccess() throws Exception {
        // given
        var userId = UUID.randomUUID();
        var request = new UpdateUserRequest("new@email.com", "newPassword");
        var query = FindUserQuery.builder()
                .id(userId)
                .name("name")
                .email("email@mail.com")
                .password("password")
                .build();

        when(loadUserPort.loadUserById(userId)).thenReturn(Optional.of(query));

        mockMvc.perform(
                        RestDocumentationRequestBuilders.put("/api/users/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isNoContent()
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "USERS 수정 성공",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("USER V1")
                                                .summary("USER 수정")
                                                .description(
                                                        """
                                                                ## USER 수정 엔드포인트 입니다.
                                                                
                                                                ---
                                                                
                                                                성공 시 NO_CONTENT(204) 가 리턴됩니다.
                                                                
                                                                """)
                                                .build()
                                )

                        )
                );
    }

    @Test
    @DisplayName("User 삭제하기")
    public void testDeleteUserSuccess() throws Exception {
        // given
        var userId = UUID.randomUUID();

        mockMvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/users/" + userId)
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().isNoContent()
                )
                .andDo(
                        MockMvcRestDocumentationWrapper.document(
                                "USERS 삭제 성공",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                                ResourceDocumentation.resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("USER V1")
                                                .summary("USER 삭제")
                                                .description(
                                                        """
                                                                ## USER 삭제 엔드포인트 입니다.
                                                                
                                                                ---
                                                                
                                                                성공 시 NO_CONTENT(204) 가 리턴됩니다.
                                                                
                                                                """)
                                                .build()
                                )

                        )
                );
    }
}
