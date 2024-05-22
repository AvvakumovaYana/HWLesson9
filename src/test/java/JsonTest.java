import JsonModel.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

public class JsonTest {

    private ClassLoader cl = JsonTest.class.getClassLoader();

    @DisplayName("Проверка содержания json файла")
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("Doc 4.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(reader, Person.class);

            Assertions.assertEquals(1, person.ID );
            Assertions.assertEquals("Marina", person.FirstName);
            Assertions.assertEquals("Lebedeva", person.SecondName);
            Assertions.assertEquals("20.05.1990", person.Birthday);
            Assertions.assertEquals("test@gmail.com", person.Email[0]);
            Assertions.assertEquals("test@yandex.ru", person.Email[1]);
            Assertions.assertEquals("London", person.City);
        }
    }
}