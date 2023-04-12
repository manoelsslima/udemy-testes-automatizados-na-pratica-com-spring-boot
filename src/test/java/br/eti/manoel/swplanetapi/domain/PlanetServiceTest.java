package br.eti.manoel.swplanetapi.domain;

import br.eti.manoel.swplanetapi.common.PlanetConstants;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

/**
 * Monta o contexto de aplicação do spring boot, escanear os beans e carregar o contexto de aplicação completo.
 * <br /><br />
 * É possível indicar para carregar apenas as classes que desejamos que o teste carregue através do atributo
 * classes = {}
 */
//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class) // habilitando o mockito pro teste
public class PlanetServiceTest {

    // @Autowired
    @InjectMocks // instancia o PlanetService e cria os mocks das dependências dele
    private PlanetService planetService;

    /**
     * O Repository é uma dependência do Service. Como o teste é unitário, será necessário mockar as dependências.
     */
    // @MockBean
    @Mock
    private PlanetRepository planetRepository;

    /**
     * O nome do método de teste segue o padrão operacao_estado_retorno.
     *
     * Os testes seguem o princípio AAA (arrange, act e assert)
     * Arrange: arruma os dados pro teste, coloca os stubs
     * Act: executa a operação a ser testada
     * Assert: aferir se o método sob teste teve os resultados esperados
     */
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {

        /**
         * stub - mockando o repositório. Quando salvar o planeta, retorna o planeta salvo.
         */
        // Arrange
        when(planetRepository.save(PlanetConstants.PLANET)).thenReturn(PlanetConstants.PLANET);

        // Act
        // sut - system under test
        Planet sut = planetService.create(PlanetConstants.PLANET);

        // Assert
        Assertions.assertThat(sut).isEqualTo(PlanetConstants.PLANET);
    }
}
