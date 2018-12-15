package com.github.aidan.adsl.server.service;

import java.io.IOException;

public interface IpService {

    String getIp() throws IOException;

    String refreshIp() throws IOException;
}
