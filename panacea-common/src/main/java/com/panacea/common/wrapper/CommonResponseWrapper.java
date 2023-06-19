package com.panacea.common.wrapper;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

@Slf4j
public class CommonResponseWrapper extends HttpServletResponseWrapper {

    private ServletOutputStream servletOutputStream;
    private PrintWriter printWriter;
    private ServletOutputStreamCopier servletOutputStreamCopier;

    public CommonResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException("getWriter() has already been called on this response.");
        }
        if (servletOutputStream == null) {
            servletOutputStream = getResponse().getOutputStream();
            servletOutputStreamCopier = new ServletOutputStreamCopier(servletOutputStream);
        }
        return servletOutputStreamCopier;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (servletOutputStream != null) {
            throw new IllegalStateException("getOutputStream() has already been called on this response.");
        }
        if (printWriter == null) {
            servletOutputStreamCopier = new ServletOutputStreamCopier(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(servletOutputStreamCopier, getResponse().getCharacterEncoding()), true);
        }
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (printWriter != null) {
            printWriter.flush();
        } else if (servletOutputStream != null) {
            servletOutputStreamCopier.flush();
        }
    }

    public String getResponseContent() {
        if (null == servletOutputStreamCopier || null == servletOutputStreamCopier.getCopy()) {
            return "";
        } else {
            return new String(servletOutputStreamCopier.getCopy());
        }
    }
}