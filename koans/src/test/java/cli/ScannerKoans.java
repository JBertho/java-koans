package cli;

import io.fries.koans.Koan;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.regex.MatchResult;

import static io.fries.koans.KoanAssert.__;
import static io.fries.koans.KoanAssert.assertThat;
import static io.fries.koans.cli.Scanners.fakeKeyboardInput;

class ScannerKoans {

    @Koan
    void scanner_is_initialized_with_an_input_stream_and_should_always_be_closed() {
        // Use the System.in stream to read from the user keyboard in a console app.
        Scanner scanner = new Scanner(System.in);

        // You should always close your scanner in order to close and release the underlying stream.
        scanner.close();

        // __
        scanner.next();
        // __
    }

    @Koan
    void use_the_next_method_to_read_a_string() {
        // The fakeKeyboardInput(String) method will mock the keyboard input behavior of stdin.
        fakeKeyboardInput("input");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next()).isEqualTo(__);
    }

    @Koan
    void next_only_reads_words_one_a_a_time() {
        fakeKeyboardInput("multiple words in stdin");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
    }

    @Koan
    void the_use_delimiter_method_changes_the_word_delimiter() {
        fakeKeyboardInput("multiple,words,in,stdin");
        Scanner scanner = new Scanner(System.in);

        scanner.useDelimiter(",");

        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
        assertThat(scanner.next()).isEqualTo(__);
    }

    @Koan
    void next_throws_a_no_such_element_exception_when_the_input_stream_is_empty() {
        fakeKeyboardInput("");
        Scanner scanner = new Scanner(System.in);

        // __
        scanner.next();
        // __
    }

    @Koan
    void has_next_lets_you_know_if_there_is_more_data_to_scan() {
        fakeKeyboardInput("multiple");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNext()).isEqualTo(__);
    }

    @Koan
    void use_next_line_to_read_until_next_carriage_return() {
        fakeKeyboardInput("multiple words in stdin\n");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNextLine()).isEqualTo(__);
        assertThat(scanner.nextLine()).isEqualTo(__);
    }

    @Koan
    void use_next_int_to_read_integers() {
        fakeKeyboardInput("3");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.hasNextInt()).isEqualTo(__);
        assertThat(scanner.nextInt()).isEqualTo(__);
        // Next methods exist for standard types (boolean, double, ...)
    }

    @Test
    void next_can_take_a_regex_pattern() {
        fakeKeyboardInput("cat cats catz");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
    }

    @Test
    void next_throws_an_input_mismatch_exception_when_the_pattern_is_not_respected() {
        fakeKeyboardInput("cat catch");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
        // __
        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
        // __
    }

    @Test
    void use_has_next_with_a_pattern_to_ensure_that_the_next_element_matches_your_expectations() {
        fakeKeyboardInput("cat catch");
        Scanner scanner = new Scanner(System.in);
        assertThat(scanner.next("cat[sz]?")).isEqualTo(__);
        assertThat(scanner.hasNext("cat[sz]?")).isEqualTo(__);
    }

    @Test
    void use_find_in_line_int_order_to_find_an_element_matching_your_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo(__);
        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo(__);
        assertThat(scanner.findInLine("cat[sz]?")).isEqualTo(__);
    }

    @Test
    void find_in_line_returns_null_when_no_element_matches_the_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        assertThat(scanner.findInLine("dog[sz]?")).isEqualTo("dog");
    }

    @Test
    void find_all_returns_all_occurrences_matching_the_pattern() {
        fakeKeyboardInput("One cat, two cats, three catz.");
        Scanner scanner = new Scanner(System.in);

        String[] cats = scanner.findAll("cat[sz]?").map(MatchResult::group).toArray(String[]::new);

        assertThat(cats[0]).isEqualTo(__);
        assertThat(cats[1]).isEqualTo(__);
        assertThat(cats[2]).isEqualTo(__);
    }
}
