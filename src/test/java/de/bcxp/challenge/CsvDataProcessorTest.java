package de.bcxp.challenge;
import de.bcxp.challenge.DataProcessors.csvDataProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


class CsvDataProcessorTest {

    private csvDataProcessor processor;

    @BeforeEach
    void setUp() {
// random data for as List in List
        List<List<String>> data = Arrays.asList(
                Arrays.asList("Name", "column_1", "column_2"),
                Arrays.asList("A", "10000", "2000"),
                Arrays.asList("B", "15000", "3000"),
                Arrays.asList("C", "20000", "5000"));
        processor = new csvDataProcessor(data);

    }

    @Test
    void testEmptyData() {
// only header with no data underneath
        List<List<String>> emptyData = Arrays.asList(
                Arrays.asList("Name", "column_1", "column_2")
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new csvDataProcessor(emptyData);
        });

        assertTrue(exception.getMessage().contains("empty"));
    }

    @Test
    void testGetIndexOfHeader() {
        assertEquals(1, processor.getIndexOfHeader("column_1"));
        assertEquals(-1, processor.getIndexOfHeader("column_99")); // Not found case
    }

    @Test
    void testCorrectNumberFormat() {
        assertEquals("1000.50", processor.correctNumberFormat("1.000,50"));
        assertEquals("10.25", processor.correctNumberFormat("10,25"));
        assertEquals("1234567.89", processor.correctNumberFormat("1.234.567,89"));
    }

    @Test
    void testGetColumnsDifference() {
        List<Double> difference = processor.getColumnsDifference(1, 2);
        assertEquals(Arrays.asList(8000.0, 12000.0, 15000.0), difference);
    }

    @Test
    void testGetColumnsDifference_InvalidIndex() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            processor.getColumnsDifference(5, 2); // Invalid column index
        });

        assertTrue(exception.getMessage().contains("Index"));
    }

    @Test
    void testGetColumnsDivision() {
        List<Double> division = processor.getColumnsDivision(1, 2);
        assertEquals(Arrays.asList(5.0, 5.0, 4.0), division);
    }


    @Test
    void testGetColumnsDivision_DivideByZero() {
        List<List<String>> data = Arrays.asList(
                Arrays.asList("Name", "column_1", "column_2"),
                Arrays.asList("A", "10000", "0") // Division by zero case
        );
        csvDataProcessor processorWithZero = new csvDataProcessor(data);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            processorWithZero.getColumnsDivision(1, 2);
        });

        assertEquals("The Divisor is 0 therefore it can't be divided!", exception.getMessage());
    }

    @Test
    void testGetFromRecord() {
        List<String> record = processor.getFromRecord(0);
        assertEquals(Arrays.asList("A", "10000", "2000"), record);
    }

    @Test
    void testGetFromRecord_InvalidIndex() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            processor.getFromRecord(10); // Index out of bounds
        });

        assertTrue(exception.getMessage().contains("Index"));
    }
}
