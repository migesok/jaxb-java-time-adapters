package com.migesok.jaxb.adapter.javatime;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

import static java.util.Objects.requireNonNull;

/**
 * {@link javax.xml.bind.annotation.adapters.XmlAdapter XmlAdapter} mapping any JSR-310
 * {@link java.time.temporal.TemporalAccessor TemporalAccessor} to string using provided
 * {@link java.time.format.DateTimeFormatter DateTimeFormatter}
 * <p>
 * Example:
 * <pre>
 * {@code
 *  class DottedDateXmlAdapter extends TemporalAccessorXmlAdapter<LocalDate> {
 *      public DottedDateXmlAdapter() {
 *          super(DateTimeFormatter.ofPattern("dd.MM.yyyy"), LocalDate::from);
 *      }
 *  }
 * }
 * </pre>
 *
 * @param <T> mapped temporal type
 */
public class TemporalAccessorXmlAdapter<T extends TemporalAccessor> extends XmlAdapter<String, T> {
    private final DateTimeFormatter formatter;
    private final TemporalQuery<? extends T> temporalQuery;

    /**
     * @param formatter     the formatter for printing and parsing, not null
     * @param temporalQuery the query defining the type to parse to, not null
     */
    public TemporalAccessorXmlAdapter(@Nonnull DateTimeFormatter formatter,
                                      @Nonnull TemporalQuery<? extends T> temporalQuery) {
        this.formatter = requireNonNull(formatter, "formatter must not be null");
        this.temporalQuery = requireNonNull(temporalQuery, "temporal query must not be null");
    }

    @Override
    public T unmarshal(String stringValue) {
        return stringValue != null ? formatter.parse(stringValue, temporalQuery) : null;
    }

    @Override
    public String marshal(T value) {
        return value != null ? formatter.format(value) : null;
    }
}
