package ru.netology.geo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 19.09.2022
 */
public class GeoServiceImplTest {
    GeoServiceImpl geoServiceImpl = new GeoServiceImpl();
    @DisplayName("Проверка Российского IP")
    @Test
    void shouldBeRussia() {
        assertEquals(Country.RUSSIA, geoServiceImpl.byIp("172.").getCountry());
    }
    @DisplayName("Проверка забугорного IP")
    @Test
    void shouldBeUSA() {
        assertEquals(Country.USA, geoServiceImpl.byIp("96.").getCountry());
    }
}
