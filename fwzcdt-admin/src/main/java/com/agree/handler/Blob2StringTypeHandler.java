package com.agree.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

/**
 * Blob转String类型转换器
 */
public class Blob2StringTypeHandler extends BaseTypeHandler<String> {

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    String parameter, JdbcType jdbcType) throws SQLException {
        if(jdbcType == JdbcType.BLOB){
            ByteArrayInputStream bis;
            try {
                byte[] b = parameter.getBytes(DEFAULT_CHARSET);
                bis = new ByteArrayInputStream(b);
                ps.setBinaryStream(i, bis, b.length);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Blob Encoding Error!", e);
            }
        }else{
            ps.setString(i, parameter);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        try {
            Blob blob = rs.getBlob(columnName);
            byte[] returnValue = new byte[0];
            if (null != blob) {
                returnValue = blob.getBytes(1, (int) blob.length());
            }
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (Exception e) {
            return rs.getString(columnName);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        try {
            Blob blob = rs.getBlob(columnIndex);
            byte[] returnValue = new byte[0];
            if (null != blob) {
                returnValue = blob.getBytes(1, (int) blob.length());
            }
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (Exception e) {
            return rs.getString(columnIndex);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        byte[] returnValue = new byte[0];
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!", e);
        }
    }

}