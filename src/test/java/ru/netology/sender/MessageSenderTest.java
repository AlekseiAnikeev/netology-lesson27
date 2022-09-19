package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 19.09.2022
 */
@ExtendWith(MockitoExtension.class)
public class MessageSenderTest {
    @Mock
    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSenderImpl messageSenderImpl;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    @DisplayName("Тест русских IP")
    void shouldBeRussia() {
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> headers = Map.of("x-real-ip", "172.");
        assertEquals("Добро пожаловать", messageSenderImpl.send(headers));
    }

    @Test
    @DisplayName("Тест забугорных IP")
    void shouldBeEnglish() {
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(USA)).thenReturn("Welcome");
        Map<String, String> headers = Map.of("x-real-ip", "96.");
        assertEquals("Welcome", messageSenderImpl.send(headers));
    }
}
