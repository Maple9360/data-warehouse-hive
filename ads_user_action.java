// ORM class for table 'ads_user_action'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri Jun 24 22:26:42 CST 2022
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ads_user_action extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("cart_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.cart_count = (Long)value;
      }
    });
    setters.put("dt", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.dt = (java.sql.Date)value;
      }
    });
    setters.put("good_detail_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.good_detail_count = (Long)value;
      }
    });
    setters.put("home_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.home_count = (Long)value;
      }
    });
    setters.put("order_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.order_count = (Long)value;
      }
    });
    setters.put("payment_count", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.payment_count = (Long)value;
      }
    });
    setters.put("recent_days", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ads_user_action.this.recent_days = (Long)value;
      }
    });
  }
  public ads_user_action() {
    init0();
  }
  private Long cart_count;
  public Long get_cart_count() {
    return cart_count;
  }
  public void set_cart_count(Long cart_count) {
    this.cart_count = cart_count;
  }
  public ads_user_action with_cart_count(Long cart_count) {
    this.cart_count = cart_count;
    return this;
  }
  private java.sql.Date dt;
  public java.sql.Date get_dt() {
    return dt;
  }
  public void set_dt(java.sql.Date dt) {
    this.dt = dt;
  }
  public ads_user_action with_dt(java.sql.Date dt) {
    this.dt = dt;
    return this;
  }
  private Long good_detail_count;
  public Long get_good_detail_count() {
    return good_detail_count;
  }
  public void set_good_detail_count(Long good_detail_count) {
    this.good_detail_count = good_detail_count;
  }
  public ads_user_action with_good_detail_count(Long good_detail_count) {
    this.good_detail_count = good_detail_count;
    return this;
  }
  private Long home_count;
  public Long get_home_count() {
    return home_count;
  }
  public void set_home_count(Long home_count) {
    this.home_count = home_count;
  }
  public ads_user_action with_home_count(Long home_count) {
    this.home_count = home_count;
    return this;
  }
  private Long order_count;
  public Long get_order_count() {
    return order_count;
  }
  public void set_order_count(Long order_count) {
    this.order_count = order_count;
  }
  public ads_user_action with_order_count(Long order_count) {
    this.order_count = order_count;
    return this;
  }
  private Long payment_count;
  public Long get_payment_count() {
    return payment_count;
  }
  public void set_payment_count(Long payment_count) {
    this.payment_count = payment_count;
  }
  public ads_user_action with_payment_count(Long payment_count) {
    this.payment_count = payment_count;
    return this;
  }
  private Long recent_days;
  public Long get_recent_days() {
    return recent_days;
  }
  public void set_recent_days(Long recent_days) {
    this.recent_days = recent_days;
  }
  public ads_user_action with_recent_days(Long recent_days) {
    this.recent_days = recent_days;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ads_user_action)) {
      return false;
    }
    ads_user_action that = (ads_user_action) o;
    boolean equal = true;
    equal = equal && (this.cart_count == null ? that.cart_count == null : this.cart_count.equals(that.cart_count));
    equal = equal && (this.dt == null ? that.dt == null : this.dt.equals(that.dt));
    equal = equal && (this.good_detail_count == null ? that.good_detail_count == null : this.good_detail_count.equals(that.good_detail_count));
    equal = equal && (this.home_count == null ? that.home_count == null : this.home_count.equals(that.home_count));
    equal = equal && (this.order_count == null ? that.order_count == null : this.order_count.equals(that.order_count));
    equal = equal && (this.payment_count == null ? that.payment_count == null : this.payment_count.equals(that.payment_count));
    equal = equal && (this.recent_days == null ? that.recent_days == null : this.recent_days.equals(that.recent_days));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ads_user_action)) {
      return false;
    }
    ads_user_action that = (ads_user_action) o;
    boolean equal = true;
    equal = equal && (this.cart_count == null ? that.cart_count == null : this.cart_count.equals(that.cart_count));
    equal = equal && (this.dt == null ? that.dt == null : this.dt.equals(that.dt));
    equal = equal && (this.good_detail_count == null ? that.good_detail_count == null : this.good_detail_count.equals(that.good_detail_count));
    equal = equal && (this.home_count == null ? that.home_count == null : this.home_count.equals(that.home_count));
    equal = equal && (this.order_count == null ? that.order_count == null : this.order_count.equals(that.order_count));
    equal = equal && (this.payment_count == null ? that.payment_count == null : this.payment_count.equals(that.payment_count));
    equal = equal && (this.recent_days == null ? that.recent_days == null : this.recent_days.equals(that.recent_days));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cart_count = JdbcWritableBridge.readLong(1, __dbResults);
    this.dt = JdbcWritableBridge.readDate(2, __dbResults);
    this.good_detail_count = JdbcWritableBridge.readLong(3, __dbResults);
    this.home_count = JdbcWritableBridge.readLong(4, __dbResults);
    this.order_count = JdbcWritableBridge.readLong(5, __dbResults);
    this.payment_count = JdbcWritableBridge.readLong(6, __dbResults);
    this.recent_days = JdbcWritableBridge.readLong(7, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.cart_count = JdbcWritableBridge.readLong(1, __dbResults);
    this.dt = JdbcWritableBridge.readDate(2, __dbResults);
    this.good_detail_count = JdbcWritableBridge.readLong(3, __dbResults);
    this.home_count = JdbcWritableBridge.readLong(4, __dbResults);
    this.order_count = JdbcWritableBridge.readLong(5, __dbResults);
    this.payment_count = JdbcWritableBridge.readLong(6, __dbResults);
    this.recent_days = JdbcWritableBridge.readLong(7, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(cart_count, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeDate(dt, 2 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeLong(good_detail_count, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(home_count, 4 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(order_count, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(payment_count, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(recent_days, 7 + __off, -5, __dbStmt);
    return 7;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(cart_count, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeDate(dt, 2 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeLong(good_detail_count, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(home_count, 4 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(order_count, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(payment_count, 6 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(recent_days, 7 + __off, -5, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cart_count = null;
    } else {
    this.cart_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.dt = null;
    } else {
    this.dt = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.good_detail_count = null;
    } else {
    this.good_detail_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.home_count = null;
    } else {
    this.home_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.order_count = null;
    } else {
    this.order_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.payment_count = null;
    } else {
    this.payment_count = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.recent_days = null;
    } else {
    this.recent_days = Long.valueOf(__dataIn.readLong());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cart_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.cart_count);
    }
    if (null == this.dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.dt.getTime());
    }
    if (null == this.good_detail_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.good_detail_count);
    }
    if (null == this.home_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.home_count);
    }
    if (null == this.order_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.order_count);
    }
    if (null == this.payment_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.payment_count);
    }
    if (null == this.recent_days) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.recent_days);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.cart_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.cart_count);
    }
    if (null == this.dt) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.dt.getTime());
    }
    if (null == this.good_detail_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.good_detail_count);
    }
    if (null == this.home_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.home_count);
    }
    if (null == this.order_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.order_count);
    }
    if (null == this.payment_count) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.payment_count);
    }
    if (null == this.recent_days) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.recent_days);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(cart_count==null?"null":"" + cart_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dt==null?"null":"" + dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(good_detail_count==null?"null":"" + good_detail_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(home_count==null?"null":"" + home_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(order_count==null?"null":"" + order_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(payment_count==null?"null":"" + payment_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(recent_days==null?"null":"" + recent_days, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(cart_count==null?"null":"" + cart_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(dt==null?"null":"" + dt, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(good_detail_count==null?"null":"" + good_detail_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(home_count==null?"null":"" + home_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(order_count==null?"null":"" + order_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(payment_count==null?"null":"" + payment_count, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(recent_days==null?"null":"" + recent_days, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 9, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.cart_count = null; } else {
      this.cart_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.dt = null; } else {
      this.dt = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.good_detail_count = null; } else {
      this.good_detail_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.home_count = null; } else {
      this.home_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.order_count = null; } else {
      this.order_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.payment_count = null; } else {
      this.payment_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.recent_days = null; } else {
      this.recent_days = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.cart_count = null; } else {
      this.cart_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.dt = null; } else {
      this.dt = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.good_detail_count = null; } else {
      this.good_detail_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.home_count = null; } else {
      this.home_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.order_count = null; } else {
      this.order_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.payment_count = null; } else {
      this.payment_count = Long.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "\\N";
    }
    if (__cur_str.equals("\\N") || __cur_str.length() == 0) { this.recent_days = null; } else {
      this.recent_days = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    ads_user_action o = (ads_user_action) super.clone();
    o.dt = (o.dt != null) ? (java.sql.Date) o.dt.clone() : null;
    return o;
  }

  public void clone0(ads_user_action o) throws CloneNotSupportedException {
    o.dt = (o.dt != null) ? (java.sql.Date) o.dt.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("cart_count", this.cart_count);
    __sqoop$field_map.put("dt", this.dt);
    __sqoop$field_map.put("good_detail_count", this.good_detail_count);
    __sqoop$field_map.put("home_count", this.home_count);
    __sqoop$field_map.put("order_count", this.order_count);
    __sqoop$field_map.put("payment_count", this.payment_count);
    __sqoop$field_map.put("recent_days", this.recent_days);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("cart_count", this.cart_count);
    __sqoop$field_map.put("dt", this.dt);
    __sqoop$field_map.put("good_detail_count", this.good_detail_count);
    __sqoop$field_map.put("home_count", this.home_count);
    __sqoop$field_map.put("order_count", this.order_count);
    __sqoop$field_map.put("payment_count", this.payment_count);
    __sqoop$field_map.put("recent_days", this.recent_days);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
