package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnvConfig {
    private static Map<String, String> envVars = new HashMap<>();

    static {
        try {
            loadEnv();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadEnv() throws IOException {
        String envFilePath = ".env";
        try (BufferedReader reader = new BufferedReader(new FileReader(envFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts.length > 1 ? parts[1].trim() : "";
                    envVars.put(key, value);
                }
            }
        }
    }

    public static String get(String key) {
        return envVars.getOrDefault(key, "");
    }

    public static String get(String key, String defaultValue) {
        return envVars.getOrDefault(key, defaultValue);
    }
}

