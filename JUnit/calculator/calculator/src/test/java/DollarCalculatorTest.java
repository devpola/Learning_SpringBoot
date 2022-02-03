import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//mocking을 위해 mockito core, mockito junit jupyter 필요
@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    //Test 이전
    @BeforeEach
    public void init(){
        //connect가 실행될 때, 코드 상의 1000원이 대입되는 것이 아니라 3000이 대입되도록
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("hello");
    }

    @Test
    public void dollarTest(){
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10, 10));

        Assertions.assertEquals(22000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        Assertions.assertEquals(60000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }
}
