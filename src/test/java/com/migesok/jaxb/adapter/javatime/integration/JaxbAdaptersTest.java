package com.migesok.jaxb.adapter.javatime.integration;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import com.migesok.jaxb.adapter.javatime.DurationXmlAdapter;
import com.migesok.jaxb.adapter.javatime.InstantXmlAdapter;
import com.migesok.jaxb.adapter.javatime.LocalDateTimeXmlAdapter;
import com.migesok.jaxb.adapter.javatime.LocalDateXmlAdapter;
import com.migesok.jaxb.adapter.javatime.LocalTimeXmlAdapter;
import com.migesok.jaxb.adapter.javatime.MonthDayXmlAdapter;
import com.migesok.jaxb.adapter.javatime.OffsetDateTimeXmlAdapter;
import com.migesok.jaxb.adapter.javatime.OffsetTimeXmlAdapter;
import com.migesok.jaxb.adapter.javatime.PeriodXmlAdapter;
import com.migesok.jaxb.adapter.javatime.YearMonthXmlAdapter;
import com.migesok.jaxb.adapter.javatime.YearXmlAdapter;
import com.migesok.jaxb.adapter.javatime.ZoneIdXmlAdapter;
import com.migesok.jaxb.adapter.javatime.ZonedDateTimeXmlAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JaxbAdaptersTest {
    private JAXBContext jaxbContext;

    @BeforeEach
    void setUp() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(Bean.class);
    }

    @Test
    void unmarshalBean() throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setEventHandler(new DefaultValidationEventHandler());
        Bean bean = (Bean) unmarshaller.unmarshal(getClass().getResourceAsStream("/bean.xml"));

        assertNotNull(bean);
        assertEquals(bean.duration, Duration.parse("P2DT3H4M"));
        assertEquals(bean.duration, Duration.parse("P2DT3H4M"));
        assertEquals(bean.period, Period.parse("P1Y2M3W4D"));
        assertEquals(bean.instant, Instant.parse("2007-12-03T10:15:30.00Z"));
        assertEquals(bean.zonedDateTime, ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"));
        assertEquals(bean.localDate, LocalDate.parse("2014-12-31+01:00",DateTimeFormatter.ISO_DATE));
        assertEquals(bean.localDate2, LocalDate.parse("2014-12-31"));
        assertEquals(bean.localDateTime, LocalDateTime.parse("2007-12-03T10:15:30"));
        assertEquals(bean.localTime, LocalTime.parse("10:15:30"));
        assertEquals(bean.offsetDateTime, OffsetDateTime.parse("2007-12-03T10:15:30+01:00"));
        assertEquals(bean.offsetTime, OffsetTime.parse("10:15:30+01:00"));
        assertEquals(bean.month, Month.FEBRUARY);
        assertEquals(bean.dayOfWeek, DayOfWeek.WEDNESDAY);
        assertEquals(bean.year, Year.of(-2014));
        assertEquals(bean.yearMonth, YearMonth.of(2014, 12));
        assertEquals(bean.monthDay, MonthDay.of(Month.DECEMBER, 3));
        assertEquals(bean.zoneOffset, ZoneOffset.ofHoursMinutes(-12, 0));
        assertEquals(bean.zoneId, ZoneId.of("America/New_York"));
    }

    @XmlRootElement
    static class Bean {
        @XmlElement
        @XmlJavaTypeAdapter(DurationXmlAdapter.class)
        Duration duration;
        @XmlElement
        @XmlJavaTypeAdapter(PeriodXmlAdapter.class)
        Period period;
        @XmlElement
        @XmlJavaTypeAdapter(InstantXmlAdapter.class)
        Instant instant;
        @XmlElement
        @XmlJavaTypeAdapter(ZonedDateTimeXmlAdapter.class)
        ZonedDateTime zonedDateTime;
        @XmlElement
        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        LocalDate localDate;
        @XmlElement
        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        LocalDate localDate2;
        @XmlElement
        @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
        LocalDateTime localDateTime;
        @XmlElement
        @XmlJavaTypeAdapter(LocalTimeXmlAdapter.class)
        LocalTime localTime;
        @XmlElement
        @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
        OffsetDateTime offsetDateTime;
        @XmlElement
        @XmlJavaTypeAdapter(OffsetTimeXmlAdapter.class)
        OffsetTime offsetTime;
        @XmlElement
        Month month;
        @XmlElement
        DayOfWeek dayOfWeek;
        @XmlElement
        @XmlJavaTypeAdapter(YearXmlAdapter.class)
        Year year;
        @XmlElement
        @XmlJavaTypeAdapter(YearMonthXmlAdapter.class)
        YearMonth yearMonth;
        @XmlElement
        @XmlJavaTypeAdapter(MonthDayXmlAdapter.class)
        MonthDay monthDay;
        @XmlElement
        @XmlJavaTypeAdapter(ZoneIdXmlAdapter.class)
        ZoneOffset zoneOffset;
        @XmlElement
        @XmlJavaTypeAdapter(ZoneIdXmlAdapter.class)
        ZoneId zoneId;
    }
}
