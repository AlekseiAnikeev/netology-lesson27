package ru.netology.i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aleksey Anikeev aka AgentChe
 * Date of creation: 19.09.2022
 */
public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
    @DisplayName("проверка русской локализации")
    @Test
    void shouldGiveCorrectLocalization() {
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
    @DisplayName("проверка забугорной локализации")
    @Test
    void shouldCorrectLocalization() {
        assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
        assertEquals("Welcome", localizationService.locale(Country.GERMANY));
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }
}
