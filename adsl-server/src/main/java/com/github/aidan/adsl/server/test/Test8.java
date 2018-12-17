package com.github.aidan.adsl.server.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Test8 {

    /**
     * 连接linux系统
     * @param args
     */
    public static void main(String[] args) {
        try {
            Connection conn = new Connection("157.52.202.19",20302);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword("root",
                    "q123456");
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed");
            }
            Session sess = conn.openSession();
            sess.requestPTY("bash");
            sess.startShell();
            InputStream stdout = new StreamGobbler(sess.getStdout());
            InputStream stderr = new StreamGobbler(sess.getStderr());
            BufferedReader stdoutReader = new BufferedReader(
                    new InputStreamReader(stdout));
            BufferedReader stderrReader = new BufferedReader(
                    new InputStreamReader(stderr));
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(sess.getStdin());
            String temp = "";
            while (!temp.equals("exit")) {
                System.out.print("[root@vmone ~]#");
                temp = inputReader.readLine();
                out.println(temp);
                out.flush();
                String line = null;
                while ((line = stdoutReader.readLine()) != null) {
                    if (line.length() == 0) {// line等于null从来不会发生，导致程序卡在这里
                        continue;
                    } else{
                        System.out.println(line);
                    }
                }
                System.out.println("Here is the output from stderr:");
                while (true) {
                    line = stderrReader.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                }
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
            System.out.println("close connection");
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }


}