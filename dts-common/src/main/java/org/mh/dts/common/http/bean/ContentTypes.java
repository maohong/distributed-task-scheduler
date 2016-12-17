package org.mh.dts.common.http.bean;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

import java.nio.charset.Charset;

/**
 * @Author maohong@sogou-inc.com
 * 2016/12/5 17:12
 */
public final class ContentTypes {
    public static final ContentType UTF8_TEXT_PLAIN;
    public static final ContentType GBK_TEXT_PLAIN;
    public static final ContentType UTF8_CSV_TEXT;
    public static final ContentType GBK_CSV_TEXT;
    public static final ContentType APPLICATION_PDF;
    public static final ContentType APPLICATION_DOC;
    public static final ContentType APPLICATION_MS_EXCEL_XLS;
    public static final ContentType APPLICATION_MS_EXCEL_XLSX;
    /** @deprecated */
    @Deprecated
    public static final ContentType APPLICATION_MS_EXCEL;
    public static final ContentType JPEG;
    public static final ContentType GIF;
    public static final ContentType PNG;

    public ContentTypes() {
    }

    static {
        GBK_TEXT_PLAIN = ContentType.create("text/plain", Charset.forName("GBK"));
        UTF8_TEXT_PLAIN = ContentType.create("text/plain", Consts.UTF_8);
        GBK_CSV_TEXT = ContentType.create("text/comma-separated-values", Charset.forName("GBK"));
        UTF8_CSV_TEXT = ContentType.create("text/comma-separated-values", Consts.UTF_8);
        APPLICATION_PDF = ContentType.create("application/pdf");
        APPLICATION_DOC = ContentType.create("application/msword", (Charset)null);
        APPLICATION_MS_EXCEL_XLS = ContentType.create("application/vnd.ms-excel", (Charset)null);
        APPLICATION_MS_EXCEL_XLSX = ContentType.create("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", (Charset)null);
        APPLICATION_MS_EXCEL = APPLICATION_MS_EXCEL_XLS;
        JPEG = ContentType.create("image/jpeg", (Charset)null);
        GIF = ContentType.create("image/gif", (Charset)null);
        PNG = ContentType.create("image/png", (Charset)null);
    }
}