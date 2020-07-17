package project.assistant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.assistant.Model.Beans.Gender;
import project.assistant.Model.Beans.RoleBean;
import project.assistant.Model.Beans.UserBean;
import project.assistant.Repositories.IRoleRepository;


import java.net.URL;
import java.util.HashSet;
import java.util.Set;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = AssistantApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private MockMvc mvc;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private IRoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/user");
    }
    private UserBean createRandomUser() {
        Set<RoleBean> set=new HashSet<RoleBean>();
        set.add(roleRepository.findById(new Long(1)).get());
        UserBean userBean = new UserBean(){{
            setFirstName("omid");
            setLastName("rajabi");
            setUsername("omidrajabi.6868@yahoo.com");
            setRoleBeans(set);
            setGender(Gender.MALE);
            setPassword("123456");
        }};
        return userBean;
    }

    @Test
    public void whenGetAllUsers_thenOK() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(base.toString())
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk());
    }
//
//    @Test
//    public void whenGetUsersByEmail_thenOK() throws Exception{
//        UserBean userBean = createRandomUser();
//
//        ResponseEntity<UserBean> response=template.getForEntity(
//                base.toString()+"/username?username="+userBean.getUsername(),UserBean.class);
//
//        assertEquals(((Integer) HttpStatus.OK.value()), ((Integer)response.getStatusCode().value()));
//    }
//
//
//    @Test
//    public void addUser()throws Exception{
//        ResponseEntity<UserBean> response=template.postForEntity(base.toString(),createRandomUser(),UserBean.class);
//        assertEquals(((Integer) HttpStatus.CREATED.value()), ((Integer) response.getStatusCodeValue()));
//    }
//    @Test
//    public void updateUser()throws Exception{
//       template.put(base.toString()+"/"+createRandomUser().getId(),createRandomUser());
//    }

}
