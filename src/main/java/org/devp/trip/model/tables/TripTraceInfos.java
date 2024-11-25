/*
 * This file is generated by jOOQ.
 */
package org.devp.trip.model.tables;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.devp.trip.model.Keys;
import org.devp.trip.model.TripManagement;
import org.devp.trip.model.tables.records.TripTraceInfosRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TripTraceInfos extends TableImpl<TripTraceInfosRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>trip_management.trip_trace_infos</code>
     */
    public static final TripTraceInfos TRIP_TRACE_INFOS = new TripTraceInfos();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TripTraceInfosRecord> getRecordType() {
        return TripTraceInfosRecord.class;
    }

    /**
     * The column <code>trip_management.trip_trace_infos.id</code>.
     */
    public final TableField<TripTraceInfosRecord, ULong> ID = createField(DSL.name("id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.user_id</code>.
     */
    public final TableField<TripTraceInfosRecord, ULong> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.location</code>.
     */
    public final TableField<TripTraceInfosRecord, String> LOCATION = createField(DSL.name("location"), SQLDataType.VARCHAR(191).nullable(false), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.latlng</code>.
     */
    public final TableField<TripTraceInfosRecord, String> LATLNG = createField(DSL.name("latlng"), SQLDataType.VARCHAR(191).nullable(false), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.created_at</code>.
     */
    public final TableField<TripTraceInfosRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(0), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.updated_at</code>.
     */
    public final TableField<TripTraceInfosRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(0), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.trip_id</code>.
     */
    public final TableField<TripTraceInfosRecord, ULong> TRIP_ID = createField(DSL.name("trip_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>trip_management.trip_trace_infos.trace_time</code>.
     */
    public final TableField<TripTraceInfosRecord, LocalTime> TRACE_TIME = createField(DSL.name("trace_time"), SQLDataType.LOCALTIME, this, "");

    private TripTraceInfos(Name alias, Table<TripTraceInfosRecord> aliased) {
        this(alias, aliased, null);
    }

    private TripTraceInfos(Name alias, Table<TripTraceInfosRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>trip_management.trip_trace_infos</code> table reference
     */
    public TripTraceInfos(String alias) {
        this(DSL.name(alias), TRIP_TRACE_INFOS);
    }

    /**
     * Create an aliased <code>trip_management.trip_trace_infos</code> table reference
     */
    public TripTraceInfos(Name alias) {
        this(alias, TRIP_TRACE_INFOS);
    }

    /**
     * Create a <code>trip_management.trip_trace_infos</code> table reference
     */
    public TripTraceInfos() {
        this(DSL.name("trip_trace_infos"), null);
    }

    public <O extends Record> TripTraceInfos(Table<O> child, ForeignKey<O, TripTraceInfosRecord> key) {
        super(child, key, TRIP_TRACE_INFOS);
    }

    @Override
    public Schema getSchema() {
        return TripManagement.TRIP_MANAGEMENT;
    }

    @Override
    public Identity<TripTraceInfosRecord, ULong> getIdentity() {
        return (Identity<TripTraceInfosRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TripTraceInfosRecord> getPrimaryKey() {
        return Keys.KEY_TRIP_TRACE_INFOS_PRIMARY;
    }

    @Override
    public List<UniqueKey<TripTraceInfosRecord>> getKeys() {
        return Arrays.<UniqueKey<TripTraceInfosRecord>>asList(Keys.KEY_TRIP_TRACE_INFOS_PRIMARY);
    }

    @Override
    public TripTraceInfos as(String alias) {
        return new TripTraceInfos(DSL.name(alias), this);
    }

    @Override
    public TripTraceInfos as(Name alias) {
        return new TripTraceInfos(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TripTraceInfos rename(String name) {
        return new TripTraceInfos(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TripTraceInfos rename(Name name) {
        return new TripTraceInfos(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<ULong, ULong, String, String, LocalDateTime, LocalDateTime, ULong, LocalTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
