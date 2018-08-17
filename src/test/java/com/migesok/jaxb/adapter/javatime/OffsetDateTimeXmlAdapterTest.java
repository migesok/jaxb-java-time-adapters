package com.migesok.jaxb.adapter.javatime;

import java.time.OffsetDateTime;

class OffsetDateTimeXmlAdapterTest
        extends AbstractToStringAdapterTest<OffsetDateTime, OffsetDateTimeXmlAdapter> {

    OffsetDateTimeXmlAdapterTest() {
        super(OffsetDateTimeXmlAdapter.class);
    }

    @Override
    OffsetDateTime getNotNullValue() {
        return OffsetDateTime.parse("2007-12-03T10:15:30+01:00");
    }
}
