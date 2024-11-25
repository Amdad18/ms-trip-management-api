/*
 * This file is generated by jOOQ.
 */
package org.devp.trip.model.tables.records;


import java.time.LocalDateTime;
import java.time.LocalTime;

import org.devp.trip.model.tables.TripTraceInfos;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TripTraceInfosRecord extends UpdatableRecordImpl<TripTraceInfosRecord> implements Record8<ULong, ULong, String, String, LocalDateTime, LocalDateTime, ULong, LocalTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>trip_management.trip_trace_infos.id</code>.
     */
    public TripTraceInfosRecord setId(ULong value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.user_id</code>.
     */
    public TripTraceInfosRecord setUserId(ULong value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.user_id</code>.
     */
    public ULong getUserId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.location</code>.
     */
    public TripTraceInfosRecord setLocation(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.location</code>.
     */
    public String getLocation() {
        return (String) get(2);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.latlng</code>.
     */
    public TripTraceInfosRecord setLatlng(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.latlng</code>.
     */
    public String getLatlng() {
        return (String) get(3);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.created_at</code>.
     */
    public TripTraceInfosRecord setCreatedAt(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.updated_at</code>.
     */
    public TripTraceInfosRecord setUpdatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.trip_id</code>.
     */
    public TripTraceInfosRecord setTripId(ULong value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.trip_id</code>.
     */
    public ULong getTripId() {
        return (ULong) get(6);
    }

    /**
     * Setter for <code>trip_management.trip_trace_infos.trace_time</code>.
     */
    public TripTraceInfosRecord setTraceTime(LocalTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>trip_management.trip_trace_infos.trace_time</code>.
     */
    public LocalTime getTraceTime() {
        return (LocalTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, ULong, String, String, LocalDateTime, LocalDateTime, ULong, LocalTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<ULong, ULong, String, String, LocalDateTime, LocalDateTime, ULong, LocalTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return TripTraceInfos.TRIP_TRACE_INFOS.ID;
    }

    @Override
    public Field<ULong> field2() {
        return TripTraceInfos.TRIP_TRACE_INFOS.USER_ID;
    }

    @Override
    public Field<String> field3() {
        return TripTraceInfos.TRIP_TRACE_INFOS.LOCATION;
    }

    @Override
    public Field<String> field4() {
        return TripTraceInfos.TRIP_TRACE_INFOS.LATLNG;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return TripTraceInfos.TRIP_TRACE_INFOS.CREATED_AT;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return TripTraceInfos.TRIP_TRACE_INFOS.UPDATED_AT;
    }

    @Override
    public Field<ULong> field7() {
        return TripTraceInfos.TRIP_TRACE_INFOS.TRIP_ID;
    }

    @Override
    public Field<LocalTime> field8() {
        return TripTraceInfos.TRIP_TRACE_INFOS.TRACE_TIME;
    }

    @Override
    public ULong component1() {
        return getId();
    }

    @Override
    public ULong component2() {
        return getUserId();
    }

    @Override
    public String component3() {
        return getLocation();
    }

    @Override
    public String component4() {
        return getLatlng();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdatedAt();
    }

    @Override
    public ULong component7() {
        return getTripId();
    }

    @Override
    public LocalTime component8() {
        return getTraceTime();
    }

    @Override
    public ULong value1() {
        return getId();
    }

    @Override
    public ULong value2() {
        return getUserId();
    }

    @Override
    public String value3() {
        return getLocation();
    }

    @Override
    public String value4() {
        return getLatlng();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedAt();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdatedAt();
    }

    @Override
    public ULong value7() {
        return getTripId();
    }

    @Override
    public LocalTime value8() {
        return getTraceTime();
    }

    @Override
    public TripTraceInfosRecord value1(ULong value) {
        setId(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value2(ULong value) {
        setUserId(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value3(String value) {
        setLocation(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value4(String value) {
        setLatlng(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value5(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value6(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value7(ULong value) {
        setTripId(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord value8(LocalTime value) {
        setTraceTime(value);
        return this;
    }

    @Override
    public TripTraceInfosRecord values(ULong value1, ULong value2, String value3, String value4, LocalDateTime value5, LocalDateTime value6, ULong value7, LocalTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TripTraceInfosRecord
     */
    public TripTraceInfosRecord() {
        super(TripTraceInfos.TRIP_TRACE_INFOS);
    }

    /**
     * Create a detached, initialised TripTraceInfosRecord
     */
    public TripTraceInfosRecord(ULong id, ULong userId, String location, String latlng, LocalDateTime createdAt, LocalDateTime updatedAt, ULong tripId, LocalTime traceTime) {
        super(TripTraceInfos.TRIP_TRACE_INFOS);

        setId(id);
        setUserId(userId);
        setLocation(location);
        setLatlng(latlng);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setTripId(tripId);
        setTraceTime(traceTime);
    }
}