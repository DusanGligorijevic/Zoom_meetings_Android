package rs.raf.projekatjul.dusan_gligorijevic_rn9319.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EasternStandardTimeModel {

    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("client_ip")
    @Expose
    private String clientIp;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("day_of_week")
    @Expose
    private Integer dayOfWeek;
    @SerializedName("day_of_year")
    @Expose
    private Integer dayOfYear;
    @SerializedName("dst")
    @Expose
    private Boolean dst;
    @SerializedName("dst_from")
    @Expose
    private Object dstFrom;
    @SerializedName("dst_offset")
    @Expose
    private Integer dstOffset;
    @SerializedName("dst_until")
    @Expose
    private Object dstUntil;
    @SerializedName("raw_offset")
    @Expose
    private Integer rawOffset;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("unixtime")
    @Expose
    private Integer unixtime;
    @SerializedName("utc_datetime")
    @Expose
    private String utcDatetime;
    @SerializedName("utc_offset")
    @Expose
    private String utcOffset;
    @SerializedName("week_number")
    @Expose
    private Integer weekNumber;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public Boolean getDst() {
        return dst;
    }

    public void setDst(Boolean dst) {
        this.dst = dst;
    }

    public Object getDstFrom() {
        return dstFrom;
    }

    public void setDstFrom(Object dstFrom) {
        this.dstFrom = dstFrom;
    }

    public Integer getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(Integer dstOffset) {
        this.dstOffset = dstOffset;
    }

    public Object getDstUntil() {
        return dstUntil;
    }

    public void setDstUntil(Object dstUntil) {
        this.dstUntil = dstUntil;
    }

    public Integer getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(Integer rawOffset) {
        this.rawOffset = rawOffset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(Integer unixtime) {
        this.unixtime = unixtime;
    }

    public String getUtcDatetime() {
        return utcDatetime;
    }

    public void setUtcDatetime(String utcDatetime) {
        this.utcDatetime = utcDatetime;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

}