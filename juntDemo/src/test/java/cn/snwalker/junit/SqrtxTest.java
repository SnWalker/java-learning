package cn.snwalker.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sqrtx 算法测试")
class SqrtxTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Nested
    @DisplayName("正常用例测试")
    class HappyPathTests {

        @ParameterizedTest(name = "输入 {0} 应该返回 {1}")
        @CsvSource({
                "0, 0",
                "1, 1",
                "4, 2",
                "8, 2",
                "9, 3",
                "2147395600, 46340" // 46340^2
        })
        void testValidInputs(int input, int expected) {
            assertEquals(expected, solution.mySqrt(input));
        }

        @Test
        @DisplayName("测试最大整数输入")
        void testIntegerMax() {
            // Integer.MAX_VALUE = 2147483647
            assertEquals(46340, solution.mySqrt(Integer.MAX_VALUE));
        }
    }

    @Nested
    @DisplayName("异常逻辑测试")
    class ExceptionTests {

        @Test
        @DisplayName("输入负数应抛出 IllegalArgumentException")
        void testNegativeInput() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> solution.mySqrt(-1),
                    "应该对负数输入抛出异常"
            );
            // 验证异常信息是否包含关键字段
            assertTrue(exception.getMessage().contains("不能为负数"));
        }
    }
}