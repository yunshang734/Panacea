package com.panacea.common.wrapper;

import com.panacea.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

@Slf4j
public class CommonRequestWrapper extends HttpServletRequestWrapper {

    private String requestContent;

    public CommonRequestWrapper(HttpServletRequest httpServletRequest) throws IOException {
        super(httpServletRequest);
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = httpServletRequest.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(CommonConstant.DEFAULT_CHARSET)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.requestContent = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestContent.getBytes());
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    public String getRequestContent() {
        String method = getMethod();
        if (CommonConstant.REQUEST_METHOD_GET.equals(method)) {
            return getQueryString();
        } else if (CommonConstant.REQUEST_METHOD_POST.equals(method)) {
            return requestContent;
        } else {
            return "";
        }
    }
}