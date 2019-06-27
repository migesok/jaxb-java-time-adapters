package io.github.threetenjaxb.core;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code XmlAdapter} mapping JSR-310 {@code ZonedDateTime} to ISO-8601 string
 * <p>
 * String format details: {@link java.time.format.DateTimeFormatter#ISO_ZONED_DATE_TIME}
 *
 * @see javax.xml.bind.annotation.adapters.XmlAdapter
 * @see java.time.ZonedDateTime
 */
public class ZonedDateTimeXmlAdapter extends TemporalAccessorXmlAdapter<ZonedDateTime> {
    public ZonedDateTimeXmlAdapter() {
        super(DateTimeFormatter.ISO_OFFSET_DATE_TIME, ZonedDateTime::from);
    }
}
