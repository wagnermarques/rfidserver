
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("JUnit5ExampleTest")
class JUnit5ExampleTest {

	public JUnit5ExampleTest(TestInfo testInfo) {
		assertEquals("JUnit5ExampleTest", testInfo.getDisplayName());
	}

	@Test
	@DisplayName("TEST 1")
	@Tag("my-tag")
	void test1(TestInfo testInfo) {
		assertEquals("TEST 1", testInfo.getDisplayName());
		assertTrue(testInfo.getTags().contains("my-tag"));
	}

	@Test
	@DisplayName("Teste mais basico pra testar se o junit5 ta funcionando...")
	void justAnExample() {
		System.out.println("This test method should be run");
	}

	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	void palindromes(String candidate) {
		assertTrue(isPalindrome(candidate));
	}

	private boolean isPalindrome(String candidate) {		
		return true;
	}
}
