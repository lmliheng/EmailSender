package org.asset.Config;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class EmailConfig {
    private String to;
    private String from;
    private String host;
    private String username;
    private String password;
    private String alias;
    private Properties properties;


    public EmailConfig() {

        loadConfig();

    }

    private void loadConfig() {

        Yaml yaml = new Yaml();
        InputStream inputStream = EmailConfig.class.getResourceAsStream("/Info/SenderInfo.yml");
        Map<String, Object> config = yaml.load(inputStream);

        to = (String) config.get("to");
        from = (String) config.get("from");
        host = (String) config.get("host");
        username = (String) config.get("username");
        password = (String) config.get("password");
        alias = (String) config.get("alias");

        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    // Getters for the configuration properties
    public String getTo() { return to; }
    public String getFrom() { return from; }
    public String getHost() { return host; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getAlias() { return alias; }

    public Properties getProperties() { return properties; }


}